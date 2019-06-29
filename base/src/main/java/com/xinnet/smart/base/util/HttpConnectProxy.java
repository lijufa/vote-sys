package com.xinnet.smart.base.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xinnet.smart.base.PropertiesBase;
import com.xinnet.smart.base.enums.ContentTypeEnum;
import com.xinnet.smart.vo.GenericResponse;
import com.xinnet.smart.vo.HttpProxyResponse;

public class HttpConnectProxy extends PropertiesBase {
	static final Logger logger = LoggerFactory.getLogger(HttpConnectProxy.class);
	static int timeout = 60000;
	static String GET = "GET";
	static String POST = "POST";
	static String DELETE = "DELETE";
	static String UTF8 = "UTF-8";
	static int times = 3;//重试次数

	/**
	 * POST 请求指定的地址, 默认请求类型 (application/x-www-form-urlencoded)
	 * @param sb 主机地址(不包含 "/" )
	 * @param action 除主机地址后的请求地址
	 * @param map 参数
	 * @return GenericResponse
	 */
	//	@Deprecated
	//	public static HttpProxyResponse connectThird(StringBuilder sb, String action, Map<String, Object> map) {
	//		sb.append('/');
	//		sb.append(action);
	//		return post(sb.toString(), map);
	//	}
	/**
	 * 通用请求
	 * @author xiaojuan
	 * @date 2015年5月11日 下午5:26:37
	 * @param hostIp
	 * @param action
	 * @param map
	 * @return GenericResponse
	 */
	//	@Deprecated
	//	public static HttpProxyResponse connectThird(String hostIp, Serializable port, String action, Map<String, Object> map) {
	//		StringBuilder sb = new StringBuilder();
	//		sb.append(HTTP);
	//		sb.append(hostIp);
	//		sb.append(COLON);
	//		sb.append(port);
	//		sb.append(SLASH);
	//		sb.append(action);
	//		return post(sb.toString(), map);
	//	}
	/**
	 * 重复循环 urls, 但只要有一个请求成功则退出循环, 这里主要是业务上应用的问题, 请求类型是 Application/JSON
	 * @param data 请求的时发送的数据
	 * @param uri 请求的路径, 不包括发送的主机地址
	 * @param urls 请求发送的主机列表
	 * @return String 返回的内容
	 */
	public static String json(String data, String uri, String... urls) {
		String ret = null;
		for (String url : urls) {
			try {
				ret = post(url + uri, data, ContentTypeEnum.JSON, null);
				if (ret != null) {
					break;
				}
			} catch (Exception e) {
				logger.error(UTrace.trace(e));
			}
		}
		return ret;
	}

	public static String jsonWithHeader(String data, String uri, Map<String, Object> headParamter, String... urls) {
		String ret = null;
		for (String url : urls) {
			try {
				ret = post(url + uri, data, ContentTypeEnum.JSON, headParamter);
				if (ret != null) {
					break;
				}
			} catch (Exception e) {
				logger.error(UTrace.trace(e));
			}
		}
		return ret;
	}

	public static String jsonUrlWithHeader(String data, String url, Map<String, Object> headParamter) {
		String ret = null;
		try {
			ret = post(url, data, ContentTypeEnum.JSON, headParamter);
		} catch (Exception e) {
			logger.error(UTrace.trace(e));
		}
		return ret;
	}

	/**
	 * GET 请求最终执行的方法, 默认请求类型为 application/x-www-form-urlencoded
	 * @param url
	 * @return url 返回的内容(String)
	 */
	//	@Deprecated
	//	private final static String getContent(URL url) {
	//		HttpURLConnection connection = connects(url, RequestMethod.GET, true, false, ContentTypeEnum.URLENCODED);
	//		if (connection != null) {
	//			try {
	//				if (connection.getResponseCode() == 200) {
	//					return UStream.read(connection.getInputStream(), Charsets.UTF_8);
	//				}
	//			} catch (Throwable e) {
	//				logger.error(UTrace.trace(e));
	//			} finally {
	//				connection.disconnect();
	//			}
	//		}
	//		return null;
	//	}
	/**
	 * GET 请求指定的地址
	 * @param urlStr
	 * @return url 返回的内容(String)
	 */
	public final static String getContent(String url) {
		//		URL url = new URL(urlStr);
		HttpURLConnection connection = connects(url, RequestMethod.GET, true, false, ContentTypeEnum.URLENCODED, null);
		if (connection != null) {
			try {
				if (connection.getResponseCode() == 200) {
					return UStream.read(connection.getInputStream(), Charsets.UTF_8);
				}
			} catch (Throwable e) {
				logger.error(UTrace.trace(e));
			} finally {
				connection.disconnect();
			}
		}
		return null;
	}

	public static HttpProxyResponse getHttpProxyResponse(String url, Map<String, Object> map) {
		logger.debug("url:{}\nmap:{}", url, map);
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		if (map != null) {
			sb.append('?');
			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				Object values = entry.getValue();
				if (values instanceof String[]) {
					for (String value : (String[]) values) {
						sb.append(entry.getKey());
						sb.append('=');
						try {
							sb.append(URLEncoder.encode(value, UTF8));
						} catch (UnsupportedEncodingException e) {
							logger.error(e.getMessage());
						}
						sb.append('&');
					}
				} else if (values instanceof String) {
					sb.append(entry.getKey());
					sb.append('=');
					try {
						sb.append(URLEncoder.encode((String) values, UTF8));
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage());
					}
					sb.append('&');
				} else {
					sb.append(entry.getKey());
					sb.append('=');
					try {
						sb.append(URLEncoder.encode(String.valueOf(values), UTF8));
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage());
					}
					sb.append('&');
				}
			}
		}
		return getHttpProxyResponse(sb.toString());
	}

	public static HttpProxyResponse getHttpProxyResponse(String url) {
		HttpProxyResponse ret = new HttpProxyResponse();
		ret.setUrl(url);
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod(GET);
			connection.setReadTimeout(timeout);
			connection.setUseCaches(false);
			try {
				connection.connect();
			} catch (java.net.ConnectException e) {
				ret.error(e);
				return ret;
			}
			if (connection.getResponseCode() == 200) {
				ret.success();
				ret.setResult(UStream.read(connection.getInputStream(), Charsets.UTF_8));
			}
		} catch (Throwable e) {
			ret.error(e);
			logger.error(ret.getErrorMessage());
			StringBuilder sb = new StringBuilder();
			sb.append("[GET]:");
			sb.append(url);
			logger.error(sb.toString());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return ret;
	}

	public static HttpProxyResponse getHttpProxyResponseWithHeader(String url, Map<String, Object> map, Map<String, Object> headParams) {
		logger.debug("url:{}\nmap:{}", url, map);
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		if (map != null) {
			sb.append('?');
			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				Object values = entry.getValue();
				if (values instanceof String[]) {
					for (String value : (String[]) values) {
						sb.append(entry.getKey());
						sb.append('=');
						try {
							sb.append(URLEncoder.encode(value, UTF8));
						} catch (UnsupportedEncodingException e) {
							logger.error(e.getMessage());
						}
						sb.append('&');
					}
				} else if (values instanceof String) {
					sb.append(entry.getKey());
					sb.append('=');
					try {
						sb.append(URLEncoder.encode((String) values, UTF8));
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage());
					}
					sb.append('&');
				} else {
					sb.append(entry.getKey());
					sb.append('=');
					try {
						sb.append(URLEncoder.encode(String.valueOf(values), UTF8));
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage());
					}
					sb.append('&');
				}
			}
		}
		return getHttpProxyResponseWithHeader(sb.toString(), headParams);
	}

	public static HttpProxyResponse getHttpProxyResponseWithHeader(String url, Map<String, Object> headParams) {
		HttpProxyResponse ret = new HttpProxyResponse();
		ret.setUrl(url);
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod(GET);
			connection.setReadTimeout(timeout);
			connection.setUseCaches(false);
			if (headParams != null) {
				for (String key : headParams.keySet()) {
					connection.setRequestProperty(key, headParams.get(key).toString());
				}
			}
			try {
				connection.connect();
			} catch (java.net.ConnectException e) {
				ret.error(e);
				return ret;
			}
			if (connection.getResponseCode() == 200) {
				ret.success();
				ret.setResult(UStream.read(connection.getInputStream(), Charsets.UTF_8));
			}
		} catch (Throwable e) {
			ret.error(e);
			logger.error(ret.getErrorMessage());
			StringBuilder sb = new StringBuilder();
			sb.append("[GET]:");
			sb.append(url);
			logger.error(sb.toString());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return ret;
	}

	//	public static HttpProxyResponse postObject(String url, Object obj) {
	//		return post(url, UMethod.getMap(obj));
	//	}
	/**
	 * POST 请求指定的地址
	 * @param contentType 请求的文件类型
	 * @param url	地址包括主机及请求的地址
	 * @param data
	 * @return String 返回的内容
	 */
	public static String post(ContentTypeEnum contentType, String url, String data) {
		try {
			return post(url, data, contentType, null);
		} catch (Exception e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	public static HttpProxyResponse postObject(String url, Object obj) {
		return post(url, UMethod.getMap(obj));
	}

	public static <T> HttpProxyResponse post(String url, Map<String, T> map) {
		logger.debug("url:{}\nmap:{}", url, map);
		HttpProxyResponse ret = new HttpProxyResponse();
		try {
			ret.setUrl(url);
			if (map != null) {
				StringBuilder sb = new StringBuilder();
				Iterator<Entry<String, T>> iterator = map.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, T> entry = iterator.next();
					String key = entry.getKey();
					T value = entry.getValue();
					if (value != null) {
						if (value.getClass().isArray()) {
							parseArray(sb, key, value);
						} else if (value instanceof List) {
							parseList(sb, key, value);
						} else {
							parseObject(sb, key, value);
						}
					}
				}
				sb.append("t=");
				sb.append(System.nanoTime());
				logger.debug("visit url {}, param {} --------------------->", url, sb.toString());
				ret.setData(sb.toString());
			}
			//			post(ret);
			HttpURLConnection connection = null;
			DataOutputStream out = null;
			try {
				connection = (HttpURLConnection) new URL(ret.getUrl()).openConnection();
				connection.setRequestMethod(POST);
				connection.setReadTimeout(timeout);
				connection.setUseCaches(false);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				connection.connect();
				if (ret.getData() != null) {
					out = new DataOutputStream(connection.getOutputStream());
					out.writeBytes(ret.getData());
					out.flush();
				}
				// 与UResponse配套，0为success
				if (connection.getResponseCode() == 200) {
					ret.success();
					ret.setResult(UStream.read(connection.getInputStream(), Charsets.UTF_8));
				}
			} catch (IOException e) {
				ret.error(e);
				logger.error("post url=" + ret.getUrl() + "error:" + UTrace.trace(e));
				logger.error(ret.getErrorMessage());
			} catch (Throwable e) {
				ret.error(e);
				logger.error("post url=" + ret.getUrl() + "error:" + UTrace.trace(e));
				logger.error(ret.getErrorMessage());
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException ignore) {
					}
				}
				if (connection != null) {
					connection.disconnect();
				}
				outLogs(POST, ret);
			}
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
		return ret;
	}

	public static <T> HttpProxyResponse postWithHeader(String url, Map<String, T> map, Map<String, Object> headParams) {
		HttpProxyResponse ret = new HttpProxyResponse();
		try {
			ret.setUrl(url);
			if (map != null) {
				StringBuilder sb = new StringBuilder();
				Iterator<Entry<String, T>> iterator = map.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, T> entry = iterator.next();
					String key = entry.getKey();
					T value = entry.getValue();
					if (value != null) {
						if (value.getClass().isArray()) {
							parseArray(sb, key, value);
						} else if (value instanceof List) {
							parseList(sb, key, value);
						} else {
							parseObject(sb, key, value);
						}
					}
				}
				logger.debug("visit param--------------------->" + sb.toString());
				ret.setData(sb.toString());
			}
			// post(ret);
			HttpURLConnection connection = null;
			DataOutputStream out = null;
			try {
				URL url2 = new URL(url);
				if ("https".equalsIgnoreCase(url2.getProtocol())) {
					SslUtils.ignoreSsl();
				}
				connection = (HttpURLConnection) url2.openConnection();
				connection.setRequestMethod(POST);
				connection.setReadTimeout(timeout);
				connection.setUseCaches(false);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				if (headParams != null) {
					for (String key : headParams.keySet()) {
						connection.setRequestProperty(key, headParams.get(key).toString());
					}
				}
				connection.connect();
				if (ret.getData() != null) {
					out = new DataOutputStream(connection.getOutputStream());
					out.writeBytes(ret.getData());
					out.flush();
				}
				// 与UResponse配套，0为success
				if (connection.getResponseCode() == 200) {
					ret.success();
					ret.setResult(UStream.read(connection.getInputStream(), Charsets.UTF_8));
				}
			} catch (IOException e) {
				ret.error(e);
				logger.error(ret.getErrorMessage());
			} catch (Throwable e) {
				ret.error(e);
				logger.error(ret.getErrorMessage());
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException ignore) {
					}
				}
				if (connection != null) {
					connection.disconnect();
				}
				outLogs(POST, ret);
			}
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
		return ret;
	}

	/**
	 * POST 请求指定的地址
	 * @param contentType 请求的文件类型
	 */
	//	@Deprecated
	//	public static String post(ContentTypeEnum contentType, StringBuilder serverpath, String action, String data) {
	//		serverpath.append(SLASH);
	//		serverpath.append(action);
	//		return post(contentType, serverpath.toString(), data);
	//	}
	/**
	 * 两种 POST 最终请求的方法, 这里是第一种, 请求返回的内容将会作为 String 返回.
	 * @param url
	 * @param data
	 * @param contentType
	 * @return String
	 */
	private static final String post(String url, String data, ContentTypeEnum contentType, Map<String, Object> headParamter) {
		HttpURLConnection connection = connects(url, RequestMethod.POST, true, true, contentType, headParamter);
		logger.debug("{}?{}", url, data);
		if (connection != null) {
			OutputStream out = null;
			try {
				out = connection.getOutputStream();
				out.write(data.getBytes(Charsets.UTF_8));
				out.flush();
				if (connection.getResponseCode() == 200) {
					return UStream.read(connection.getInputStream(), Charsets.UTF_8);
				}
			} catch (Throwable e) {
				logger.error(UTrace.trace(e));
				logger.error("url:{},data:{},", url, data);
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException ignore) {
					}
				}
				connection.disconnect();
			}
		}
		return null;
	}

	/**
	 * POST, GET 获取 HttpURLConnection 最终的调用.
	 */
	//	@Deprecated
	//	private static final HttpURLConnection connect(URL url, RequestMethod method, boolean doInput, boolean doOutput, ContentTypeEnum contentType, int timeout) {
	//		HttpURLConnection ret = null;
	//		try {
	//			ret = (HttpURLConnection) url.openConnection();
	//			ret.setDoInput(doInput);
	//			ret.setDoOutput(doOutput);
	//			ret.setConnectTimeout(timeout * 5);
	//			ret.setReadTimeout(timeout);
	//			ret.setRequestMethod(method.name());
	//			ret.setUseCaches(false);
	//			ret.setRequestProperty("Content-Type", contentType.getValue());
	//			ret.connect();
	//		} catch (java.net.ConnectException e) {
	//			logger.error("{}:{}", e, url);
	//			ret = null;
	//		} catch (Throwable e) {
	//			logger.error(UTrace.trace(e));
	//			ret = null;
	//		}
	//		return ret;
	//	}
	/**
	 * 获取 HttpUrlConnection , timeout 是设置请求失败后可以重复几次调用.
	 */
	private final static HttpURLConnection connects(String urlstr, RequestMethod method, boolean doInput, boolean doOutput, ContentTypeEnum contentType, Map<String, Object> headParams) {
		int i = 0;
		HttpURLConnection ret = null;
		//int t = timeout;
		do {
			//			ret = connect(url, method, doInput, doOutput, contentType, t);
			//			HttpURLConnection ret = null;
			try {
				URL url = new URL(urlstr);
				if ("https".equalsIgnoreCase(url.getProtocol())) {
					SslUtils.ignoreSsl();
				}
				ret = (HttpURLConnection) url.openConnection();
				ret.setDoInput(doInput);
				ret.setDoOutput(doOutput);
				ret.setConnectTimeout(timeout * 5);
				ret.setReadTimeout(timeout);
				ret.setRequestMethod(method.name());
				ret.setUseCaches(false);
				ret.setRequestProperty("Content-Type", contentType.getValue());
				if (headParams != null && headParams.size() > 0) {
					for (String key : headParams.keySet()) {
						ret.setRequestProperty(key, headParams.get(key).toString());
					}
				}
				ret.connect();
			} catch (java.net.ConnectException e) {
				logger.error("{}:{}", e, urlstr);
				ret = null;
			} catch (Throwable e) {
				logger.error(UTrace.trace(e));
				ret = null;
			}
			if (ret != null) {
				break;
			}
			//			t = t * 2;
		} while (++i < times);
		return ret;
	}

	private static void parseList(StringBuilder sb, String key, Object values) {
		List objs = ((List) values);
		int size = objs.size();
		if (size <= 0)
			return;
		Map<String, Method> getters = UMethod.getGetterMap(objs.get(0).getClass());
		for (int i = 0; i < objs.size(); i++) {
			Object obj = objs.get(i);
			if (obj == null)
				continue;
			Iterator<Entry<String, Method>> iteratorGetter = getters.entrySet().iterator();
			while (iteratorGetter.hasNext()) {
				Entry<String, Method> entryGetter = iteratorGetter.next();
				try {
					parseObject(sb, key + "[" + i + "]." + entryGetter.getKey(), entryGetter.getValue().invoke(obj));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.error(UTrace.trace(e));
				}
			}
		}
	}

	private static void parseArray(StringBuilder sb, String key, Object values) {
		if (values instanceof Serializable[]) {
			for (Serializable value : (Serializable[]) values) {
				parseObject(sb, key, value);
			}
		} else {
			Map<String, Method> getters = UMethod.getGetterMap(values.getClass().getComponentType());
			Object[] objs = ((Object[]) values);
			for (int i = 0; i < objs.length; i++) {
				Object obj = objs[i];
				if (obj == null)
					continue;
				Iterator<Entry<String, Method>> iteratorGetter = getters.entrySet().iterator();
				while (iteratorGetter.hasNext()) {
					Entry<String, Method> entryGetter = iteratorGetter.next();
					try {
						parseObject(sb, key + "[" + i + "]." + entryGetter.getKey(), entryGetter.getValue().invoke(obj));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						logger.error(UTrace.trace(e));
					}
				}
			}
		}
	}

	private static void parseObject(StringBuilder sb, String key, Object value) {
		if (value == null)
			return;
		if (value instanceof Serializable) {
			sb.append(parseString(key, String.valueOf(value)));
		} else {
			sb.append(parseString(key, UJson.toString(value)));
		}
	}

	private static String parseString(String key, String value) {
		StringBuilder sb = new StringBuilder();
		if (value != null) {
			try {
				sb.append(key);
				sb.append('=');
				sb.append(URLEncoder.encode(String.valueOf(value), UTF8));
				sb.append('&');
			} catch (Throwable ignore) {
				//logger.error(UTrace.trace(e));
			}
		}
		return sb.toString();
	}

	private static void outLogs(String method, HttpProxyResponse ret) {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(method).append("]:");
		if (ret != null) {
			sb.append(ret.getUrl());
			if (ret.getData() != null) {
				sb.append("?");
				sb.append(ret.getData());
			}
			sb.append("\n");
			sb.append(ret.getResult());
		}
		if (GenericResponse.isSuccess(ret)) {
			//监控日志不再输出、内容太多
			if (sb.toString().indexOf("monitor") < 0) {
				logger.info(sb.toString());
			}
		} else {
			logger.error(sb.toString());
		}
	}

	//	public static int getTimeout() {
	//		return timeout;
	//	}
	//
	//	public static void setTimeout(int timeout) {
	//		HttpConnectProxy.timeout = timeout;
	//	}
	//	public static Logger getLogger() {
	//		return logger;
	//	}
	/**
	 * 把对象中不为空的属性放入map中
	 * 
	 * @param t
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	//	@Deprecated
	//	public static <T> Map<String, Object> getObjectToMap(T t) {
	//		Map<String, Object> map = new HashMap<String, Object>();
	//		Map<String, Method> getters = UMethod.getGetterMap(t.getClass());
	//		for (Entry<String, Method> entry : getters.entrySet()) {
	//			try {
	//				Object obj = entry.getValue().invoke(t);
	//				if (obj != null) {
	//					map.put(entry.getKey(), obj);
	//				}
	//			} catch (Throwable e) {
	//				if (logger.isDebugEnabled()) {
	//					logger.debug(e.getMessage());
	//				}
	//			}
	//		}
	//		return map;
	//	}
	@Deprecated
	public static HttpProxyResponse getSslRequest(String url) {
		HttpProxyResponse ret = new HttpProxyResponse();
		ret.setUrl(url);
		HttpURLConnection connection = null;
		try {
			if ("https".equalsIgnoreCase(new URL(url).getProtocol())) {
				SslUtils.ignoreSsl();
			}
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod(GET);
			connection.setReadTimeout(timeout);
			connection.setUseCaches(false);
			try {
				connection.connect();
			} catch (java.net.ConnectException e) {
				ret.error(e);
				return ret;
			}
			if (connection.getResponseCode() == 200) {
				ret.success();
				ret.setResult(UStream.read(connection.getInputStream(), Charsets.UTF_8));
			}
		} catch (Throwable e) {
			ret.error(e);
			logger.error(ret.getErrorMessage());
			StringBuilder sb = new StringBuilder();
			sb.append("[GET]:");
			sb.append(url);
			logger.error(sb.toString());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return ret;
	}

	public static HttpProxyResponse deleteHttpProxyResponseWithHeader(String url, Map<String, Object> map, Map<String, Object> headParams) {
		logger.debug("url:{}\nmap:{}", url, map);
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		if (map != null) {
			sb.append('?');
			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				Object values = entry.getValue();
				if (values instanceof String[]) {
					for (String value : (String[]) values) {
						sb.append(entry.getKey());
						sb.append('=');
						try {
							sb.append(URLEncoder.encode(value, UTF8));
						} catch (UnsupportedEncodingException e) {
							logger.error(e.getMessage());
						}
						sb.append('&');
					}
				} else if (values instanceof String) {
					sb.append(entry.getKey());
					sb.append('=');
					try {
						sb.append(URLEncoder.encode((String) values, UTF8));
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage());
					}
					sb.append('&');
				} else {
					sb.append(entry.getKey());
					sb.append('=');
					try {
						sb.append(URLEncoder.encode(String.valueOf(values), UTF8));
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage());
					}
					sb.append('&');
				}
			}
		}
		return deleteHttpProxyResponseWithHeader(sb.toString(), headParams);
	}

	public static HttpProxyResponse deleteHttpProxyResponseWithHeader(String url, Map<String, Object> headParams) {
		HttpProxyResponse ret = new HttpProxyResponse();
		ret.setUrl(url);
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod(DELETE);
			connection.setReadTimeout(timeout);
			connection.setUseCaches(false);
			if (headParams != null) {
				for (String key : headParams.keySet()) {
					connection.setRequestProperty(key, headParams.get(key).toString());
				}
			}
			try {
				connection.connect();
			} catch (java.net.ConnectException e) {
				ret.error(e);
				return ret;
			}
			if (connection.getResponseCode() == 200) {
				ret.success();
				ret.setResult(UStream.read(connection.getInputStream(), Charsets.UTF_8));
			}
		} catch (Throwable e) {
			ret.error(e);
			logger.error(ret.getErrorMessage());
			StringBuilder sb = new StringBuilder();
			sb.append("[DELETE]:");
			sb.append(url);
			logger.error(sb.toString());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return ret;
	}
}
