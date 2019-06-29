package com.xinnet.smart.cloud.network.util;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtilsV1 {
	/*
	 * 
	 * 	
	 	ipV6Start42540488161975842760550356425300246529
		ipV6  End42540488161975842760550356425300246783
		end-start:254

	 */
	public static void main(String[] args) {
		System.out.println(StringToLong("2001::0001"));
		System.out.println(BigIntToString(new BigInteger("42540488161975842760550356425300246529")));
	}

	/**
	 * 将字符串形式的ip地址转换为long
	 * 
	 * @param ipInString
	 *            字符串形式的ip地址
	 * @return long形式的ip地址
	 */
	public static BigInteger StringToLong(String ipInString) {
		ipInString = ipInString.replace(" ", "");
		byte[] bytes;
		if (ipInString.contains(":"))
			bytes = ipv6ToBytes(ipInString);
		else
			bytes = ipv4ToBytes(ipInString);
		BigInteger bigInt = new BigInteger(bytes);
		return bigInt;
	}

	/**
	 * 将整数形式的ip地址转换为字符串形式
	 * 
	 * @param ipInBigInt
	 *            整数形式的ip地址
	 * @return 字符串形式的ip地址
	 */
	public static String BigIntToString(BigInteger ipInBigInt) {
		try {
			String ip = InetAddress.getByAddress(ipInBigInt.toByteArray()).toString();
			return ip.substring(ip.indexOf('/') + 1).trim();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ipv6地址转有符号byte[17]
	 */
	private static byte[] ipv6ToBytes(String ipv6) {
		byte[] ret = new byte[17];
		ret[0] = 0;
		int ib = 16;
		boolean comFlag = false;// ipv4混合模式标记
		if (ipv6.startsWith(":"))// 去掉开头的冒号
			ipv6 = ipv6.substring(1);
		String groups[] = ipv6.split(":");
		for (int ig = groups.length - 1; ig > -1; ig--) {// 反向扫描
			if (groups[ig].contains(".")) {
				// 出现ipv4混合模式
				byte[] temp = ipv4ToBytes(groups[ig]);
				ret[ib--] = temp[4];
				ret[ib--] = temp[3];
				ret[ib--] = temp[2];
				ret[ib--] = temp[1];
				comFlag = true;
			} else if ("".equals(groups[ig])) {
				// 出现零长度压缩,计算缺少的组数
				int zlg = 9 - (groups.length + (comFlag ? 1 : 0));
				while (zlg-- > 0) {// 将这些组置0
					ret[ib--] = 0;
					ret[ib--] = 0;
				}
			} else {
				int temp = Integer.parseInt(groups[ig], 16);
				ret[ib--] = (byte) temp;
				ret[ib--] = (byte) (temp >> 8);
			}
		}
		return ret;
	}

	/**
	 * ipv4地址转有符号byte[5]
	 */
	private static byte[] ipv4ToBytes(String ipv4) {
		byte[] ret = new byte[5];
		ret[0] = 0;
		// 先找到IP地址字符串中.的位置
		int position1 = ipv4.indexOf(".");
		int position2 = ipv4.indexOf(".", position1 + 1);
		int position3 = ipv4.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型
		ret[1] = (byte) Integer.parseInt(ipv4.substring(0, position1));
		ret[2] = (byte) Integer.parseInt(ipv4.substring(position1 + 1, position2));
		ret[3] = (byte) Integer.parseInt(ipv4.substring(position2 + 1, position3));
		ret[4] = (byte) Integer.parseInt(ipv4.substring(position3 + 1));
		return ret;
	}

	/**
	 * @param ipAdress ipv4或ipv6字符串
	 * @return 4 ：ipv4, 6:ipv6, 0:地址不对
	 * @throws Exception
	 */
	public static int isIpV4OrV6(String ipAdress) throws Exception {
		InetAddress address = InetAddress.getByName(ipAdress);
		if (address instanceof Inet4Address)
			return 4;
		else if (address instanceof Inet6Address)
			return 6;
		return 0;
	}

	/*
	 * 验证IP是否属于某个IP段
	 * 
	 * ipSection IP段（以'-'分隔）
	 * 
	 * ip 所验证的IP号码
	 */
	public static boolean ipExistsInRange(String ip, String ipSection) {
		ipSection = ipSection.trim();
		ip = ip.trim();
		int idx = ipSection.indexOf('-');
		String beginIP = ipSection.substring(0, idx);
		String endIP = ipSection.substring(idx + 1);
		return getIp2long(beginIP) <= getIp2long(ip) && getIp2long(ip) <= getIp2long(endIP);
	}

	public static long getIp2long(String ip) {
		ip = ip.trim();
		String[] ips = ip.split("\\.");
		long ip2long = 0L;
		for (int i = 0; i < 4; ++i) {
			ip2long = ip2long << 8 | Integer.parseInt(ips[i]);
		}
		return ip2long;
	}

	public static long getIp2long2(String ip) {
		ip = ip.trim();
		String[] ips = ip.split("\\.");
		long ip1 = Integer.parseInt(ips[0]);
		long ip2 = Integer.parseInt(ips[1]);
		long ip3 = Integer.parseInt(ips[2]);
		long ip4 = Integer.parseInt(ips[3]);
		long ip2long = 1L * ip1 * 256 * 256 * 256 + ip2 * 256 * 256 + ip3 * 256 + ip4;
		return ip2long;
	}

	public void test1() {
		try {
			String addr = "192.168.1.3/20";
			String[] parts = addr.split("/");
			String ip = parts[0];
			int prefix;
			if (parts.length < 2) {
				prefix = 0;
			} else {
				prefix = Integer.parseInt(parts[1]);
			}
			int mask = 0xffffffff << (32 - prefix);
			System.out.println("Prefix=" + prefix);
			System.out.println("Address=" + ip);
			int value = mask;
			byte[] bytes = new byte[] { (byte) (value >>> 24), (byte) (value >> 16 & 0xff), (byte) (value >> 8 & 0xff), (byte) (value & 0xff) };
			InetAddress netAddr = InetAddress.getByAddress(bytes);
			System.out.println("Mask=" + netAddr.getHostAddress());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void test2() {
		try {
			String addr = "2001:0000:0000:0000:0000:0000:0000:ab01/64";
			String[] parts = addr.split("/");
			String ip = parts[0];
			int prefix;
			if (parts.length < 2) {
				prefix = 0;
			} else {
				prefix = Integer.parseInt(parts[1]);
			}
			int mask = 0xffffffff << (128 - prefix);
			System.out.println("Prefix=" + prefix);
			System.out.println("Address=" + ip);
			System.out.println("Mask=" + mask);
			int value = mask;
			byte[] bytes = new byte[] { (byte) (value >>> 24), (byte) (value >> 16 & 0xff), (byte) (value >> 8 & 0xff), (byte) (value & 0xff) };
			InetAddress netAddr = InetAddress.getByAddress(bytes);
			System.out.println("Mask=" + netAddr.getHostAddress());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
