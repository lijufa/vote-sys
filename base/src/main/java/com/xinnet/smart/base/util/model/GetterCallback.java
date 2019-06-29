package com.xinnet.smart.base.util.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.ReflectionUtils;
import com.xinnet.smart.base.util.UMethod;

/**
 * 取get方法
 * @author meitao
 * @date 2014-8-5 上午9:52:22
 */
public class GetterCallback implements ReflectionUtils.MethodCallback {
	final Map<String, Method> map = new HashMap<String, Method>();

	@Override
	public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
		if (UMethod.isGetter(method)) {
			//TODO generic type
			String name = UMethod.getFieldName(method);
			//确保字段不重复
			if (!map.containsKey(name)) {
				map.put(name, method);
			}
		}
	}

	public Map<String, Method> getMap() {
		return map;
	}
}
