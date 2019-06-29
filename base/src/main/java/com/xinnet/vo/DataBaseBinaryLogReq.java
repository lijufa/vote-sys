package com.xinnet.vo;

public class DataBaseBinaryLogReq {
	private Integer dbInstanceId;
	private Long dbInstanceUuid;
	private Long arrowUuid;
	private Integer instanceType;
	private Long customerId;
	private Long subAccountId;
	private String dbVersion;
	/**
	 * 实例存储网ip地址（从虚机存储网ip）
	 */
	private String storageIp;
	/**
	 * 实例管理网ip地址
	 */
	private String manageIp;
	private String lastestBinlogName;
	private Long hostId;//从虚机机host id
	private Long serverId;//云数据库备份服务器ID
	private String serverIp;//云数据库备份服务器IP
	private Integer port;//端口
	private String password;//数据库密码
	private String dbname;//数据名称
	private String dbInstanceName;//云数据库实例名称
	private String path;//备份路径
	private Double size;
	private Integer arrowNumber;//数据库实例对应的云主机数量 1 单机  2 双机
	private String binlog_pos;//数据库底层物理备份   pos点
	private String binlog_file;
	private String start_time;
	private String end_time;
	private String binlog_filesize;

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getBinlog_filesize() {
		return binlog_filesize;
	}

	public void setBinlog_filesize(String binlog_filesize) {
		this.binlog_filesize = binlog_filesize;
	}

	public Integer getDbInstanceId() {
		return dbInstanceId;
	}

	public void setDbInstanceId(Integer dbInstanceId) {
		this.dbInstanceId = dbInstanceId;
	}

	public Long getDbInstanceUuid() {
		return dbInstanceUuid;
	}

	public void setDbInstanceUuid(Long dbInstanceUuid) {
		this.dbInstanceUuid = dbInstanceUuid;
	}

	public Long getArrowUuid() {
		return arrowUuid;
	}

	public void setArrowUuid(Long arrowUuid) {
		this.arrowUuid = arrowUuid;
	}

	public Integer getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(Integer instanceType) {
		this.instanceType = instanceType;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSubAccountId() {
		return subAccountId;
	}

	public void setSubAccountId(Long subAccountId) {
		this.subAccountId = subAccountId;
	}

	public String getDbVersion() {
		return dbVersion;
	}

	public void setDbVersion(String dbVersion) {
		this.dbVersion = dbVersion;
	}

	public String getStorageIp() {
		return storageIp;
	}

	public void setStorageIp(String storageIp) {
		this.storageIp = storageIp;
	}

	public String getManageIp() {
		return manageIp;
	}

	public void setManageIp(String manageIp) {
		this.manageIp = manageIp;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbInstanceName() {
		return dbInstanceName;
	}

	public void setDbInstanceName(String dbInstanceName) {
		this.dbInstanceName = dbInstanceName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Integer getArrowNumber() {
		return arrowNumber;
	}

	public void setArrowNumber(Integer arrowNumber) {
		this.arrowNumber = arrowNumber;
	}

	public String getBinlog_file() {
		return binlog_file;
	}

	public void setBinlog_file(String binlog_file) {
		this.binlog_file = binlog_file;
	}

	public String getBinlog_pos() {
		return binlog_pos;
	}

	public void setBinlog_pos(String binlog_pos) {
		this.binlog_pos = binlog_pos;
	}

	public String getLastestBinlogName() {
		return lastestBinlogName;
	}

	public void setLastestBinlogName(String lastestBinlogName) {
		this.lastestBinlogName = lastestBinlogName;
	}
}
