package com.xinnet.smart.base.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import com.xinnet.smart.IEmpty;

/**
 * Method反射工具
 * @author meitao
 * @date 2014年7月15日 上午9:40:02
 */
public class UMethod {
	static final Logger logger = LoggerFactory.getLogger(UMethod.class);

	public static void trimArgs(Object o) {
		if (o != null) {
			Map<String, Method> setters = UMethod.getSetterMap(o.getClass());
			Map<String, Method> getters = UMethod.getGetterMap(o.getClass());
			Iterator<Entry<String, Method>> iterator = getters.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Method> entry = iterator.next();
				Method getter = entry.getValue();
				Method setter = setters.get(entry.getKey());
				if (setter != null) {
					try {
						Object values = getter.invoke(o);
						if (values != null) {
							Class<?> type = getter.getReturnType();
							if (type.isArray()) {
								int notEmptyCount = 0;
								for (Object value : (Object[]) values) {
									if (value != null) {
										if (value instanceof String) {
											if (UString.notEmpty((String) value)) {
												notEmptyCount++;
												break;
											}
										} else {
											notEmptyCount++;
											break;
										}
									}
								}
								if (notEmptyCount == 0) {
									setter.invoke(o, IEmpty.INVOKE_ARGS);
								}
							} else if (String.class.equals(type)) {
								if (UString.isEmpty((String) values)) {
									setter.invoke(o, IEmpty.INVOKE_ARGS);
								}
							}
						}
					} catch (Throwable e) {
						logger.error(UTrace.trace(e));
					}
				}
			}
		}
	}

	public static Map<String, Object> getMap(Object o) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Method> getters = UMethod.getGetterMap(o.getClass());
		Iterator<Entry<String, Method>> iterator = getters.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Method> entry = iterator.next();
			try {
				ret.put(entry.getKey(), entry.getValue().invoke(o));
			} catch (Throwable ignore) {
				logger.error(ignore.getMessage());
			}
		}
		return ret;
	}

	public static Map<String, String> getStringMap(Object o) {
		Map<String, String> ret = new HashMap<String, String>();
		Map<String, Method> getters = UMethod.getGetterMap(o.getClass());
		Iterator<Entry<String, Method>> iterator = getters.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Method> entry = iterator.next();
			try {
				ret.put(entry.getKey(), String.valueOf(entry.getValue().invoke(o)));
			} catch (Throwable ignore) {
				logger.error(ignore.getMessage());
			}
		}
		return ret;
	}

	public static Map<String, Method> getGetterMap(Class<?> c) {
		GetterCallback callback = new GetterCallback();
		ReflectionUtils.doWithMethods(c, callback);
		return callback.getMap();
	}

	public static Collection<Method> getGetterCollection(Class<?> c) {
		return getGetterMap(c).values();
	}

	public static Method[] getGetterArray(Class<?> c) {
		return getGetterCollection(c).toArray(new Method[0]);
	}

	public static Map<String, Method> getSetterMap(Class<?> c) {
		SetterCallback callback = new SetterCallback();
		ReflectionUtils.doWithMethods(c, callback);
		return callback.getMap();
	}

	public static Collection<Method> getSetterCollection(Class<?> c) {
		return getSetterMap(c).values();
	}

	public static Method[] getSetterArray(Class<?> c) {
		return getSetterCollection(c).toArray(new Method[0]);
	}

	public static Class<?> getReturnType(Class<?> clazz, Method method) {
		Class<?> type = method.getReturnType();
		Type genericType = method.getGenericReturnType();
		if (Object.class.equals(type)) {
			//TODO 如果参数中有Object类型?
			if (Object.class.equals(genericType)) {
				//解决@Override接口
				return null;
			}
			return getGenericType(clazz, method, genericType);
		} else {
			return type;
		}
	}

	public static Class<?> getParameterType(Class<?> clazz, Method method) {
		Class<?> type = method.getParameterTypes()[0];
		Type genericType = method.getGenericParameterTypes()[0];
		if (Object.class.equals(type)) {
			//TODO 如果参数中有Object类型?
			if (Object.class.equals(genericType)) {
				//解决@Override接口
				return null;
			}
			return getGenericType(clazz, method, genericType);
		} else {
			return type;
		}
	}

	public static Class<?>[] getParameterTypes(Class<?> clazz, Method method) {
		Class<?>[] typeS = method.getParameterTypes();
		Type[] genericTypeS = method.getGenericParameterTypes();
		int j = typeS.length;
		Class<?>[] ret = new Class<?>[j];
		int i = 0;
		while (i < j) {
			if (Object.class.equals(typeS[i])) {
				//TODO 如果参数中有Object类型?
				if (Object.class.equals(genericTypeS[i])) {
					//解决@Override接口
					return null;
				}
				ret[i] = getGenericType(clazz, method, genericTypeS[i]);
			} else {
				ret[i] = typeS[i];
			}
			i++;
		}
		return ret;
	}

	public static void debugParameterAnnotations(Method method) {
		Annotation[][] annotationSS = method.getParameterAnnotations();
		StringBuilder sb = new StringBuilder();
		for (Annotation[] annotationS : annotationSS) {
			for (Annotation annotation : annotationS) {
				sb.append(annotation);
				sb.append(',');
			}
			sb.append('\n');
		}
		logger.debug(sb.toString());
	}

	public static String getFieldName(Method method) {
		String s = method.getName();
		s = s.replaceFirst("get|set|is", IEmpty.STRING);
		s = s.substring(0, 1).toLowerCase() + s.substring(1);
		return s;
	}

	public static boolean isGetter(Method method) {
		if (method.getParameterTypes().length == 0) {
			String name = method.getName();
			if (name.startsWith("get")) {
				return !"getClass".equals(name);
			} else {
				return name.startsWith("is");
			}
		}
		return false;
	}

	public static boolean isSetter(Method method) {
		if (method.getParameterTypes().length == 1) {
			return method.getName().startsWith("set");
		}
		return false;
	}

	/**
	 * 将第一个参数对象的属性值拷贝到第二个参数对象
	 * @author meitao
	 * @date 2012-7-31
	 * @param o
	 * @param o2 void
	 */
	public static void copy(Object original, Object target) {
		Object[] values = new Object[1];
		Map<String, Method> setters = UMethod.getSetterMap(original.getClass());
		Map<String, Method> getters = UMethod.getGetterMap(target.getClass());
		Iterator<Entry<String, Method>> iterator = getters.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Method> entry = iterator.next();
			Method getter = entry.getValue();
			Method setter = setters.get(entry.getKey());
			if (setter != null && setter.getParameterTypes()[0].equals(getter.getReturnType())) {
				try {
					values[0] = getter.invoke(original);
					setter.invoke(target, values);
				} catch (Throwable ignore) {
				}
			}
		}
	}

	public static void copyNotNull(Object original, Object target) {
		Map<String, Method> setters = UMethod.getSetterMap(original.getClass());
		Map<String, Method> getters = UMethod.getGetterMap(target.getClass());
		Iterator<Entry<String, Method>> iterator = getters.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Method> entry = iterator.next();
			Method getter = entry.getValue();
			Method setter = setters.get(entry.getKey());
			if (setter != null && setter.getParameterTypes()[0].equals(getter.getReturnType())) {
				try {
					Object value = getter.invoke(original);
					if (value != null) {
						setter.invoke(target, value);
					}
				} catch (Throwable e) {
					logger.error(UTrace.trace(e));
				}
			}
		}
	}

	public static Class<?> getGenericType(Class<?> clazz, Method method, Type genericType) {
		if (genericType instanceof TypeVariable) {
			TypeVariable<?> genericTypeVariable = (TypeVariable<?>) genericType;
			Class<?> declaringClass = method.getDeclaringClass();
			Map<String, Type> actualTypeArguments = getActualTypeArguments(clazz, declaringClass);
			if (actualTypeArguments != null) {
				TypeVariable<?>[] typeVariableS = declaringClass.getTypeParameters();
				int j = typeVariableS.length;
				int i = 0;
				while (i < j) {
					TypeVariable<?> typeVariable = typeVariableS[i];
					String name = typeVariable.getName();
					if (name.equals(genericTypeVariable.getName())) {
						if (actualTypeArguments.containsKey(name)) {
							return (Class<?>) actualTypeArguments.get(name);
						}
					}
					i++;
				}
			}
		} else if (genericType instanceof ParameterizedType) {
			Type type = ((ParameterizedType) genericType).getActualTypeArguments()[0];
			if (type instanceof Class<?>) {
				return (Class<?>) type;
			}
		}
		return Object.class;
	}

	public static Map<String, Type> getActualTypeArguments(Class<?> clazz, Class<?> declaringClass) {
		Type genericSuperclass;
		while ((genericSuperclass = clazz.getGenericSuperclass()) != null) {
			if (genericSuperclass instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
				if (declaringClass.equals(parameterizedType.getRawType())) {
					Map<String, Type> ret = new HashMap<String, Type>();
					Type[] paramS = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
					int j = paramS.length;
					int i = -1;
					while (++i < j) {
						Type param = paramS[i];
						if (param instanceof TypeVariable) {
							//TypeVariable<?> paramTypeVariable = (TypeVariable<?>) param;
							//actualTypeArguments.put(paramTypeVariable.getName(), param);
						} else if (param instanceof Class<?>) {
							TypeVariable<?> type = clazz.getSuperclass().getTypeParameters()[i];
							ret.put(type.getName(), param);
						}
					}
					return ret;
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

	public static String[] getParameterNames(Method method) {
		Class<?> c = method.getDeclaringClass();
		ClassPool pool = ClassPool.getDefault();
		pool.insertClassPath(new ClassClassPath(c));
		CtClass cc;
		CtMethod cm = null;
		try {
			cc = pool.get(c.getName());
			cm = cc.getDeclaredMethod(method.getName());
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		}
		// 使用javaassist的反射方法获取方法的参数名  
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		int length = 0;
		try {
			length = cm.getParameterTypes().length;
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
		String[] ret = new String[length];
		int staticIndex = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < length; i++)
			ret[i] = attr.variableName(i + staticIndex);
		return ret;
	}

	public static String[] getComment(Method method) {
		Class<?> c = method.getDeclaringClass();
		ClassPool pool = ClassPool.getDefault();
		pool.insertClassPath(new ClassClassPath(c));
		CtClass cc;
		CtMethod cm = null;
		try {
			cc = pool.get(c.getName());
			cm = cc.getDeclaredMethod(method.getName());
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		}
		// 使用javaassist的反射方法获取方法的参数名
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		int length = 0;
		try {
			length = cm.getParameterTypes().length;
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
		String[] ret = new String[length];
		int staticIndex = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < length; i++)
			ret[i] = attr.variableName(i + staticIndex);
		return ret;
	}

	static class GetterCallback implements ReflectionUtils.MethodCallback {
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

	static class SetterCallback implements ReflectionUtils.MethodCallback {
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
}
