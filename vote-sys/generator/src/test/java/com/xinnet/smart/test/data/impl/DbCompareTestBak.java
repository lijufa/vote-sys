package com.xinnet.smart.test.data.impl;

import org.junit.Test;

public class DbCompareTestBak {
	String sqlpath = "../../../db/mysql/";
	static DiffType dt = new DiffType();
	static {
		dt.debug = true;
		dt.file = false;
		dt.update = false;
	}

	//@Test
	public void smart() {
		UDatabases.diff(sqlpath + "smart/", new DbConfig("jdbc:mysql://127.0.0.1:3306/smart", "root", ""), new DbConfig("jdbc:mysql://127.0.0.1:3306/smart_bak", "root", ""), dt);
	}

	@Test
	public void smartBus() {
		UDatabases.diff(sqlpath + "smartBus/", new DbConfig("jdbc:mysql://127.0.0.1:3306/smartBus", "root", ""), new DbConfig("jdbc:mysql://127.0.0.1:3306/smartBus_bak", "root", ""), dt);
	}
}
