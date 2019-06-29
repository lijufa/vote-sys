package com.xinnet.smart.api;

/**
 * 云主机常量表
 * @author meitao
 * @date Nov 26, 2014 3:05:11 PM
 */
public interface OpenstackLoadbalanceApiDeclarations {
	//zone外调用地址
	String OPENSTACK_LOADBALANCE_INSTANCE_CREATE = "openstack_loadbalance/instance/create";
	String OPENSTACK_LOADBALANCE_INSTANCE_DELETE = "openstack_loadbalance/instance/delete";
	String OPENSTACK_LOADBALANCE_INSTANCE_RECONFIG = "openstack_loadbalance/instance/reconfig";
	String OPENSTACK_LOADBALANCE_INSTANCE_RULE_RECONFIGURE = "openstack_loadbalance/monitor_rule/reconfig";
	String OPENSTACK_LOADBALANCE_INSTANCE_RULE_ROLLBACK = "openstack_loadbalance/monitor_rule/rollback";
	String OPENSTACK_LOADBALANCE_INSTANCE_START = "openstack_loadbalance/instance/start";
	String OPENSTACK_LOADBALANCE_INSTANCE_STOP = "openstack_loadbalance/instance/stop";
	String OPENSTACK_LOADBALANCE_INSTANCE_REBOOT = "openstack_loadbalance/instance/reboot";
	String OPENSTACK_LOADBALANCE_INSTANCE = "openstack_loadbalance/instance/";
	String OPENSTACK_LOADBALANCE_INSTANCE_MONITORDATA = "openstack_loadbalance/instance/getLoadBalanceMonitorData";
	String OPENSTACK_LOADBALANCE_INSTANCE_STATINFO = "openstack_loadbalance/instance/lbstatinfo";
	//回调zone外地址
	String OPENSTACK_LOADBALANCE_INSTANCE_CREATE_ASYN_CALLBACK = "openstack_loadbalance/instance/create_asyn_callback";
	String OPENSTACK_LOADBALANCE_INSTANCE_RECONFIG_ASYN_CALLBACK = "openstack_loadbalance/instance/reconfig_asyn_callback";
	String OPENSTACK_LOADBALANCE_INSTANCE_MONITOR_RULE_RECONFIG_ASYN_CALLBACK = "openstack_loadbalance/instance/monitor_rule/config_asyn_callback";
	//调用openstack出去的地址
	String LOADBALANCE_CREATE = "load_balancer/create";
	String LOADBALANCE_DELETE = "load_balancer/delete";
	String LOADBALANCE_START = "load_balancer/start";
	String LOADBALANCE_STOP = "load_balancer/stop";
	String LOADBALANCE_REBOOT = "load_balancer/reboot";
	String LOADBALANCE_RECONFIG = "load_balancer/reconfig";
	String LOADBALANCE_MONITOR_RULE = "load_balancer/monitor_rule/reconfig";
	String LOADBALANCE_MONITOR_RULE_ROLLBACK = "load_balancer/monitor_rule/rollback";
	String LOADBALANCE_METRICS = "load_balancer/metrics";
	String LOADBALANCE_MONITOR_THRESHOLD = "load_balancer/threshold";
	//openstack回调地址
	String OPENSTACK_LOADBALANCE_CREATE_CALLBACK = "callback/loadbalance/create_callback";
	String OPENSTACK_LOADBALANCE_RECONFIG_CALLBACK = "callback/loadbalance/reconfig_callback";
	String OPENSTACK_LOADBALANCE_MONITOR_RULE_RECONFIG_CALLBACK = "callback/loadbalance/monitor_rule/config_callback";
	//获取openstack实例对应的虚机
	String OPENSTACK_LOADBALANCE_INSTANCE_ARROWS = "openstack_loadbalance/instance/arrows";
}
