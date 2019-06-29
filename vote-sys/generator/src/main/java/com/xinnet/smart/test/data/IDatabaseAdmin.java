package com.xinnet.smart.test.data;

import java.io.File;
import java.util.List;
import java.util.Map;
import com.xinnet.smart.test.model.Composite;
import com.xinnet.smart.test.model.FieldDesc;
import com.xinnet.smart.test.model.ForeignKey;

/**
 * 数据库高级接口
 * @author meitao
 * @date 2012-12-24
 */
public interface IDatabaseAdmin extends IDatabase {
	/**
	 * 执行脚本文件
	 * @author meitao
	 * @date May 24, 2015 2:27:17 PM
	 * @param sqls
	 * @return int[]
	 */
	void exec(File sqlFile);

	/**
	 * 批量执行sql
	 * @author meitao
	 * @date May 24, 2015 2:26:08 PM
	 * @param sqls
	 * @return int[]
	 */
	void exec(String... sqls);

	/**
	 * 查全部表
	 * @author meitao
	 * @date 2014-8-14 下午4:39:25
	 * @return
	 */
	List<String> showTables();

	/**
	 * 字段简表
	 * @author meitao
	 * @date 2014-8-14 下午4:39:15
	 * @param tableName
	 * @return
	 */
	Map<String, FieldDesc> desc(String tableName);

	/**
	 * 全字段
	 * @author meitao
	 * @date 2014-8-14 下午4:39:06
	 * @param tableName
	 * @return
	 */
	Map<String, FieldDesc> showFullColumns(String tableName);

	//Map<String, String> showColumns(String tableName);
	/**
	 * 查询创建语句
	 * @author meitao
	 * @date 2014-8-14 下午4:38:58
	 * @param tableName
	 * @return
	 */
	String showCreateTable(String tableName);

	/**
	 * 获取表注释
	 * @author meitao
	 * @date Sep 28, 2015 3:54:14 PM
	 * @param tableName
	 * @return String
	 */
	String getComment(String tableName);

	/**
	 * 设置表注释
	 * @author meitao
	 * @date Sep 28, 2015 3:54:00 PM
	 * @param tableName
	 * @param comment
	 */
	void setComment(String tableName, String comment);

	/**
	 * 获取主键
	 * @author meitao
	 * @date Aug 14, 2015 8:36:35 AM
	 * @param tableName
	 * @return String
	 */
	String primaryKey(String tableName);

	/**
	 * 所有表、字段相关外键（不支持嵌套外键，即一个外键依赖另一个外键）
	 * @author meitao
	 * @date 2014-8-14 下午4:38:09
	 * @return
	 */
	Map<String, Map<String, List<ForeignKey>>> references();

	/**
	 * 外键
	 * @author meitao
	 * @date 2014-8-14 下午4:38:09
	 * @param tableName
	 * @return
	 */
	List<ForeignKey> foreignKeys(String tableName);

	/**
	 * 给字段加上外键信息
	 * @author meitao
	 * @date 2014-8-14 下午10:24:35
	 * @param tableName
	 * @param fieldMap
	 */
	void foreignKeys(String tableName, Map<String, FieldDesc> fieldMap);

	/**
	 * 依赖表
	 * @author meitao
	 * @date 2014-8-14 下午4:38:04
	 * @param tableName
	 * @return
	 */
	List<String> references(String tableName);

	/**
	 * 依赖树
	 * @author meitao
	 * @date 2014-8-14 下午7:48:15
	 * @param tableName
	 * @return Composite
	 */
	Composite dependencies(String tableName, boolean isNull);

	Map<String, String> showColumnsRaw(String tableName);
}
