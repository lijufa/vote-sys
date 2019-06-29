package com.xinnet.smart.base.util;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Json转换工具
 * @author meitao
 * @date 2014年7月15日 上午9:38:59
 */
public class UJson {
	static final Logger LOGGER = LoggerFactory.getLogger(UJson.class);
	public static boolean QUOTE_FIELD_NAMES = false;

	static void configure(ObjectMapper mapper) {
		//将单个当作批量解析，可能因此无法区分返回数据类型而导致一些隐患，需要编码人员注意返回数据类型要统一 
		mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
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
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
		ObjectMapper mapper = new ObjectMapper();
		try {
			configure(mapper);
			return mapper.readValue(jsonString, clazz);
		} catch (Throwable e) {
			LOGGER.error(UTrace.trace(e));
			LOGGER.error("jsonString:" + jsonString);
			return null;
		}
	}

	public static boolean verify(String s) {
		return s.length() >= 2 && s.charAt(0) == '{' && s.endsWith("}");
	}

	public static <T> T tryObject(String jsonString, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			configure(mapper);
			return mapper.readValue(jsonString, clazz);
		} catch (Throwable ignore) {
			return null;
		}
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
			LOGGER.error(UTrace.trace(e));
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
			LOGGER.error(UTrace.trace(e));
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
			LOGGER.error(UTrace.trace(e));
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
			return null;
		}
	}
}
