package com.xinnet.smart.base.enums;

import com.xinnet.smart.base.util.UEnum;

/**
 * 实例创建状态
 */
public enum ProcessCheckResultEnum {
	CREATE(1, "创建"), SUCCESS(2, "成功"), FAIL(4, "失败");
	private Integer id;
	private String desc;

	ProcessCheckResultEnum(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public static final ProcessCheckResultEnum parse(Integer id) {
		if (id != null) {
			ProcessCheckResultEnum[] values = values();
			for (ProcessCheckResultEnum value : values) {
				if (value.id.intValue() == id.intValue()) {
					return value;
				}
			}
		}
		return null;
	}

	public static final Integer toId(String value) {
		ProcessCheckResultEnum actionState = UEnum.parse(ProcessCheckResultEnum.values(), value);
		if (actionState == null) {
			return null;
		}
		return actionState.getId();
	}
}
