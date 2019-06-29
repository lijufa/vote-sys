package com.xinnet.smart.api;

/**
 * 云主机常量表
 * @author meitao
 * @date Nov 26, 2014 3:05:11 PM
 */
public interface OpenstackControllerApiDeclarations {
	String OPENSTACK = "openstack_arrow/";
	String OPENSTACK_ARROW = "openstack_arrow/instance/";
	String OPENSTACK_ARROW_INSTANCE_CREATE = OPENSTACK_ARROW + "create";
	String OPENSTACK_ARROW_RECONFIG = OPENSTACK_ARROW + "reconfig";
	String OPENSTACK_ARROW_RECONFIG_CALLBACK = "instance/callback/reconfig";
	String OPENSTACK_ARROW_RECOVER = OPENSTACK_ARROW + "recover";
	String OPENSTACK_ARROW_RECOVER_CALLBACK = "instance/callback/recover";
	String OPENSTACK_BACKUP_CREATE = OPENSTACK_ARROW + "backup/create";
	String INSTANCE_OPERATE = "instances/actions/";
	String OPENSTACK_MONITOR = "monitor/arrow";
	String OPENSTACK_MONITOR_THRESHOLD = "monitor/threshold";
	String INSTANCE_METRICS = "instances/metrics";
	String INSTANCE_OPERATE_CALLBACK = "callback/arrow/actions/";
	String INSTANCE_CREATE = "instances/images";
	String INSTANCE_REBUILD = "instances/actions/rebuild";
	String OPENSTACK_ARROW_INSTANCE_CREATE_CALLBACK = "openstack_arrow/instance/create_callback";
	String OPENSTACK_ARROW_INSTANCE_CREATE_ASYN_CALLBACK = "openstack_arrow/instance/create_asyn_callback";
	String OPENSTACK_BACKUP_CALLBACK = "backup/callback/create";
	String OPENSTACK_BACKUP_DELETE = OPENSTACK_ARROW + "backup/delete";
	String OPENSTACK_BACKUP_DELETE_CALLBACK = "backup/callback/delete";
	String OPENSTACK_ARROW_REBUILD = OPENSTACK_ARROW + "rebuild";
	String OPENSTACK_INSTANCE_REBUILD_ASYN_CALLBACK = "openstack_arrow/instance/rebuild_asyn_callback";
	String OPENSTACK_ARROW_OPENIPV6 = OPENSTACK_ARROW + "open_ipv6";
	String OPENSTACK_ARROW_OPENIPV6_CALLBACK = "callback/arrow/actions/open_ipv6";
	String OPENSTACK_ARROW_RESET_NETWORK = OPENSTACK_ARROW + "resetNetwork";
	String OPENSTACK_INSTANCE_RESET_NETWORK = "instances/reset_network";
	String OPENSTACK_GET_TOKEN = OPENSTACK + "user/create";
	String OPENSTACK_USER_ARROW_STATUS = OPENSTACK + "arrowStatus";
	String OPENSTACK_FLOATIP_CREATE = OPENSTACK_ARROW + "float/create";
	String OPENSTACK_FLOATIP_DELETE = OPENSTACK_ARROW + "float/delete";
	String OPENSTACK_FLOATIP_RECONFIG = OPENSTACK_ARROW + "float/reconfig";
	String OPENSTACK_FLOATIP_CREATE_CALLBACK = "floatip/callback/create";
	String OPENSTACK_FLOATIP_BIND_CALLBACK = "floatip/callback/bind";
	//调用openstack出去的地址
	String GET_TOKEN_OUT = "users/create";
	String SECOND_GET_TOKEN_OUT = "users/create_user_environment_data";
	String ARROW_RECONFIG_OUT = "instances/reconfig";
	String ARROW_RECOVER_OUT = "instances/recover";
	String BACKUP_CREATE_OUT = "instances/create_instance_backup";
	String OPEN_IPV6_OUT = "instances/arrow/open_ipv6";
	String BACKUP_DELETE_OUT = "instances/delete_instance_backup";
	String OPENSTACK_INSTANCE_REBUILD_CALLBACK = "callback/instance/rebuild_callback";
	String OPENSTACK_INSTANCE_CREATE_CALLBACK = "callback/instance/create_callback";
	String ARROW_LIST_OUT = "instances/instance_list";
	String CREATE_FLOATIP_OUT = "floatingips/create";
	String DELETE_FLOATIP_OUT = "floatingips/delete";
	String RECONFIG_FLOATIP_OUT = "floatingips/reconfig";
	//浮动ip
	String FLOATINGIPS_BIND = "floatingips/bind";
	String FLOATINGIPS_UNBIND = "floatingips/unbind";
}
