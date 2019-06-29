package com.xinnet.exception;

import com.xinnet.smart.base.PropertiesBase;

public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;
	private String msgKey;

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	/**
	 * 获取消息控制器开关 true=打开 提示可以应为"system_busy" false=关闭
	*/
	public static boolean getMsgController() {
		return PropertiesBase.msgController;
	}

	public BaseException(String msgKey) {
		super(msgKey);
		this.msgKey = msgKey;
	}

	@Override
	public String toString() {
		return "exception  ==>  Key:" + msgKey + ",Msg:" + ResponseMessage.getMessage(msgKey);
	}
}
