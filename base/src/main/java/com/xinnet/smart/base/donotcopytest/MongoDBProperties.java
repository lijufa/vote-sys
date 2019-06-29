package com.xinnet.smart.base.donotcopytest;

public class MongoDBProperties {
	public MongoDBProperties(String address, String dbname) {
		setAddress(address);
		this.dbname = dbname;
	}

	public MongoDBProperties() {
	}
	private String address;
	private String host;
	private int port;
	private String dbname;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public void setAddress(String address) {
		if (address != null && !address.equals("")) {
			if (address.indexOf(":") != -1) {
				String[] addressArray = address.split(":");
				host = addressArray[0];
				port = Integer.parseInt(addressArray[1]);
			}
		}
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
}
