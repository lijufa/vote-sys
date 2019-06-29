package com.xinnet.smart.base.util;

/**
 * 表名、字段名反射工具
 * @author meitao
 * @date 2013-2-28
 */
public abstract class UName {
	public static String idToName(long id) {
		return Long.toString(id, Character.MAX_RADIX);
	}

	public static Long nameToId(String name) {
		try {
			return Long.parseLong(name, Character.MAX_RADIX);
		} catch (Throwable e) {
			return null;
		}
	}

	public static String toDaoName(String tableName) {
		return toDaoName(tableName, tableName.indexOf('_') + 1);
	}

	public static String toDaoName(String tableName, int start) {
		StringBuilder sb = new StringBuilder();
		sb.append('I');
		sb.append(toEntityName(tableName, start));
		sb.append("Dao");
		return sb.toString();
	}

	public static String toBeanName(String tableName) {
		return toBeanName(tableName, tableName.indexOf('_') + 1);
	}

	public static String toBeanName(String tableName, int start) {
		return toEntityName(tableName, start);
	}

	public static String toEntityName(String tableName) {
		return toEntityName(tableName, tableName.indexOf('_') + 1);
	}

	public static String toEntityName(String tableName, int start) {
		if (tableName == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		char[] chars = tableName.toCharArray();
		int j = chars.length;
		if (j > 0) {
			int i = start;
			sb.append(Character.toUpperCase(chars[i]));
			for (i++; i < j; i++) {
				char c = chars[i];
				if (c == '_') {
					i++;
					if (i < j) {
						sb.append(Character.toUpperCase(chars[i]));
					}
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	public static String toMethodName(String fieldName) {
		if (fieldName == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		char[] chars = fieldName.toCharArray();
		int j = chars.length;
		if (j > 0) {
			sb.append(Character.toUpperCase(chars[0]));
			for (int i = 1; i < j; i++) {
				char c = chars[i];
				if (c == '_') {
					i++;
					if (i < j) {
						sb.append(Character.toUpperCase(chars[i]));
					}
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	public static String toFieldName(String fieldName) {
		if (fieldName == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		char[] chars = fieldName.toCharArray();
		int j = chars.length;
		if (j > 0) {
			for (int i = 0; i < j; i++) {
				char c = chars[i];
				if (c == '_') {
					i++;
					if (i < j) {
						sb.append(Character.toUpperCase(chars[i]));
					}
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	public static String diskName(Long instanceId, Long diskId) {
		StringBuilder ret = new StringBuilder();
		ret.append(idToName(instanceId));
		ret.append('_');
		ret.append(idToName(diskId));
		ret.append(".img");
		return ret.toString();
	}

	public static String diskName(Long diskId) {
		StringBuilder ret = new StringBuilder();
		ret.append(idToName(diskId));
		ret.append(".img");
		return ret.toString();
	}
}
