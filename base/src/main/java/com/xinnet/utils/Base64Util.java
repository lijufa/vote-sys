package com.xinnet.utils;

import com.xinnet.smart.base.util.UName;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.UnsupportedEncodingException;

/**
 * @Author yangqiu
 * @Description 例如：
 * 用户名：admin
 * 密码:123456
 *
 * 先用户名密码拼接
 * admin/123456
 *
 * 然后再第10位置加上salt 
 * salt = !@#gsfm+_)
 *
 * 即：admin/1234!@#gsfm+_)56
 * 再 base64 ( admin/1234!@#gsfm+_)56 )   ==> YWRtaW4vMTIzNCFAI2dzZm0rXyk1Ng==
 * @Date 2019-05-23 17:47
 */
public class Base64Util {
	private static final String salt = "!@#gsfm+_)";

	/**
	 * user BASE64 加密
	 * @param username
	 * @param password
	 * @return
	 */
	public static String base64Token(String username, String password) {
		String user = username + "/" + password;
		String baseStr = user.substring(0, 10) + salt + user.substring(10);
		return getBase64(baseStr);
	}

	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	/**
	 *  解密
	 * @param s
	 * @return
	 */
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(UName.idToName(8052055));
		System.out.println(base64Token("4sl07","8052055"));
	}
}
