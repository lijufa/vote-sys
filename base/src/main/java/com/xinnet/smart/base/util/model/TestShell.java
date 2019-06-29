package com.xinnet.smart.base.util.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * shell
 * @author meitao
 * @date Oct 16, 2014 1:29:40 PM
 */
public class TestShell{
	final String path;
	final File direcotry;
	public static void main(String[] args) {
		System.out.println("begin---------------");
		 TestShell AGENT_SHELL = new TestShell("/root/loadBalanceShell/");
		 StringBuffer cmd = new StringBuffer();
			cmd.append("sh");
			//cmd.append(" ");
			cmd.append("build_smartl7_conf.sh");
			//cmd.append(" ");
			cmd.append("vs_name=vsvs1,enable=on,vip_l7=172.23.177.193:80,method=wrr,insert_cookie=on,cookie=zhang123,cookie_time=900,maxconn=666,connreuse=on,server_transparent=on,X_Forwarded_For=on,pool_name=10,healthcheck_enabled=on,healthcheck=tcp,healthcheck_delay=6666,healthcheck_timeout=6666,healthcheck_failcount=6,real_server=172.23.171.198:80/5/on#?172.23.171.199:80/6/off");
			//cmd.append(" ");
			//String[] cmdArry = cmd.toString().split(" ");
		 AGENT_SHELL.testRuns(cmd.toString());
		System.out.println("end------------");
	}
	public final void testRuns(final String commands) {
		Process process = null;
		try {
			ProcessBuilder pb = new ProcessBuilder(commands);
			if (direcotry != null) {
				pb.directory(direcotry);
			}
			process = pb.start();
			System.out.println(process.waitFor()+"=process.waitFor!!!!");
			System.out.println(read(process.getInputStream(), Charset.defaultCharset())+"=process.getInputStream!!");
			
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println(e.toString());
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
					e.printStackTrace();
				}
				//process.destroy();
				process.destroyForcibly();
			}
		}
	}
	public static final String read(InputStream is, Charset charset) {
		if (is == null) {
			return null;
		} else {
			try {
				byte[] bytes = read(is);
				if (charset != null) {
					return new String(bytes, charset);
				} else {
					return new String(bytes);
				}
			} catch (Throwable e) {
				return null;
			} finally {
				try {
					is.close();
				} catch (IOException ignore) {
				}
			}
		}
	}
	public static final byte[] read(InputStream is) {
		if (is != null) {
			try {
				int total = 0;
				List<byte[]> bytesS = new ArrayList<byte[]>();
				boolean isFinished = false;
				do {
					byte[] t = new byte[1024];
					int totalRead = is.read(t);
					switch (totalRead) {
					case 1024:
						bytesS.add(t);
						total += totalRead;
						break;
					case -1:
						isFinished = true;
						break;
					case 0:
						break;
					default:
						bytesS.add(Arrays.copyOf(t, totalRead));
						total += totalRead;
						break;
					}
				} while (!isFinished);
				byte[] ret = new byte[total];
				int destPos = 0;
				for (byte[] bytes : bytesS) {
					System.arraycopy(bytes, 0, ret, destPos, bytes.length);
					destPos += bytes.length;
				}
				return ret;
			} catch (Throwable e) {
			} finally {
				try {
					is.close();
				} catch (IOException ignore) {
				}
			}
		}
		byte[] bytes = {};
		return bytes;
	}
	private TestShell() {
		this.path = "/root/loadBalanceShell/";
		this.direcotry = new File(path);
	}
	private volatile static TestShell instance;

	public static TestShell getInstance() {
		if (instance == null) {
			synchronized (TestShell.class) {
				instance = new TestShell();
			}
		}
		return instance;
	}

	public TestShell(final String path) {
		this.path = path;
		this.direcotry = new File(path);
	}

	public final String getPath() {
		return path;
	}

	public final File getDirecotry() {
		return direcotry;
	}
}
