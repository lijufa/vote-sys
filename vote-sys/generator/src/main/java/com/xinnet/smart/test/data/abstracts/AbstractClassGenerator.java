package com.xinnet.smart.test.data.abstracts;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;
import com.xinnet.smart.test.model.FieldDesc;

public abstract class AbstractClassGenerator extends AbstractFileGenerator {
	static final Logger logger = LoggerFactory.getLogger(AbstractClassGenerator.class);

	protected abstract String getClassName(String tableName);

	public abstract String getPackageName();

	protected abstract String getImports(String tableName);

	protected abstract String genericBody(String tableName, Map<String, FieldDesc> fieldS);

	protected String getClassType() {
		return "class";
	}

	protected String annotation(String tableName) {
		return IEmpty.STRING;
	}

	private String comment(String tableName) {
		return database.getComment(tableName);
	}

	protected String defineClass(String tableName, Map<String, FieldDesc> fieldS) {
		return IEmpty.STRING;
	}

	@Override
	protected String generate(String tableName) {
		//logger.debug(tableName);
		Map<String, FieldDesc> fieldS = database.showFullColumns(tableName);
		StringBuilder sb = new StringBuilder();
		sb.append("package ");
		sb.append(getPackageName());
		sb.append(";\n\n");
		sb.append(getImports(tableName));
		sb.append("\n/**");
		sb.append("\n * ");
		sb.append(comment(tableName));
		sb.append("\n * @author meitao");
		//加时间不利于对比变化
		//sb.append("\n * @date ");
		//sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		sb.append("\n */");
		sb.append('\n');
		sb.append(annotation(tableName));
		sb.append("public ");
		sb.append(getClassType());
		sb.append(" ");
		sb.append(getClassName(tableName));
		sb.append(defineClass(tableName, fieldS));
		sb.append(" {\n");
		sb.append(genericBody(tableName, fieldS));
		sb.append("}");
		return sb.toString();
	}

	@Override
	protected String getFileName(String tableName) {
		return new StringBuilder().append(getClassName(tableName)).append(".java").toString();
	}

	@Override
	protected String getPath() {
		return "src/main/java/" + getPackageName().replace('.', '/') + "/";
	}
}
