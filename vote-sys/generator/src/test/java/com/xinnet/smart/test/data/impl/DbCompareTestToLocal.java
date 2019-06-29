package com.xinnet.smart.test.data.impl;

import org.junit.Test;

public class DbCompareTestToLocal {
	String sqlpath = "../../../db/mysql/";
	static DiffType dt = new DiffType();
	static {
		DbCompareTestToLocal.dt.debug = true;
		DbCompareTestToLocal.dt.file = false;
		DbCompareTestToLocal.dt.update = true;
	}

	@Test
	public void smartBus() {
		DbConfig source = new DbConfig("jdbc:mysql://10.12.29.1:3306/smartBus", "vps", "vps");
		DbConfig target = new DbConfig("jdbc:mysql://127.0.0.1/smartBus", "root", "");
		UDatabases.diff(this.sqlpath + "smartBus/", source, target, DbCompareTestToLocal.dt);
	}

	@Test
	public void smart() {
		DbConfig source = new DbConfig("jdbc:mysql://10.12.29.3:3306/smart", "vps", "vps");
		DbConfig target = new DbConfig("jdbc:mysql://127.0.0.1/smart", "root", "");
		UDatabases.diff(this.sqlpath + "smart/", source, target, DbCompareTestToLocal.dt);
	}
}
