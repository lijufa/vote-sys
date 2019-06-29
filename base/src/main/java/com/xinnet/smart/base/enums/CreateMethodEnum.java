package com.xinnet.smart.base.enums;

/**
 * //镜像拷贝类型(0 镜像创建快照盘;1 镜像全盘拷贝;2 备份创建增量创建;3 备份创建全量创建)
 * @author lenovo
 * @date 2016年5月20日 下午1:05:44
 */
public enum CreateMethodEnum {
	IMAGE_INCREMENT(0, "镜像创建快照盘"), IMAGE_FULL(1, "镜像全盘拷贝"), BACKUP_INCREMENT(2, "备份创建增量创建"), BACKUP_FULL(3, "备份创建全量创建");
	private final Integer create_method;
	private final String desc;

	CreateMethodEnum(Integer create_method, String desc) {
		this.create_method = create_method;
		this.desc = desc;
	}

	public Integer getCreate_method() {
		return create_method;
	}

	public String getDesc() {
		return desc;
	}
}
