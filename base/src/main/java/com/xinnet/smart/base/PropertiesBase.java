package com.xinnet.smart.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.util.UTrace;
import com.xinnet.smart.vo.GenericResponse;

public class PropertiesBase {
	public static String SH = "sh";
	public static String SSH = "ssh";
	//	String GET = "GET";
	//	String POST = "POST";
	//	String UTF8 = "UTF-8";
	public static String DOMAIN = ".pppcloud.cn";
	//	String CHMOD = "chmod";
	//	String _X = "+x";
	//	public static Shell SHELL = new Shell("/");
	//	public static Shell AGENT_SHELL = new Shell("/root/AgentShell/");
	//	public static String HTTP = "http://";
	//	public static char COLON = ':';
	//	public static char SLASH = '/';
	static Logger logger = LoggerFactory.getLogger(PropertiesBase.class);
	public static String version = "";//= UFile.getResourceString("version.properties", PropertiesBase.class);
	protected static String agentPort = "";
	public static String[] callBackUrls = null;
	public static String callBackUrl_SmartBus_Web = null;
	public static String callBackUrl_SmartBus_Manager = null;
	protected static String distributed = null;
	public static String publicKeyName;
	public static String smartBus_cloudSafe_web = null;
	/**
	 * 
	 */
	public static boolean msgController = false;
	static {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("base.properties");
		try {
			Properties vp = new Properties();
			vp.load(is);
			version = vp.getProperty("version");
			agentPort = vp.getProperty("agentPort");
			distributed = vp.getProperty("distributed");
			callBackUrls = vp.getProperty("callbackUrl").split(",");
			callBackUrl_SmartBus_Web = vp.getProperty("callbackUrl_smartBus_Web");
			callBackUrl_SmartBus_Manager = vp.getProperty("callbackUrl_smartBus_Manager");
			msgController = "0".equals(vp.getProperty("msgController")) ? false : true;
			publicKeyName = vp.getProperty("publicKeyName");
			smartBus_cloudSafe_web = vp.getProperty("smartBus_cloudSafe_web");
		} catch (IOException e1) {
			logger.error(UTrace.trace(e1));
		}
	}
	/**
	 * 部署用参数，为了便于配置
	 */
	public static GenericResponse SUCCESS = new GenericResponse().success();
}
