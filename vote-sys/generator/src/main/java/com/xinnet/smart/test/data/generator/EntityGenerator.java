package com.xinnet.smart.test.data.generator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;
import com.xinnet.smart.test.data.abstracts.AbstractClassGenerator;
import com.xinnet.smart.test.data.impl.UTypeMysql;
import com.xinnet.smart.test.model.FieldDesc;
import com.xinnet.smart.test.util.UName;

public abstract class EntityGenerator extends AbstractClassGenerator {
	static final Logger logger = LoggerFactory.getLogger(EntityGenerator.class);

	@Override
	public String getClassName(String tableName) {
		return UName.toBeanName(tableName);
	}

	@Override
	protected String getPath() {
		return "src/main/java/" + getPackageName().replace('.', '/') + "/";
	}

	@Override
	protected String annotation(String tableName) {
		logger.debug(tableName);
		StringBuilder sb = new StringBuilder();
		sb.append("@Table(name = \"");
		sb.append(tableName);
		sb.append("\")\n");
		return sb.toString();
	}

	@Override
	protected String getImports(String tableName) {
		//logger.debug(tableName);
		StringBuilder sb = new StringBuilder();
		sb.append("import javax.persistence.Table;\n");
		sb.append("import com.fasterxml.jackson.annotation.JsonIgnore;\n");
		sb.append("import com.fasterxml.jackson.annotation.JsonProperty;\n");
		sb.append("import org.springframework.format.annotation.DateTimeFormat;\n");
		return sb.toString();
	}

	@Override
	protected String defineClass(String tableName, Map<String, FieldDesc> fieldS) {
		//		StringBuilder sb = new StringBuilder();
		//		sb.append(" implements java.io.Serializable");
		//		return sb.toString();
		return IEmpty.STRING;
	}

	@Override
	protected String genericBody(String tableName, Map<String, FieldDesc> fieldS) {
		//logger.debug(tableName);
		StringBuilder sb = new StringBuilder();
		//TODO 为了单一职责，还是不采用兼容批量方式为好
		generateEmpty(sb, tableName, fieldS);
		FieldDesc primaryKeyField = fieldS.get(primaryKey);
		{
			FieldDesc field = primaryKeyArray(primaryKeyField);
			if (field != null) {
				fieldS.put(field.getName(), field);
			}
		}
		{
			FieldDesc field = where();
			fieldS.put(field.getName(), field);
		}
		sb.append(generateFields(fieldS));
		//sb.append(newInstanceMethod(tableName, fieldS.get("id")));
		sb.append(generateMethods(fieldS));
		//generateColumnJson(sb, fieldS.values());
		return sb.toString();
	}

	private void generateEmpty(StringBuilder sb, String tableName, Map<String, FieldDesc> fieldS) {
		sb.append("\tpublic static final ");
		sb.append(getClassName(tableName));
		sb.append("[] EMPTY = new ");
		sb.append(getClassName(tableName));
		sb.append("[0];\n");
	}

	private String generateFields(Map<String, FieldDesc> fieldS) {
		StringBuilder sb = new StringBuilder();
		for (FieldDesc field : fieldS.values()) {
			if (field.getJavaType() == null) {
				field.setJavaType(UTypeMysql.parseSimpleName(field.getType()));
			}
			sb.append(generateField(field));
		}
		return sb.toString();
	}

	private void comment(StringBuilder sb, FieldDesc field) {
		sb.append("\t/**\n");
		sb.append("\t *");
		sb.append(field.getComment());
		sb.append("\n");
		sb.append("\t */\n");
	}

	private String generateField(FieldDesc field) {
		StringBuilder sb = new StringBuilder();
		comment(sb, field);
		if (field.isJsonIgnore()) {
			sb.append("\t@JsonIgnore\n");
		} else if (field.getName().indexOf('_') != -1) {
			sb.append("\t@JsonProperty(\"");
			sb.append(field.getName());
			sb.append("\")\n");
		}
		if ("java.util.Date".equals(field.getJavaType())) {
			sb.append("\t@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
			sb.append("\n");
		}
		sb.append("\t");
		sb.append(field.getJavaType());
		sb.append(" ");
		sb.append(UName.toFieldName(field.getName()));
		sb.append(";\n");
		return sb.toString();
	}

	private String generateMethods(Map<String, FieldDesc> fieldS) {
		StringBuilder sb = new StringBuilder();
		for (FieldDesc field : fieldS.values()) {
			sb.append(generateMethod(field));
		}
		return sb.toString();
	}

	private String generateMethod(FieldDesc field) {
		String methodName = UName.toMethodName(field.getName());
		String fieldName = UName.toFieldName(field.getName());
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		comment(sb, field);
		sb.append("\tpublic final ");
		sb.append(field.getJavaType());
		sb.append(" get");
		sb.append(methodName);
		sb.append("() {\n");
		sb.append("\t\treturn ");
		sb.append(fieldName);
		sb.append(";\n");
		sb.append("\t}\n");
		sb.append("\n");
		comment(sb, field);
		sb.append("\tpublic final void set");
		sb.append(methodName);
		sb.append("(");
		sb.append(field.getJavaType());
		sb.append(" ");
		sb.append(fieldName);
		sb.append(") {\n");
		sb.append("\t\tthis.");
		sb.append(fieldName);
		sb.append(" = ");
		sb.append(fieldName);
		sb.append(";\n");
		sb.append("\t}\n");
		return sb.toString();
	}

	private void generateColumnJson(StringBuilder sb, Collection<FieldDesc> fieldS) {
		sb.append("\n\tpublic static String getColumnJson() {\n");
		sb.append("\t\treturn \"{");
		Iterator<FieldDesc> iterator = fieldS.iterator();
		while (iterator.hasNext()) {
			FieldDesc field = iterator.next();
			String fieldName = UName.toFieldName(field.getName());
			sb.append("\\\"");
			sb.append(fieldName);
			sb.append("\\\":\\\"");
			sb.append(field.getComment());
			sb.append("\\\"");
		}
		sb.append("}\";\n");
		sb.append("\t}\n");
	}

	@Override
	public void befores() {
		this.deletes();
	}
}
