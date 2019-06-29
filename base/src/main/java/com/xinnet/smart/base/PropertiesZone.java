package com.xinnet.smart.base;

import java.util.Random;
import com.xinnet.smart.base.util.UString;

public class PropertiesZone extends PropertiesBase {
	/*
	 * 获取回调上层应用的默认地址=Boss
	 */
	public static String getDefaultCallBackUrl() {
		return callBackUrls[0];
	}

	public static String getCallBackUrlSmartBusWeb() {
		if (UString.isEmpty(callBackUrl_SmartBus_Web)) {
			callBackUrl_SmartBus_Web = callBackUrls[1];
		}
		return callBackUrl_SmartBus_Web;
	}
	
	public static String getCallBackUrlSmartBusManager() {
		if (UString.isEmpty(callBackUrl_SmartBus_Manager)) {
			callBackUrl_SmartBus_Manager = callBackUrls[0];
		}
		return callBackUrl_SmartBus_Manager;
	}

	public static int getDistributed() {
		return Integer.valueOf(distributed);
	}
	private static int randInt = new Random().nextInt(2);

	/**
	 * 随机选择Boss或者Web回调服务器
	 * @author zhanghaitao
	 * @date 2017年10月20日 下午3:16:34
	 * @param next
	 * @return String
	 */
	public static String getRandomCallbackUrl(boolean next) {
		if (!next)
			return callBackUrls[randInt];
		else
			return callBackUrls[1 - randInt];
	}

	public static StringBuilder getServicePath(String hostip) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(hostip);
		sb.append(":");
		sb.append(agentPort);
		return sb;
	}

	public static String getAgentPort() {
		return agentPort;
	}
	//	UFile.getResourceObject("MongoDB.properties",MongoDBProperties.class);

}
