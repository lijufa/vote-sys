package com.xinnet.smart;

import java.math.BigDecimal;

public interface IEmpty {
	byte[] bytes = {};
	int[] ints = {};
	long[] longs = {};
	Long ZERO_LONG = 0L;
	String STRING = "";
	Boolean[] BOOLEANS = {};
	Long[] LONGS = {};
	String[] STRINGS = {};
	Integer[] INTEGERS = {};
	Object[] INVOKE_ARGS = { null };
	String GATEWAY = "0.0.0.0";
	BigDecimal[] BIGDECIMAL = {};
	/**
	 * 外网dns指向ip
	 */
	String DNS_INTERNET_IP = "8.8.8.8";
	/**
	 * 内网dns指向ip
	 */
	String DNS_INTRANET_IP = "0.0.0.0";
}
