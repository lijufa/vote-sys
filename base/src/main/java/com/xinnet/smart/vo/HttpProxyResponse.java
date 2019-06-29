package com.xinnet.smart.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import com.xinnet.smart.base.util.UTrace;

/**
 * 通用返回对象格式
 * @author meitao
 * @date 2013-12-12
 */
public class HttpProxyResponse extends GenericResponse {
	static final String PROXY_ERROR = "proxy_error";
	@JsonIgnore
	String url;
	@JsonIgnore
	String data;//param
	@JsonIgnore
	String result;
	String errorMessage;

	public final String getErrorMessage() {
		return errorMessage;
	}

	public final void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public final void error(Throwable e) {
		this.errorMessage = UTrace.trace(e);
	}

	public final String getResult() {
		return result;
	}

	public final void setResult(String result) {
		this.result = result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
