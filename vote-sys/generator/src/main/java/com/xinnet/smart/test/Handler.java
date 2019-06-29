package com.xinnet.smart.test;

public interface Handler<I, O> {
	O handle(I input);
}
