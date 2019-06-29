package com.xinnet.smart.api.generic;

import java.lang.reflect.Method;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xinnet.smart.api.ApiHandler;
import com.xinnet.smart.base.util.UString;

/**
 * 接口列表
 * @author meitao
 * @date 2014-6-23 下午1:26:52
 */
public class ApiHandlerForList extends ApiHandlerAbstract implements ApiHandler {
	@Override
	public void handle(Class<?> c, Method method, RequestMapping requestMapping) {
		//System.out.println(c);
		RequestMapping classRequestMapping = c.getAnnotation(RequestMapping.class);
		if (classRequestMapping != null) {
			String[] classValues = classRequestMapping.value();
			for (String classValue : classValues) {
				String[] methodValues = requestMapping.value();
				for (String methodValue : methodValues) {
					if (!methodValue.startsWith("/")) {
						methodValue = "/" + methodValue;
					}
					System.out.println(classValue + methodValue);
				}
			}
		} else {
			String classValue = UString.toLowerCaseFirst(c.getSimpleName());
			String[] methodValues = requestMapping.value();
			for (String methodValue : methodValues) {
				System.out.println(classValue + methodValue);
			}
		}
	}

	@Override
	public void before() {
	}

	@Override
	public void after() {
	}
}
