package com.xinnet.smart.mq.impl;

import com.xinnet.smart.mq.IQueues;
import com.xinnet.smart.mq.QueuePrefixEnum;

public class QueuesImpl implements IQueues {
	final String job;
	final String task;
	final String taskResult;
	final String agentResult;
	final String rollback;
	final String rollbackResult;

	public QueuesImpl(String moduleName) {
		this.job = QueuePrefixEnum.JOB.name();
		this.task = QueuePrefixEnum.TASK.name() + moduleName;
		this.taskResult = QueuePrefixEnum.TASK_RESULT.name();
		this.agentResult = QueuePrefixEnum.AGENT_RESULT.name() + moduleName;
		this.rollback = QueuePrefixEnum.ROLLBACK.name() + moduleName;
		this.rollbackResult = QueuePrefixEnum.ROLLBACK_RESULT.name();
	}

	@Override
	public String getJob() {
		return job;
	}

	@Override
	public String getTask() {
		return task;
	}

	@Override
	public String getTaskResult() {
		return taskResult;
	}

	@Override
	public String getAgentResult() {
		return agentResult;
	}

	@Override
	public String getRollback() {
		return rollback;
	}

	@Override
	public String getRollbackResult() {
		return rollbackResult;
	}
}
