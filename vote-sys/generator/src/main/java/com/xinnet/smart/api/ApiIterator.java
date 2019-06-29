package com.xinnet.smart.api;

import java.io.File;

/**
 * 接口遍历器
 * @author meitao
 * @date 2014-6-23 下午1:25:21
 */
public class ApiIterator {
	String ext = ".java";
	ApiHandler handler;

	public ApiIterator(Class<? extends ApiHandler> handleClass) {
		try {
			handler = handleClass.getConstructor().newInstance();
		} catch (Throwable e) {
		}
	}

	public void handle(String path) {
		if (handler != null) {
			handler.before();
			File file = new File(path);
			String rootAbsolutePath = file.getAbsolutePath();
			if (file.isDirectory()) {
				handleDirectory(file, rootAbsolutePath);
			} else {
				handleFile(file, rootAbsolutePath);
			}
			handler.after();
		}
	}

	public void handleDirectory(File directory, String rootAbsolutePath) {
		File[] files = directory.listFiles();
		try {
			for (File file : files) {
				if (file.isDirectory()) {
					handleDirectory(file, rootAbsolutePath);
				} else {
					handleFile(file, rootAbsolutePath);
				}
			}
		} catch (Throwable e) {
		}
	}

	public void handleFile(File file, String rootAbsolutePath) {
		try {
			if (file.getName().endsWith(ext)) {
				String path = file.getAbsolutePath();
				String className = path.substring(rootAbsolutePath.length() + 1, path.indexOf(ext)).replace(File.separatorChar, '.');
				if (className.startsWith("com.xinnet")) {
					handler.handle(Class.forName(className));
				}
			}
		} catch (Throwable e) {
		}
	}
}
