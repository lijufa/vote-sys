package com.xinnet.smart.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;

public class UStream {
	static final Logger logger = LoggerFactory.getLogger(UStream.class);
	static final int MAX_LENGTH_PER_READ = 1024;

	/**
	 * 本地流增强版
	 * @author meitao
	 * @date Oct 30, 2014 1:42:34 PM
	 * @param is
	 * @return byte[]
	 */
	public static final byte[] readAvailable(InputStream is) {
		if (is != null) {
			try {
				int aviable = is.available();
				if (aviable > 0) {
					byte[] ret = new byte[aviable];
					is.read(ret);
					return ret;
				}
			} catch (IOException e) {
				logger.error(UTrace.trace(e));
			} finally {
				try {
					is.close();
				} catch (IOException ignore) {
				}
			}
		}
		return IEmpty.bytes;
	}

	public static final byte[] read(InputStream is) {
		if (is != null) {
			try {
				int total = 0;
				List<byte[]> bytesS = new ArrayList<byte[]>();
				boolean isFinished = false;
				do {
					byte[] t = new byte[MAX_LENGTH_PER_READ];
					int totalRead = is.read(t);
					switch (totalRead) {
					case MAX_LENGTH_PER_READ://读满了
						bytesS.add(t);
						total += totalRead;
						break;
					case -1://读完了
						isFinished = true;
						break;
					case 0://没有读到，直接跳过进入下一轮循环
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
				logger.error(UTrace.trace(e));
			} finally {
				try {
					is.close();
				} catch (IOException ignore) {
				}
			}
		}
		return IEmpty.bytes;
	}

	public static String readUTF8(InputStream is) {
		return read(is, Charsets.UTF_8);
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
				logger.error(UTrace.trace(e));
				return null;
			} finally {
				try {
					is.close();
				} catch (IOException ignore) {
				}
			}
		}
	}

	public static final void write(OutputStream os, String message) {
		write(os, message.getBytes());
	}

	public static final void write(OutputStream os, String message, Charset charset) {
		write(os, message.getBytes(charset));
	}

	public static final void write(OutputStream os, byte[] message) {
		if (os != null) {
			try {
				os.write(message);
				os.flush();
			} catch (Throwable e) {
				logger.error(UTrace.trace(e));
			} finally {
				if (os != null) {
					try {
						os.close();
					} catch (IOException ignore) {
					}
				}
			}
		}
	}
}
