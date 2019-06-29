package com.xinnet.smart.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;

/**
 * 文件工具
 * @author meitao
 * @date 2014年7月16日 下午3:17:29
 */
public class UFile {
	static final Logger logger = LoggerFactory.getLogger(UFile.class);

	/**
	 * 无论是否存在都尝试创建相关目录
	 * @author meitao
	 * @date Jun 9, 2015 4:07:43 PM
	 * @param path void
	 */
	public static void mkdirIfNotExists(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	protected static URL getRefUrl(String fileName, Class<?> refClass) {
		StringBuilder sb = new StringBuilder();
		if (fileName == null || refClass == null) {
			return null;
		}
		//logger.debug(refClass.getName());
		String s = getRootPath(refClass);
		//logger.debug(s);
		if (!s.startsWith("file:")) {
			sb.append("file:");
		}
		if (s.indexOf("!/") != -1) {
			sb.append("jar:");
		}
		sb.append(s);
		sb.append(fileName);
		s = sb.toString();
		//logger.debug(s);
		try {
			return new URL(sb.toString());
		} catch (MalformedURLException e) {
			logger.error(UTrace.trace(e));
			return null;
		}
	}

	public static byte[] getRefBytes(String fileName, Class<?> refClass) {
		try {
			if (fileName != null && refClass != null) {
				File file = new File(fileName);
				if (file.exists()) {
					return UFile.read(file);
				}
				URL url = getRefUrl(fileName, refClass);
				if (url != null) {
					return UStream.readAvailable(url.openStream());
				}
			}
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
		return IEmpty.bytes;
	}

	public static String getRefString(String fileName, Class<?> refClass) {
		try {
			byte[] bytes = getRefBytes(fileName, refClass);
			if (bytes != null && bytes.length > 0) {
				return new String(bytes, Charsets.UTF_8);
			}
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
		return IEmpty.STRING;
	}

	public static <T> T getResourceObject(String fileName, Class<T> type, Class<?> refClass) {
		return UJson.toObject(UFile.getResourceString(fileName, refClass), type);
	}

	@Deprecated
	public static <T> T getResourceObject(String fileName, Class<T> refClass) {
		return UJson.toObject(UFile.getResourceString(fileName, refClass), refClass);
	}

	public static String getResourceString(String fileName, Class<?> refClass) {
		try {
			return UStream.read(refClass.getClassLoader().getResourceAsStream(fileName), Charsets.UTF_8);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		}
		return IEmpty.STRING;
	}

	//	public static String getResourceString(String fileName) {
	//		try {
	//			return UStream.read(UFile.class.getClassLoader().getSystemClassLoader().getResourceAsStream(fileName), Charsets.UTF_8);
	//		} catch (Throwable e) {
	//			logger.error(UTrace.trace(e));
	//		}
	//		return IEmpty.STRING;
	//	}
	public static <T> T getRefObject(String fileName, Class<T> modelClass, Class<?> refClass) {
		return UJson.tryObject(getRefString(fileName, refClass), modelClass);
	}

	public static <T> T getObject(String fileName, Class<T> c) {
		return getRefObject(fileName, c, c);
	}

	private static String getRootPath(Class<?> c) {
		if (c == null) {
			return null;
		}
		String classPath = c.getResource(IEmpty.STRING).getPath();
		//logger.debug(classPath);
		String packagePath = c.getPackage().getName().replace('.', '/');
		//logger.debug(packagePath);
		return classPath.substring(0, classPath.indexOf(packagePath));
	}

	public static File prepare(String path) {
		int index = path.lastIndexOf(File.separatorChar);
		if (index != -1) {
			new File(path.substring(0, index)).mkdirs();
		}
		return new File(path);
	}

	private static byte[] read(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int l = (int) file.length();
			byte[] ret = new byte[l];
			fis.read(ret);
			return ret;
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
			return IEmpty.bytes;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public static String read(File file, Charset charset) {
		if (file.exists()) {
			return new String(read(file), charset);
		} else {
			logger.error(file.getPath());
			return IEmpty.STRING;
		}
	}

	public static void write(File file, byte[] bytes, boolean append) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file, append);
			fos.write(bytes);
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public static void write(File file, String s, Charset charset, boolean append) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file, append);
			fos.write(s.getBytes(charset));
		} catch (Throwable e) {
			logger.error(UTrace.trace(e));
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 删除文件
	 * @author gaoxj
	 * @date 2014年11月5日 下午3:33:58
	 * @param path
	 * @return
	 */
	public static boolean deleteFile(String path) {
		boolean flag = false;
		File file = new File(path);
		/** 路径为文件且不为空则进行删除 **/
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
}
