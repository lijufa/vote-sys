// package com.xinnet.smart.base.cache;
//
// import java.io.Serializable;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Set;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import com.xinnet.smart.base.cache.impl.JedisExt;
// import com.xinnet.smart.base.cache.impl.LocalCacheUtils;
// import com.xinnet.smart.base.util.UJson;
// import org.springframework.util.CollectionUtils;
// /**
//  * 缓存中间件（可在本地缓存和远程缓存间切换）
//  */
// @Component
// public class CacheUtils {
// 	final Logger logger = LoggerFactory.getLogger(CacheUtils.class);
// 	@Autowired
// 	LocalCacheUtils local;
// 	@Autowired
// 	JedisExt jedis;
//
// 	public boolean exists(String key) {
// 		if (local.exists(key)) {
// 			return true;
// 		}
// 		return jedis.exists(key);
// 	}
//
// 	//	public String hvals(String key) {
// 	//		return jedis.hvals(key);
// 	//	}
// 	@SuppressWarnings("unchecked")
// 	public <T> List<T> hvals(String key, Class<T> clazz) {
// 		List<T> ret = local.hvals(key, clazz);
// 		if (ret != null) {
// 			return ret;
// 		}
// 		return UJson.tryParametricTypeObject(jedis.hvals(key), ArrayList.class, clazz);
// 	}
//
// 	public String hget(String key, String id) {
// 		return jedis.hget(key, id.toString());
// 	}
//
// 	@SuppressWarnings("unchecked")
// 	public <T> T hget(String key, Serializable id, Class<T> clazz) {
// 		T ret = null;
// 		if (id != null) {
// 			Object o = local.hget(key, id);
// 			if (o != null) {
// 				ret = (T) o;
// 			} else {
// 				ret = UJson.tryObject(jedis.hget(key, id.toString()), clazz);
// 			}
// 		}
// 		return ret;
// 	}
//
// 	public void expire(String key, int seconds) {
// 		jedis.expire(key, seconds);
// 		local.expire(key, seconds);
// 	}
//
// 	public boolean hset(String key, Serializable id, Object value) {
// 		boolean ret = jedis.hset(key, id.toString(), UJson.toString(value));
// 		if (ret) {
// 			//成功从本地缓存中删除
// 			local.hdel(key, id);
// 		} else {
// 			//失败时保存到本地缓存
// 			local.hset(key, id, value);
// 		}
// 		return ret;
// 	}
//
// 	public boolean hsetOriginal(String key, Serializable id, Serializable value) {
// 		boolean ret = jedis.hset(key, id.toString(), value.toString());
// 		if (ret) {
// 			//成功从本地缓存中删除
// 			local.hdel(key, id);
// 		} else {
// 			//失败时保存到本地缓存
// 			local.hset(key, id, value);
// 		}
// 		return ret;
// 	}
//
// 	public void hdel(String key, Serializable id) {
// 		jedis.hdel(key, id.toString());
// 		local.hdel(key, id);
// 	}
//
// 	public void del(String key) {
// 		jedis.del(key);
// 		local.del(key);
// 	}
//
// 	public boolean hexists(String key, Serializable id) {
// 		if (local.hexists(key, id)) {
// 			return true;
// 		}
// 		return jedis.hexists(key, id.toString());
// 	}
//
// 	//	public List<String> hmget(String key, String... ids) {
// 	//		return jedis.hmget(key, ids);
// 	//	}
// 	public <T> List<T> hmget(String key, Class<T> clazz, Serializable[] ids) {
// 		List<T> rets = local.hmget(key, clazz, ids);
// 		if (CollectionUtils.isEmpty(rets)) {
// 			int j = ids.length;
// 			String[] ids2 = new String[j];
// 			for (int i = 0; i < j; i++) {
// 				ids2[i] = ids[i].toString();
// 			}
// 			rets = new ArrayList<T>();
// 			List<String> ss = jedis.hmget(key, ids2);
// 			if (ss != null) {
// 				for (String s : ss) {
// 					if (s != null) {
// 						rets.add(UJson.tryObject(s, clazz));
// 					}
// 				}
// 			}
// 		}
// 		return rets;
// 	}
//
// 	public Set<String> hkeys(String key) {
// 		return jedis.hkeys(key);
// 	}
//
// 	public Long hlen(String key) {
// 		return jedis.hlen(key);
// 	}
// }
