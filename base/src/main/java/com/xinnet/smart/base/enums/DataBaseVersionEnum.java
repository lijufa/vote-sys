package com.xinnet.smart.base.enums;

public enum DataBaseVersionEnum {
	MYSQL56(1), MYSQL57(2), MYSQL57DOUBLE(3);
	private int uuid;

	DataBaseVersionEnum(int uuid) {
		this.uuid = uuid;
	}

	public int getUUID() {
		return uuid;
	}
}
