package com.xinnet.smart.test.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HashLists<K, V> extends HashMap<K, List<V>> {
	@Override
	public List<V> put(K key, List<V> list) {
		super.put(key, list);
		return list;
	}

	public V putIfNotExist(K key, V value) {
		if (containsKey(key)) {
			List<V> list = get(key);
			if (!list.contains(value)) {
				list.add(value);
			}
		} else {
			put(key, new ArrayList<V>()).add(value);
		}
		return value;
	}

	interface Handler<K, V> {
		void handle(K k, List<V> list);
	}

	public void iterate(Handler<K, V> handler) {
		Iterator<java.util.Map.Entry<K, List<V>>> iterator = entrySet().iterator();
		while (iterator.hasNext()) {
			java.util.Map.Entry<K, List<V>> entry = iterator.next();
			handler.handle(entry.getKey(), entry.getValue());
		}
	}
}
