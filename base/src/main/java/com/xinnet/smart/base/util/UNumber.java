package com.xinnet.smart.base.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Pattern;

public class UNumber {
	final static Random RANDOM = new Random();

	public final static int randInt(int max) {
		return RANDOM.nextInt(max);
	}

	public static BigDecimal celling(BigDecimal d) {
		return celling(d, 4);//对结果数据统一保留4位
		//return Math.ceil(Math.round(d * 100000) / 10) / 10000;//对结果数据统一保留4位
	}

	/**
	 * 
	 * @author Administrator
	 * @date 2016年8月3日 上午10:46:17
	 * @param value
	 * @param scale
	 * @return double
	 */
	public static BigDecimal celling(BigDecimal bd1, int scale) {
		bd1 = bd1.movePointRight(scale);
		BigDecimal bd2 = new BigDecimal(power(10, scale));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_CEILING);
	}

	/**
	 * 加法
	 * @author Administrator
	 * @date 2016年8月3日 上午10:37:34
	 * @param ds
	 * @return BigDecimal
	 */
	public static BigDecimal sum(BigDecimal... ds) {
		if (ds == null || ds.length == 0) {
			return new BigDecimal(0);
		}
		int j = ds.length;
		if (j == 1) {
			return ds[0];
		}
		BigDecimal t;
		if (ds[0] == null) {
			t = new BigDecimal(0);
		} else {
			t = ds[0];
		}
		for (int i = 1; i < j; i++) {
			if(ds[i] == null){
				ds[i] = new BigDecimal(0);
			}
			t = t.add(ds[i]);
		}
		return t;
	}

	public static double sumByDouble(double... ds) {
		if (ds == null || ds.length == 0) {
			return 0;
		}
		int j = ds.length;
		if (j == 1) {
			return ds[0];
		}
		BigDecimal t = new BigDecimal(Double.toString(ds[0]));
		for (int i = 1; i < j; i++) {
			t = t.add(new BigDecimal(Double.toString(ds[i])));
		}
		return t.doubleValue();
	}

	public static double divByDouble(double d1, double d2) {
		return divByDouble(d1, d2, 10);//中间数据小数点只需保留10位
	}

	private static double divByDouble(double d1, double d2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 乘法
	 * @author Administrator
	 * @date 2016年8月3日 上午10:37:34
	 * @param ds
	 * @return BigDecimal
	 */
	public static BigDecimal mul(BigDecimal... ds) {
		if (ds == null || ds.length == 0) {
			return new BigDecimal(0);
		}
		int j = ds.length;
		if (j == 1) {
			return ds[0];
		}
		BigDecimal t = ds[0];
		for (int i = 1; i < j; i++) {
			t = t.multiply(ds[i]);
		}
		return t;
	}

	public static double mulByDouble(double... ds) {
		if (ds == null || ds.length == 0) {
			return 0;
		}
		int j = ds.length;
		if (j == 1) {
			return ds[0];
		}
		BigDecimal t = new BigDecimal(Double.toString(ds[0]));
		for (int i = 1; i < j; i++) {
			t = t.multiply(new BigDecimal(Double.toString(ds[i])));
		}
		return t.doubleValue();
	}

	/**
	 * 减法
	 * @author Administrator
	 * @date 2016年8月3日 上午10:37:34
	 * @param ds
	 * @return BigDecimal
	 */
	public static BigDecimal sub(BigDecimal d1, BigDecimal d2) {
		return d1.subtract(d2);
	}

	/**
	 * 减法
	 * @author Administrator
	 * @date 2016年8月17日 下午3:00:49
	 * @param d1
	 * @param d2
	 * @return Double
	 */
	public static Double subBydouble(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}

	/**
	 * 除法
	 * @author Administrator
	 * @date 2016年8月3日 上午10:37:34
	 * @param ds
	 * @return BigDecimal
	 */
	public static BigDecimal div(BigDecimal d1, BigDecimal d2) {
		return div(d1, d2, 10);//中间数据小数点只需保留10位
	}

	private static BigDecimal div(BigDecimal d1, BigDecimal d2, int scale) {
		return d1.divide(d2, scale, BigDecimal.ROUND_HALF_UP);
	}

	public static void main(String[] args) {
		BigDecimal d1 = new BigDecimal(-40);
		d1 = d1.abs();
		System.out.println(d1);
	}

	public static final int power(int base, int exponent) {
		int ret = 1;
		for (int i = 0; i < exponent; i++) {
			ret *= base;
		}
		return ret;
	}

	public static final long power(long base, long exponent) {
		int ret = 1;
		for (int i = 0; i < exponent; i++) {
			ret *= base;
		}
		return ret;
	}

	/**
	 * 向上取正
	 * @author Administrator
	 * @date 2016年8月3日 上午10:42:25
	 * @param number
	 * @return int
	 */
	public static final int ceilToInt(final double number) {
		return new Double(Math.ceil(number)).intValue();
	}

	public static final boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static final boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	public static Integer sum(Integer... spaces) {
		int totalSpace = 0;
		int j = spaces.length;
		for (int i = 0; i < j; i++) {
			totalSpace += spaces[i];
		}
		return totalSpace;
	}
}
