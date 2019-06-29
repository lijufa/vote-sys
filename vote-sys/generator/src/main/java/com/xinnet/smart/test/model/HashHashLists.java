package com.xinnet.smart.test.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HashHashLists<K1, K2, V> extends HashMap<K1, HashLists<K2, V>> {
	public V putIfNotExist(K1 key1, K2 key2, V value) {
		HashLists<K2, V> hash;
		if (containsKey(key1)) {
			hash = get(key1);
			hash.putIfNotExist(key2, value);
		} else {
			hash = new HashLists<K2, V>();
			put(key1, hash);
			hash.put(key2, new ArrayList<V>()).add(value);
		}
		return value;
	}

	public interface Handler<K1, K2, V> {
		void handle(K1 k1, K2 k2, List<V> list);
	}

	public void iterate(final Handler<K1, K2, V> handler) {
		Iterator<java.util.Map.Entry<K1, HashLists<K2, V>>> iterator = entrySet().iterator();
		while (iterator.hasNext()) {
			final java.util.Map.Entry<K1, HashLists<K2, V>> entry = iterator.next();
			entry.getValue().iterate(new com.xinnet.smart.test.model.HashLists.Handler<K2, V>() {
				@Override
				public void handle(K2 k, List<V> list) {
					handler.handle(entry.getKey(), k, list);
				}
			});
		}
	}
}
