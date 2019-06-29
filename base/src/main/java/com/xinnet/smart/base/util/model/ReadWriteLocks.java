package com.xinnet.smart.base.util.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLocks {
	final Map<Long, ReadWriteLock> map = new HashMap<Long, ReadWriteLock>();

	public Lock readLock(Long uuid) {
		return get(uuid).readLock();
	}

	public Lock writeLock(Long uuid) {
		return get(uuid).writeLock();
	}

	private synchronized ReadWriteLock get(Long uuid) {
		ReadWriteLock ret = map.get(uuid);
		if (ret == null) {
			ret = new ReentrantReadWriteLock();
			map.put(uuid, ret);
		}
		return ret;
	}
}
