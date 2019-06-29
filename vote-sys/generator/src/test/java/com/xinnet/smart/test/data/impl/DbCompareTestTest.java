package com.xinnet.smart.test.data.impl;

import org.junit.Test;

public class DbCompareTestTest {
	String sqlpath = "../../../db/mysql/";
	static DiffType dt = new DiffType();
	static {
		DbCompareTestTest.dt.debug = true;
		DbCompareTestTest.dt.file = false;
		DbCompareTestTest.dt.update = true;
	}

	@Test
	public void smartBus() {
		UDatabases.diff(this.sqlpath + "smartBus/", new DbConfig("jdbc:mysql://127.0.0.1/smartBus", "root", ""), new DbConfig("jdbc:mysql://10.12.29.1:3306/smartBus", "vps", "vps"),
				DbCompareTestTest.dt);
	}

	@Test
	public void smart() {
		UDatabases.diff(this.sqlpath + "smart/", new DbConfig("jdbc:mysql://127.0.0.1/smart", "root", ""), new DbConfig("jdbc:mysql://10.12.29.3:3306/smart", "vps", "vps"), DbCompareTestTest.dt);
	}
}
