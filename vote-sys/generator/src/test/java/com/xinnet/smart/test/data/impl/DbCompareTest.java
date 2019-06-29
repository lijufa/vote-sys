package com.xinnet.smart.test.data.impl;

import org.junit.Test;

public class DbCompareTest {
	static String sqlpath = "../../db/mysql/";
	String source = "10.12.30.8";
	String target = "10.12.29.3";
	static DiffType dt = new DiffType();
	static {
		dt.debug = true;
		dt.file = true;
		dt.update = true;
	}

	@Test
	public void smart() {
		UDatabases.diff(sqlpath + "smart/diff/", new DbConfig("jdbc:mysql://" + source + ":3306/smart0114", "vps", "vps"), new DbConfig("jdbc:mysql://" + target + ":3306/smart", "vps", "vps"), dt);
	}

	@Test
	public void smartBus() {
		UDatabases.diff(sqlpath + "smartBus/diff/", new DbConfig("jdbc:mysql://" + source + ":3306/smartBus0114", "vps", "vps"),
				new DbConfig("jdbc:mysql://" + target + ":3306/smartBus", "vps", "vps"), dt);
	}
}
