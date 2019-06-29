package com.xinnet.smart.api.generic;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.codec.Charsets;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xinnet.smart.api.ApiHandler;
import com.xinnet.smart.base.util.UFile;
import com.xinnet.smart.test.model.HashLists;

/**
 * java版接口单元测试用例，以便自动化测试
 * @author meitao
 * @date 2014-6-23 下午1:26:30
 */
public class ApiHandlerForJUnit extends ApiHandlerAbstract implements ApiHandler {
	final HashLists<String, String> returnTypeHash = new HashLists<String, String>();
	final String classNameSuffix = "Test";
	final FilenameFilter filenameFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return name.startsWith("applicationContext") && name.endsWith(".xml");
		}
	};
	final String[] resources = new File("src/main/resources").list(filenameFilter);
	String imports;
	File currentFile;

	@Override
	public void handle(Class<?> c) {
		if (isController(c)) {
			currentFile = getFile(c);
			StringBuilder sb = new StringBuilder();
			sb.append("package ");
			sb.append(c.getPackage().getName());
			sb.append(";\n\n");
			sb.append(imports);
			sb.append("public class ");
			sb.append(c.getSimpleName());
			sb.append(classNameSuffix);
			sb.append(" extends AbstractJUnit4SpringContextTests {\n");
			{
				sb.append("\t@Resource\n");
				sb.append("\t");
				sb.append(c.getSimpleName());
				sb.append(" controller;\n");
				sb.append("\n");
				UFile.write(currentFile, sb.toString(), Charsets.UTF_8, false);
				handleMethods(c);
			}
			UFile.write(currentFile, "}", Charsets.UTF_8, true);
		}
	}

	@Override
	public void handle(Class<?> c, Method method, RequestMapping requestMapping) {
		//System.out.println(c.getName() + '.' + method.getName());
		StringBuilder sb = new StringBuilder();
		sb.append("\t@Test\n");
		sb.append("\tpublic void ");
		sb.append(method.getName());
		sb.append("() throws Exception {\n");
		{
			sb.append("\t\tAssert.assertNotNull(controller);\n");
			handleParameters(sb, method);
			String returnType = method.getReturnType().getName();
			switch (returnType) {
			case "void":
				sb.append("\t\tcontroller.");
				sb.append(method.getName());
				sb.append("(");
				handleParameters2(sb, method);
				sb.append(");\n");
				break;
			case "boolean":
				sb.append("\t\tAssert.assertTrue(controller.");
				sb.append(method.getName());
				sb.append("(");
				handleParameters2(sb, method);
				sb.append("));\n");
				break;
			case "java.lang.String":
				sb.append("\t\tString ret = controller.");
				sb.append(method.getName());
				sb.append("(");
				handleParameters2(sb, method);
				sb.append(");\n");
				sb.append("\t\tif (!\"success\".equals(ret)) {\n");
				{
					sb.append("\t\t\tSystem.out.println(ret);\n");
					sb.append("\t\t\tAssert.assertTrue(false);\n");
				}
				sb.append("\t\t}\n");
				break;
			default:
				returnTypeHash.putIfNotExist(returnType, method.toString());
				sb.append("\t\tAssert.assertNotNull(controller.");
				sb.append(method.getName());
				sb.append("(");
				handleParameters2(sb, method);
				sb.append("));\n");
				break;
			}
		}
		sb.append("\t}\n\n");
		UFile.write(currentFile, sb.toString(), Charsets.UTF_8, true);
	}

	public void handleParameters(StringBuilder sbTarget, Method method) {
		String s = method.toString();
		//System.out.println(s);
		String strParameters = s.substring(s.indexOf('(') + 1, s.length() - 1);
		if (strParameters.length() > 0) {
			//System.out.println(strParameters);
			String[] parameterTypeStrings = strParameters.split(",");
			for (int i = 0; i < parameterTypeStrings.length; i++) {
				StringBuilder sb = new StringBuilder();
				String parameterTypeString = parameterTypeStrings[i];
				//System.out.println(parameterTypeString);
				{
					sb.append("\t\t");
					sb.append(parameterTypeString);
					sb.append(" p");
					sb.append(i);
					sb.append(" = ");
					switch (parameterTypeString) {
					case "int":
						sb.append("0");
						break;
					case "long":
						sb.append("0L");
						break;
					case "java.lang.String":
						sb.append("\"\"");
						break;
					case "javax.servlet.http.HttpServletRequest":
						sb.append("new MockHttpServletRequest()");
						break;
					case "javax.servlet.http.HttpServletResponse":
						sb.append("new MockHttpServletResponse()");
						break;
					default:
						sb.append("EasyMock.createMock(");
						sb.append(parameterTypeString);
						sb.append(".class)");
						break;
					}
					sb.append(";\n");
				}
				switch (parameterTypeString) {
				case "org.springframework.web.multipart.MultipartFile":
					handleParameterMultipartFile(sb, i);
					break;
				}
				sbTarget.append(sb);
			}
		}
	}

	public void handleParameterMultipartFile(StringBuilder sb, int i) {
		sb.append("\t\t{\n");
		{
			sb.append("\t\t\tInputStream inputStream = EasyMock.createMock(InputStream.class);\n");
			sb.append("\t\t\tEasyMock.expect(p" + i + ".getBytes()).andReturn(\"getBytes\".getBytes());\n");
			sb.append("\t\t\tEasyMock.expect(p" + i + ".getInputStream()).andReturn(inputStream);\n");
			sb.append("\t\t\tEasyMock.expect(p" + i + ".getOriginalFilename()).andReturn(\"getOriginalFilename\");\n");
			sb.append("\t\t\tEasyMock.replay(p1);\n");
		}
		sb.append("\t\t}\n");
	}

	public void handleParameterDefault(StringBuilder sb, Class<?> parameterType) {
		sb.append("EasyMock.createMock(");
		sb.append(parameterType.getName());
		sb.append(".class)");
	}

	public void handleParameters2(StringBuilder sb, Method method) {
		try {
			Class<?>[] parameterTypes = method.getParameterTypes();
			int i = 0, j = parameterTypes.length;
			while (i < j) {
				sb.append("p");
				sb.append(i);
				i++;
				if (i < j) {
					sb.append(",");
				}
			}
		} catch (Throwable e) {
		}
	}

	@Override
	public void before() {
		StringBuilder sb = new StringBuilder();
		sb.append("import java.io.InputStream;\n");
		sb.append("\n");
		sb.append("import javax.annotation.Resource;\n");
		sb.append("\n");
		sb.append("import org.easymock.EasyMock;\n");
		sb.append("import org.junit.Assert;\n");
		sb.append("import org.junit.Test;\n");
		sb.append("import org.junit.runner.RunWith;\n");
		sb.append("import org.springframework.mock.web.MockHttpServletRequest;\n");
		sb.append("import org.springframework.mock.web.MockHttpServletResponse;\n");
		sb.append("import org.springframework.test.context.ContextConfiguration;\n");
		sb.append("import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;\n");
		sb.append("import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;\n");
		sb.append("\n");
		sb.append("@RunWith(SpringJUnit4ClassRunner.class)\n");
		sb.append("@ContextConfiguration(locations = {");
		{
			int i = 0, j = resources.length;
			while (i < j) {
				String resource = resources[i];
				sb.append("\"classpath:");
				sb.append(resource);
				sb.append("\"");
				i++;
				if (i < j) {
					sb.append(',');
				}
			}
		}
		sb.append("})\n");
		imports = sb.toString();
	}

	@Override
	public void after() {
		Iterator<Entry<String, List<String>>> iterator = returnTypeHash.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, List<String>> entry = iterator.next();
			System.out.println(entry.getKey());
			List<String> ss = entry.getValue();
			for (String s : ss) {
				System.out.println("\t" + s);
			}
		}
	}

	public HashLists<String, String> getReturnTypeHash() {
		return returnTypeHash;
	}

	public String getClassNameSuffix() {
		return classNameSuffix;
	}

	public FilenameFilter getFilenameFilter() {
		return filenameFilter;
	}

	public String[] getResources() {
		return resources;
	}

	public String getImports() {
		return imports;
	}
}
