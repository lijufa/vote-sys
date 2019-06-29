package com.xinnet.smart.mq.impl;

import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.util.UJson;
import com.xinnet.smart.mq.IConvertion;

/**
 * 对象转换方法
 * @author meitao
 * @date Oct 9, 2014 9:51:50 AM
 */
public class ConvertionImpl implements IConvertion {
	private static final Logger logger = LoggerFactory.getLogger(ConvertionImpl.class);

	@Override
	public final byte[] toBytes(Object object) {
		if (object == null) {
			return null;
		}
		String msg = new StringBuilder().append(object.getClass().getName()).append(":").append(UJson.toString(object)).toString();
		logger.info(msg);
		return msg.getBytes(Charsets.UTF_8);
	}

	@Override
	public final Object toObject(byte[] bytes) {
		String s = new String(bytes, Charsets.UTF_8);
		try {
			int index = s.indexOf(":");
			if (index != -1) {
				return UJson.tryObject(s.substring(index + 1), Class.forName(s.substring(0, index)));
			} else {
				return s;
			}
		} catch (Throwable ignore) {
			logger.debug(ignore.getMessage());
			return null;
		}
	}
}
