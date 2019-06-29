package com.xinnet.smart.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UTime {
	static final String NTPDATE = "ntpdate";
	static final String _u = "-u";
	static final Logger logger = LoggerFactory.getLogger(UTime.class);
	final static long KILO = 1000;
	final static long MILLION = KILO * KILO;
	//	public final static Long max(Date date, Long time) {
	//		if (date != null) {
	//			if (time == null || time < date.getTime()) {
	//				time = date.getTime();
	//			}
	//		}
	//		return time;
	//	}

	//	public final static double hourPercent(Date date1, Date date2) {
	//		return new Long(date1.getTime() - date2.getTime()).doubleValue() / 3600000d;
	//	}
	public final static long microSeconds() {
		return System.currentTimeMillis() * KILO + System.nanoTime() / KILO % KILO;
	}

	public final static long microToMilli(long microSeconds) {
		return microSeconds / KILO;
	}

	//	public final static Long milliToMicro(long milliSeconds) {
	//		return milliSeconds * KILO;
	//	}
	public final static Long microToMilli(Long microSeconds) {
		if (microSeconds == null) {
			return null;
		}
		return microToMilli(microSeconds.longValue());
	}
	/*
	public final static Long milliToMicro(Long milliSeconds) {
		if (milliSeconds == null) {
			return null;
		}
		return milliToMicro(milliSeconds.longValue());
	}
	*/
	/**
	 * 从ntp server同步时间
	 * @author meitao
	 * @date Nov 7, 2014 6:16:45 PM
	 * @param serverIP
	 * @return Long
	
	@Deprecated
	public static boolean ntpdate(String serverIP) {
		return PropertiesAgent.SHELL.runs(NTPDATE, _u, serverIP).isSuccess();
	}
	 */
	/**
	 * 打印时间差
	 * @author meitao
	 * @date Nov 11, 2014 10:46:24 AM
	 * @param title
	 * @param tBegin
	 * @param dtMax
	 
	public static final void dt(String title, long tBegin, long dtMax) {
		long tEnd = System.currentTimeMillis();
		if ((tEnd - tBegin) > dtMax) {
			StringBuilder sb = new StringBuilder();
			sb.append(title);
			sb.append(tEnd - tBegin);
			sb.append("ms");
			logger.info(sb.toString());
		}
	}
	*/
}
