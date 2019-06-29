package com.xinnet.smart.base.cache.impl;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xinnet.smart.base.util.UTrace;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisExt {
	final Logger logger = LoggerFactory.getLogger(JedisExt.class);
	//	IPoolAdapter<Jedis> pool = JedisCachePool.getInstance();
	@Autowired
	JedisPool pool;

	private JedisPool getPool() {
		return pool;
	}

	private Jedis getResource() {
		try {
			return pool.getResource();
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
		return null;
	}

	public boolean exists(String key) {
		if (key == null) {
			return false;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return false;
		}
		try {
			return resource.exists(key);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return false;
		} finally {
			getPool().returnResource(resource);
		}
	}

	public void expire(String key, int seconds) {
		Jedis resource = getResource();
		if (resource != null) {
			try {
				resource.expire(key, seconds);
			} catch (Throwable e) {
				logger.error(UTrace.trace(e));
			} finally {
				getPool().returnResource(resource);
			}
		}
	}

	public String hvals(String key) {
		if (key == null) {
			return null;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return null;
		}
		if (!resource.exists(key)) {
			return null;
		}
		try {
			List<String> ret = resource.hvals(key);
			if (ret == null) {
				return null;
			}
			return ret.toString();
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			getPool().returnResource(resource);
		}
	}

	public String hget(String key, String field) {
		Jedis resource = getResource();
		if (resource == null) {
			return null;
		}
		try {
			return resource.hget(key, field);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			getPool().returnResource(resource);
		}
	}

	public boolean hset(String key, String field, String value) {
		if (key == null || field == null || value == null) {
			return false;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return false;
		}
		try {
			return resource.hset(key, field, value) != null;
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return false;
		} finally {
			getPool().returnResource(resource);
		}
	}

	public boolean hdel(String key, String field) {
		if (key == null || field == null) {
			return false;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return false;
		}
		try {
			if (resource.hget(key, field) == null) {
				return true;//不存在等价于删除成功
			}
			return resource.hdel(key, field) != null;
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return false;
		} finally {
			getPool().returnResource(resource);
		}
	}

	public void del(String key) {
		if (key == null) {
			return;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return;
		}
		try {
			resource.del(key);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		} finally {
			getPool().returnResource(resource);
		}
	}

	public boolean hexists(String key, String field) {
		if (key == null) {
			return false;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return false;
		}
		try {
			return resource.hexists(key, field);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return false;
		} finally {
			getPool().returnResource(resource);
		}
	}

	public List<String> hmget(String key, String... fields) {
		if (key == null || fields == null) {
			return null;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return null;
		}
		try {
			return resource.hmget(key, fields);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			getPool().returnResource(resource);
		}
	}

	//查询hash的size
	public Long hlen(String key) {
		if (key == null) {
			return null;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return null;
		}
		try {
			return resource.hlen(key);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			getPool().returnResource(resource);
		}
	}

	public String setex(String key, int seconds, String value) {
		if (key == null) {
			return null;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return null;
		}
		try {
			return resource.setex(key, seconds, value);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			getPool().returnResource(resource);
		}
	}

	public String get(String key) {
		if (key == null) {
			return null;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return null;
		}
		try {
			return resource.get(key);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			getPool().returnResource(resource);
		}
	}
	
	public Set<String> hkeys(String key) {
		if (key == null) {
			return null;
		}
		Jedis resource = getResource();
		if (resource == null) {
			return null;
		}
		try {
			return resource.hkeys(key);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			getPool().returnResource(resource);
		}
	}
}
