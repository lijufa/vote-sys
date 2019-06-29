package com.xinnet.smart.test.data.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;
import com.xinnet.smart.base.util.UFile;
import com.xinnet.smart.base.util.UString;
import com.xinnet.smart.base.util.UTrace;
import com.xinnet.smart.test.data.IDatabaseAdmin;
import com.xinnet.smart.test.data.IResultSetGetter;
import com.xinnet.smart.test.model.Composite;
import com.xinnet.smart.test.model.FieldDesc;
import com.xinnet.smart.test.model.ForeignKey;

public class DatabaseMySQLAdmin extends DatabaseImpl implements IDatabaseAdmin {
	static final Logger logger = LoggerFactory.getLogger(DatabaseMySQLAdmin.class);

	@Override
	public void exec(File sqlFile) {
		try {
			exec(new String(UFile.read(sqlFile), "UTF-8").split(";\n"));
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
	}

	@Override
	public void exec(String... sqls) {
		try {
			for (String sql : sqls) {
				update(sql);
			}
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
	}

	@Override
	public List<String> showTables() {
		return query("show tables", new IResultSetGetter<List<String>>() {
			@Override
			public List<String> get(ResultSet rs) throws SQLException {
				List<String> ret = new ArrayList<String>();
				while (rs.next()) {
					ret.add(rs.getString(1));
				}
				return ret;
			}
		});
	}

	@Override
	public Map<String, FieldDesc> desc(String tableName) {
		return query("DESC " + tableName, new IResultSetGetter<Map<String, FieldDesc>>() {
			@Override
			public Map<String, FieldDesc> get(ResultSet rs) throws SQLException {
				Map<String, FieldDesc> ret = new HashMap<String, FieldDesc>();
				while (rs.next()) {
					FieldDesc field = new FieldDesc();
					int i = 0;
					field.setName(rs.getString(++i));
					field.setType(rs.getString(++i));
					field.setNull("YES".equals(rs.getString(++i)));
					field.setKeyType(rs.getString(++i));
					field.setDefaultValue(rs.getString(++i));
					field.setExtra(rs.getString(++i));
					ret.put(field.getName(), field);
				}
				return ret;
			}
		});
	}

	@Override
	public void foreignKeys(String tableName, Map<String, FieldDesc> fieldMap) {
		List<ForeignKey> foreignKeys = foreignKeys(tableName);
		for (ForeignKey foreignKey : foreignKeys) {
			fieldMap.get(foreignKey.getFieldName()).setForeignKey(foreignKey);
		}
	}

	@Override
	public Map<String, FieldDesc> showFullColumns(String tableName) {
		return query("SHOW FULL COLUMNS FROM `" + tableName + "`", new IResultSetGetter<Map<String, FieldDesc>>() {
			@Override
			public Map<String, FieldDesc> get(ResultSet rs) throws SQLException {
				Map<String, FieldDesc> ret = new HashMap<String, FieldDesc>();
				while (rs.next()) {
					FieldDesc field = new FieldDesc();
					int i = 0;
					field.setName(rs.getString(++i));
					field.setType(rs.getString(++i));
					field.setCollation(rs.getString(++i));
					field.setNull("YES".equals(rs.getString(++i)));
					field.setKeyType(rs.getString(++i));
					field.setDefaultValue(rs.getString(++i));
					field.setExtra(rs.getString(++i));
					field.setPrivileges(rs.getString(++i));
					field.setComment(rs.getString(++i));
					field.setAutoIncrement(field.getExtra().contains("auto_increment"));
					ret.put(field.getName(), field);
				}
				return ret;
			}
		});
	}

	@Override
	public Map<String, String> showColumnsRaw(String tableName) {
		String sql = showCreateTable(tableName);
		if (sql == null) {
			return null;
		}
		Map<String, String> ret = new HashMap<String, String>();
		String[] ss = sql.split("\n");
		for (String s : ss) {
			s = s.trim();
			int nameBegin = s.indexOf('`');
			int nameEnd = s.lastIndexOf('`');
			if (nameBegin == 0) {
				int index = s.lastIndexOf(',');
				if (index > 0) {
					s = s.substring(0, index);
				}
				ret.put(s.substring(nameBegin + 1, nameEnd), s);
			}
		}
		return ret;
	}

	@Override
	public String showCreateTable(String tableName) {
		return query("SHOW CREATE TABLE `" + tableName + "`", new IResultSetGetter<String>() {
			@Override
			public String get(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getString(2);
				}
				return null;
			}
		});
	}

	@Override
	public Map<String, Map<String, List<ForeignKey>>> references() {
		Map<String, Map<String, List<ForeignKey>>> ret = new HashMap<String, Map<String, List<ForeignKey>>>();
		List<String> tables = showTables();
		for (String table : tables) {
			List<ForeignKey> foreignKeys = foreignKeys(table);
			for (ForeignKey foreignKey : foreignKeys) {
				put(ret, foreignKey, foreignKey.getRefTableName(), foreignKey.getRefFieldName());
				put(ret, foreignKey, foreignKey.getTableName(), foreignKey.getFieldName());
			}
		}
		return ret;
	}

	void put(Map<String, Map<String, List<ForeignKey>>> ret, ForeignKey foreignKey, String tableName, String fieldName) {
		Map<String, List<ForeignKey>> fields = ret.get(tableName);
		if (fields == null) {
			fields = new HashMap<String, List<ForeignKey>>();
			ret.put(tableName, fields);
		}
		List<ForeignKey> field = fields.get(fieldName);
		if (field == null) {
			field = new ArrayList<ForeignKey>();
			fields.put(fieldName, field);
		}
		field.add(foreignKey);
	}

	/**
	 * 依赖表
	 * @author meitao
	 * @date 2014-8-14 下午4:37:33
	 * @param tableName
	 * @return
	 * @see com.xinnet.smart.test.data.IDatabases#references(java.lang.String)
	 */
	@Override
	public List<ForeignKey> foreignKeys(String tableName) {
		List<ForeignKey> ret = new ArrayList<ForeignKey>();
		String sql = showCreateTable(tableName);
		if (sql != null) {
			String[] ss = sql.split("CONSTRAINT `");
			String[] names = new String[4];
			int i = 0, j = ss.length;
			while (++i < j) {
				String s = ss[i];
				ForeignKey foreignKey = new ForeignKey();
				int idx;
				//LOGGER.debug(s);
				for (int k = 0; k < 4; k++) {
					idx = s.indexOf('`');
					names[k] = s.substring(0, idx);
					s = s.substring(idx + 1);
					s = s.substring(s.indexOf('`') + 1);
				}
				//LOGGER.debug(UJson.toString(names));
				foreignKey.setTableName(tableName);
				foreignKey.setConstraint(names[0]);
				foreignKey.setFieldName(names[1]);
				foreignKey.setRefTableName(names[2]);
				foreignKey.setRefFieldName(names[3]);
				ret.add(foreignKey);
			}
		}
		return ret;
	}

	@Override
	public List<String> references(String tableName) {
		List<String> ret = new ArrayList<String>();
		ret.add(tableName);
		deepReferences(tableName, ret, 0);
		ret.remove(tableName);
		return ret;
	}

	/**
	 * 深度依赖
	 * @author meitao
	 * @date 2014-8-14 下午4:39:54
	 * @param tableName
	 * @param list
	 */
	public void deepReferences(String tableName, List<String> list, int depth) {
		depth++;
		List<ForeignKey> foreignKeys = foreignKeys(tableName);
		Map<String, FieldDesc> fieldS = desc(tableName);
		list.remove(tableName);
		for (ForeignKey foreignKey : foreignKeys) {
			FieldDesc field = fieldS.get(foreignKey.getFieldName());
			if (!field.isNull()) {
				String refTableName = foreignKey.getRefTableName();
				list.add(refTableName);
				deepReferences(refTableName, list, depth);
			}
		}
	}

	@Override
	public Composite dependencies(String tableName, boolean isNull) {
		return dependencies(tableName, null, isNull);
	}

	/**
	 * 依赖树
	 * @author meitao
	 * @date 2014-8-14 下午7:46:53
	 * @param tableName 当前表名
	 * @param parent 父级树
	 * @param isNull 是否统计可空字段
	 * @return Composite
	 */
	public Composite dependencies(String tableName, Composite parent, boolean isNull) {
		Composite ret = new Composite();
		ret.setParent(parent);
		List<ForeignKey> foreignKeys = foreignKeys(tableName);
		Map<String, FieldDesc> fieldS = desc(tableName);
		if (isNull) {
			//统计可空字段
			for (ForeignKey foreignKey : foreignKeys) {
				String refTableName = foreignKey.getRefTableName();
				//TODO 防止循环引用导致程序死循环，暂时只判断一级
				if (!tableName.equals(refTableName)) {
					ret.put(refTableName, dependencies(refTableName, ret, isNull));
				}
			}
		} else {
			//不统计可空字段
			for (ForeignKey foreignKey : foreignKeys) {
				FieldDesc field = fieldS.get(foreignKey.getFieldName());
				if (!field.isNull()) {
					String refTableName = foreignKey.getRefTableName();
					ret.put(refTableName, dependencies(refTableName, ret, isNull));
				}
			}
		}
		return ret;
	}

	/**
	 * 调试循环依赖
	 * @author meitao
	 * @date 2014-8-14 下午6:27:42
	 * @param composite
	 * @param references
	 */
	void debug(Composite composite, String tableName, List<String> references) {
		StringBuilder sbDbg = new StringBuilder();
		sbDbg.append(String.valueOf(composite.depth()));
		sbDbg.append(',');
		sbDbg.append(tableName);
		sbDbg.append(',');
		sbDbg.append(String.valueOf(references));
		LOGGER.debug(sbDbg.toString());
	}

	@Override
	public String getComment(String tableName) {
		String s = showCreateTable(tableName);
		if (s == null) {
			return IEmpty.STRING;
		}
		s = s.substring(s.lastIndexOf("CHARSET="));
		int idx = s.lastIndexOf("COMMENT=");
		return idx == -1 ? IEmpty.STRING : s.substring(idx + 9, s.lastIndexOf('\''));
	}

	@Override
	public void setComment(String tableName, String comment) {
		StringBuilder sb = new StringBuilder();
		sb.append("ALTER TABLE `");
		sb.append(tableName);
		sb.append("` COMMENT '");
		sb.append(comment);
		sb.append("';");
		update(sb.toString());
	}

	@Override
	public String primaryKey(String tableName) {
		return UString.substring(showCreateTable(tableName), "PRIMARY KEY (`", "`)");
	}
}
