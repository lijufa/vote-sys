package com.xinnet.exception;

/**
 * 跨域调用异常，主调用方 非200
 */

public class RpcException extends BaseException {

	private static final long serialVersionUID = 1L;

	public RpcException(String msgKey) {
		super(msgKey);
	}

}
