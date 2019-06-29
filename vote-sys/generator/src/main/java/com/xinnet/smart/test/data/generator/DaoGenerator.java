package com.xinnet.smart.test.data.generator;

import com.xinnet.smart.test.data.abstracts.AbstractClassGenerator;
import com.xinnet.smart.test.data.impl.UTypeMysql;
import com.xinnet.smart.test.model.FieldDesc;
import com.xinnet.smart.test.util.UName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DaoGenerator extends AbstractClassGenerator {
	static final Logger LOGGER = LoggerFactory.getLogger(DaoGenerator.class);
	final EntityGenerator bean;

	public DaoGenerator(EntityGenerator bean) {
		this.bean = bean;
	}

	public String getPackageName(String url) {
		//return "com.xinnet.cloud.web.dao." + getDatabaseName(url);
		return "com.xinnet.dao";
	}

	static String getDatabaseName(String url) {
		int beginIndex = url.lastIndexOf('/');
		int endIndex = url.indexOf('?');
		return url.substring(beginIndex + 1, endIndex != -1 ? endIndex : url.length());
	}

	@Override
	protected String getClassName(String tableName) {
		return UName.toDaoName(tableName);
	}

	@Override
	protected String getPath() {
		return "src/main/java/" + getPackageName().replace('.', '/') + "/";
	}

	@Override
	public String getPackageName() {
		return getPackageName(url);
	}

	@Override
	protected String getImports(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("import java.util.List;\n");
		sb.append("import org.apache.ibatis.session.RowBounds;\n");
		sb.append("import ");
		sb.append(bean.getPackageName());
		sb.append('.');
		sb.append(bean.getClassName(tableName));
		sb.append(";\n");
		return sb.toString();
	}

	@Override
	protected String genericBody(String tableName, Map<String, FieldDesc> fieldS) {
		StringBuilder sb = new StringBuilder();
		{
			sb.append("\tint insert(");
			sb.append(bean.getClassName(tableName));
			sb.append(" bean);\n");
		}
		sb.append('\n');
		{
			sb.append("\tint inserts(java.lang.Iterable<");
			sb.append(bean.getClassName(tableName));
			sb.append("> bean);\n");
		}
		sb.append('\n');
		{
			sb.append("\t");
			sb.append(bean.getClassName(tableName));
			sb.append(" selectOne(");
			sb.append(bean.getClassName(tableName));
			sb.append(" where);\n");
		}
		sb.append('\n');
		{
			sb.append("\tList<");
			sb.append(bean.getClassName(tableName));
			sb.append("> select(");
			sb.append(bean.getClassName(tableName));
			sb.append(" where);\n");
		}
		sb.append('\n');
		{
			sb.append("\tList<");
			sb.append(bean.getClassName(tableName));
			sb.append("> selectPaged(");
			sb.append(bean.getClassName(tableName));
			sb.append(" where, RowBounds rowBounds);\n");
		}
		sb.append('\n');
		{
			sb.append("\tjava.lang.Long total(");
			sb.append(bean.getClassName(tableName));
			sb.append(" where);\n");
		}
		deletes(sb);
		FieldDesc primaryKeyField = fieldS.get(primaryKey);
		if (primaryKeyField != null) {
			String Id = UTypeMysql.parseSimpleName(primaryKeyField.getType());
			delete(sb, primaryKeyField);
			sb.append('\n');
			{
				sb.append("\tint update(");
				sb.append(bean.getClassName(tableName));
				sb.append(" set);\n");
			}
			sb.append('\n');
			{
				sb.append("\tint updates(");
				sb.append(bean.getClassName(tableName));
				sb.append(" sets);\n");
			}
			sb.append('\n');
			{
				sb.append("\tint updateAll(");
				sb.append(bean.getClassName(tableName));
				sb.append(" set);\n");
			}
			sb.append('\n');
			{
				sb.append("\t");
				sb.append(bean.getClassName(tableName));
				sb.append(" selectBy");
				sb.append(UName.toMethodName(primaryKeyField.getName()));
				sb.append("(");
				sb.append(Id);
				sb.append(" ");
				sb.append(UName.toFieldName(primaryKeyField.getName()));
				sb.append(");\n");
			}
			sb.append('\n');
			{
				sb.append("\tList<");
				sb.append(bean.getClassName(tableName));
				sb.append("> selectBy");
				sb.append(UName.toMethodName(primaryKeyField.getName()));
				sb.append("Array(");
				sb.append(Id);
				sb.append("[] ");
				sb.append(UName.toFieldName(primaryKeyField.getName()));
				sb.append("s);\n");
			}
			sb.append('\n');
			{
				sb.append("\tList<");
				sb.append(Id);
				sb.append("> select");
				sb.append(UName.toMethodName(primaryKeyField.getName()));
				sb.append("(");
				sb.append(bean.getClassName(tableName));
				sb.append(" where);\n");
			}
		}
		Map<String, FieldDesc> fieldMap = database.desc(tableName);
		database.foreignKeys(tableName, fieldMap);
		for (FieldDesc field : fieldMap.values()) {
			if (field.getForeignKey() != null && !field.getName().equals(this.primaryKey)) {
				{
					sb.append("\n\tList<");
					sb.append(UTypeMysql.parseSimpleName(field.getType()));
					sb.append("> select");
					sb.append(UName.toMethodName(field.getName()));
					sb.append("(");
					sb.append(bean.getClassName(tableName));
					sb.append(" where);\n");
				}
				{
					sb.append("\n\tList<");
					sb.append(bean.getClassName(tableName));
					sb.append("> selectBy");
					sb.append(UName.toMethodName(field.getName()));
					sb.append("(");
					sb.append(UTypeMysql.parseSimpleName(field.getType()));
					sb.append(" ");
					sb.append(UName.toFieldName(field.getName()));
					sb.append(");\n");
				}
				{
					sb.append("\n\tList<");
					sb.append(bean.getClassName(tableName));
					sb.append("> selectBy");
					sb.append(UName.toMethodName(field.getName()));
					sb.append("Array(");
					sb.append(UTypeMysql.parseSimpleName(field.getType()));
					sb.append("[] ");
					sb.append(UName.toFieldName(field.getName()));
					sb.append(");\n");
				}
				{
					sb.append("\n\tList<");
					sb.append(bean.getClassName(tableName));
					sb.append("> selectBy");
					sb.append(UName.toMethodName(field.getName()));
					sb.append("ArrayPaged(");
					sb.append(UTypeMysql.parseSimpleName(field.getType()));
					sb.append("[] ");
					sb.append(UName.toFieldName(field.getName()));
					sb.append(", RowBounds rowBounds);\n");
				}
			}
		}
		sb.append('\n');
		{
			sb.append("\tList<");
			sb.append(bean.getClassName(tableName));
			sb.append("> selectAll();\n");
		}
		return sb.toString();
	}

	private void deletes(StringBuilder sb) {
		sb.append('\n');
		sb.append("\tint deletes();\n");
	}

	private void delete(StringBuilder sb, FieldDesc primaryKeyField) {
		sb.append('\n');
		sb.append("\tint delete(");
		sb.append(UTypeMysql.parseSimpleName(primaryKeyField.getType()));
		sb.append(" ");
		sb.append(UName.toFieldName(primaryKeyField.getName()));
		sb.append(");\n");
	}

	@Override
	protected String getClassType() {
		return "interface";
	}

	@Override
	public void befores() {
	}

	@Override
	public void beforeEachDb() {
		this.deletes();
	}
}
