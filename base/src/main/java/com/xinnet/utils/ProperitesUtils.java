package com.xinnet.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProperitesUtils {
	private static Logger logger = LoggerFactory.getLogger(ProperitesUtils.class);

	public static Properties getPropertiesWithThread(String filepath) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
		properties.load(new InputStreamReader(inputStream, "UTF-8"));
		return properties;
	}

	public static String getPropertiesConent(String filepath) throws IOException {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
		String content = IOUtils.toString(inputStream, "UTF-8");
		return content;
	}

	public static void main(String[] args) {
		try {
			String ret = getPropertiesConent("base.properties");
			System.out.println(ret);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
