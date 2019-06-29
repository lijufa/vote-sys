package com.xinnet.smart.vo;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.util.UJson;
import com.xinnet.smart.base.util.UStream;
import com.xinnet.smart.base.util.UTrace;

/**
 * 返回处理
 * 
 * @author meitao
 * @date 2014年7月15日 下午6:25:17
 */
public class UResponse {
	static final Logger logger = LoggerFactory.getLogger(UResponse.class);
	static final String JSON = "application/json;charset=utf-8";
	static final String TEXT = "text/plain;charset=utf-8";
	static final String XML = "application/json;charset=utf-8";
	static final String HTML = "text/html;charset=UTF-8";

	/**
	 * 直接输出html
	 * 
	 * @author HongjianZ
	 * @date
	 * @param code
	 * @param message
	 * @param response
	 */
	public static void html(String message, HttpServletResponse response) {
		write(HTML, 0, message, response);
	}

	/**
	 * 输出XML返回结果并输出日志
	 * 
	 * @author meitao
	 * @date May 4, 2015 2:20:41 PM
	 * @param message
	 * @param response
	 *            void
	 */
	public static void xml(String message, HttpServletResponse response) {
		// 输出请求返回结果到调试日志，为了确保和请求对应上，必须打印请求地址、参数
		write(XML, 0, message, response);
	}

	/**
	 * json返回
	 * 
	 * @author meitao
	 * @date Nov 12, 2014 11:27:52 AM
	 * @param message
	 * @param response
	 */
	public static void text(String message, HttpServletResponse response) {
		write(TEXT, 0, message, response);
	}

	public static void jsonList(List<?> data, HttpServletResponse response) {
		ListResponse<Object> ret = new ListResponse<Object>();
		if (data != null) {
			ret.getData().addAll(data);
		}
		json(ret, response);
	}

	/**
	 * json返回
	 * 
	 * @author meitao
	 * @date Nov 12, 2014 11:41:56 AM
	 * @param ret
	 * @param response
	 */
	public static void json(GenericResponse ret, HttpServletResponse response) {
		if (ret.getCache() == null) {
			write(JSON, 0, UJson.toString(ret), response);
		} else {
			write(JSON, 0, ret.getCache(), response);
		}
	}

	@Deprecated
	public static void jsonNoCode(GenericResponse ret, HttpServletResponse response) {
		json(ret, response);
	}

	/**
	 * 输出JSON
	 * @author meitao
	 * @date Sep 24, 2015 11:22:58 AM
	 * @param code
	 * @param message
	 * @param response void
	 */
	public static void json(String message, HttpServletResponse response) {
		write(JSON, 0, message, response);
	}

	/**
	 * 输出XML返回结果并输出日志
	 * 
	 * @author meitao
	 * @date Nov 12, 2014 11:24:21 AM
	 * @param code
	 * @param message
	 * @param request
	 * @param response
	 */
	public static void xml(int code, String message, HttpServletResponse response) {
		// 输出请求返回结果到调试日志，为了确保和请求对应上，必须打印请求地址、参数
		write(XML, code, message, response);
	}

	/**
	 * 输出请求返回结果
	 * 
	 * @author meitao
	 * @date Nov 12, 2014 11:24:34 AM
	 * @param contentType
	 * @param code
	 * @param message
	 * @param response
	 */
	private static void write(String contentType, int code, String message, HttpServletResponse response) {
		try {
			response.setContentType(contentType);
			response.setStatus(200 + code);// 与Ajax配套，0为success
			response.setHeader("Access-Control-Allow-Origin", "*");// 支持跨域防问
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
		write(message, response);
	}

	/**
	 * 输出
	 * @author meitao
	 * @date Sep 24, 2015 11:14:09 AM
	 * @param message
	 * @param response void
	 */
	private static void write(String message, HttpServletResponse response) {
		try {
			if (message != null) {
				UStream.write(response.getOutputStream(), message.trim(), Charsets.UTF_8);
			}
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		} finally {
		}
	}
}
