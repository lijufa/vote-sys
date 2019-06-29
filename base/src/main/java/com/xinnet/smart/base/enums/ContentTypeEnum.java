package com.xinnet.smart.base.enums;

public enum ContentTypeEnum {
	JSON("application/json; charset=UTF-8"), URLENCODED("application/x-www-form-urlencoded"), MULTIPART("multipart/form-data");
	final String value;

	ContentTypeEnum(String value) {
		this.value = value;
	}

	public final String getValue() {
		return value;
	}
}
