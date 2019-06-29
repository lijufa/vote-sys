package com.xinnet.smart.base.cache;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xinnet.smart.base.util.UTrace;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Component
public class ShardedJedisUtils {
	final Logger logger = LoggerFactory.getLogger(ShardedJedisUtils.class);
	//	IPoolAdapter<ShardedJedis> pool = ShardedJedisCachePool.getInstance();
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	private ShardedJedis getResource() {
		try {
			return shardedJedisPool.getResource();
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			shardedJedisPool = null;
		}
		return null;
	}

	public final Long publish(String channel, String message) {
		ShardedJedis resource = getResource();
		if (resource == null) {
			return null;
		}
		try {
			// 发送消息
			return resource.getShard(channel).publish(channel, message);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			shardedJedisPool.returnResourceObject(resource);
		}
	}

	public boolean exists(String key) {
		ShardedJedis resource = getResource();
		if (resource == null) {
			return false;
		}
		try {
			return resource.exists(key);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return false;
		} finally {
			shardedJedisPool.returnResourceObject(resource);
		}
	}

	public void expire(String key, int seconds) {
		ShardedJedis resource = getResource();
		if (resource != null) {
			try {
				resource.expire(key, seconds);
			} catch (Throwable e) {
				logger.error(UTrace.trace(e));
			} finally {
				shardedJedisPool.returnResource(resource);
			}
		}
	}

	public String hvals(String key) {
		ShardedJedis resource = getResource();
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
			shardedJedisPool.returnResourceObject(resource);
		}
	}

	public String hget(String key, String field) {
		ShardedJedis resource = getResource();
		if (resource == null) {
			return null;
		}
		try {
			return resource.hget(key, field);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			shardedJedisPool.returnResourceObject(resource);
		}
	}

	public boolean hset(String key, String field, String value) {
		ShardedJedis resource = getResource();
		if (resource == null) {
			return false;
		}
		try {
			return resource.hset(key, field, value) != null;
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return false;
		} finally {
			shardedJedisPool.returnResourceObject(resource);
		}
	}

	public boolean hdel(String key, String field) {
		ShardedJedis resource = getResource();
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
			shardedJedisPool.returnResourceObject(resource);
		}
	}

	public void del(String key) {
		ShardedJedis resource = getResource();
		if (resource == null) {
			return;
		}
		try {
			resource.del(key);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		} finally {
			shardedJedisPool.returnResourceObject(resource);
		}
	}

	public boolean hexists(String key, String field) {
		ShardedJedis resource = getResource();
		if (resource == null) {
			return false;
		}
		try {
			return resource.hexists(key, field);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return false;
		} finally {
			shardedJedisPool.returnResourceObject(resource);
		}
	}

	public List<String> hmget(String key, String... fields) {
		ShardedJedis resource = getResource();
		if (resource == null) {
			return null;
		}
		try {
			return resource.hmget(key, fields);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return null;
		} finally {
			shardedJedisPool.returnResourceObject(resource);
		}
	}
}
