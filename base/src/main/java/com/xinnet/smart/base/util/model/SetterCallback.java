package com.xinnet.smart.base.util.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.ReflectionUtils;
import com.xinnet.smart.base.util.UMethod;

/**
 * 取set方法
 * @author meitao
 * @date 2014-8-5 上午9:52:01
 */
public class SetterCallback implements ReflectionUtils.MethodCallback {
	final Map<String, Method> map = new HashMap<String, Method>();

	@Override
	public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
		if (UMethod.isSetter(method)) {
			Class<?>[] typeS = UMethod.getParameterTypes(method.getDeclaringClass(), method);
			if (typeS != null) {
				String name = UMethod.getFieldName(method);
				if (!map.containsKey(name)) {
					map.put(name, method);
				}
			}
		}
	}

	public Map<String, Method> getMap() {
		return map;
	}
}
