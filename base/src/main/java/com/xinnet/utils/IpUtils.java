package com.xinnet.utils;

import com.xinnet.smart.base.util.UString;

/**
 * ip相关的工具类
 * @author leijianteng
 * @date 2017年9月18日 上午11:05:35
 */
public class IpUtils {
	/**
	 * 是否是正确的ip
	 * @author leijianteng
	 * @date 2017年9月18日 上午11:10:25
	 * @param ip
	 * @return Boolean
	 */
	public static Boolean isCorrectIp(String ip) {
		if (UString.isEmpty(ip)) {
			return Boolean.FALSE;
		}
		// 定义正则表达式
		StringBuffer reg = new StringBuffer();
		reg.append("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.");
		reg.append("(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.");
		reg.append("(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.");
		reg.append("(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");
		// 判断ip地址是否与正则表达式匹配
		if (ip.matches(reg.toString())) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * 是否是正确的ip地址段
	 * @author leijianteng
	 * @date 2017年9月18日 上午11:10:33
	 * @param ip Boolean
	 */
	public static Boolean isCorrectIpField(String ip) {
		// 定义正则表达式
		StringBuffer reg = new StringBuffer();
		reg.append("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.");
		reg.append("(\\d{1,3})\\.");
		reg.append("(\\d{1,3})\\.");
		reg.append("(\\d{1,3})\\/(\\d{1,3})$");
		// 判断ip地址是否与正则表达式匹配
		if (ip.matches(reg.toString())) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * 将ip数组转换成String
	 * @author leijianteng
	 * @date 2017年9月18日 上午11:39:26
	 * @param ip_address
	 * @return String
	 */
	public static String getIpStringByArray(String[] ipAddress) {
		StringBuffer retIpAddress = new StringBuffer();
		if (ipAddress == null || ipAddress.length == 0) {
			return retIpAddress.toString();
		}
		for (String ip : ipAddress) {
			retIpAddress.append(ip);
			retIpAddress.append(",");
		}
		return retIpAddress.toString().substring(0, retIpAddress.length() - 1);
	}
}
