package com.xinnet.smart.base.util.model;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
import com.xinnet.smart.base.enums.ContentTypeEnum;
import com.xinnet.smart.base.util.IHttpClient;
import com.xinnet.smart.base.util.UJson;
import com.xinnet.smart.base.util.UMethod;
import com.xinnet.smart.base.util.UStream;
import com.xinnet.smart.base.util.UTrace;

public class HttpClientImpl implements IHttpClient {
	static final Logger logger = LoggerFactory.getLogger(HttpClientImpl.class);
	int timeout = 60000;
	int times = 3;//重试次数

	@Override
	public final String get(String url) {
		try {
			return get(new URL(url));
		} catch (MalformedURLException e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	public String post(String url, String data) {
		return post(ContentTypeEnum.URLENCODED, url, data);
	}

	@Override
	public String post(ContentTypeEnum contentType, String url, String data) {
		try {
			return post(new URL(url), data, contentType);
		} catch (MalformedURLException e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	@Override
	public String json(String data, String uri, String... urls) {
		String ret = null;
		for (String url : urls) {
			try {
				ret = post(new URL(url + uri), data, ContentTypeEnum.JSON);
				if (ret != null) {
					break;
				}
			} catch (MalformedURLException e) {
				logger.error(UTrace.trace(e));
			}
		}
		return ret;
	}

	@Override
	public String post(String ip, Serializable port, String action, String data) {
		return post(ContentTypeEnum.URLENCODED, ip, port, action, data);
	}

	@Override
	public String post(ContentTypeEnum contentType, String ip, Serializable port, String action, String data) {
		StringBuilder sb = new StringBuilder();
		sb.append(HTTP);
		sb.append(ip);
		sb.append(COLON);
		sb.append(port);
		sb.append(SLASH);
		sb.append(action);
		return post(contentType, sb.toString(), data);
	}

	//---------------------------------------------------------------------------
	private final String get(URL url) {
		HttpURLConnection connection = connects(url, RequestMethod.GET, true, false, ContentTypeEnum.URLENCODED);
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

	private final String post(URL url, String data, ContentTypeEnum contentType) {
		HttpURLConnection connection = connects(url, RequestMethod.POST, true, true, contentType);
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

	//---------------------------------------------------------------------------
	@Override
	public final String param(Object obj) {
		return param(UMethod.getMap(obj));
	}

	@Override
	public final String param(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		if (map != null) {
			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				String key = entry.getKey();
				Object values = entry.getValue();
				if (values == null) {
				} else if (values.getClass().isArray()) {
					parseArray(sb, key, values);
				} else if (values instanceof List) {
					parseList(sb, key, values);
				} else {
					parseObject(sb, key, values);
				}
			}
			sb.append("t=");
			sb.append(System.nanoTime());
		}
		return sb.toString();
	}

	private static void parseList(StringBuilder sb, String key, Object values) {
		List objs = ((List) values);
		int size = objs.size();
		if (size > 0) {
			Map<String, Method> getters = UMethod.getGetterMap(objs.get(0).getClass());
			for (int i = 0; i < objs.size(); i++) {
				Object obj = objs.get(i);
				if (obj != null) {
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
				if (obj != null) {
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
	}

	private static void parseObject(StringBuilder sb, String key, Object value) {
		if (value != null) {
			if (value instanceof Serializable) {
				sb.append(parseString(key, String.valueOf(value)));
			} else {
				sb.append(parseString(key, UJson.toString(value)));
			}
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

	private final HttpURLConnection connects(URL url, RequestMethod method, boolean doInput, boolean doOutput, ContentTypeEnum contentType) {
		int i = 0;
		HttpURLConnection ret;
		int t = timeout;
		do {
			ret = connect(url, method, doInput, doOutput, contentType, t);
			if (ret != null) {
				break;
			}
			t = t * 2;
		} while (++i < times);
		return ret;
	}

	private final HttpURLConnection connect(URL url, RequestMethod method, boolean doInput, boolean doOutput, ContentTypeEnum contentType, int timeout) {
		HttpURLConnection ret = null;
		try {
			ret = (HttpURLConnection) url.openConnection();
			ret.setDoInput(doInput);
			ret.setDoOutput(doOutput);
			ret.setConnectTimeout(timeout * 5);
			ret.setReadTimeout(timeout);
			ret.setRequestMethod(method.name());
			ret.setUseCaches(false);
			ret.setRequestProperty("Content-Type", contentType.getValue());
			ret.connect();
		} catch (java.net.ConnectException e) {
			logger.error("{}:{}", e, url);
			ret = null;
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			ret = null;
		}
		return ret;
	}

	public final int getTimeout() {
		return timeout;
	}

	public final void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public static final Logger getLogger() {
		return logger;
	}
}
