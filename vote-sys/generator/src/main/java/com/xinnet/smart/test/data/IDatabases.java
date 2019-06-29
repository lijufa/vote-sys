package com.xinnet.smart.test.data;

import java.util.Set;

/**
 * 数据库高级接口
 * @author meitao
 * @date 2012-12-24
 */
public interface IDatabases {
	/**
	 * 根据url查数据源
	 * @author meitao
	 * @date May 18, 2015 10:13:29 AM
	 * @param url
	 * @return DatabaseAdmin
	 */
	IDatabaseAdmin getDatabase(String url);

	/**
	 * 获取所有数据源的url
	 * @author meitao
	 * @date May 18, 2015 10:14:08 AM
	 * @return Set<String>
	 */
	Set<String> urlSet();

	/**
	 * 获取第一个数据源
	 * @return
	 */
	IDatabaseAdmin getDatabase();
}
