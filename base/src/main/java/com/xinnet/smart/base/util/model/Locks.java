package com.xinnet.smart.base.util.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {
	final Map<Long, Lock> map = new HashMap<Long, Lock>();

	public Lock lock(Long uuid) {
		Lock ret = get(uuid);
		ret.lock();
		return ret;
	}

	public void unlock(Long uuid) {
		get(uuid).unlock();
	}

	public synchronized Lock get(Long uuid) {
		Lock ret = map.get(uuid);
		if (ret == null) {
			ret = new ReentrantLock();
			map.put(uuid, ret);
		}
		return ret;
	}
}
