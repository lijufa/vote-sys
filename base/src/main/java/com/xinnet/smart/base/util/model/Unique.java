package com.xinnet.smart.base.util.model;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.PropertiesZone;

/**
 * 唯一编号生成工具
 * @author meitao
 * @date 2014年7月16日 下午3:16:27
 */
public final class Unique {
	static final Logger logger = LoggerFactory.getLogger(Unique.class);
	static final int BIT_PER_MS = 17;//128000000/s
	static final int BIT_SERVER = 3;//vdc内部最多8台
	final static int DISTRIBUTED = PropertiesZone.getDistributed();
	//	final static int DISTRIBUTED = UString.parseInt(UFile.getResourceString("DISTRIBUTED", Unique.class));
	long LAST;
	static Map<String, Unique> MAP = new HashMap<String, Unique>();

	public synchronized long getLong() {
		long t = System.currentTimeMillis() << BIT_PER_MS;
		if (t <= LAST) {
			t = LAST + 1;
		}
		return ((LAST = t) << BIT_SERVER) | (DISTRIBUTED & 0xF);
	}

	public String getString() {
		return Long.toString(getLong(), Character.MAX_RADIX);
	}

	static synchronized Unique get(String key) {
		Unique ret = MAP.get(key);
		if (ret == null) {
			ret = new Unique();
			MAP.put(key, ret);
		}
		return ret;
	}

	public static Long getLong(String key) {
		return get(key).getLong();
	}

	public static Long getLong(Class<?> c) {
		return get(c.getName()).getLong();
	}

	public static String getString(Class<?> c) {
		return get(c.getName()).getString();
	}
	//	public static <T extends IUnique> T newInstance(Class<T> c) {
	//		try {
	//			T ret = c.getConstructor().newInstance();
	//			ret.setId(getLong(c));
	//			return ret;
	//		} catch (Throwable e) {
	//			logger.error(UTrace.trace(e));
	//			return null;
	//		}
	//	}
}
