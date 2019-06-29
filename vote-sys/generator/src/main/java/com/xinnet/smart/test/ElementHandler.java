package com.xinnet.smart.test;

/**
 * 元素处理器
 * @author meitao
 * @date 2014-6-20 下午2:17:16
 */
public interface ElementHandler<T> {
	/**
	 * 元素处理方法
	 * @author meitao
	 * @date 2014-8-12 下午7:10:57
	 * @param element 元素
	 */
	void handle(T element);

	void befores();

	void afters();
}
