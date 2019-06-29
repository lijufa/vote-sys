package com.xinnet.smart.api;

import java.io.File;
import java.lang.reflect.Method;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接口处理器
 * @author meitao
 * @date 2014-6-20 下午2:17:16
 */
public interface ApiHandler {
	File getFile(Class<?> c);

	void before();

	void after();

	/**
	 * 处理类
	 * @author meitao
	 * @date 2014-6-23 上午10:16:52
	 * @param file
	 * @param c
	 */
	void handle(Class<?> c);

	/**
	 * 处理方法
	 * @author meitao
	 * @date 2014-6-23 上午10:16:48
	 * @param file
	 * @param c
	 * @param method
	 * @param requestMapping
	 */
	void handle(Class<?> c, Method method, RequestMapping requestMapping);
}
