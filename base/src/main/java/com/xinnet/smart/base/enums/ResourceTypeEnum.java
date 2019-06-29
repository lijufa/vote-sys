package com.xinnet.smart.base.enums;

public enum ResourceTypeEnum {
	ARROW(1, "ARROW"), LOADBALANCE(2, "负载均衡"), DATABASE(3, "数据库");
	private final Integer id;
	private final String title;

	ResourceTypeEnum(Integer id, String title) {
		this.id = id;
		this.title = title;
	}

	public final Integer getId() {
		return id;
	}

	public final String getTitle() {
		return title;
	}

	public static final ResourceTypeEnum parse(Integer id) {
		if (id != null) {
			ResourceTypeEnum[] values = values();
			for (ResourceTypeEnum value : values) {
				if (value.id.intValue() == id.intValue()) {
					return value;
				}
			}
		}
		return null;
	}
}
