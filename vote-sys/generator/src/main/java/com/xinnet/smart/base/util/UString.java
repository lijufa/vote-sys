package com.xinnet.smart.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;

/**
 * 字符串处理工具类
 * @author meitao
 * @date 2013-2-28
 */
public abstract class UString {
	static final Logger logger = LoggerFactory.getLogger(UString.class);
	static final Random RANDOM = new Random();

	/**
	 * 过滤空字符串
	 * @author meitao
	 * @date Jun 26, 2015 7:45:42 PM
	 * @param array
	 * @return String[]
	 */
	public static String[] trim(String[] array) {
		List<String> ret = new ArrayList<String>();
		if (array != null && array.length > 0) {
			int j = array.length;
			for (int i = 0; i < j; i++) {
				String s = array[i];
				if (s != null && s.length() > 0) {
					ret.add(s);
				}
			}
		}
		return ret.toArray(IEmpty.STRINGS);
	}

	public static Boolean parseBoolean(String s, Boolean defaultValue) {
		if (s == null) {
			return defaultValue;
		}
		return parseBool(s);
	}

	public static boolean parseBool(String s) {
		return "1".equals(s) || "true".equalsIgnoreCase(s);
	}

	public static Long[] parseLong(String[] ss) {
		if (isEmpty(ss)) {
			return null;
		}
		try {
			int j = ss.length;
			List<Long> ret = new ArrayList<Long>();
			for (int i = 0; i < j; i++) {
				Long x = parseLong(ss[i], null);
				if (x != null) {
					ret.add(x);
				}
			}
			return ret.toArray(IEmpty.Longs);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	public static Long parseLong(String s) {
		return parseLong(s, null);
	}

	public static Long parseLong(String s, Long defaultValue) {
		if (isEmpty(s)) {
			return defaultValue;
		}
		try {
			return Long.parseLong(s);
		} catch (Throwable ignore) {
			return defaultValue;
		}
	}

	public static Integer[] parseInt(String[] ss) {
		if (isEmpty(ss)) {
			return null;
		}
		try {
			int j = ss.length;
			List<Integer> ret = new ArrayList<Integer>();
			for (int i = 0; i < j; i++) {
				Integer x = parseInt(ss[i], null);
				if (x != null) {
					ret.add(x);
				}
			}
			return ret.toArray(IEmpty.Integers);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	public static Integer parseInt(String s) {
		return parseInt(s, null);
	}

	public static Integer parseInt(String s, Integer defaultValue) {
		if (isEmpty(s)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(s);
		} catch (Throwable ignore) {
			return defaultValue;
		}
	}

	public static String attribute(String s, String prefix, String suffix) {
		if (s != null && prefix != null && suffix != null) {
			int beginIndex = s.indexOf(prefix);
			if (beginIndex != -1) {
				beginIndex += prefix.length();
				s = s.substring(beginIndex);
				int endIndex = s.indexOf(suffix);
				if (endIndex != -1) {
					return s.substring(0, endIndex);
				}
			}
		}
		return null;
	}

	/**
	 * 检验字符串是否为空或null
	 */
	public static boolean notEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	/**
	 * 检验字符串是否为空或null
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	public static String parseEmptyAsNull(String s) {
		return isEmpty(s) ? null : s;
	}

	public static boolean isEmpty(String[] ss) {
		if (ArrayUtils.isEmpty(ss)) {
			return true;
		}
		int j = ss.length;
		int numNotEmpty = 0;
		for (int i = 0; i < j; i++) {
			String s = ss[i];
			if (s == null) {
				ss[i] = IEmpty.STRING;
			} else if (s.length() == 0) {
			} else {
				numNotEmpty++;
			}
		}
		return numNotEmpty == 0;
	}

	/**
	 * 首字母小写
	 * @author meitao
	 * @date 2014年7月25日 下午1:01:07
	 * @param s
	 * @return String
	 */
	public static String toLowerCaseFirst(String s) {
		if (notEmpty(s)) {
			return new StringBuilder(s.substring(0, 1).toLowerCase()).append(s.substring(1)).toString();
		}
		return s;
	}

	public static String toString(byte... args) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (byte b : args) {
			if (sb.length() > 1) {
				sb.append(',');
			}
			sb.append(b);
		}
		sb.append(']');
		return sb.toString();
	}

	/**
	 * 输出复合数据到字符串
	 * @author meitao
	 * @date 2014-7-28 下午3:43:51
	 * @param args
	 * @return
	 */
	public static String toString(Object... args) {
		if (args == null) {
			return "null";
		}
		switch (args.length) {
		case 0:
			return IEmpty.STRING;
		case 1:
			Object o = args[0];
			if (o instanceof byte[]) {
				return toString((byte[]) o);
			} else if (o instanceof Object[]) {
				return toString((Object[]) o);
			} else {
				return String.valueOf(args[0]);
			}
		default:
			StringBuilder sb = new StringBuilder();
			sb.append('[');
			for (Object e : args) {
				if (sb.length() > 1) {
					sb.append(',');
				}
				sb.append(toString(e));
			}
			sb.append(']');
			return sb.toString();
		}
	}

	/**
	 * 将字符串转化为长整形类，如发生错误返回null
	 * @author meitao
	 * @date 2014-7-28 下午3:12:37
	 * @param s
	 * @return
	 */
	public static Long toLong(String s) {
		if (s != null) {
			s = s.trim();
			if (s.length() > 0) {
				try {
					return new Long(s);
				} catch (NumberFormatException e) {
				}
			}
		}
		return null;
	}

	public static long toLong(String s, long defaultValue) {
		if (s != null) {
			s = s.trim();
			if (s.length() > 0) {
				try {
					return Long.parseLong(s);
				} catch (NumberFormatException e) {
				}
			}
		}
		return defaultValue;
	}

	/**
	 * 首字母大写
	 * @author meitao
	 * @date 2014年7月25日 下午1:01:19
	 * @param s
	 * @return String
	 */
	public static String toUpperCaseFirst(String s) {
		if (notEmpty(s)) {
			return new StringBuilder(s.substring(0, 1).toUpperCase()).append(s.substring(1)).toString();
		}
		return s;
	}

	/**
	 * 得到字符串的前缀(不带隔断字符)，如果找不到则返回空字符串
	 * @author meitao
	 * @date 2014-7-28 下午3:34:04
	 * @param s
	 * @param c
	 * @param times
	 * @return
	 */
	public static String prefix(String s, char c, int times) {
		int i = -1, j = s.length(), k = 0, t = j;
		while ((k < times) && (++i < j)) {
			if ('.' == s.charAt(i)) {
				k++;
				t = i;
			}
		}
		return s.substring(0, t);
	}

	/**
	 * 得到字符串的后缀(带隔断字符)，如果找不到则返回空字符串
	 * @author meitao
	 * @date 2014-7-28 下午3:10:42
	 * @param s
	 * @param c
	 * @return
	 */
	public static String suffix(String s, char c) {
		if (s != null) {
			int i = s.lastIndexOf(c);
			if (i != -1) {
				return s.substring(i);
			}
		}
		return IEmpty.STRING;
	}

	/**
	 * 得到字符串的前缀(带隔断字符)，如果找不到则返回空字符串
	 * @author meitao
	 * @date 2014-7-28 下午3:10:47
	 * @param s
	 * @param c
	 * @return
	 */
	public static String prefix(String s, char c) {
		if (s != null) {
			int i = s.lastIndexOf(c);
			if (i != -1) {
				return s.substring(0, i + 1);
			}
		}
		return IEmpty.STRING;
	}

	/**
	 * 得到字符串的前缀(不带隔断字符)，如果找不到则返回完整字符串
	 * @author meitao
	 * @date 2014-7-28 下午3:10:55
	 * @param s
	 * @param c
	 * @return
	 */
	public static String prefixWithoutSeparator(String s, char c) {
		if (s != null) {
			s = s.replace(IEmpty.STRING + c + c, Character.toString(c));
			int i = s.lastIndexOf(c);
			if (i != -1) {
				return s.substring(0, i);
			}
		}
		return s;
	}

	/**
	 * 得到字符串的后缀(不带隔断字符)，如果找不到则返回完整字符串
	 * @author meitao
	 * @date 2014-7-28 下午3:11:24
	 * @param s
	 * @param c
	 * @return
	 */
	public static String suffixWithoutSeparator(String s, char c) {
		if (s != null) {
			s = s.replace(IEmpty.STRING + c + c, Character.toString(c));
			int i = s.lastIndexOf(c);
			if (i != -1) {
				return s.substring(i + 1);
			}
		}
		return s;
	}

	/**
	 * 版本比较
	 * @author meitao
	 * @date 2014年7月25日 下午1:00:57
	 * @param version1
	 * @param version2
	 * @return String
	 */
	public static String laterVersion(String version1, String version2) {
		if (version1 != null && version2 != null) {
			if (version1.equals(version2)) {
				return version1;
			} else {
				String[] array1 = version1.trim().toLowerCase().split("\\.");
				String[] array2 = version2.trim().toLowerCase().split("\\.");
				int i = 0;
				int j = array1.length;
				String t1 = IEmpty.STRING;
				String t2 = IEmpty.STRING;
				while (i < j) {
					t1 = array1[i];
					if (array2.length > i) {
						t2 = array2[i];
						if (!t1.equals(t2)) {
							break;
						}
					} else {
						return version1;
					}
					++i;
				}
				if (t1.length() > t2.length()) {
					return version1;
				} else if (t1.length() < t2.length()) {
					return version2;
				}
				return t1.compareTo(t2) > 0 ? version1 : version2;
			}
		}
		return null;
	}

	public static String random(char[] charset, int length) {
		if (charset == null || charset.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(charset[RANDOM.nextInt(charset.length) % charset.length]);
		}
		return sb.toString();
	}

	public static String like(String s) {
		if (UString.notEmpty(s)) {
			return '%' + s + '%';
		} else {
			return null;
		}
	}

	public static String[] beginEnd(String[] ss) {
		if (isEmpty(ss)) {
			return IEmpty.BeginEnd;
		} else if (ss.length == 1) {
			return new String[] { ss[0], IEmpty.STRING };
		} else {
			return ss;
		}
	}

	public static String substring(String s, String begin, String end) {
		if (isEmpty(s) || isEmpty(begin) || isEmpty(end)) {
			return IEmpty.STRING;
		}
		int beginIndex = s.indexOf(begin);
		if (beginIndex == -1) {
			return IEmpty.STRING;
		}
		s = s.substring(beginIndex + begin.length());
		int endIndex = s.indexOf(end);
		if (endIndex == -1) {
			return IEmpty.STRING;
		}
		return s.substring(0, endIndex);
	}

	public static Double parseDouble(String s) {
		return parseDouble(s, null);
	}

	public static Double parseDouble(String s, Double defaultValue) {
		if (isEmpty(s)) {
			return defaultValue;
		}
		try {
			return Double.parseDouble(s);
		} catch (Throwable ignore) {
			return defaultValue;
		}
	}
}
