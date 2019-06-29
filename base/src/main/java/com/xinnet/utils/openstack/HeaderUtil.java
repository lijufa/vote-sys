package com.xinnet.utils.openstack;

import com.xinnet.smart.base.util.UString;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yangqiu
 * @Description Post请求头参数
 * @Date 2019-05-23 20:06
 */
public class HeaderUtil {
	public static Map<String, Object> headerParam(String token) throws Exception {
		Map<String, Object> headerParam = new HashMap<>();
		headerParam.put("platform", PropertiesOpenstack.platform);
		headerParam.put("envi", PropertiesOpenstack.envi);
		headerParam.put("zone-uuid", PropertiesOpenstack.zoneUuid);
		if (UString.notEmpty(token)) {
			headerParam.put("auth-token", token);
		} else {
			throw new Exception("token is null");
		}
		return headerParam;
	}
}
