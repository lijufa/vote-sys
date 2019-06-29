// package com.xinnet.smart.base.cache.impl;
//
// import java.io.Serializable;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Timer;
// import java.util.TimerTask;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;
//
// @Component
// public class LocalCacheUtils {
// 	final Logger logger = LoggerFactory.getLogger(LocalCacheUtils.class);
// 	private static Map<String, Map<Serializable, Object>> LOCAL_CACHE = new HashMap<String, Map<Serializable, Object>>();
//
// 	public boolean exists(String key) {
// 		return LOCAL_CACHE.containsKey(key);
// 	}
//
// 	public void expire(String key, int seconds) {
// 		Timer timer = new Timer();
// 		timer.schedule(new TimerTask() {
// 			@Override
// 			public void run() {
// 				LOCAL_CACHE.remove(key);
// 			}
// 		}, seconds * 1000);
// 	}
//
// 	public Collection<Object> hvals(String key) {
// 		Map<Serializable, Object> ret = LOCAL_CACHE.get(key);
// 		if (ret == null) {
// 			return null;
// 		}
// 		return ret.values();
// 	}
//
// 	public <T> List<T> hvals(String key, Class<T> clazz) {
// 		Collection<Object> vals = hvals(key);
// 		if (vals == null) {
// 			return null;
// 		}
// 		List<T> rets = new ArrayList<T>();
// 		for (Object val : vals) {
// 			rets.add((T) val);
// 		}
// 		return rets;
// 	}
//
// 	public Object hget(String key, Serializable id) {
// 		if (key == null || id == null) {
// 			return null;
// 		}
// 		Map<Serializable, Object> map = LOCAL_CACHE.get(key);
// 		if (map == null) {
// 			return null;
// 		}
// 		return map.get(id);
// 	}
//
// 	public void hset(String key, Serializable id, Object value) {
// 		Map<Serializable, Object> map = LOCAL_CACHE.get(key);
// 		if (map == null) {
// 			map = new HashMap<Serializable, Object>();
// 			LOCAL_CACHE.put(key, map);
// 		}
// 		map.put(id, value);
// 	}
//
// 	public void hdel(String key, Serializable id) {
// 		Map<Serializable, Object> map = LOCAL_CACHE.get(key);
// 		if (map != null) {
// 			map.remove(id);
// 		}
// 	}
//
// 	public void del(String key) {
// 		LOCAL_CACHE.remove(key);
// 	}
//
// 	public boolean hexists(String key, Serializable id) {
// 		Map<Serializable, Object> map = LOCAL_CACHE.get(key);
// 		if (map == null) {
// 			return false;
// 		}
// 		return map.containsKey(id);
// 	}
//
// 	@SuppressWarnings("unchecked")
// 	public <T> List<T> hmget(String key, Class<T> clazz, Serializable... ids) {
// 		List<T> rets = new ArrayList<T>();
// 		if (key != null && ids != null) {
// 			Map<Serializable, Object> map = LOCAL_CACHE.get(key);
// 			if (map != null) {
// 				for (Serializable id : ids) {
// 					Object o = map.get(id);
// 					if (o != null) {
// 						rets.add((T) o);
// 					}
// 				}
// 			}
// 		}
// 		return rets;
// 	}
// }
