package com.xinnet.vo;

public class BinaryLogBackupDeletionReq {
	private String serverIp;//云数据库备份服务器IP
	private String binlogPaths;//删除文件的路径，以空格分割

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getBinlogPaths() {
		return binlogPaths;
	}

	public void setBinlogPaths(String binlogPaths) {
		this.binlogPaths = binlogPaths;
	}
}
