package com.xinnet.smart.vo;

public class GenericShellResponse extends GenericResponse {
	Integer code;
	String result;
	String errorMessage;

	public final Integer getCode() {
		return code;
	}

	public final void setCode(Integer code) {
		if (code == 0) {
			success();
		}
		this.code = code;
	}

	public final String getResult() {
		return result;
	}

	public final void setResult(String result) {
		this.result = result;
	}

	public final String getErrorMessage() {
		return errorMessage;
	}

	public final void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
