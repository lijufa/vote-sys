package com.xinnet.smart.api;

/**
 * 云数据库API常量表
 */
public interface DataBaseControllerApiDeclarations {
	String DATABASE_INSTANCE_CREATE = "database/instance/create";
	String DATABASE_INSTANCE_DELETE = "database/instance/deleteArrow";//删除云数据库实例对应虚机
	String DATABASE_INSTANCE_RECONFIG = "database/instance/reconfigDbInstance";//变更云数据库配置
	String DATABASE_VALIDATE_RECONFIG = "database/instance/reconfigValidateResource";//变更云数据库校验资源
	String DATABASE_INSTANCE_SYNC_DB = "database/instance/syncDb";
	String DATABASE_INSTANCE_UNDEFINE_ARROW = "database/instance/undefineArrow";
	String INSTANCE_REBOOT = "database/instance/reboot";
	String DATABASE_CHECK_SERVER = "database/instance/checkServer";//检测云数据库实例主备服务状态
	String DATABASE_CHECK_SYNC = "database/instance/checkSync";//检测云数据库实例主备同步状态
	String DATABASE_DISK_SIZE = "database/instance/checkDiskSize";//检测云数据库实例磁盘占用容量
	String DATABASE_DELETE_DELAY_BACKUP = "database/instance/deleteDelayBackup";//删除延期备份
	String DATABASE_CREATE = "database/create";
	String DATABASE_BACKUP_CREATE = "database/backup/create";
	String DATABASE_SYNC_DATABASE_BY_FLOOR = "database/syncDatabaseByFloor";//同步底层数据库
	String DATABASE_SYNC_DATABASE_ACCOUNT_BY_FLOOR = "/database/account/syncDatabaseAccountByFloor";//同步底层数据库账号
	String DATABASE_BACKUP_DELETE = "database/backup/delete";
	String DATABASE_BACKUP_DELETEPATH = "database/backup/deletePath";
	String DATABASE_BACKUP_RECOVER = "database/backup/recover";
	String DATABASE_BACKUPPATH_SIZE = "database/instance/queryPathSize";
	String DATABASE_DELETE = "database/delete";
	String DATABASE_ACCOUNT_CREATE = "database/account/create";
	String DATABASE_ACCOUNT_RECREATE = "database/account/recreate";
	String DATABASE_ACCOUNT_DELETE = "database/account/delete";
	String DATABASE_ACCOUNT_RESET_PASSWORD = "database/account/reset_password";
	String DATABASE_ACCOUNT_AUTHORIZATION = "database/account/authorization";
	String DATABASE_WHITE_LIST_ADD = "database/white_list/add";
	String DATABASE_WHITE_LIST_DELETE = "database/white_list/delete";
	String DATABASE_BINARY_LOG_BACKUP_DELETE_PATH = "database/backup/binary/log/delete";
	String DATABASE_BINARY_LOG_BACKUP_CREATE_PATH = "database/backup/binary/log/create";
	//callback
	String API_INSTANCE_CALLBACK = "database/instance/callback";
	String API_INSTANCE_RECONFIG_CALLBACK = "database/instance/reconfigCallback";
	String API_INSTANCE_AUTHRESET_CALLBACK = "database/instance/authResetCallback";
	String API_INSTANCE_CREATE_CALLBACK = "database/instance/create/updateState";
	String API_INSTANCE_CREATE_SYNC_DATABASE = "database/instance/syncDbCallback";
	String API_INSTANCE_CLONE_CALLBACK = "database/instance/clone/update/state";
	final String DATABASE_BACKUP_PATH = "/smart/database/backup/";
	String DATABASE_INSTANCE_CLOSE_INTER_IP = "database/instance/closeInterIp";//关闭或开启外网
	String DATABASE_INSTANCE_CHANGE_DB_PORT = "database/instance/changeDbPort";//修改数据库实例端口
	String DATABASE_INSTANCE_CHANGE_SYNCH_MODE = "database/instance/changeSynch";//修改同步方式
	String DATABASE_INSTANCE_OPEN_INTER_IP = "database/instance/openInterIp";//开通外网
	String DATABASE_INSTANCE_CHANGE_DB_PORT_CALLBACK = "database/instance/changeDbPortCallback";//修改数据库实例端口
	String DATABASE_INSTANCE_CHANGE_SYNCH_MODE_CALLBACK = "database/instance/changeSynchCallback";//修改同步方式
	String DATABASE_INSTANCE_BACKUP_BINARY_LOG = "database/instance/binarylog/backup";//二进制日志备份
	String DATABASE_INSTANCE_CLONE = "database/instance/clone";//物理备份集克隆  按时间点克隆
	String DATABASE_INSTANCE_AUTO_SWITCH = "database/instance/auto/switch";//主动调用自动切换功能
	String DATABASE_INSTANCE_AUTO_SWITCH_CALLBACK = "database/instance/auto/switch/callBack";//自动切换
	String DATABASE_INSTANCE_MANUAL_SWITCH = "database/instance/manual/switch";//手动切换
	String DATABASE_INSTANCE_MANUAL_SWITCH_CALLBACK = "database/instance/manual/switch/callBack";//手动切换
	String DATABASE_INSTANCE_KEEPALIVED_CALLBACK = "database/instance/keepalived/callBack";//自动切换
	String DATABASE_INSTANCE_CREATE_BINARY_LOG_BACKUP_FAILURE = "database/instance/backup/createBinaryLogFailure";//备份二进制日志失败时删除文件
}
