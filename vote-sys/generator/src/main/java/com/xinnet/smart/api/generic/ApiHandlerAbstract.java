package com.xinnet.smart.api.generic;

import java.io.File;
import java.lang.reflect.Method;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xinnet.smart.api.ApiHandler;
import com.xinnet.smart.api.ApiIterator;
import com.xinnet.smart.base.util.UFile;

/**
 * 接口处理器抽象实现
 * @author meitao
 * @date 2014-6-20 下午2:19:08
 */
public abstract class ApiHandlerAbstract implements ApiHandler {
	public void test() {
		new ApiIterator(getClass()).handle("src/main/java");
	}
	final String ext = "Test.java";
	final String rootAbsolutePathForTest = new File("src/test/java").getAbsolutePath();

	@Override
	public File getFile(Class<?> c) {
		String className = c.getName();
		String path = rootAbsolutePathForTest + File.separatorChar + className.replace('.', File.separatorChar) + ext;
		// System.out.println(path);
		return UFile.prepare(path);
	}

	/**
	 * @author meitao
	 * @date 2014-6-20 下午2:16:16
	 * @param c
	 * @see com.xinnet.smart.api.ApiHandler#handle(java.lang.Class)
	 */
	@Override
	public void handle(Class<?> c) {
		//System.out.println(c);
		if (isController(c)) {
			//System.out.println(c);
			handleMethods(c);
		}
	}

	/**
	 * 判断控制器
	 * @author meitao
	 * @date 2014-6-20 下午2:50:09
	 * @param c
	 * @return
	 */
	protected boolean isController(Class<?> c) {
		return c.getAnnotation(Controller.class) != null;
	}

	/**
	 * 遍历方法
	 * @author meitao
	 * @date 2014-6-20 下午2:50:21
	 * @param c
	 * @see org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping#determineUrlsForHandler(java.lang.String)
	 */
	protected void handleMethods(final Class<?> c) {
		ReflectionUtils.doWithMethods(c, new ReflectionUtils.MethodCallback() {
			@Override
			public void doWith(Method method) {
				RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
				if (requestMapping != null) {
					//System.out.println(c);
					handle(c, method, requestMapping);
				}
			}
		}, ReflectionUtils.USER_DECLARED_METHODS);
	}
}
