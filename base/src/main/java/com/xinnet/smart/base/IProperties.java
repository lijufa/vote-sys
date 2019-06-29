package com.xinnet.smart.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.util.UFile;
import com.xinnet.smart.base.util.UTrace;
import com.xinnet.smart.base.util.model.Shell;
import com.xinnet.smart.vo.GenericResponse;

public class IProperties {
	public static String SH = "sh";
	//	String GET = "GET";
	//	String POST = "POST";
	//	String UTF8 = "UTF-8";
	public static String DOMAIN = ".pppcloud.cn";
	//	String CHMOD = "chmod";
	//	String _X = "+x";
	public static Shell SHELL = new Shell("/");
	public static Shell AGENT_SHELL = new Shell("/root/AgentShell/");
	public static String HTTP = "http://";
	public static char COLON = ':';
	public static char SLASH = '/';
	static Logger logger = LoggerFactory.getLogger(IProperties.class);
	public static String version = UFile.getResourceString("version.properties", IProperties.class);
	public static String path = "";
	static {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("version.properties");
		try {
			Properties vp = new Properties();
			vp.load(is);
			path = vp.getProperty("version");
		} catch (IOException e1) {
			logger.error(UTrace.trace(e1));
		}
	}
	/**
	 * 部署用参数，为了便于配置
	 */
	public static GenericResponse SUCCESS = new GenericResponse().success();
}
