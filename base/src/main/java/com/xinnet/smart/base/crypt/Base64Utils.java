package com.xinnet.smart.base.crypt;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;

/**
 * base64解密服务
 * @author meitao
 * @date Sep 28, 2014 4:50:00 PM
 */
public class Base64Utils {
	private static Base64 BASE64 = new Base64();

	public static String decode(String source) {
		if (source == null) {
			return source;
		}
		return new String(BASE64.decode(source.getBytes(Charsets.UTF_8)), Charsets.UTF_8);
	}

	public static String encode(String source) {
		if (source == null) {
			return null;
		}
		return new String(BASE64.encode(source.getBytes(Charsets.UTF_8)), Charsets.UTF_8);
	}

	public static String encode(byte[] source) {
		if (source == null) {
			return null;
		}
		return new String(BASE64.encode(source), Charsets.UTF_8);
	}
}
