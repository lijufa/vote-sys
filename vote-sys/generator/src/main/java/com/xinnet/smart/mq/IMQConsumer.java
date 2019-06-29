package com.xinnet.smart.mq;

/**
 * MQ消费者
 * @author meitao
 * @date 2014-8-1 下午3:35:43
 */
public interface IMQConsumer {
	/**
	 * 销毁MQ客户端
	 * @author meitao
	 * @date Nov 7, 2014 5:54:13 PM
	 */
	void finalize();

	/**
	 * 初始化client，主要是起监听队列
	 * @author meitao
	 * @date Oct 15, 2014 2:43:02 PM
	 * @return boolean 初始化是否成功
	 */
	boolean init();

	/**
	 * 主要为了确保服务已经成功初始化或注入，因为监听队列就可能会收到消息，会调用相关服务
	 * @author meitao
	 * @date Oct 15, 2014 2:43:52 PM
	 * @return boolean 是否就绪
	 */
	boolean isPrepared();

	/**
	 * 监听队列
	 * @author meitao
	 * @date Oct 10, 2014 12:21:06 PM
	 * @param queue
	 * @return
	 */
	boolean listen(String queue);

	/**
	 * 处理接收到的消息
	 * @author meitao
	 * @date Dec 25, 2014 9:11:04 AM
	 * @param queue
	 * @param message
	 * @throws Throwable
	 */
	void onReceive(String queue, Object message) throws Throwable;
}
