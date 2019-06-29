package com.xinnet.smart.test;

import java.util.Iterator;

/**
 * 通用遍历器
 * @author meitao
 * @date 2014-6-23 下午1:25:21
 */
public interface ElementIterator<T> {
	/**
	 * 遍历元素
	 * @author meitao
	 * @date 2014-8-12 下午7:09:00
	 */
	void each(Iterator<T> iterable);

	void each(T tableName);
}
