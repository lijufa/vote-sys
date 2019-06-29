package com.xinnet.smart.api;

/**
 * 云主机常量表
 * @author meitao
 * @date Nov 26, 2014 3:05:11 PM
 */
public interface TopApiDeclarations {
	/**
	 * controller分配资源成功后
	 * 1、调用TOP=web/boss的api地址
	 */
	//云主机
	public static String TOP_ARROW_CREATE_ASSIGN_HOST_CALLBACK_URL = "arrow/create/updateResource";
	public static String TOP_ARROW_CREATE_CALLBACK_URL = "arrow/create/updateState";
	//云数据库
	public static String TOP_DB_ARROW_CREATE_ASSIGN_HOST_CALLBACK_URL = "database/instance/create/updateResource";
	public static String TOP_DB_ARROW_CREATE_CALLBACK_URL = "database/instance/create/updateState";
	//负载均衡
	public static String TOP_LB_ARROW_CREATE_ASSIGN_HOST_CALLBACK_URL = "load_balancer/create/updateResource";
	public static String TOP_LB_ARROW_CREATE_CALLBACK_URL = "load_balancer/create/updateState";
}
