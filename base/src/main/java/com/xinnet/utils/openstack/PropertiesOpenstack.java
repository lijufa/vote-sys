package com.xinnet.utils.openstack;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.util.UTrace;
import com.xinnet.smart.vo.GenericResponse;

public class PropertiesOpenstack {
	static Logger logger = LoggerFactory.getLogger(PropertiesOpenstack.class);
	public static String openstackUrl = null;
	public static String openstackCallbackUrl = null;
	public static String zoneUuid = null;
	public static String envi = null;
	public static String platform = null;
	public static String openstackLoadbalanceUrl = null;
	public static String openstackLoadbalanceCallbackUrl = null;
	static {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("openstack.properties");
		try {
			Properties vp = new Properties();
			vp.load(is);
			openstackUrl = vp.getProperty("openstack_url");
			zoneUuid = vp.getProperty("zone_uuid");
			envi = vp.getProperty("envi");
			platform = vp.getProperty("platform");
			openstackCallbackUrl = vp.getProperty("openstack_callback_url");
			openstackLoadbalanceUrl = vp.getProperty("openstack_loadbalance_url");
			openstackLoadbalanceCallbackUrl = vp.getProperty("openstack_loadbalance_callback_url");
		} catch (IOException e1) {
			logger.error(UTrace.trace(e1));
		}
	}
	/**
	 * 部署用参数，为了便于配置
	 */
	public static GenericResponse SUCCESS = new GenericResponse().success();
}
