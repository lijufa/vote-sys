package com.xinnet.exception;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.xinnet.utils.ProperitesUtils;

public class ResponseMessage {
	private static Logger logger = LoggerFactory.getLogger(ResponseMessage.class);
	private static Properties properties = null;
	private static String exceptionMsg_file = "promptMessage.properties";
	static {
		//加载异常属性文件
		try {
			properties = ProperitesUtils.getPropertiesWithThread(exceptionMsg_file);
		} catch (IOException e) {
			logger.error("提示消息属性文件加载失败");
		}
	}

	public static String getMessage(String key) {
		String msg = properties.getProperty(key);
		if (StringUtils.isEmpty(msg))
			msg = "请设置key=" + key + "的值!!";
		return msg;
	}
}
