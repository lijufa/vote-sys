package com.xinnet.smart.test.data.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.sql.DataSource;
import org.apache.commons.codec.Charsets;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;
import com.xinnet.smart.base.util.UFile;
import com.xinnet.smart.base.util.UString;
import com.xinnet.smart.test.data.IDatabaseAdmin;
import com.xinnet.smart.test.model.ForeignKey;

public class UDatabases {
	static final Logger logger = LoggerFactory.getLogger(UDatabases.class);
	static final String SEPARATOR_SQL = ";\n";

	public static IDatabaseAdmin newInstance() {
		return new DatabaseMySQLAdmin();
	}

	public static IDatabaseAdmin newInstance(DataSource dataSource) {
		IDatabaseAdmin ret = newInstance();
		ret.setDataSource(dataSource);
		return ret;
	}

	public static void diff(String fileNamePrefix, DbConfig sourceConfig, DbConfig targetConfig, DiffType diffType) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		File file = new File(fileNamePrefix + sdf.format(System.currentTimeMillis()) + ".sql");
		IDatabaseAdmin source = newInstance(getDataSource(sourceConfig));
		IDatabaseAdmin target = newInstance(getDataSource(targetConfig));
		{
			List<String> sqls = UDatabases.compareStructure(source, target);
			execute(file, target, targetConfig, sqls, diffType);
		}
		{
			List<String> sqls = UDatabases.compareReferences(source, target);
			execute(file, target, targetConfig, sqls, diffType);
		}
	}

	/**
	 * 结构
	 * @author meitao
	 * @date Aug 19, 2015 9:55:39 AM
	 * @param source
	 * @param target
	 * @return List<String>
	 */
	public static List<String> compareStructure(IDatabaseAdmin source, IDatabaseAdmin target) {
		Map<String, Map<String, List<ForeignKey>>> targetReferences = target.references();
		List<String> ret = new ArrayList<String>();
		List<String> sourceTables = source.showTables();
		//logger.debug("sourceTables:{}", sourceTables);
		if (sourceTables != null) {
			List<String> targetTables = target.showTables();
			if (targetTables != null) {
				//logger.debug("targetTables:{}", targetTables);
				for (String sourceTable : sourceTables) {
					if (targetTables.remove(sourceTable)) {
						//移除成功表示存在
						//两个库都存在的表仍需比较表结构
						ret.addAll(compareTable(sourceTable, source, target, targetReferences.get(sourceTable)));
					} else {
						//创建新增的表
						ret.add(source.showCreateTable(sourceTable));
					}
				}
				//需要删除的表
				for (String targetTable : targetTables) {
					StringBuilder sb = new StringBuilder();
					sb.append("DROP TABLE `");
					sb.append(targetTable);
					sb.append("`");
					ret.add(sb.toString());
				}
			}
		}
		return ret;
	}

	/**
	 * 比较表结构
	 * @author meitao
	 * @date Aug 19, 2015 9:55:24 AM
	 * @param tableName
	 * @param source
	 * @param sourceForeignKeys
	 * @param target
	 * @param targetForeignKeys
	 * @return List<String>
	 */
	public static List<String> compareTable(String tableName, IDatabaseAdmin source, IDatabaseAdmin target, Map<String, List<ForeignKey>> targetForeignKeys) {
		List<String> ret = new ArrayList<String>();
		if (UString.notEmpty(tableName)) {
			String sourceSql = source.showCreateTable(tableName);
			//logger.debug(sourceSql);
			String targetSql = target.showCreateTable(tableName);
			//logger.debug(targetSql);
			if (sourceSql != null && !sourceSql.equals(targetSql)) {
				//先只比较字段
				String s1 = sourceSql.substring(sourceSql.indexOf('('), sourceSql.lastIndexOf(')')).trim();
				String s2 = targetSql.substring(targetSql.indexOf('('), targetSql.lastIndexOf(')')).trim();
				if (!s1.equals(s2)) {
					Map<String, String> sourceFields = source.showColumnsRaw(tableName);
					Map<String, String> targetFields = target.showColumnsRaw(tableName);
					{
						Iterator<Entry<String, String>> iterator = sourceFields.entrySet().iterator();
						while (iterator.hasNext()) {
							Entry<String, String> entry = iterator.next();
							String fieldName = entry.getKey();
							String targetField = targetFields.remove(fieldName);
							if (targetField == null) {
								//目标库中无此字段，需要添加
								StringBuilder sb = new StringBuilder();
								sb.append("ALTER TABLE `");
								sb.append(tableName);
								sb.append("` ADD ");
								sb.append(entry.getValue().replace(" AUTO_INCREMENT", ""));
								ret.add(sb.toString());
							} else {
								//目标库中有此字段，需要进一步比较
								if (targetField.equals(entry.getValue())) {
									//字段一样，无须改动
									continue;
								}
								if (targetForeignKeys != null) {
									ret.addAll(removeReferences(targetForeignKeys.get(fieldName)));
								}
								StringBuilder sb = new StringBuilder();
								sb.append("ALTER TABLE `");
								sb.append(tableName);
								sb.append("` CHANGE `");
								sb.append(fieldName);
								sb.append("` ");
								sb.append(entry.getValue());
								ret.add(sb.toString());
							}
						}
					}
					{
						Iterator<Entry<String, String>> iterator = targetFields.entrySet().iterator();
						while (iterator.hasNext()) {
							Entry<String, String> entry = iterator.next();
							String fieldName = entry.getKey();
							if (targetForeignKeys != null) {
								ret.addAll(removeReferences(targetForeignKeys.get(fieldName)));
							}
							StringBuilder sb = new StringBuilder();
							sb.append("ALTER TABLE `");
							sb.append(tableName);
							sb.append("` DROP COLUMN `");
							sb.append(fieldName);
							sb.append("`");
							ret.add(sb.toString());
						}
					}
				}
				ret.addAll(comparePrimaryKey(tableName, source, target, targetForeignKeys));
				String comment = compareComment(tableName, source, target);
				if (comment != null)
					ret.add(comment);
			}
		}
		return ret;
	}

	private static String compareComment(String tableName, IDatabaseAdmin source, IDatabaseAdmin target) {
		String sourceComment = source.getComment(tableName);
		String targetComment = target.getComment(tableName);
		if (!sourceComment.equals(targetComment)) {
			//target.setComment(tableName, sourceComment);
			StringBuilder sb = new StringBuilder();
			sb.append("ALTER TABLE `");
			sb.append(tableName);
			sb.append("` COMMENT '");
			sb.append(sourceComment);
			sb.append("'");
			return sb.toString();
		}
		return null;
	}

	/**
	 * 比较外键
	 * @author meitao
	 * @date Aug 19, 2015 9:55:10 AM
	 * @param source
	 * @param target
	 * @return List<String>
	 */
	public static List<String> compareReferences(IDatabaseAdmin source, IDatabaseAdmin target) {
		List<String> ret = new ArrayList<String>();
		List<String> tables = target.showTables();
		for (String table : tables) {
			List<ForeignKey> targetForeignKeys = target.foreignKeys(table);
			List<ForeignKey> sourceForeignKeys = source.foreignKeys(table);
			List<ForeignKey> oldForeignKeys = new ArrayList<ForeignKey>();
			oldForeignKeys.addAll(targetForeignKeys);
			List<ForeignKey> newForeignKeys = new ArrayList<ForeignKey>();
			for (ForeignKey sourceForeignKey : sourceForeignKeys) {
				if (oldForeignKeys.remove(sourceForeignKey)) {
					//排除掉相同的部分
				} else {
					newForeignKeys.add(sourceForeignKey);
				}
			}
			if (oldForeignKeys.size() > 0) {
				//删掉旧的外键
				ret.addAll(removeReferences(oldForeignKeys));
				//logger.debug(UJson.toString(targetForeignKeys));
			}
			if (newForeignKeys.size() > 0) {
				//添加新的外键
				ret.addAll(addReferences(newForeignKeys));
				//logger.debug(UJson.toString(sourceForeignKeys));
			}
		}
		return ret;
	}

	/**
	 * 比较主键
	 * @author meitao
	 * @date Aug 19, 2015 9:55:02 AM
	 * @param source
	 * @param target
	 * @return List<String>
	 */
	public static List<String> comparePrimaryKey(String tableName, IDatabaseAdmin source, IDatabaseAdmin target, Map<String, List<ForeignKey>> targetForeignKeys) {
		List<String> ret = new ArrayList<String>();
		String sourcePrimaryKey = source.primaryKey(tableName);
		String targetPrimaryKey = target.primaryKey(tableName);
		if (!sourcePrimaryKey.equals(targetPrimaryKey)) {
			if (UString.notEmpty(targetPrimaryKey)) {
				if (targetForeignKeys != null) {
					ret.addAll(removeReferences(targetForeignKeys.get(targetPrimaryKey)));
				}
				Map<String, String> targetFields = target.showColumnsRaw(tableName);
				String sTargetPrimaryKey = targetFields.get(targetPrimaryKey);
				if (sTargetPrimaryKey == null) {
					logger.debug("{},{}", tableName, targetPrimaryKey);
				} else if (sTargetPrimaryKey.contains(" AUTO_INCREMENT")) {
					StringBuilder sb = new StringBuilder();
					sb.append("ALTER TABLE `");
					sb.append(tableName);
					sb.append("` CHANGE `");
					sb.append(targetPrimaryKey);
					sb.append("` ");
					sb.append(sTargetPrimaryKey.replace(" AUTO_INCREMENT", ""));
					ret.add(sb.toString());
				}
				StringBuilder sb = new StringBuilder();
				sb.append("ALTER TABLE `");
				sb.append(tableName);
				sb.append("` DROP PRIMARY KEY");
				ret.add(sb.toString());
			}
			{
				//关联删除问题
				StringBuilder sb = new StringBuilder();
				sb.append("DELETE FROM `");
				sb.append(tableName);
				sb.append("`");
				ret.add(sb.toString());
			}
			{
				StringBuilder sb = new StringBuilder();
				sb.append("ALTER TABLE `");
				sb.append(tableName);
				sb.append("` ADD PRIMARY KEY(`");
				sb.append(sourcePrimaryKey);
				sb.append("`)");
				ret.add(sb.toString());
			}
		}
		return ret;
	}

	public static List<String> removeReferences(Iterable<ForeignKey> foreignKeys) {
		List<String> ret = new ArrayList<String>();
		if (foreignKeys != null) {
			for (ForeignKey foreignKey : foreignKeys) {
				StringBuilder sb = new StringBuilder();
				sb.append("ALTER TABLE `");
				sb.append(foreignKey.getTableName());
				sb.append("` DROP FOREIGN KEY `");
				sb.append(foreignKey.getConstraint());
				sb.append("`");
				ret.add(sb.toString());
			}
		}
		return ret;
	}

	public static List<String> addReferences(Iterable<ForeignKey> foreignKeys) {
		List<String> ret = new ArrayList<String>();
		if (foreignKeys != null) {
			for (ForeignKey foreignKey : foreignKeys) {
				StringBuilder sb = new StringBuilder();
				sb.append("ALTER TABLE `");
				sb.append(foreignKey.getTableName());
				sb.append("` ADD CONSTRAINT `");
				sb.append(foreignKey.getConstraint());
				sb.append("` FOREIGN KEY (`");
				sb.append(foreignKey.getFieldName());
				sb.append("`) REFERENCES `");
				sb.append(foreignKey.getRefTableName());
				sb.append("`(`");
				sb.append(foreignKey.getRefFieldName());
				sb.append("`)");
				ret.add(sb.toString());
			}
		}
		return ret;
	}

	public static List<String> compare(IDatabaseAdmin source, IDatabaseAdmin target) {
		List<String> ret = compareStructure(source, target);
		//比较外键
		ret.addAll(compareReferences(source, target));
		//TODO 比较数据
		return ret;
	}

	public static void execute(File file, IDatabaseAdmin targetDb, DbConfig targetConfig, List<String> sqls, DiffType diffType) {
		StringBuilder sb = new StringBuilder();
		for (String sql : sqls) {
			sb.append(sql);
			sb.append(SEPARATOR_SQL);
		}
		if (sb.length() > 0) {
			String sql = sb.toString();
			if (diffType.isDebug()) {
				logger.info(targetConfig.url);
				logger.info(sql);
			}
			if (diffType.isFile()) {
				//UFile.write(file, before(parseDatabase(sourceConfig.getUrl())), true);
				UFile.write(file, sql.getBytes(Charsets.UTF_8), true);
			}
			if (diffType.isUpdate()) {
				targetDb.exec(sqls.toArray(IEmpty.STRINGS));
			}
		}
	}

	private static String parseDatabase(String url) {
		int index = url.lastIndexOf('?');
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url.substring(url.lastIndexOf('/') + 1);
	}

	public static DataSource getDataSource(DbConfig config) {
		BasicDataSource ret = new BasicDataSource();
		ret.setDriverClassName("com.mysql.jdbc.Driver");
		ret.setUrl(config.getUrl() + "?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&failOverReadOnly=false");
		ret.setUsername(config.getUser());
		ret.setPassword(config.getPass());
		return ret;
	}
}
