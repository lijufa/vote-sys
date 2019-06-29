package com.xinnet.smart.base.util.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.util.UJson;
import com.xinnet.smart.base.util.UStream;
import com.xinnet.smart.base.util.UTrace;
import com.xinnet.smart.vo.GenericShellResponse;

/**
 * 云数据库shell调用与调试类，用于直接执行mysql命令
 */
public class DataBaseShell {
	static final Logger logger = LoggerFactory.getLogger(DataBaseShell.class);
	private volatile static DataBaseShell instance;

	public static DataBaseShell getInstance() {
		if (instance == null) {
			synchronized (DataBaseShell.class) {
				instance = new DataBaseShell();
			}
		}
		return instance;
	}

	/**
	 * 执行命令数组。格式为：<br/>
	 * String[] commands = { "sh", "-c", "mysql -uroot -pG2n0C1z5 -e \"create database lei123456\"" };
	 * @author leijianteng
	 * @date 2017年10月9日 上午9:34:19
	 * @param commands
	 * @return GenericShellResponse
	 */
	public GenericShellResponse runStringArryCmds(String[] commands) {
		GenericShellResponse ret = new GenericShellResponse();
		Process process = null;
		try {
			//process = Runtime.getRuntime().exec(commands);
			ProcessBuilder pb = new ProcessBuilder(commands);
			process = pb.start();
			ret.setCode(process.waitFor());
			if (ret.getCode() == 0) {
				ret.setMsgInfo(UStream.read(process.getInputStream(), Charsets.UTF_8));
				ret.setSuccess(Boolean.TRUE);
			} else {
				ret.setMsgInfo(UStream.read(process.getErrorStream(), Charsets.UTF_8));
				ret.setSuccess(Boolean.FALSE);
			}
			return ret;
		} catch (Throwable e) {
			ret.setErrorMessage(UTrace.trace(e));
			return ret;
		} finally {
			if (process != null) {
				try {
					if (process.getInputStream() != null) {
						process.getInputStream().close();
					}
					if (process.getOutputStream() != null) {
						process.getOutputStream().close();
					}
					if (process.getErrorStream() != null) {
						process.getErrorStream().close();
					}
				} catch (IOException e) {
					logger.error(UTrace.trace(e));
				}
				process.destroyForcibly();
			}
			info(ret, Arrays.asList(commands));
		}
	}

	/**
	 * 执行list<String>命令<br/>
	 * 双引号等均不需要转义。直接list add即可。
	 * @author leijianteng
	 * @date 2017年10月9日 上午9:36:19
	 * @param commands
	 * @return GenericShellResponse
	 */
	public GenericShellResponse runListCmds(List<String> commands) {
		GenericShellResponse ret = new GenericShellResponse();
		Process process = null;
		try {
			//process = Runtime.getRuntime().exec(commands);
			ProcessBuilder pb = new ProcessBuilder(commands);
			process = pb.start();
			ret.setCode(process.waitFor());
			if (ret.getCode() == 0) {
				ret.setMsgInfo(UStream.read(process.getInputStream(), Charsets.UTF_8));
				ret.setSuccess(Boolean.TRUE);
			} else {
				ret.setMsgInfo(UStream.read(process.getErrorStream(), Charsets.UTF_8));
				ret.setSuccess(Boolean.FALSE);
			}
			return ret;
		} catch (Throwable e) {
			ret.setErrorMessage(UTrace.trace(e));
			return ret;
		} finally {
			if (process != null) {
				try {
					if (process.getInputStream() != null) {
						process.getInputStream().close();
					}
					if (process.getOutputStream() != null) {
						process.getOutputStream().close();
					}
					if (process.getErrorStream() != null) {
						process.getErrorStream().close();
					}
				} catch (IOException e) {
					logger.error(UTrace.trace(e));
				}
				process.destroyForcibly();
			}
			info(ret, commands);
		}
	}

	private final void info(final GenericShellResponse ret, final List<String> commands) {
		if (ret.isSuccess()) {
			if (logger.isDebugEnabled() || logger.isInfoEnabled()) {
				logger.info(commands(commands));
				logger.info(UJson.toString(ret));
			}
		} else {
			logger.error(commands(commands));
			logger.error(ret.getResult());
		}
	}

	public final static String commands(final List<String> commands) {
		StringBuilder sb = new StringBuilder();
		for (String command : commands) {
			if ("".equals(command)) {
				sb.append('"');
				sb.append(command);
				sb.append('"');
			} else {
				sb.append(command);
			}
			sb.append(' ');
		}
		return sb.toString();
	}

	/**
	 * 得到公用命令
	 * mysql -uroot -p'123456' -h122.14.198.116 -P3306 -S /mysql/log/mysql.sock -e
	 * @author leijianteng
	 * @date 2017年9月30日 下午2:15:51
	 * @param databasePassword
	 * @param managerIpAddress
	 * @param port
	 * @return StringBuffer
	 */
	public static List<String> getCommonCommands(String password, String ipAddress, Integer port) {
		List<String> cmd = new ArrayList<String>();
		cmd.add("/usr/local/mysql/bin/mysql");
		cmd.add("-uroot");
		cmd.add("-p" + password);
		cmd.add("-h" + ipAddress);
		cmd.add("-P" + port);
		cmd.add("-e");
		return cmd;
	}
}
