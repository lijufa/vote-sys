package com.xinnet.smart.test.data.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UTypeMysql {
	static final Map<String, Class<?>> TYPE_MAP = new HashMap<String, Class<?>>();
	static {
		TYPE_MAP.put("bit", Boolean.class);
		TYPE_MAP.put("char(1)", Character.class);
		TYPE_MAP.put("tinyint", Integer.class);
		TYPE_MAP.put("int", Integer.class);
		TYPE_MAP.put("bigint", Long.class);
		TYPE_MAP.put("decimal", Double.class);
		TYPE_MAP.put("double", Double.class);
		TYPE_MAP.put("char", String.class);
		TYPE_MAP.put("varchar", String.class);
		TYPE_MAP.put("longtext", String.class);
		TYPE_MAP.put("text", String.class);
		TYPE_MAP.put("enum", String.class);
		//日期对应字符串类型可支持更高精度，且方便用字符串查询和返回
		TYPE_MAP.put("datetime", Date.class);
		TYPE_MAP.put("timestamp", Date.class);
		TYPE_MAP.put("date", Date.class);
	}

	public static Class<?> parse(String type) {
		if (TYPE_MAP.containsKey(type)) {
			return TYPE_MAP.get(type);
		} else {
			int idx = type.indexOf('(');
			if (idx != -1) {
				type = type.substring(0, idx);
			}
			return TYPE_MAP.get(type);
		}
	}

	public static String parseClassName(String type) {
		String ret = null;
		Class<?> c = parse(type);
		if (c != null) {
			ret = c.getName();
		}
		return ret;
	}

	public static String parseSimpleName(String type) {
		String ret = null;
		Class<?> c = parse(type);
		if (c != null) {
			if (c.getName().startsWith("java.lang")) {
				ret = c.getSimpleName();
			} else {
				ret = c.getName();
			}
		}
		return ret;
	}
}
