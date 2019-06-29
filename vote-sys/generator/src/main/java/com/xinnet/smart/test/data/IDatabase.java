package com.xinnet.smart.test.data;

import javax.sql.DataSource;

/**
 * 数据库基础接口
 * @author meitao
 * @date 2012-12-24
 */
public interface IDatabase {
	/**
	 * 设置数据源
	 * @author meitao
	 * @date May 18, 2015 9:57:18 AM
	 * @param dataSource void
	 */
	void setDataSource(DataSource dataSource);

	/**
	 * 更新
	 * @author meitao
	 * @date May 18, 2015 9:40:30 AM
	 * @param sql
	 * @return int
	 */
	int update(String sql);

	/**
	 * 查询
	 * @author meitao
	 * @date May 18, 2015 9:40:35 AM
	 * @param sql
	 * @param getter
	 * @return T
	 */
	<T> T query(String sql, IResultSetGetter<T> getter);
}
