package com.xinnet.log.vo;

import java.io.Serializable;

public class CustomerVerifyCodeSendVo implements Serializable {
	private static final long serialVersionUID = 7231028343544219275L;
	Integer type;//类型 0 手机 1 邮箱
	String phone;//手机
	String email;//邮箱
	String business_type;//业务状态（例如：FloatIpDel）

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
