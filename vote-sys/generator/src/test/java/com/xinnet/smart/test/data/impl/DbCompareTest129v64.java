package com.xinnet.smart.test.data.impl;

import org.junit.Test;

public class DbCompareTest129v64 {
	String sqlpath = "../../../db/mysql/";
	static DiffType dt = new DiffType();
	static {
		DbCompareTest129v64.dt.debug = true;
		DbCompareTest129v64.dt.file = false;
		DbCompareTest129v64.dt.update = false;
	}

	@Test
	public void smartBus64() {
		UDatabases.diff(this.sqlpath + "smartBus/", new DbConfig("jdbc:mysql://10.12.30.129:3306/smartBus", "vps", "vps"), new DbConfig("jdbc:mysql://10.12.28.64:3306/smartBus", "xinnet",
				"#m1e2i3t4a5o6"), DbCompareTest129v64.dt);
	}
}
