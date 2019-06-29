package com.xinnet.smart.mq;

import com.xinnet.smart.mq.impl.ConvertionImpl;

/**
 * MQ发送器
 * @author meitao
 * @date 2014-8-1 下午3:35:43
 */
public interface IMQSender {
	IConvertion CONVERTION = new ConvertionImpl();

	/**
	 * 发送消息到指定队列
	 * @author meitao
	 * @date Nov 26, 2014 2:16:51 PM
	 * @param queue
	 * @param message
	 * @return boolean
	 */
	boolean send(String queue, Object message);

	/**
	 * 发送消息到指定队列
	 * @author meitao
	 * @date Nov 26, 2014 2:17:21 PM
	 * @param queue
	 * @param message
	 * @param isPassive 是否用已有的队列
	 * @return boolean
	 */
	boolean send(String queue, Object message, boolean isPassive);

	void close();
}
