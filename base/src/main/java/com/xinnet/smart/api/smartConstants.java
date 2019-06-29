package com.xinnet.smart.api;

public interface smartConstants {
	public static final String MAP_COUNT_KEY = "count";
	public static final String MAP_VM_DATA_KEY = "vmData";
	public static final String MAP_HOST_DATA_KEY = "hostData";
	public static final String REQUEST_MAXROWS_KEY = "max_rows";
	public static final String REQUEST_LAST_SYNC_CREATETIME_KEY = "last_sync_createtime";
	public static final int COUNT_RANGE = 1; //同步数据过程中允许的误差值
	public static final int DEFAULT_MAX_SYNC_HOURS = 24; //默认同步数据保存时间
	public static final int DEFAULT_MAX_STAT_HOURS = DEFAULT_MAX_SYNC_HOURS; //默认统计数据保存时间
	public static final int DEFAULT_MAX_ROWS = 300;
	public static final int DEFAULT_STAT_HOST_MAX_ROWS = 300;
	public static final String MULTIPLE_SPLIT = ";";
	public static final String NAME_DATA_SPLIT = "\\|";
	public static final int NO_PAGING = 0;
}
