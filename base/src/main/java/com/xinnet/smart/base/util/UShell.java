package com.xinnet.smart.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.PropertiesAgent;
import com.xinnet.smart.vo.GenericResponse;

public class UShell {
	static final Logger logger = LoggerFactory.getLogger(UShell.class);
	static final String NEW = "NEW";
	static final String IPTABLES = "/sbin/iptables";
	static final String _D = "-D";
	static final String _I = "-I";
	static final String _J = "-j";
	static final String _M = "-m";
	static final String _P = "-p";
	static final String INPUT = "INPUT";
	static final String STATE = "state";
	static final String TCP = "tcp";
	static final String ACCEPT = "ACCEPT";
	static final String __STATE = "--state";
	static final String __DPORT = "--dport";

	public static final GenericResponse chmod(final String path) {
		return PropertiesAgent.SHELL.runs("/bin/chmod", "-R", "+x", path);
	}

	public static final GenericResponse iptables(final int port) {
		return iptables(NEW, TCP, String.valueOf(port), ACCEPT);
	}

	public static final GenericResponse bin(final String... cmd) {
		if (cmd != null && cmd.length > 0) {
			cmd[0] = "/bin/" + cmd[0];
			return PropertiesAgent.SHELL.runs(cmd);
		} else {
			return new GenericResponse().success();
		}
	}

	public static final GenericResponse iptables(final int beginPort, final int endPort) {
		return iptables(NEW, TCP, beginPort + ":" + endPort, ACCEPT);
	}

	public static final GenericResponse iptables(final String state, final String portocol, final String port, final String rule) {
		//先删除下，以免重复添加
		PropertiesAgent.SHELL.runs(IPTABLES, _D, INPUT, _M, STATE, __STATE, state, _M, portocol, _P, portocol, __DPORT, port, _J, rule);
		return PropertiesAgent.SHELL.runs(IPTABLES, _I, INPUT, _M, STATE, __STATE, state, _M, portocol, _P, portocol, __DPORT, port, _J, rule);
	}

	public static final String osName() {
		return System.getProperties().getProperty("os.name");
	}
}
