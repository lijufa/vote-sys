package com.xinnet.smart.api.impl;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.codec.Charsets;
// import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xinnet.smart.api.ApiHandler;
import com.xinnet.smart.api.generic.ApiHandlerAbstract;
import com.xinnet.smart.base.util.UFile;
import com.xinnet.smart.base.util.UMethod;
import com.xinnet.smart.base.util.UString;
import com.xinnet.smart.base.util.UTrace;
import com.xinnet.smart.test.model.HashHashLists;
import com.xinnet.smart.test.model.HashHashLists.Handler;

/**
 * web版接口单元测试用例
 * 
 * @author meitao
 * @date 2014-6-23 下午1:25:08
 */
public class ApiHandlerForHtmlController extends ApiHandlerAbstract implements ApiHandler {
	static final Logger logger = LoggerFactory.getLogger(ApiHandlerForHtmlController.class);
	final HashHashLists<String, String, Method> urlHash = new HashHashLists<String, String, Method>();
	final File file;
	final String host = "../";

	public ApiHandlerForHtmlController() {
		file = UFile.prepare("src/main/webapp/html/apis.htm");
	}

	@Override
	// @Test
	public void test() {
		super.test();
	}

	@Override
	public void handle(Class<?> c, Method method, RequestMapping requestMapping) {
		try {
			RequestMapping classRequestMapping = c.getAnnotation(RequestMapping.class);
			if (classRequestMapping != null) {
				String[] classValues = classRequestMapping.value();
				for (String classValue : classValues) {
					urlHash.putIfNotExist(classValue, c.getName(), method);
				}
			} else {
				String classValue = UString.toLowerCaseFirst(c.getSimpleName());
				urlHash.putIfNotExist(classValue, c.getName(), method);
			}
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
	}

	@Override
	public void before() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>apis</title>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append("</head>");
		sb.append("<body>");
		UFile.write(file, sb.toString(), Charsets.UTF_8, false);
	}

	@Override
	public void after() {
		urlHash.iterate(new Handler<String, String, Method>() {
			@Override
			public void handle(String classValue, String className, List<Method> methods) {
				//logger.debug(classValue);
				//logger.debug(className);
				//logger.debug(methods.toString());
				StringBuilder sb = new StringBuilder();
				sb.append(className);
				sb.append("<br />\n");
				for (Method method : methods) {
					RequestMapping methodRequestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
					//if (methodRequestMapping != null) 
					{
						RequestMethod[] requestMethods = methodRequestMapping.method();
						RequestMethod requestMethod = RequestMethod.GET;
						for (RequestMethod rm : requestMethods) {
							requestMethod = rm;
							if (RequestMethod.GET.equals(rm)) {
								break;
							}
						}
						String[] methodValues = methodRequestMapping.value();
						//logger.debug(UJson.toString(methodValues));
						if (methodValues != null) {
							for (String methodValue : methodValues) {
								if (!methodValue.startsWith("/")) {
									methodValue = "/" + methodValue;
								}
								String url = classValue + methodValue;
								switch (requestMethod) {
								case GET:
									get(sb, url, method, className);
									break;
								default:
									other(requestMethod.name(), url);
									break;
								}
							}
						}
					}
				}
				// sb.append("<br />\n");
				UFile.write(file, sb.toString(), Charsets.UTF_8, true);
			}
		});
		StringBuilder sb = new StringBuilder();
		sb.append("</body>");
		sb.append("</html>");
		UFile.write(file, sb.toString(), Charsets.UTF_8, true);
	}

	public void get(StringBuilder sb, String url, Method method, String className) {
		sb.append("　　");
		sb.append("<a target=\"_blank\" href=\"");
		sb.append(host);
		sb.append(url);
		sb.append('?');
		sb.append(getParams(className, method));
		sb.append("\" title=\"");
		sb.append(method.toString().replace("public ", "").replace(className + ".", ""));
		sb.append("\">");
		sb.append(url);
		sb.append("</a>");
		sb.append("<br />\n");
	}

	public String getParams(String className, Method method) {
		StringBuilder sb = new StringBuilder();
		String[] parameterNames = UMethod.getParameterNames(method);
		Class<?>[] parameterTypes = method.getParameterTypes();
		try {
			int i = -1, j = parameterNames.length;
			while (++i < j) {
				Class<?> parameterType = parameterTypes[i];
				//logger.debug(parameterType.getName());
				switch (parameterType.getName()) {
				case "javax.servlet.http.HttpServletRequest":
					break;
				case "javax.servlet.http.HttpServletResponse":
					break;
				default:
					if (Serializable.class.isAssignableFrom(parameterType)) {
						if (sb.length() > 0) {
							sb.append('&');
						}
						String parameterName = parameterNames[i];
						sb.append(parameterName);
						sb.append('=');
						switch (parameterName) {
						case "businessId":
							sb.append(System.currentTimeMillis());
							break;
						default:
							break;
						}
					} else {
						List<String> paramNames = new ArrayList<String>();
						Map<String, Method> getters = UMethod.getSetterMap(parameterType);
						//logger.debug(getters.toString());
						Set<String> keys = getters.keySet();
						for (String key : keys) {
							if (!paramNames.contains(key)) {
								paramNames.add(key);
							}
						}
						logger.debug(paramNames.toString());
						for (String paramName : paramNames) {
							if (sb.length() > 0) {
								sb.append('&');
							}
							switch (paramName) {
							case "ip":
								//sb.append("ip.ipadds=192.168.1.12&ip.subnet=255.255.0.0&ip.gateway1=192.169.1.0&ip.dns1=8.8.8.8");
								break;
							case "eip":
								//sb.append("eip[0].ipadds=10.12.31.11&eip[0].subnet=255.255.255.0&eip[0].gateway=10.12.1.1&eip[0].dns=10.12.12.12");
								break;
							default:
								sb.append(paramName);
								sb.append('=');
								switch (paramName) {
								case "businessId":
									sb.append(System.currentTimeMillis());
									break;
								case "hid":
								case "hostId":
									//sb.append("1");
									break;
								case "password":
									sb.append("123456");
									break;
								case "space":
									sb.append("11");
									break;
								case "imageId":
									sb.append("1");
									break;
								case "brandwidth":
									sb.append("10");
									break;
								case "cpu":
									sb.append("1");
									break;
								case "memory":
									sb.append("1");
									break;
								case "number":
									sb.append("2");
									break;
								default:
									break;
								}
								break;
							}
						}
						break;
					}
				}
			}
		} catch (Throwable e) {
		}
		return sb.toString();
	}

	public void other(String method, String url) {
		System.out.println(method + ':' + url);
	}
}
