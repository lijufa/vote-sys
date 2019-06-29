package com.xinnet.smart.vo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.xinnet.smart.base.util.UJson;
import com.xinnet.smart.base.util.UMethod;
import com.xinnet.smart.base.util.UString;
import com.xinnet.smart.base.util.UTrace;

public class URequest {
	static final Logger logger = LoggerFactory.getLogger(URequest.class);
	public static final String PAGE = "page";
	static final String LIMIT = "limit";
	public static final int PAGE_LIMIT_MAX = 100;
	public static final int PAGE_LIMIT_DEFAULT = 50;

	public static int getLimit(HttpServletRequest request, int maxLimit) {
		int ret = getLimit(UString.parseInt(request.getParameter(LIMIT), PAGE_LIMIT_DEFAULT));
		return Math.min(ret, maxLimit);
	}

	public static int getLimit(Integer limit) {
		if (limit != null) {
			return Math.max(limit.intValue(), 1);
		}
		return 1;
	}

	public static Integer getPage(HttpServletRequest request) {
		return getPage(request.getParameter(PAGE));
	}

	public static int getPage(String page) {
		return getPage(UString.parseInt(page, 1));
	}

	public static int getPage(Integer page) {
		if (page != null) {
			return Math.max(page.intValue(), 1);
		}
		return 1;
	}

	/**
	 * 分页
	 * @author meitao
	 * @date Jul 2, 2015 2:39:15 PM
	 * @param request
	 * @return RowBounds
	 */
	public static RowBounds getRowBounds(HttpServletRequest request) {
		int limit = getLimit(request, PAGE_LIMIT_MAX);
		int offset = limit * (getPage(request) - 1);
		return new RowBounds(offset, limit);
	}

	public static Paginator getPaginator(RowBounds rowBounds, Integer page, long total) {
		return new Paginator(URequest.getPage(page), Math.min(rowBounds.getLimit(), PAGE_LIMIT_MAX), total);
	}

	/**
	 * 打印返回日志，为了确保和请求对应上，同时打印请求地址、参数
	 * @author meitao
	 * @date Nov 12, 2014 11:40:26 AM
	 * @param ret
	 * @param request
	 */
	public static void log(GenericResponse ret, HttpServletRequest request) {
		if (ret != null && ret.notSuccess()) {
			StringBuilder sb = new StringBuilder();
			sb.append(URequest.getRequestInfo(request));
			sb.append('\n');
			sb.append(UJson.toString(ret));
			logger.info(sb.toString());
		}
	}

	public static String getRequestURI(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		return request.getRequestURI().substring(request.getContextPath().length());
	}

	/**
	 * 获取请求地址
	 * 支持直接部署到tomcat或者apache根路径到端口的重定向，其他情况暂不支持
	 * @param request
	 * @return
	 */
	public static String getRequestURL(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String host = request.getHeader("x-forwarded-server");
		if (host != null) {
			return "http://" + host + request.getRequestURI();
		} else {
			return request.getRequestURL().toString();
		}
	}

	public static String getQueryString(HttpServletRequest request) {
		return getQueryString(request, "?");
	}

	/**
	 * 根据需要获取请求参数，并平
	 * @author meitao
	 * @date 2013-6-6
	 * @param request
	 * @param appendStart
	 * @return String
	 */
	public static String getQueryString(HttpServletRequest request, String appendStart) {
		if (request == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String, String[]> map = request.getParameterMap();
		return getQueryString(map, appendStart);
	}

	public static String getQueryString(Map<String, String[]> map) {
		return getQueryString(map, "?");
	}

	/**
	 * 根据需要获取请求参数
	 * @author meitao
	 * @date 2013-6-6
	 * @param request
	 * @param appendStart
	 * @return String
	 */
	public static String getQueryString(Map<String, String[]> map, String appendStart) {
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, String[]>> it = map.entrySet().iterator();
		Map.Entry<String, String[]> entry = null;
		String paramName = null;
		String[] paramValues = null;
		if (it.hasNext()) {
			sb.append(appendStart);
		}
		while (it.hasNext()) {
			entry = it.next();
			paramName = entry.getKey();
			paramValues = entry.getValue();
			int i = 0;
			int len = paramValues.length;
			while (i < len) {
				sb.append(paramName);
				sb.append("=");
				try {
					sb.append(URLEncoder.encode(paramValues[i], "UTF-8"));
				} catch (UnsupportedEncodingException e) {
				}
				i++;
				if (i < len) {
					sb.append("&");
				}
			}
			if (it.hasNext()) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * 获取完整请求信息
	 * @param request
	 * @return
	 */
	public static String getRequestInfo(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(getRequestURL(request));
		sb.append(getQueryString(request));
		return sb.toString();
	}

	public static void addCookie(HttpServletResponse response, String name, String value) {
		response.addCookie(new Cookie(name, value));
	}

	public static <T> T getObject(HttpServletRequest request, Class<T> c) {
		T ret = UJson.tryObject(getBody(request), c);
		if (ret == null) {
			ret = getObject(request.getParameterMap(), c);
		}
		return ret;
	}

	public static <T> T getObject(@SuppressWarnings("rawtypes") Map requestMap, Class<T> c) {
		try {
			if (c == null) {
				return null;
			}
			@SuppressWarnings("unchecked")
			Map<String, String[]> map = requestMap;
			Map<String, Method> methods = UMethod.getSetterMap(c);
			Iterator<Entry<String, Method>> iterator = methods.entrySet().iterator();
			Constructor<?>[] constructors = c.getConstructors();
			if (constructors == null || constructors.length == 0) {
				return null;
			}
			T ret = c.newInstance();
			while (iterator.hasNext()) {
				Entry<String, Method> entry = iterator.next();
				String[] values = map.get(entry.getKey());
				if (values != null) {
					Method method = entry.getValue();
					Class<?> type = UMethod.getParameterType(c, method);
					if (type.isArray()) {
						Object obj = UJson.tryArray(values, type.getComponentType());
						if (obj != null) {
							try {
								method.invoke(ret, obj);
							} catch (Throwable e) {
								logger.error(UTrace.trace(e));
							}
						}
					} else if (String.class.equals(type)) {
						try {
							method.invoke(ret, values[0]);
						} catch (Throwable e) {
							logger.error(UTrace.trace(e));
						}
					} else {
						try {
							method.invoke(ret, UJson.tryObject(values[0], type));
						} catch (Throwable e) {
							logger.error(UTrace.trace(e));
						}
					}
				}
			}
			return ret;
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	public static String getBody(HttpServletRequest request) {
		try {
			//			int length = request.getContentLength();
			//			if (length > 0) {
			//				char[] chars = new char[length];
			//				request.getReader().read(chars);
			//				return new String(chars);
			//			}
			//		} catch (Throwable e) {
			//			logger.error(UTrace.trace(e));
			//		}
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			logger.info(request.getRequestURI() + "请求参数:" + sb.toString());
			return sb.toString();
			// 将资料解码
			//			return URLDecoder.decode(sb.toString(), "UTF-8");
		} catch (Throwable e) {
			logger.error("URequest解码异常{}", UTrace.trace(e));
		}
		return "";
	}

	/**
	 * 获取客户端请求IP
	 * @author wanghongyu
	 * @date 2015年9月19日 下午3:50:38
	 * @param request
	 * @return String
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (StringUtils.isBlank(ip) || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (StringUtils.isBlank(ip) || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (StringUtils.isBlank(ip) || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
