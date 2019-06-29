package com.xinnet.smart.mq;

public interface IQueues {
	/**
	 * 模块job消息队列
	 * @author meitao
	 * @date Sep 23, 2014 2:18:49 PM
	 * @return String
	 */
	String getJob();

	/**
	 * 模块task消息队列
	 * @author meitao
	 * @date Sep 23, 2014 2:18:49 PM
	 * @return String
	 */
	String getTask();

	/**
	 * 模块task返回消息队列
	 * @author meitao
	 * @date Sep 23, 2014 2:18:49 PM
	 * @return String
	 */
	String getTaskResult();

	/**
	 * 模块agent返回消息队列
	 * @author meitao
	 * @date Sep 23, 2014 2:18:49 PM
	 * @return String
	 */
	String getAgentResult();

	/**
	 * 模块回滚消息队列
	 * @author meitao
	 * @date Sep 23, 2014 2:18:49 PM
	 * @return String
	 */
	String getRollback();

	/**
	 * 模块回滚返回消息队列
	 * @author meitao
	 * @date Sep 23, 2014 2:18:49 PM
	 * @return String
	 */
	String getRollbackResult();
}
