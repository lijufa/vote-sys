package com.xinnet.smart.base;

public interface IHandler<Input, Output> {
	//TODO 讨论
	Output handle(Input o);
}
