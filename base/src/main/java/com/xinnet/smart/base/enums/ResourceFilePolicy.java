package com.xinnet.smart.base.enums;

public enum ResourceFilePolicy {
	/**
	 * 文件存在,不用下载
	 */
	Exists(1, "文件存在,不用下载"),
	/**
	 * 可以下载(文件不存在,缓存目录够用)
	 */
	AcceptDownload(3, "可以下载(文件不存在,缓存目录够用)"),
	/**
	 * 验证使用进程时间超时,其他错误
	 */
	otherError(4, "其他错误"),
	/**
	 *直接下载作为磁盘使用(文件不存在,缓存目录不够用)
	 */
	AcceptDownloadAsDisk(6, "直接下载作为磁盘使用(文件不存在,缓存目录不够用)"),
	/**
	 * 空间不够,要继续删除
	 */
	SpaceNotEnough(7, "空间不够,要继续删除");
	private int id;
	private String desc;

	ResourceFilePolicy(int id, String desc) {
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
