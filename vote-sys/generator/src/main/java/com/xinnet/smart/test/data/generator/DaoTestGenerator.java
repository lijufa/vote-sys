package com.xinnet.smart.test.data.generator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.IEmpty;
import com.xinnet.smart.base.util.UString;
import com.xinnet.smart.test.data.abstracts.AbstractClassGenerator;
import com.xinnet.smart.test.data.impl.UTypeMysql;
import com.xinnet.smart.test.model.Composite;
import com.xinnet.smart.test.model.FieldDesc;
import com.xinnet.smart.test.util.UName;

public class DaoTestGenerator extends AbstractClassGenerator {
	static final Logger LOGGER = LoggerFactory.getLogger(DaoTestGenerator.class);
	final EntityGenerator entity;
	final DaoGenerator dao;

	public DaoTestGenerator(EntityGenerator entity, DaoGenerator dao) {
		this.entity = entity;
		this.dao = dao;
	}

	@Override
	protected String getClassName(String tableName) {
		return com.xinnet.smart.test.util.UName.toDaoName(tableName) + "Test";
	}

	@Override
	public String getPackageName() {
		return dao.getPackageName();
	}

	@Override
	protected String getPath() {
		return "src/test/java/" + getPackageName().replace('.', '/') + "/";
	}

	@Override
	protected String annotation(String tableName) {
		return IEmpty.STRING;
	}
	int int1 = 1;
	int int2 = 2;
	int iUnique = 7;

	@Override
	protected String getImports(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("import java.util.Date;\n");
		sb.append("import java.util.List;\n");
		sb.append("import javax.annotation.Resource;\n");
		sb.append("import org.junit.After;\n");
		sb.append("import org.junit.Assert;\n");
		sb.append("import org.junit.Before;\n");
		sb.append("import org.junit.Test;\n");
		sb.append("import ");
		sb.append(entity.getPackageName());
		sb.append(".*;\n");
		sb.append("import com.xinnet.smart.cloud.generic.DaoGenericTest;\n");
		sb.append("import com.xinnet.smart.common.constant.IConstants;\n");
		return sb.toString();
	}

	@Override
	protected String defineClass(String tableName, Map<String, FieldDesc> fieldS) {
		return " extends DaoGenericTest";
	}

	@Override
	protected String genericBody(String tableName, Map<String, FieldDesc> fieldS) {
		DaoName = UName.toDaoName(tableName);
		daoName = UString.toLowerCaseFirst(DaoName);
		EntityName = UName.toBeanName(tableName);
		entityName = UString.toLowerCaseFirst(EntityName);
		database.foreignKeys(tableName, fieldS);
		StringBuilder sb = new StringBuilder();
		Composite dependencies = database.dependencies(tableName, false);
		List<String> asc = dependencies.asc();
		List<String> desc = dependencies.desc();
		{
			resource(sb, tableName, DaoName, daoName, EntityName, entityName);
			for (String reference : desc) {
				resource(sb, reference);
			}
		}
		{
			sb.append("\n\t@Before\n");
			sb.append("\tpublic void before");
			//sb.append(UName.toMethodName(tableName));
			sb.append("() {\n");
			{
				//依赖调用
				for (String reference : asc) {
					before(sb, reference);
				}
				before(sb, tableName, DaoName, daoName, EntityName, entityName);
			}
			sb.append("\t}\n");
		}
		{
			sb.append("\n\t@After\n");
			sb.append("\tpublic void after");
			//sb.append(UName.toMethodName(tableName));
			sb.append("() {\n");
			{
				after(sb, tableName);
				//依赖调用
				for (String reference : desc) {
					after(sb, reference);
				}
			}
			sb.append("\t}\n");
		}
		{
			sb.append("\n\t@Test\n");
			sb.append("\tpublic void update() {\n");
			{
				eachForUpdate(sb, entityName, fieldS.values());
				sb.append("\t\t");
				sb.append(daoName);
				sb.append(".update(");
				sb.append(entityName);
				sb.append(");\n");
			}
			sb.append("\t}\n");
		}
		//test dao.select
		{
			sb.append("\n\t@Test\n");
			sb.append("\tpublic void select() {\n");
			//方法内部
			{
				{
					sb.append("\t\t");
					sb.append(EntityName);
					sb.append(" where = new ");
					sb.append(EntityName);
					sb.append("();\n");
				}
				{
					sb.append("\t\twhere.setId(");
					sb.append(entityName);
					sb.append("Id);\n");
				}
				{
					sb.append("\t\tList<");
					sb.append(EntityName);
					sb.append("> list = ");
					sb.append(daoName);
					sb.append(".select(where);\n");
				}
				{
					sb.append("\t\t");
					sb.append(EntityName);
					sb.append(" entity = list.get(0);\n");
				}
				Map<String, FieldDesc> fieldMap = database.desc(tableName);
				assertsNotNull(sb, fieldMap.values());
			}
			sb.append("\t}\n");
		}
		//test dao.selectOne
		{
			sb.append("\n\t@Test\n");
			sb.append("\tpublic void selectOne() {\n");
			//方法内部
			{
				{
					sb.append("\t\t");
					sb.append(EntityName);
					sb.append(" where = new ");
					sb.append(EntityName);
					sb.append("();\n");
				}
				{
					sb.append("\t\twhere.setId(");
					sb.append(entityName);
					sb.append("Id);\n");
				}
				{
					sb.append("\t\t");
					sb.append(EntityName);
					sb.append(" entity = ");
					sb.append(daoName);
					sb.append(".selectOne(where);\n");
				}
				Map<String, FieldDesc> fieldMap = database.desc(tableName);
				assertsNotNull(sb, fieldMap.values());
			}
			sb.append("\t}\n");
		}
		//test dao.selectById
		{
			sb.append("\n\t@Test\n");
			sb.append("\tpublic void selectById() {\n");
			//方法内部
			{
				{
					sb.append("\t\t");
					sb.append(EntityName);
					sb.append(" entity = ");
					sb.append(daoName);
					sb.append(".selectById(");
					sb.append(entityName);
					sb.append("Id);\n");
				}
				Map<String, FieldDesc> fieldMap = database.desc(tableName);
				assertsNotNull(sb, fieldMap.values());
			}
			sb.append("\t}\n");
		}
		int1 += 2;
		int2 += 2;
		return sb.toString();
	}

	void assertsNotNull(StringBuilder sb, Collection<FieldDesc> values) {
		for (FieldDesc field : values) {
			if (field.isNull()) {
				continue;
			}
			sb.append("\t\tAssert.assertNotNull(entity.get");
			sb.append(UName.toMethodName(field.getName()));
			sb.append("());\n");
		}
	}
	String DaoName;
	String daoName;
	String EntityName;
	String entityName;

	/**
	 * 依赖注入
	 * @author meitao
	 * @date 2014-8-14 下午4:23:19
	 * @param sb
	 * @param tableName
	 * @param depth
	 */
	private void resource(StringBuilder sb, String tableName) {
		String EntityName = UName.toBeanName(tableName);
		String entityName = UString.toLowerCaseFirst(EntityName);
		String DaoName = UName.toDaoName(tableName);
		String daoName = UString.toLowerCaseFirst(DaoName);
		resource(sb, tableName, DaoName, daoName, EntityName, entityName);
	}

	private void resource(StringBuilder sb, String tableName, String DaoName, String daoName, String EntityName, String entityName) {
		{
			sb.append("\t@Resource\n");
			sb.append("\t");
			sb.append(DaoName);
			sb.append(" ");
			sb.append(daoName);
			sb.append(";\n");
		}
		{
			sb.append("\t");
			sb.append(EntityName);
			sb.append(" ");
			sb.append(entityName);
			sb.append(";\n");
		}
		{
			FieldDesc field = database.desc(tableName).get(primaryKey);
			sb.append("\t");
			sb.append(UTypeMysql.parseSimpleName(field.getType()));
			sb.append(" ");
			sb.append(entityName);
			sb.append("Id;\n");
		}
	}

	private void before(StringBuilder sb, String tableName) {
		String EntityName = UName.toBeanName(tableName);
		String entityName = UString.toLowerCaseFirst(EntityName);
		String DaoName = UName.toDaoName(tableName);
		String daoName = UString.toLowerCaseFirst(DaoName);
		before(sb, tableName, DaoName, daoName, EntityName, entityName);
	}

	private void before(StringBuilder sb, String tableName, String DaoName, String daoName, String EntityName, String entityName) {
		{
			sb.append("\t\t");
			sb.append(entityName);
			sb.append(" = new ");
			sb.append(EntityName);
			sb.append("();\n");
		}
		Map<String, FieldDesc> fieldMap = database.desc(tableName);
		database.foreignKeys(tableName, fieldMap);
		eachForInsert(sb, entityName, fieldMap.values());
		{
			sb.append("\t\t");
			sb.append(entityName);
			sb.append("Id = ");
			FieldDesc field = database.desc(tableName).get(this.primaryKey);
			String type = field.getType();
			String javaType = UTypeMysql.parseClassName(type);
			switch (javaType) {
			case "java.lang.Long":
				sb.append("IConstants.RANDOM.nextLong() & 0x7fffffffffffffffL");
				break;
			case "java.lang.Integer":
				if (type.startsWith("tinyint")) {
					sb.append("IConstants.RANDOM.nextInt(99)");
				} else {
					sb.append("IConstants.RANDOM.nextInt(2147483647)");
				}
				break;
			default:
				sb.append(type);//TODO 如果有其他类型主键会显示出来类型
				break;
			}
			sb.append(";\n");
		}
		{
			sb.append("\t\t");
			sb.append(entityName);
			sb.append(".setId(");
			sb.append(entityName);
			sb.append("Id);\n");
		}
		{
			sb.append("\t\t");
			sb.append(daoName);
			sb.append(".insert(");
			sb.append(entityName);
			sb.append(");\n");
		}
	}

	private void after(StringBuilder sb, String tableName) {
		String EntityName = UName.toBeanName(tableName);
		String entityName = UString.toLowerCaseFirst(EntityName);
		String DaoName = UName.toDaoName(tableName);
		String daoName = UString.toLowerCaseFirst(DaoName);
		after(sb, daoName, entityName);
	}

	private void after(StringBuilder sb, String daoName, String entityName) {
		sb.append("\t\t");
		sb.append(daoName);
		sb.append(".delete(");
		sb.append(entityName);
		sb.append("Id);\n");
	}

	private void eachForInsert(StringBuilder sb, String entityName, Iterable<FieldDesc> fieldS) {
		for (FieldDesc field : fieldS) {
			if ("id".equals(field.getName())) {
				continue;
			}
			if (field.isNull()) {
				continue;
			}
			sb.append("\t\t");
			sb.append(entityName);
			sb.append(".set");
			sb.append(UName.toMethodName(field.getName()));
			sb.append("(");
			if (primaryKey.equals(field.getName())) {
				sb.append(entityName);
				sb.append(UName.toMethodName(primaryKey));
			} else if (field.getForeignKey() != null) {
				sb.append(UString.toLowerCaseFirst(UName.toBeanName(field.getForeignKey().getRefTableName())));
				sb.append(UName.toMethodName(primaryKey));
			} else {
				String type = field.getType();
				String javaType = UTypeMysql.parseClassName(type);
				switch (javaType) {
				case "java.lang.Long":
					sb.append("IConstants.RANDOM.nextLong() & 0x7fffffffffffffffL");
					break;
				case "java.lang.Integer":
					if (type.startsWith("tinyint")) {
						sb.append("IConstants.RANDOM.nextInt(99)");
					} else {
						sb.append("IConstants.RANDOM.nextInt(2147483647)");
					}
					break;
				case "java.lang.Double":
					sb.append("IConstants.RANDOM.nextDouble()");
					break;
				case "java.lang.String":
					sb.append('"');
					if (type.startsWith("enum")) {
						//枚举的话应该至少有两个类型，否则不合理
						sb.append(type.substring(type.indexOf('\'') + 1, type.indexOf("',")));
					} else {
						sb.append(int1 * iUnique);
					}
					sb.append('"');
					break;
				case "java.util.Date":
					sb.append("new Date()");
					break;
				case "java.lang.Boolean":
					sb.append("false");
					break;
				default:
					sb.append(type);//TODO 如果有其他类型字段会显示出来类型
					break;
				}
			}
			sb.append(");\n");
		}
	}

	private void eachForUpdate(StringBuilder sb, String entityName, Iterable<FieldDesc> fieldS) {
		for (FieldDesc field : fieldS) {
			if ("id".equals(field.getName())) {
				continue;
			}
			if (field.getForeignKey() != null) {
				continue;
			}
			sb.append("\t\t");
			sb.append(entityName);
			sb.append(".set");
			sb.append(UName.toMethodName(field.getName()));
			sb.append("(");
			String type = field.getType();
			String javaType = UTypeMysql.parseClassName(type);
			switch (javaType) {
			case "java.lang.Long":
				sb.append("IConstants.RANDOM.nextLong() & 0x7fffffffffffffffL");
				break;
			case "java.lang.Integer":
				if (type.startsWith("tinyint")) {
					sb.append("IConstants.RANDOM.nextInt(99)");
				} else {
					sb.append("IConstants.RANDOM.nextInt(2147483647)");
				}
				break;
			case "java.lang.Double":
				sb.append("IConstants.RANDOM.nextDouble()");
				break;
			case "java.lang.String":
				sb.append('"');
				if (type.startsWith("enum")) {
					//枚举的话应该至少有两个类型，否则不合理
					sb.append(type.substring(type.lastIndexOf(",'") + 2, type.lastIndexOf('\'')));
				} else {
					sb.append(int2 * iUnique);
				}
				sb.append('"');
				break;
			case "java.util.Date":
				sb.append("new Date()");
				break;
			case "java.lang.Boolean":
				sb.append("false");
				break;
			default:
				sb.append(type);
				break;
			}
			sb.append(");\n");
		}
	}

	@Override
	public void befores() {
		this.deletes();
	}
}
