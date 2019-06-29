package com.xinnet.smart.base;

import com.xinnet.smart.base.util.model.Shell;

public class PropertiesAgent extends PropertiesBase {
	//	public static String SH = "sh";
	//	String GET = "GET";
	//	String POST = "POST";
	//	String UTF8 = "UTF-8";
	//	public static String DOMAIN = ".pppcloud.cn";
	//	String CHMOD = "chmod";
	//	String _X = "+x";
	public static Shell SHELL = new Shell("/");
	public static Shell AGENT_SHELL = new Shell("/root/AgentShell/");
	public static Shell DATABASE_AGENT_SHELL = new Shell("/root/AgentShell/database/mysql5.6/");
	public static Shell DATABASE_AGENT_SHELL_MYSQL57 = new Shell("/root/AgentShell/database/mysql5.7/");
}
