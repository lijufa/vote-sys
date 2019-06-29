package com.xinnet.smart.base;

public interface ITime {
	String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	long MS_PER_HOUR = 3600000L;//每小时的毫秒数
	long MS_PER_DAY = MS_PER_HOUR * 24;//每天的毫秒数
	long MIN_MS_PER_MONTH = MS_PER_DAY * 28;//每月最少毫秒数
	long MIN_MS_PER_TOW_MONTH = MS_PER_DAY * (28 + 31);//每两个月最少毫秒数
	long MIN_MS_PER_YEAR = MS_PER_DAY * 365;//每年的最少毫秒数
}