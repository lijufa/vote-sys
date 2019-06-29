package com.xinnet.smart.test.generic;

import java.util.Iterator;
import com.xinnet.smart.test.ElementHandler;
import com.xinnet.smart.test.ElementIterator;

public class ElementIteratorImpl<T> implements ElementIterator<T> {
	final ElementHandler<? super T> handler;

	public ElementIteratorImpl(Class<? extends ElementHandler<? super T>> handleClass) throws Throwable {
		handler = handleClass.getConstructor().newInstance();
	}

	public ElementIteratorImpl(ElementHandler<? super T> handler) throws Throwable {
		this.handler = handler;
	}

	public ElementHandler<? super T> getHandler() {
		return handler;
	}

	@Override
	public void each(Iterator<T> iterator) {
		if (handler != null && iterator != null) {
			while (iterator.hasNext()) {
				handler.handle(iterator.next());
			}
		}
	}

	@Override
	public void each(T tableName) {
		if (handler != null && tableName != null) {
			handler.handle(tableName);
		}
	}
}
