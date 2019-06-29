package com.xinnet.smart.test.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.druid.pool.DruidDataSource;
import com.xinnet.smart.test.data.IDatabaseAdmin;
import com.xinnet.smart.test.data.IDatabases;

@Component
public class DatabasesMySQL implements IDatabases {
	static final Logger logger = LoggerFactory.getLogger(DatabasesMySQL.class);
	final Map<String, IDatabaseAdmin> dbMap = new HashMap<String, IDatabaseAdmin>();
	private List<DataSource> dataSources;

	public Map<String, IDatabaseAdmin> getDbMap() {
		return dbMap;
	}

	@Autowired
	void setDataSources(List<DataSource> dataSources) {
		this.dataSources = dataSources;
		if (dataSources != null) {
			for (DataSource dataSource : dataSources) {
				if (dataSource instanceof BasicDataSource) {
					dbMap.put(((BasicDataSource) dataSource).getUrl(), UDatabases.newInstance(dataSource));
				} else if (dataSource instanceof DruidDataSource) {
					dbMap.put(((DruidDataSource) dataSource).getUrl(), UDatabases.newInstance(dataSource));
				}
			}
		}
	}

	@Override
	public IDatabaseAdmin getDatabase() {
		if (dataSources == null || dataSources.size() == 0) {
			logger.debug("dataSources is null.");
		}
		return UDatabases.newInstance(dataSources.get(0));
	}

	@Override
	public IDatabaseAdmin getDatabase(String url) {
		return dbMap.get(url);
	}

	@Override
	public Set<String> urlSet() {
		return dbMap.keySet();
	}
}
