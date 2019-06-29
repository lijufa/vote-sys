package com.xinnet.smart.test.data.generator;

import com.xinnet.smart.test.Handler;
import com.xinnet.smart.test.data.abstracts.AbstractFileGenerator;
import com.xinnet.smart.test.data.impl.UTypeMysql;
import com.xinnet.smart.test.model.FieldDesc;
import com.xinnet.smart.test.util.UName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Mapper生成工具
 * @author meitao
 * @date 2014-8-15 上午9:28:16
 */
public class MapperGenerator extends AbstractFileGenerator {
	static final Logger logger = LoggerFactory.getLogger(MapperGenerator.class);
	final EntityGenerator entity;
	final DaoGenerator dao;

	public MapperGenerator(EntityGenerator entity, DaoGenerator dao) {
		this.entity = entity;
		this.dao = dao;
	}
	private final Handler<FieldDesc, String> setHandler = new Handler<FieldDesc, String>() {
		@Override
		public String handle(FieldDesc field) {
			StringBuilder sb = new StringBuilder();
			if (!primaryKey.equals(field.getName())) {
				String fieldName = UName.toFieldName(field.getName());
				sb.append("\t\t\t<if test=\"");
				sb.append(fieldName);
				sb.append(" != null\">\n");
				sb.append("\t\t\t\t`");
				sb.append(field.getName());
				sb.append("`=#{");
				sb.append(fieldName);
				sb.append("},\n");
				sb.append("\t\t\t</if>\n");
			}
			return sb.toString();
		}
	};
	private static final Handler<FieldDesc, String> fieldHandler = new Handler<FieldDesc, String>() {
		@Override
		public String handle(FieldDesc field) {
			StringBuilder sb = new StringBuilder();
			String name = field.getName();
			sb.append("t.`");
			sb.append(name);
			sb.append('`');
			if (name.indexOf('_') != -1) {
				sb.append(" AS `");
				sb.append(UName.toFieldName(field.getName()));
				sb.append('`');
			}
			return sb.toString();
		}
	};
	private static final Handler<FieldDesc, String> whereHandler = new Handler<FieldDesc, String>() {
		@Override
		public String handle(FieldDesc field) {
			return eachWhere(field);
		}
	};

	private static final String eachWhere(FieldDesc field) {
		StringBuilder sb = new StringBuilder();
		String fieldName = UName.toFieldName(field.getName());
		sb.append("\t\t\t<if test=\"");
		sb.append(fieldName);
		sb.append(" != null\"> AND t.`");
		sb.append(field.getName());
		sb.append("`");
		if (field.getComment().indexOf('&') == 0) {
			sb.append("&amp;");
		} else {
			sb.append('=');
		}
		sb.append("#{");
		sb.append(fieldName);
		sb.append("}</if>\n");
		return sb.toString();
	}

	@Override
	protected String getPath() {
		return "src/main/resource/mapper/";
	}

	@Override
	protected String getFileName(String tableName) {
		return UName.toEntityName(tableName) + "Mapper.xml";
	}

	@Override
	protected String generate(String tableName) {
		Map<String, FieldDesc> fieldS = database.showFullColumns(tableName);
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
		sb.append("<mapper namespace=\"");
		sb.append(dao.getPackageName(url));
		sb.append('.');
		sb.append(UName.toDaoName(tableName));
		sb.append("\">\n");
		sb.append("\t<sql id=\"tablename\">`");
		sb.append(tableName);
		sb.append("`</sql>\n");
		sb.append("\t<sql id=\"fields\">");
		sb.append(generateFields(fieldS, fieldHandler, ','));
		sb.append("</sql>\n");
		sb.append("\t<sql id=\"fieldsForInsert\">");
		sb.append(generateFields(fieldS, new Handler<FieldDesc, String>() {
			@Override
			public String handle(FieldDesc field) {
				return new StringBuilder().append('`').append(field.getName()).append('`').toString();
			}
		}, ','));
		sb.append("</sql>\n");
		String className = entity.getClassName(tableName);
		insert(sb, fieldS, className);
		inserts(sb, fieldS, className);
		deletes(sb, fieldS, className);
		selectOne(sb, fieldS, className);
		select(sb, fieldS, className);
		selectPaged(sb, fieldS, className);
		total(sb, fieldS, className);
		FieldDesc primaryKeyField = fieldS.get(primaryKey);
		if (primaryKeyField != null) {
			delete(primaryKeyField, sb, fieldS, className);
			update(primaryKeyField, sb, fieldS, className);
			updates(primaryKeyField, sb, fieldS, className);
			updateAll(primaryKeyField, sb, fieldS, className);
			selectByPrimaryKey(primaryKeyField, sb, fieldS, className);
			selectByPrimaryKeyArray(primaryKeyField, sb, fieldS, className);
			selectPrimaryKey(primaryKeyField, sb, fieldS, className);
		}
		Map<String, FieldDesc> fieldMap = database.desc(tableName);
		database.foreignKeys(tableName, fieldMap);
		for (FieldDesc field : fieldMap.values()) {
			if (field.getForeignKey() != null && !field.getName().equals(this.primaryKey)) {
				selectForeignKey(field, sb, fieldS, className);
				selectByForeignKey(field, sb, fieldS, className);
				selectByForeignKeyArray(field, sb, fieldS, className);
				selectByForeignKeyArrayPaged(field, sb, fieldS, className);
			}
		}
		selectAll(sb, fieldS, className);
		sb.append("</mapper>");
		return sb.toString();
	}

	private void insert(StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<insert id=\"insert\" parameterType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		FieldDesc primaryKeyField = fieldS.get(primaryKey);
		if (primaryKeyField.isAutoIncrement()) {
			sb.append("\" useGeneratedKeys=\"true\" keyProperty=\"");
			sb.append(primaryKey);
		}
		sb.append("\">\n");
		sb.append("\t\tINSERT INTO <include refid=\"tablename\"/>(<include refid=\"fieldsForInsert\"/>) \n");
		sb.append("\t\tVALUES(");
		sb.append(generateFields(fieldS, new Handler<FieldDesc, String>() {
			@Override
			public String handle(FieldDesc field) {
				return new StringBuilder().append("#{").append(UName.toFieldName(field.getName())).append("}").toString();
			}
		}, ','));
		sb.append(")\n");
		sb.append("\t</insert>\n");
	}

	private void inserts(StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<insert id=\"inserts\" parameterType=\"java.lang.Iterable");
		//FieldDesc primaryKeyField = fieldS.get(primaryKey);
		//if (primaryKeyField.isAutoIncrement()) {
		//	sb.append("\" useGeneratedKeys=\"true\" keyProperty=\"");
		//	sb.append(primaryKey);
		//}
		sb.append("\">\n");
		sb.append("\t\tINSERT INTO <include refid=\"tablename\"/>(<include refid=\"fieldsForInsert\"/>) VALUES\n");
		sb.append("\t\t<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\n");
		sb.append("\t\t\t(");
		sb.append(generateFields(fieldS, new Handler<FieldDesc, String>() {
			@Override
			public String handle(FieldDesc field) {
				return new StringBuilder().append("#{item.").append(UName.toFieldName(field.getName())).append("}").toString();
			}
		}, ','));
		sb.append(")\n");
		sb.append("\t\t</foreach>\n");
		sb.append("\t</insert>\n");
	}

	private void deletes(StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<delete id=\"deletes\">\n");
		sb.append("\t\tDELETE FROM <include refid=\"tablename\"/>\n");
		sb.append("\t</delete>\n");
	}

	private void delete(FieldDesc primaryKeyField, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<delete id=\"delete\" parameterType=\"");
		sb.append(UTypeMysql.parseClassName(primaryKeyField.getType()));
		sb.append("\">\n");
		sb.append("\t\tDELETE FROM <include refid=\"tablename\"/> WHERE `");
		sb.append(primaryKeyField.getName());
		sb.append("`=#{");
		sb.append(UName.toFieldName(primaryKeyField.getName()));
		sb.append("}\n");
		sb.append("\t</delete>\n");
	}

	private void update(FieldDesc primaryKeyField, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<update id=\"update\" parameterType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\">\n");
		sb.append("\t\tUPDATE <include refid=\"tablename\"/>\n");
		sb.append("\t\t<set>\n");
		sb.append(generateFields(fieldS, setHandler, ' '));
		sb.append("\t\t</set>\n");
		sb.append("\t\tWHERE `");
		sb.append(primaryKeyField.getName());
		sb.append("`=#{");
		sb.append(UName.toFieldName(primaryKeyField.getName()));
		sb.append("}\n");
		sb.append("\t</update>\n");
	}

	private void updates(FieldDesc primaryKeyField, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		if (primaryKeyField != null) {
			sb.append("\t<update id=\"updates\" parameterType=\"");
			sb.append(entity.getPackageName());
			sb.append('.');
			sb.append(className);
			sb.append("\">\n");
			sb.append("\t\tUPDATE <include refid=\"tablename\"/>\n");
			sb.append("\t\t<set>\n");
			sb.append(generateFields(fieldS, setHandler, ' '));
			sb.append("\t\t</set>\n");
			sb.append("\t\tWHERE ");
			whereArray(sb, primaryKeyField, false);
			sb.append("\t</update>\n");
		}
	}

	private void updateAll(FieldDesc primaryKeyField, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		FieldDesc field = primaryKeyArray(primaryKeyField);
		if (field != null) {
			sb.append("\t<update id=\"updateAll\" parameterType=\"");
			sb.append(entity.getPackageName());
			sb.append('.');
			sb.append(className);
			sb.append("\">\n");
			sb.append("\t\tUPDATE <include refid=\"tablename\"/>\n");
			sb.append("\t\t<set>\n");
			sb.append(generateFields(fieldS, setHandler, ' '));
			sb.append("\t\t</set>\n");
			sb.append("\t</update>\n");
		}
	}

	private void selectByPrimaryKey(FieldDesc primaryKeyField, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"selectBy");
		sb.append(UName.toMethodName(primaryKeyField.getName()));
		sb.append("\" resultType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\"  parameterType=\"");
		sb.append(UTypeMysql.parseClassName(primaryKeyField.getType()));
		sb.append("\">\n");
		sb.append("\t\tSELECT <include refid=\"fields\"/> FROM <include refid=\"tablename\"/> t WHERE `");
		sb.append(primaryKeyField.getName());
		sb.append("`=#{");
		sb.append(UName.toFieldName(primaryKeyField.getName()));
		sb.append("}\n");
		sb.append("\t</select>\n");
	}

	private void selectByPrimaryKeyArray(FieldDesc primaryKeyField, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"selectBy");
		sb.append(UName.toMethodName(primaryKeyField.getName()));
		sb.append("Array\" resultType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\">\n");
		sb.append("\t\tSELECT <include refid=\"fields\"/> FROM <include refid=\"tablename\"/> t WHERE t.`");
		sb.append(primaryKeyField.getName());
		sb.append("` IN \n");
		sb.append("\t\t<foreach collection=\"array\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>\n");
		orderBy(primaryKeyField, sb, fieldS);
		sb.append("\t</select>\n");
	}

	private void selectPrimaryKey(FieldDesc primaryKeyField, StringBuilder target, Map<String, FieldDesc> fieldS, String className) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t<select id=\"select");
		sb.append(UName.toMethodName(primaryKeyField.getName()));
		sb.append("\" resultType=\"");
		sb.append(UTypeMysql.parseClassName(primaryKeyField.getType()));
		sb.append("\"  parameterType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\">\n");
		sb.append("\t\tSELECT `");
		sb.append(primaryKeyField.getName());
		sb.append("` FROM <include refid=\"tablename\"/> t\n");
		sb.append("\t\t<where>\n");
		sb.append(generateFields(fieldS, whereHandler, ' '));
		sb.append("\t\t</where>\n");
		orderBy(sb, fieldS);
		sb.append("\t</select>\n");
		target.append(sb);
	}

	void orderBy(FieldDesc primaryKeyField, StringBuilder sb, Map<String, FieldDesc> fieldS) {
		Map<String, Boolean> orderBys = new HashMap<String, Boolean>();
		orderBys.put("order", true);
		orderBys.put("update_time", false);
		orderBys.put("create_time", false);
		orderBys.put(primaryKeyField.getName(), false);
		sb.append("\t\tORDER BY ");
		boolean isAppend = false;
		Iterator<Entry<String, Boolean>> iterator = orderBys.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Boolean> entry = iterator.next();
			FieldDesc field = fieldS.get(entry.getKey());
			if (field != null) {
				if (isAppend) {
					sb.append(',');
				} else {
					isAppend = true;
				}
				sb.append("t.`");
				sb.append(entry.getKey());
				sb.append("`");
				if (Boolean.FALSE.equals(entry.getValue())) {
					sb.append(" desc");
				}
			}
		}
		sb.append("\n");
	}

	void orderBy(StringBuilder sb, Map<String, FieldDesc> fieldS) {
		orderBy(fieldS.get(primaryKey), sb, fieldS);
	}

	private void selectOne(StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"selectOne\" resultType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\"  parameterType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\">\n");
		sb.append("\t\tSELECT <include refid=\"fields\"/> FROM <include refid=\"tablename\"/> t\n");
		sb.append("\t\t<where>\n");
		sb.append(generateFields(fieldS, whereHandler, ' '));
		sb.append("\t\t</where>\n");
		sb.append("\t\tLIMIT 1\n");
		sb.append("\t</select>\n");
	}

	private void select(StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"select\" resultType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\"  parameterType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\">\n");
		sb.append("\t\tSELECT <include refid=\"fields\"/> FROM <include refid=\"tablename\"/> t\n");
		sb.append("\t\t<where>\n");
		sb.append(generateFields(fieldS, whereHandler, ' '));
		sb.append("\t\t</where>\n");
		orderBy(sb, fieldS);
		sb.append("\t</select>\n");
	}

	private void selectPaged(StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"selectPaged\" resultType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\"  parameterType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\">\n");
		sb.append("\t\tSELECT <include refid=\"fields\"/> FROM <include refid=\"tablename\"/> t\n");
		sb.append("\t\t<where>\n");
		sb.append(generateFields(fieldS, whereHandler, ' '));
		sb.append("\t\t</where>\n");
		orderBy(sb, fieldS);
		sb.append("\t</select>\n");
	}

	private void total(StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"total\" resultType=\"java.lang.Long\"  parameterType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\">\n");
		sb.append("\t\tSELECT COUNT(1) FROM <include refid=\"tablename\"/> t\n");
		sb.append("\t\t<where>\n");
		sb.append(generateFields(fieldS, whereHandler, ' '));
		sb.append("\t\t</where>\n");
		sb.append("\t</select>\n");
	}

	private void selectForeignKey(FieldDesc field, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"select");
		sb.append(UName.toMethodName(field.getName()));
		sb.append("\" resultType=\"");
		sb.append(UTypeMysql.parseClassName(field.getType()));
		sb.append("\"  parameterType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\">\n");
		sb.append("\t\tSELECT DISTINCT ");
		sb.append(field.getName());
		sb.append(" FROM <include refid=\"tablename\"/> t\n");
		sb.append("\t\t<where>\n");
		sb.append(generateFields(fieldS, whereHandler, ' '));
		sb.append("\t\t</where>\n");
		orderBy(sb, fieldS);
		sb.append("\t</select>\n");
	}

	private void selectByForeignKey(FieldDesc field, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		String fieldName = UName.toFieldName(field.getName());
		sb.append("\t<select id=\"selectBy");
		sb.append(UName.toMethodName(field.getName()));
		sb.append("\" resultType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\"  parameterType=\"");
		sb.append(UTypeMysql.parseClassName(field.getType()));
		sb.append("\">\n");
		sb.append("\t\tSELECT <include refid=\"fields\"/> FROM <include refid=\"tablename\"/> t\n");
		sb.append("\t\tWHERE `");
		sb.append(field.getName());
		sb.append("`=#{");
		sb.append(fieldName);
		sb.append("}\n");
		orderBy(sb, fieldS);
		sb.append("\t</select>\n");
	}

	private void selectByForeignKeyArray(FieldDesc field, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"selectBy");
		sb.append(UName.toMethodName(field.getName()));
		sb.append("Array\" resultType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\"  parameterType=\"");
		sb.append(UTypeMysql.parseClassName(field.getType()));
		sb.append("\">\n");
		sb.append("\t\tSELECT <include refid=\"fields\"/> FROM <include refid=\"tablename\"/> t\n");
		sb.append("\t\tWHERE `");
		sb.append(field.getName());
		sb.append("` IN \n");
		sb.append("\t\t<foreach collection=\"array\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">#{id}</foreach>\n");
		orderBy(sb, fieldS);
		sb.append("\t</select>\n");
	}

	private void selectByForeignKeyArrayPaged(FieldDesc field, StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"selectBy");
		sb.append(UName.toMethodName(field.getName()));
		sb.append("ArrayPaged\" resultType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\"  parameterType=\"");
		sb.append(UTypeMysql.parseClassName(field.getType()));
		sb.append("\">\n");
		sb.append("\t\tSELECT <include refid=\"fields\"/> FROM <include refid=\"tablename\"/> t\n");
		sb.append("\t\tWHERE `");
		sb.append(field.getName());
		sb.append("` IN \n");
		sb.append("\t\t<foreach collection=\"array\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">#{id}</foreach>\n");
		orderBy(sb, fieldS);
		sb.append("\t</select>\n");
	}

	private void selectAll(StringBuilder sb, Map<String, FieldDesc> fieldS, String className) {
		sb.append("\t<select id=\"selectAll\" resultType=\"");
		sb.append(entity.getPackageName());
		sb.append('.');
		sb.append(className);
		sb.append("\">\n");
		sb.append("\t\tSELECT <include refid=\"fields\"/> FROM <include refid=\"tablename\"/> t\n");
		orderBy(sb, fieldS);
		sb.append("\t</select>\n");
	}

	private String generateFields(Map<String, FieldDesc> fieldS, Handler<FieldDesc, String> handler, char seperator) {
		StringBuilder sb = new StringBuilder();
		Iterator<FieldDesc> iterator = fieldS.values().iterator();
		if (iterator.hasNext()) {
			sb.append(handler.handle(iterator.next()));
		}
		while (iterator.hasNext()) {
			sb.append(seperator);
			sb.append(handler.handle(iterator.next()));
		}
		if (handler == whereHandler) {
			whereArray(sb, fieldS.get(primaryKey), true);
			sb.append("\t\t\t<if test=\"_where != null\"> AND ${_where}</if>\n");
		}
		return sb.toString();
	}

	private final void whereArray(final StringBuilder sb, final FieldDesc primaryKeyField, boolean optional) {
		FieldDesc fieldArray = primaryKeyArray(primaryKeyField);
		if (fieldArray != null) {
			if (optional) {
				String fieldName = UName.toFieldName(fieldArray.getName());
				sb.append("\t\t\t<if test=\"");
				sb.append(fieldName);
				sb.append(" != null\"> AND ");
			}
			sb.append("`");
			sb.append(primaryKeyField.getName());
			sb.append("` IN \n");
			sb.append("\t\t\t\t<foreach collection=\"");
			sb.append(UName.toFieldName(fieldArray.getName()));
			sb.append("\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>\n");
			if (optional) {
				sb.append("\t\t\t</if>\n");
			}
		}
	}

	@Override
	public void befores() {
		this.deletes();
	}
}
