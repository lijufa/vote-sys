package com.xinnet.smart.test.data.impl;

import org.junit.Test;

public class DbCompareLocalTo129 {
	static String sqlpath = "../../db/mysql/";
	String source = "127.0.0.1:3306";
	String target = "10.12.30.129:3306";
	static DiffType dt = new DiffType();
	static {
		DbCompareLocalTo129.dt.debug = true;
		DbCompareLocalTo129.dt.file = false;
		DbCompareLocalTo129.dt.update = true;
	}

	@Test
	public void smart() {
		DbConfig source = new DbConfig("jdbc:mysql://" + this.source + "/smart", "root", "");
		DbConfig target = new DbConfig("jdbc:mysql://" + this.target + "/smart", "vps", "vps");
		UDatabases.diff(DbCompareLocalTo129.sqlpath + "smart/diff/", source, target, DbCompareLocalTo129.dt);
	}

	@Test
	public void smartBus() {
		DbConfig source = new DbConfig("jdbc:mysql://" + this.source + "/smartBus", "root", "");
		DbConfig target = new DbConfig("jdbc:mysql://" + this.target + "/smartBus", "vps", "vps");
		UDatabases.diff(DbCompareLocalTo129.sqlpath + "smartBus/diff/", source, target, DbCompareLocalTo129.dt);
	}
}
