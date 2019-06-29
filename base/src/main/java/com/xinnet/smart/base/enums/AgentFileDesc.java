package com.xinnet.smart.base.enums;

public enum AgentFileDesc {
	isExists(1, "本地文件存在"),
	notExistsNeedDelete(2, "本地文件不存在,缓存目录不够用需要删除镜像和备份后缓存够用"), 
	notExistsNeedDownload(3, "本地文件不存在,缓存目录够用下载"),
	otherError(4,"其他错误"),
	notExistsNeedDeleteBackup(5, "本地文件不存在,缓存目录不够用需要删除备份后缓存够用"),
	notExistsNeedUsedAsDisk(6,"本地文件不存在,缓存目录不够用直接使用作为磁盘使用");
	private int id;
	private String desc;

	AgentFileDesc(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setStatus(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
