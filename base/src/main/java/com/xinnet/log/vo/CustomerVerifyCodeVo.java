package com.xinnet.log.vo;

import java.io.Serializable;

public class CustomerVerifyCodeVo implements Serializable {
	private static final long serialVersionUID = 7231028343544219275L;
	String ver_type;//验证类型phone email
	String ver_code;//验证码
	String business_type;//业务状态（例如：FloatIpDel）

	public String getVer_type() {
		return ver_type;
	}

	public void setVer_type(String ver_type) {
		this.ver_type = ver_type;
	}

	public String getVer_code() {
		return ver_code;
	}

	public void setVer_code(String ver_code) {
		this.ver_code = ver_code;
	}

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
}
