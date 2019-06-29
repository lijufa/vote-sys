package com.xinnet.smart.base.util;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;

/**
 * Json转换工具
 * @author meitao
 * @date 2014年7月15日 上午9:38:59
 */
public class UJson {
	static final Logger logger = LoggerFactory.getLogger(UJson.class);
	private static boolean QUOTE_FIELD_NAMES = false;

	public static final boolean isQUOTE_FIELD_NAMES() {
		return QUOTE_FIELD_NAMES;
	}

	public static final void setQUOTE_FIELD_NAMES(boolean qUOTE_FIELD_NAMES) {
		QUOTE_FIELD_NAMES = qUOTE_FIELD_NAMES;
	}

	static void configure(ObjectMapper mapper) {
		// 转json时不转为null的属性
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
		//java内部交换数据属性名可不加引号
		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, QUOTE_FIELD_NAMES);
		//支持无引号属性名
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		//支持单引号
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		//TOFIX:No serializer found for class
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		//将单个当作批量解析，可能因此无法区分返回数据类型而导致一些隐患，需要编码人员注意返回数据类型要统一 
		mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static final Object tryObject(String[] s, Class<?> type) {
		Object ret = null;
		if (s != null && s.length > 0) {
			if (type.isArray()) {
				if (String[].class.equals(type)) {
					ret = s;
				} else {
					ret = UJson.tryArray(s, type.getComponentType());
				}
			} else {
				if (String.class.equals(type)) {
					ret = s[0];
				} else {
					ret = UJson.tryObject(s[0], type);
				}
			}
		}
		return ret;
	}

	/**
	 * 将Json字符串转换成指定类的实例
	 * @author meitao
	 * @date Sep 4, 2014 7:05:29 AM
	 * @param jsonString
	 * @param Class<T> clazz
	 * @return T 如果失败了会返回null
	 */
	public static <T> T toObject(String jsonString, Class<T> clazz) {
		if (UString.isEmpty(jsonString)) {
			return null;
		}
		if (String.class.equals(clazz)) {
			return (T) jsonString;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			configure(mapper);
			return mapper.readValue(jsonString, clazz);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			logger.error("jsonString:" + jsonString);
			return null;
		}
	}

	public static boolean verify(String s) {
		return s.length() >= 2 && s.charAt(0) == '{' && s.endsWith("}");
	}

	public static <T> T tryObject(String jsonString, Class<T> clazz) {
		if (UString.isEmpty(jsonString)) {
			return null;
		}
		if (String.class.equals(clazz)) {
			return (T) jsonString;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			configure(mapper);
			return mapper.readValue(jsonString, clazz);
		} catch (Throwable ignore) {
			logger.debug(jsonString);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Class<T> clazz) {
		switch (clazz.getName()) {
		case "java.lang.String":
			return (T[]) IEmpty.STRINGS;
		case "java.lang.Integer":
			return (T[]) IEmpty.INTEGERS;
		case "java.lang.Long":
			return (T[]) IEmpty.LONGS;
		case "java.lang.Boolean":
			return (T[]) IEmpty.BOOLEANS;
		default:
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] tryArray(String[] ss, Class<T> clazz) {
		if (String.class.equals(clazz)) {
			return (T[]) ss;
		}
		T[] array = toArray(clazz);
		if (array == null) {
			return null;
		}
		List<T> ret = new ArrayList<T>();
		for (String s : ss) {
			ret.add(tryObject(s, clazz));
		}
		return ret.toArray(array);
	}

	/**
	 * 把json串转化成list
	 * 
	 * @author xiaojuan
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> toObjectlist(String jsonString, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			configure(mapper);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
			List<T> list = (List<T>) mapper.readValue(jsonString, javaType);
			return list;
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	/**
	 * 将Java对象转换成Json字符串
	 * @author meitao
	 * @date Sep 4, 2014 7:04:29 AM
	 * @param Object object
	 * @return String 如果失败了会返回null
	 */
	public static String toString(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			configure(mapper);
			return mapper.writeValueAsString(object);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T toParametricTypeObject(String s, Class<T> parametrized, Class<?>... parameterClasses) {
		if (s == null || s.length() == 0) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			configure(mapper);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
			return (T) mapper.readValue(s, javaType);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T tryParametricTypeObject(String s, Class<T> parametrized, Class<?>... parameterClasses) {
		if (s == null || s.length() == 0) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			configure(mapper);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
			return (T) mapper.readValue(s, javaType);
		} catch (Throwable ignore) {
			logger.error(UTrace.trace(ignore));
			return null;
		}
	}
}
