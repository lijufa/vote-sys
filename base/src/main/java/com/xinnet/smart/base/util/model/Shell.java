package com.xinnet.smart.base.util.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.PropertiesBase;
import com.xinnet.smart.base.util.UJson;
import com.xinnet.smart.base.util.UStream;
import com.xinnet.smart.base.util.UTrace;
import com.xinnet.smart.vo.GenericShellResponse;

/**
 * shell调用与调试类，请勿改动，以免依赖其他代码增加调试复杂度
 * @author meitao
 * @date Oct 16, 2014 1:29:40 PM
 */
public class Shell extends PropertiesBase {
	static final Logger logger = LoggerFactory.getLogger(Shell.class);
	final String path;
	final File direcotry;

	private Shell() {
		this.path = "/root/AgentShell/";
		this.direcotry = new File(path);
	}
	private volatile static Shell instance;

	public static Shell getInstance() {
		if (instance == null) {
			synchronized (Shell.class) {
				instance = new Shell();
			}
		}
		return instance;
	}

	public Shell(final String path) {
		this.path = path;
		this.direcotry = new File(path);
	}

	public final String getPath() {
		return path;
	}

	public final File getDirecotry() {
		return direcotry;
	}

	public final GenericShellResponse runs(final String... commands) {
		List<String> ss = new ArrayList<String>(commands.length);
		for (String command : commands) {
			ss.add(command);
		}
		return runs(ss);
	}

	public final GenericShellResponse runs(final List<String> commands) {
		GenericShellResponse ret = new GenericShellResponse();
		Process process = null;
		try {
			ProcessBuilder pb = new ProcessBuilder(commands);
			if (direcotry != null) {
				pb.directory(direcotry);
				if (logger.isDebugEnabled() || logger.isInfoEnabled()) {
					logger.info("shell direcotry---------->{}", direcotry);
				}
			}
			process = pb.start();
			ret.setCode(process.waitFor());
			ret.setResult(UStream.read(process.getInputStream(), Charsets.UTF_8));
			if (ret.getCode() == 0) {
				//ret.setMsgInfo(UStream.read(process.getInputStream(), Charsets.UTF_8));
				ret.setSuccess(Boolean.TRUE);
			} else {
				//ret.setMsgInfo(UStream.read(process.getErrorStream(), Charsets.UTF_8));
				//logger.info("shellParameter and shellReturnResult INFO---------->{}", commands.toString() + "and result:(" + ret.getResult() + ";Code:" + ret.getCode());
				ret.setSuccess(Boolean.FALSE);
				if (logger.isDebugEnabled() || logger.isInfoEnabled()) {
					logger.info("RUN SCRIPT ERROR INFO---------->{}", UStream.read(process.getErrorStream(), Charsets.UTF_8));
				}
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
				//process.destroy();
				process.destroyForcibly();
			}
			info(ret, commands);
		}
	}

	public GenericShellResponse runCommands(final List<String> commands) {
		GenericShellResponse ret = new GenericShellResponse();
		Process process = null;
		try {
			ProcessBuilder pb = new ProcessBuilder(commands);
			process = pb.start();
			ret.setCode(process.waitFor());
			ret.setResult(UStream.read(process.getInputStream(), Charsets.UTF_8));
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
				//process.destroy();
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
}
