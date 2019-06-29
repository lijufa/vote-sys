package com.xinnet.log.vo;

import java.io.Serializable;

public class CustomerAuthInfoVo implements Serializable {
	private static final long serialVersionUID = 7231028343544219275L;
	Long id;//用户id
	String auth_type; //PERSONALNOCHINA  个人大陆PERSONALCHINA  个人非大陆TOCOMPANY（升级公司）COMPANY   公司
	String image_type; //图像类型
	String image; //图像
	String name;//姓名
	String id_card_number;
	String birthday;
	String address;
	String valid_date;//身份证有效期
	String issued_by;//发证机关
	String card_number;
	String audit_status;
	String audit_conclusions;
	String image_url;
	boolean three_certificates;//是否3证合一：true,false
	String str;//str=id&时间戳  并且是加密的

	public CustomerAuthInfoVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuth_type() {
		return auth_type;
	}

	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}

	public String getImage_type() {
		return image_type;
	}

	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId_card_number() {
		return id_card_number;
	}

	public void setId_card_number(String id_card_number) {
		this.id_card_number = id_card_number;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getValid_date() {
		return valid_date;
	}

	public void setValid_date(String valid_date) {
		this.valid_date = valid_date;
	}

	public String getIssued_by() {
		return issued_by;
	}

	public void setIssued_by(String issued_by) {
		this.issued_by = issued_by;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}

	public String getAudit_conclusions() {
		return audit_conclusions;
	}

	public void setAudit_conclusions(String audit_conclusions) {
		this.audit_conclusions = audit_conclusions;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public boolean isThree_certificates() {
		return three_certificates;
	}

	public void setThree_certificates(boolean three_certificates) {
		this.three_certificates = three_certificates;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return "CustomerAuthInfoVo [id=" + id + ", auth_type=" + auth_type + ", image_type=" + image_type + ", image=" + image + ", name=" + name + ", id_card_number=" + id_card_number
				+ ", birthday=" + birthday + ", address=" + address + ", valid_date=" + valid_date + ", issued_by=" + issued_by + ", card_number=" + card_number + ", audit_status=" + audit_status
				+ ", audit_conclusions=" + audit_conclusions + ", image_url=" + image_url + ", three_certificates=" + three_certificates + ", str=" + str + "]";
	}
}
