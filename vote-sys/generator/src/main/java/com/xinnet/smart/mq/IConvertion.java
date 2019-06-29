package com.xinnet.smart.mq;

/**
 * 消息协议之对象转换
 * @author meitao
 * @date Oct 10, 2014 9:17:47 AM
 */
public interface IConvertion {
	/**
	 * 发送前转换为byte数组
	 * @author meitao
	 * @date Oct 10, 2014 9:17:59 AM
	 * @param Object object
	 * @return byte[]
	 */
	byte[] toBytes(Object object);

	/**
	 * 接收后转换为对象
	 * @author meitao
	 * @date Oct 10, 2014 9:18:38 AM
	 * @param byte[] bytes
	 * @return Object
	 */
	Object toObject(byte[] bytes);
}
