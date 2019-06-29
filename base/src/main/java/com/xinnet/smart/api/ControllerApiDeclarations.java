package com.xinnet.smart.api;

/**
 * 云主机常量表
 * @author meitao
 * @date Nov 26, 2014 3:05:11 PM
 */
public interface ControllerApiDeclarations {
	String BUSINESS_ID = "businessId";
	String ARROW_ = "instance/";
	String ARROW_CREATE = "instance/create";
	String ARROW_RECONFIG = "instance/reconfig";
	String ARROW_RECONFIGNETWORK = "instance/reconfigNetWork";
	String ARROW_VNC = "instance/vnc";
	String ARROW_REBUILD = "instance/rebuild";
	String ARROW_RESET_AUTH = "instance/resetAuth";
	String ARROW_RESET_PWD = "instance/resetPwd";
	String ARROW_RESET_KEY = "instance/resetKey";
	String ARROW_IP = "network/ip/listByArrow";
	String SG_EXTEND = "sg/extend";
	String SG_REDUCE = "sg/reduce";
	String SG_UNDEFINE = "sg/undefine";
	String SG_ADD_POLICY = "sg/addPolicy";
	String SG_DEL_POLICY = "sg/delPolicy";
	String SG_ADD_IP_MAC = "sg/addIpMac";
	String SG_DEL_IP_MAC = "sg/delIpMac";
	String BACKUP_LIST = "backup/list";
	String BACKUP_CREATE = "backup/create";
	String BACKUP_MANUAL_CREATE = "backup/create";
	//String BACKUP_CREATE_INCREMENT = "backup/createIncrement";
	String BACKUP_DELETE = "backup/delete";
	String DELETE_BACKUP = "backup/delete";
	String DELETE_BACKUP_ARROW_DELETE = "backup/deleteWithArrowDelete";
	String ARROW_RECOVER = "instance/recover";
	String BACKUP_QUERYBACKUP_SIZE = "backup/querySize";
	String BACKUP_QUERYBACKUP_DELETE_PROCESS = "backup/queryDeleteProcess";
	String WHITE_LIST_CREATE = "network/sg/whiteListPortStart";
	String LB_WHITE_LIST_CREATE = "network/sg/lbwhiteListPortStart";
	String LB_WHITE_LIST_DELETE = "network/sg/lbwhiteListPortClose";
	String LB_MONITORDATA = "loadbalance/getLoadBalanceMonitorData";
	String DB_MONITORDATA = "database/monitor/getDbMonitorData";
	String LB_MONITORDATAHEALTH = "loadbalance/getLoadBalanceMonitorDataHealth";
	String WHITE_LIST_DELETE = "network/sg/whiteListPortClose";
	String WHITE_LIST_SYNC = "network/sg/whiteListPortSync";
	String ARROW_RESET_NETWORK = "instance/resetNetwork";
	String CLEAR_VNC_URL = "instance/clearVncUrl";
	String CREATE_VIRTUAL_IP = "virtualip/queryVip";
	String DELETE_VIRTUAL_IP = "virtualip/deleteVip";
	String UPDATE_VIRTUAL_IP = "virtualip/updateVip";
	String ARROW_AND_NET_WORK_RESET = "instance/reconfigArrowAndNetWork";
	String LOADBALANCE_ = "loadbalance/";
	String LB_ADDBINDOPARROW = "loadbalance/addbindoparrow";
	String LB_DELETEBINDOPARROW = "loadbalance/deletebindoparrow";
	String LB_UPDATEBINDOPARROW = "loadbalance/updatebindoparrow";
	String LB_UPDATELOADBALANCERULE = "loadbalance/updateloadbalancerule";
	String INSTANCE_ARROW_SPEED = "instance/arrowSpeed";
	String INSTANCE_LIMIT_DISK_SPEED = "instance/limitDiskSpeed";
	String INSTANCE_LIMIT_CPU_SPEED = "instance/limitCpuSpeed";
	String FLOATIP_BIND = "float_ip/bind";
	String FLOATIP_UNBIND = "float_ip/unbind";
	String FLOATIP_RECONFIG = "float_ip/reconfig";
}
