package com.xinnet.smart.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 通用返回对象格式
 * 
 * @author meitao
 * @date 2013-12-12
 */
public class GenericResponse {
	static final String SUCCESS = "success";
	private boolean success = false;// 是否成功
	@JsonProperty("response")
	private String response;// 返回提示信息
	@JsonProperty("code")
	private String msgCode;// 内部提示信息
	@JsonProperty("message")
	private String msgInfo;// 外部提示信息
	@JsonIgnore
	private String cache;
	@JsonIgnore
	boolean recordLog = true;
	String result;
	String returndata;

	public GenericResponse() {
	}

	public GenericResponse(boolean success, String msgCode, String msgInfo) {
		this.success = success;
		this.msgCode = msgCode;
		this.msgInfo = msgInfo;
	}

	public boolean isSuccess() {
		return success;
	}

	public boolean notSuccess() {
		return !success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public static boolean isSuccess(GenericResponse r) {
		return r != null && r.success;
	}

	public static boolean notSuccess(GenericResponse r) {
		return r == null || !r.success;
	}

	public void init(GenericResponse r) {
		this.success = r.success;
		this.msgCode = r.msgCode;
		this.msgInfo = r.msgInfo;
	}

	public GenericResponse success() {
		this.success = true;
		this.msgCode = "success";
		this.msgInfo = SUCCESS;
		return this;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public final String getMsgInfo() {
		return msgInfo;
	}

	public final void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public static String getSuccess() {
		return SUCCESS;
	}

	final String getCache() {
		return cache;
	}

	final void setCache(String cache) {
		this.cache = cache;
	}

	public final boolean getRecordLog() {
		return recordLog;
	}

	public final void setRecordLog(boolean recordLog) {
		this.recordLog = recordLog;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
