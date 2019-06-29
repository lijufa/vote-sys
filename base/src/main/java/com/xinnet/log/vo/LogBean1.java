package com.xinnet.log.vo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class LogBean1 implements java.io.Serializable {
	java.lang.Long id;//主键id
	@JsonProperty("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date createTime;//生成时间
	@JsonProperty("user_id")
	java.lang.Long userId;//操作用户id
	@JsonProperty("user_name")
	java.lang.String userName;//操作用户名称
	@JsonProperty("customer_id")
	java.lang.Long customerId;//操作客户id
	@JsonProperty("ip_addr")
	java.lang.String ipAddr;//操作ip
	@JsonProperty("module_name")
	java.lang.String moduleName;//操作模块
	java.lang.String operation;
	java.lang.String parameter;
	String key;
	String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public java.lang.Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(java.lang.Long customerId) {
		this.customerId = customerId;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.Long getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(java.lang.String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public java.lang.String getModuleName() {
		return moduleName;
	}

	public void setModuleName(java.lang.String moduleName) {
		this.moduleName = moduleName;
	}

	public java.lang.String getOperation() {
		return operation;
	}

	public void setOperation(java.lang.String operation) {
		this.operation = operation;
	}

	public java.lang.String getParameter() {
		return parameter;
	}

	public void setParameter(java.lang.String parameter) {
		this.parameter = parameter;
	}

	public static String getColumnJson() {
		return "{\"content\":\"日志内容\"\"id\":\"主键id\"\"createTime\":\"生成时间\"\"logTypeId\":\"操作类型id\"\"userId\":\"操作用户id\"}";
	}
}