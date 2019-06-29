package com.xinnet.smart.base.enums;

public enum BackupRecord {
	downloading(1, "下载中"), downloaded(2, "下载完成"), downloadFailed(3, "下载失败");
	private int status;
	private String desc;

	BackupRecord(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
