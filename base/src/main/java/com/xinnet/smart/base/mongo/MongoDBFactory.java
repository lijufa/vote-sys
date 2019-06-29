package com.xinnet.smart.base.mongo;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.xinnet.smart.base.PropertiesBase;
import com.xinnet.smart.base.donotcopytest.MongoDBProperties;
import com.xinnet.smart.base.util.UTrace;

public class MongoDBFactory {
	private static final Logger logger = LoggerFactory.getLogger(MongoDBFactory.class);
	private DB db;
	public static MongoDBProperties MONGODB_PROP = null;
	/**
	 * 解析MongoDb
	 */
	static {
		try {
			Properties props_mongo = new Properties();
			props_mongo.load(PropertiesBase.class.getClassLoader().getResourceAsStream("MongoDB.properties"));
			MONGODB_PROP = new MongoDBProperties(props_mongo.getProperty("address"), props_mongo.getProperty("dbname"));
		} catch (IOException e) {
			logger.info("Properties:load  MongoDB.properties failed");
		}
	}

	private static class MongoDBFactoryHolder {
		private static final MongoDBFactory INSTANCE = new MongoDBFactory();
	}

	public static MongoDBFactory getInstance() {
		return MongoDBFactoryHolder.INSTANCE;
	}

	private MongoDBFactory() {
		init(); //初始化
	}

	/**
	 * @author wanghongyu
	 * @date 2015年8月10日 下午4:18:07 void
	 */
	private void init() {
		String host = MONGODB_PROP.getHost();
		int port = MONGODB_PROP.getPort();
		String dbname = MONGODB_PROP.getDbname();
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient(new ServerAddress(host, port));
			db = mongoClient.getDB(dbname);
		} catch (UnknownHostException e) {
			logger.error(UTrace.trace(e));
		}
	}

	/**
	 * 
	 * @author wanghongyu
	 * @date 2015年8月10日 下午4:16:10
	 * @param collectionName
	 * @return DBCollection
	 */
	public DBCollection getCollection(String collectionName) {
		return db.getCollection(collectionName);
	}

	public DB getDB() {
		return this.db;
	}
}
