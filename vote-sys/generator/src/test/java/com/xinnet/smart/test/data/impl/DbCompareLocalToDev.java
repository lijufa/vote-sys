package com.xinnet.smart.test.data.impl;

import org.junit.Test;

public class DbCompareLocalToDev {
	String sqlpath = "../../../db/mysql/";
	static DiffType dt = new DiffType();
	static {
		DbCompareLocalToDev.dt.debug = true;
		DbCompareLocalToDev.dt.file = false;
		DbCompareLocalToDev.dt.update = true;
	}

	@Test
	public void smartBus() {
		DbConfig source = new DbConfig("jdbc:mysql://127.0.0.1:3306/smartBus", "root", "");
		DbConfig target = new DbConfig("jdbc:mysql://10.12.28.64:3306/smartBus", "xinnet", "#m1e2i3t4a5o6");
		UDatabases.diff(this.sqlpath + "smartBus/", source, target, DbCompareLocalToDev.dt);
	}

	@Test
	public void smart() {
		DbConfig source = new DbConfig("jdbc:mysql://127.0.0.1:3306/smart", "root", "");
		DbConfig target = new DbConfig("jdbc:mysql://10.12.28.75:3306/smart", "vps", "vps");
		UDatabases.diff(this.sqlpath + "smartBus/", source, target, DbCompareLocalToDev.dt);
	}
}
