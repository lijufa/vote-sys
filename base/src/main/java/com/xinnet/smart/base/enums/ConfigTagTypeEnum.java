package com.xinnet.smart.base.enums;

public enum ConfigTagTypeEnum {
	HIGHCPU(1, "高配cpu"),
	HIGHMEMORY(2, "高配内存"), 
	SSDDISK(3, "高配硬盘"), 
	BALANCECONFIG(4,"均衡配置"),
	COMMONCPU(5, "普通cpu"), 
	COMMONMEMORY(6, "普通内存"), 
	COMMONDISK(7, "普通硬盘"); 
	private Integer id;
	private String desc;

	private ConfigTagTypeEnum(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public static ConfigTagTypeEnum parseId(Integer id) {
		if (id != null) {
			ConfigTagTypeEnum[] values = ConfigTagTypeEnum.values();
			for (ConfigTagTypeEnum value : values) {
				if (value.id.intValue() == id.intValue()) {
					return value;
				}
			}
		}
		return null;
	}
}
