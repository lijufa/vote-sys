package com.xinnet.interceptor.mybatis.jackson2;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.xinnet.interceptor.mybatis.domain.PageList;

/**
 * @author miemiedev
 */
public class PageListJsonMapper extends ObjectMapper {
	public PageListJsonMapper() {
		SimpleModule module = new SimpleModule("PageListJSONModule", new Version(1, 0, 0, null, null, null));
		module.addSerializer(PageList.class, new PageListJsonSerializer(this));
		registerModule(module);
	}
}
