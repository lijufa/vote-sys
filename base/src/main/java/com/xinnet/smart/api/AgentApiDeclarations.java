package com.xinnet.smart.api;

/**
 * 云主机常量表
 * @author meitao
 * @date Nov 26, 2014 3:05:11 PM
 */
public interface AgentApiDeclarations {
	String SUCCESS = "success";
	String FAIL = "fail";
	String VOLUME = "volume";
	String VOLUME_PATH = "volumePoolMountPath";
	String S_FAILED_TO_UNLOCK_HOST_RESOURCES = "failed to unlock host.";
	//------------------ send agent -------------------------
	String NAME = "name";
	//------------------ image api -------------------------
	String IMAGE_ADDVMREL = "api/image/addvmrel";
	String IMAGE_DELVMREL = "api/image/deletevmrel";
	//------------------ agent api
	String AGENT_INSTANCE_DEFINE = "instance/define";
	String AGENT_INSTANCE_UNDEFINE = "instance/undefine";
	String AGENT_INSTANCE_CREATE = "instance/create";
	String AGENT_INSTANCE_SHUTDOWN = "instance/shutdown";
	String AGENT_INSTANCE_DESTROY = "instance/destroy";
	String AGENT_INSTANCE_REBOOT = "instance/reboot";
	String AGENT_INSTANCE_RESET = "instance/reset";
	String AGENT_INSTANCE_FREEZE = "instance/freeze";
	String AGENT_INSTANCE_VNC = "instance/vnc";
	String AGENT_IMAGE_GET = "image/getimage";
	String AGENT_IMAGE_DELHISTORY = "image/deleteHistory";
	String GET_HOST_ACTION = "monitor/host/get";
	String GET_VM_ACTION = "monitor/vm/get";
	String GET_VMSTAT_ACTION = "monitor/vmstat/get";
	String WHITE_LIST_CREATE = "whiteList/whiteListPortStart";
	String WHITE_LIST_DELETE = "whiteList/whiteListPortClose";
	String WHITE_LIST_SYNC = "whiteList/whiteListPortSync";
	String MANUAL_CREATE = "backup/create";
	String BACKUP_DELETE = "backup/delete";
	String INSTANCE_RECOVER = "instance/recover";
	String BACKUP_QUERYBACKUP_SIZE = "backup/querySize";
	String BACKUP_QUERYBACKUP_FILE_SIZE = "backup/queryDiskSize";
	String BIND_IP_MAC = "isolationStrategies/bind";
	String UNBIND_IP_MAC = "isolationStrategies/unbind";
	String add_Host_Strategy = "isolationStrategies/addHostStrategy";
	String ARROW_FLOATIP_BIND = "float_ip/bind";
	String ARROW_FLOATIP_UNBIND = "float_ip/unbind";
	//安全组
	String SYNC_SECURITY_GROUP_BY_CUSTOMER = "sg/syncSecurityGroupByCustomer";//应有的生产机检查、同步
	String ONLY_SYNC_SECURITY_GROUP = "sg/onlySyncSecurityGroupByCustomer";//只同步
	String SYNC_OTHER_SECURITY_GROUP_BY_CUSTOMER = "sg/syncOtherSecurityGroupByCustomer";//其他的生产机检查、同步
	String ADD_HOST_POLICY = "sg/addHostPolicy";
	String DEL_HOST_POLICY = "sg/delHostPolicy";
	String ADD_POLICY = "sg/addPolicy";
	String DEL_POLICY = "sg/delPolicy";
	String ADD_IP_MAC = "sg/addIpMac";
	String DEL_IP_MAC = "sg/delIpMac";
	String ADD_PASS_IP = "sg/addPassIp";
	String DEL_PASS_IP = "sg/delPassIp";
	String SYNC_SECURITY_GROUP_CALLBACK = "security_group/syncSecurityGroupByCustomerCallback";
	//云数据库API
	String AGENT_DB_INSTANCE_DEFINE = "database/instance/define";
	String AGENT_DB_INSTANCE_UNDEFINE = "database/instance/undefine";
	String AGENT_DB_INSTANCE_REBOOT = "database/instance/reboot";
	String AGENT_DB_INSTANCE_SYNC = "database/instance/sync";
	String AGENT_DB_INSTANCE_SYNC_57 = "database/instance/master_slave_sync";
	String AGENT_DB_CHECK_SERVER = "database/instance/checkServer";
	String AGENT_DB_CHECK_SYNC = "database/instance/checkSync";
	String AGENT_DB_DISK_SIZE = "database/instance/checkDiskSize";
	String AGENT_DB_DELETE_DELAY_BACKUP = "database/instance/deleteDelayBackup";
	String AGENT_DB_DATABASE_CREATE = "database/create";
	String AGENT_DB_DATABASE_SYNC = "database/syncDatabaseByFloor";
	String AGENT_DB_DATABASE_ACCOUNT_SYNC = "database/account/syncDatabaseAccountByFloor";
	String AGENT_DB_DATABASE_DELETE = "database/delete";
	String AGENT_DB_DATABASE_CREATE_CALLBACK = "database/createCallback";
	String AGENT_DB_DATABASE_DELETE_CALLBACK = "database/deleteCallback";
	String AGENT_DB_DATABASE_SYNC_CALLBACK = "database/syncCallback";
	String AGENT_DB_DATABASE_ACCOUNT_SYNC_CALLBACK = "database/account/syncCallback";
	String AGENT_DB_DATABASE_BACKUP_CREATE_CALLBACK = "database/backupCreateCallback";
	String AGENT_DB_DATABASE_BACKUP_DELETE_CALLBACK = "database/backupDeleteCallback";
	String AGENT_DB_DATABASE_BACKUP_RECOVER_CALLBACK = "database/backupRecoverCallback";
	String AGENT_DB_DATABASE_ACCOUNT_CREATE = "database/account/create";
	String AGENT_DB_DATABASE_ACCOUNT_RECREATE = "database/account/recreate";
	String AGENT_DB_DATABASE_ACCOUNT_CREATE_CALLBACK = "database/account/createCallback";
	String AGENT_DB_DATABASE_ACCOUNT_DELETE = "database/account/delete";
	String AGENT_DB_DATABASE_ACCOUNT_RESET_PASSWORD = "database/account/reset_password";
	String AGENT_DB_DATABASE_ACCOUNT_RESET_PASSWORD_CALLBACK = "database/account/resetPasswordCallback";
	String AGENT_DB_DATABASE_ACCOUNT_AUTHORIZATION = "database/account/authorization";
	String AGENT_DB_DATABASE_ACCOUNT_AUTHORIZATION_CALLBACK = "database/account/authorizationCallback";
	String AGENT_DB_DATABASE_WHITE_LIST_ADD = "database/white_list/add";
	String AGENT_DB_DATABASE_WHITE_LIST_DELETE = "database/white_list/delete";
	String AGENT_DB_INSTANCE_RECONFIG = "database/instance/reconfigDbInstance";
	String AGENT_DB_DATABASE_BACKUP_CREATE_BINARY_LOG_CALLBACK = "database/createBinaryLogBackupCallback";
	//负载均衡api
	String AGENT_LB_INSTANCE_DEFINE = "loadbalance/define";
	String AGENT_LB_INSTANCE_UNDEFINE = "loadbalance/undefine";
	String AGENT_LB_INSTANCE_START = "loadbalance/start";
	String AGENT_LB_INSTANCE_SHUTDOWN = "loadbalance/shutdown";
	String AGENT_LB_INSTANCE_REBOOT = "loadbalance/reboot";
	String AGENT_LB_UPLOAD_CERTIFICATE = "loadbalance/uploadCertificate";
	String AGENT_LB_BINDARROW = "loadbalance/bindArrow";
	String AGENT_LB_DELBINDARROW = "loadbalance/delbindArrow";
	String AGENT_LB_UPDATEBINDARROW = "loadbalance/updatebindArrow";
	String AGENT_LB_UPDATEBINDARROWWEIGHT = "loadbalance/updatebindArrowWeight";
	String AGENT_LB_ARROWHEALTHSTATUS = "loadbalance/getMonitorArrowHealthStatus";
	String AGENT_LB_INSTANCE_RECONFIG = "loadbalance/reconfigLbInstance";
	String AGENT_LB_INSTANCE_RECONFIG_BANDWIDTH = "loadbalance/reconfigLbInstanceBandWidth";
	String AGENT_LB_MASTERORSLAVESWITCH = "loadbalance/masterOrSlaveSwitch";
	String AGENT_DB_INSTANCE_AUTO_SWITCH = "database/instance/auto/switch";
	String AGENT_DB_INSTANCE_MANUAL_SWITCH = "database/instance/manual/switch";
}
