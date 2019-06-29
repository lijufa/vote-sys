package com.xinnet.smart.test.data.abstracts;

import java.util.Set;
import javax.annotation.Resource;
import com.xinnet.smart.test.ElementHandler;
import com.xinnet.smart.test.ElementIterator;
import com.xinnet.smart.test.data.IDatabaseAdmin;
import com.xinnet.smart.test.data.IDatabases;
import com.xinnet.smart.test.generic.ElementIteratorImpl;

public abstract class AbstractTableHandler implements ElementHandler<String> {
	protected String url;
	@Resource
	protected IDatabases databases;
	protected IDatabaseAdmin database;

	public void beforeEachDb() {
	}

	public void afterEachDb() {
	}

	@Override
	public void afters() {
	}

	public void test() throws Throwable {
		this.befores();
		Set<String> urls = databases.urlSet();
		ElementIterator<String> iterator = new ElementIteratorImpl<String>(this);
		for (String url : urls) {
			this.url = url;
			this.database = databases.getDatabase(url);
			this.beforeEachDb();
			iterator.each(database.showTables().iterator());
			this.afterEachDb();
		}
		this.afters();
	}

	public void generatorOneTable(String tableName) throws Throwable {
		this.database = databases.getDatabase();
		ElementIterator<String> iterator = new ElementIteratorImpl<String>(this);
		iterator.each(tableName);
	}
}
