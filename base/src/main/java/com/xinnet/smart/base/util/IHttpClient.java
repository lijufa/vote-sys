package com.xinnet.smart.base.util;

import java.io.Serializable;
import java.util.Map;
import com.xinnet.smart.base.enums.ContentTypeEnum;

/**
 * http client
 * @author meitao
 * @date Sep 23, 2015 1:26:54 AM
 */
public interface IHttpClient {
	String HTTP = "http://";
	char COLON = ':';
	char SLASH = '/';
	String UTF8 = "UTF-8";

	/**
	 * GET：获取数据
	 * @author meitao
	 * @date Sep 23, 2015 1:41:56 AM
	 * @param url
	 * @return String
	 */
	String get(String url);

	/**
	 * POST：提交数据
	 * application/x-www-form-urlencoded
	 * @author meitao
	 * @date Sep 26, 2015 9:40:51 PM
	 * @param url
	 * @param data
	 * @return String
	 */
	//String post(String url, String data);
	/**
	 * POST：提交数据
	 * @author meitao
	 * @date Sep 26, 2015 9:38:18 PM
	 * @param contentType
	 * @param url
	 * @param param
	 * @return String
	 */
	String post(ContentTypeEnum contentType, String url, String param);

	/**
	 * 提交json数据
	 * @author meitao
	 * @date Nov 3, 2015 12:41:32 PM
	 * @param data
	 * @param urls
	 * @return String
	 */
	String json(String data, String uri, String... urls);

	/**
	 * POST：访问指定IP、端口提交数据
	 * application/x-www-form-urlencoded
	 * @author meitao
	 * @date Sep 26, 2015 9:38:06 PM
	 * @param ip
	 * @param port
	 * @param action
	 * @param param
	 * @return String
	 */
	String post(String ip, Serializable port, String action, String param);

	/**
	 * POST：访问指定IP、端口提交数据
	 * @author meitao
	 * @date Sep 26, 2015 9:38:06 PM
	 * @param contentType
	 * @param ip
	 * @param port
	 * @param action
	 * @param param
	 * @return String
	 */
	String post(ContentTypeEnum contentType, String ip, Serializable port, String action, String param);

	/**
	 * 生成参数
	 * @author meitao
	 * @date Sep 25, 2015 10:50:05 AM
	 * @param obj
	 * @return String
	 */
	String param(Object param);

	/**
	 * 生成参数
	 * @author meitao
	 * @date Sep 25, 2015 10:48:51 AM
	 * @param map
	 * @return String
	 */
	String param(Map<String, Object> param);
}
