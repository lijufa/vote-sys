package com.xinnet.smart.base.enums;

import com.xinnet.smart.base.util.UEnum;

public enum FloatIpActionStateEnum {
	NONE(0, "可用"),
	CREATE(1, "创建中"), 
	BINDING(2, "绑定中"),
	BINDED(3,"已绑定"),
	UNBINDING(4, "解绑中");
	private Integer id;
	private String desc;

	private FloatIpActionStateEnum(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public static final FloatIpActionStateEnum parse(Integer id) {
		if (id != null) {
			FloatIpActionStateEnum[] values = values();
			for (FloatIpActionStateEnum value : values) {
				if (value.id.intValue() == id.intValue()) {
					return value;
				}
			}
		}
		return null;
	}

	public static final Integer toId(String value) {
		FloatIpActionStateEnum actionState = UEnum.parse(FloatIpActionStateEnum.values(), value);
		if (actionState == null) {
			return null;
		}
		return actionState.getId();
	}
}
