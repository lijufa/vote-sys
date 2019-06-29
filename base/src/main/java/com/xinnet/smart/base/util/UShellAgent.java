package com.xinnet.smart.base.util;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;
import com.xinnet.smart.base.PropertiesAgent;

public class UShellAgent {
	final static Logger logger = LoggerFactory.getLogger(UShellAgent.class);

	public static boolean excuteShellFix(String... cmd) {
		return cmd != null && cmd.length > 0 && PropertiesAgent.AGENT_SHELL.runs(cmd).isSuccess();
	}

	public static String excuteShell(List<String> cmd) {
		if (cmd != null && cmd.size() > 0) {
			return PropertiesAgent.AGENT_SHELL.runs(cmd).getResult();
		}
		return IEmpty.STRING;
	}

	public static String excuteShell(String cmd) {
		if (cmd != null && cmd.length() > 0) {
			return PropertiesAgent.AGENT_SHELL.runs(cmd.split(" ")).getResult();
		}
		return IEmpty.STRING;
	}

	public static String excuteShell(String[] cmd) {
		if (cmd != null && cmd.length > 0) {
			return PropertiesAgent.AGENT_SHELL.runs(cmd).getResult();
		}
		return IEmpty.STRING;
	}
}
