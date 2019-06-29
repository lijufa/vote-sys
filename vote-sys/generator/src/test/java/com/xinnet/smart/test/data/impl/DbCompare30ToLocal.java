package com.xinnet.smart.test.data.impl;

import org.junit.Test;

public class DbCompare30ToLocal {
	static String sqlpath = "../../db/mysql/";
	String sourceSmartBus = "127.0.0.1:3306";
	String sourceSmart = "127.0.0.1:3306";
	String targetSmartBus = "10.12.29.1:13306";
	String targetSmart = "10.12.29.3:13306";
	static DiffType dt = new DiffType();
	static {
		DbCompare30ToLocal.dt.debug = true;
		DbCompare30ToLocal.dt.file = false;
		//DbCompare30ToLocal.dt.update = true;
	}

	@Test
	public void smart() {
		DbConfig source = new DbConfig("jdbc:mysql://" + this.sourceSmart + "/devsmart", "vps", "vps");
		DbConfig target = new DbConfig("jdbc:mysql://" + this.targetSmart + "/smart", "vps", "vps");
		UDatabases.diff(DbCompare30ToLocal.sqlpath + "smart/diff/", source, target, DbCompare30ToLocal.dt);
	}

	@Test
	public void smartBus() {
		DbConfig source = new DbConfig("jdbc:mysql://" + this.sourceSmartBus + "/devsmartBus", "vps", "vps");
		DbConfig target = new DbConfig("jdbc:mysql://" + this.targetSmartBus + "/smartBus", "vps", "vps");
		UDatabases.diff(DbCompare30ToLocal.sqlpath + "smartBus/diff/", source, target, DbCompare30ToLocal.dt);
	}
}
