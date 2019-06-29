package com.xinnet.smart.test.model;

import com.xinnet.smart.base.util.UJson;

/**
 * 字段描述信息
 * @author meitao
 * @date 2014-8-12 下午9:28:41
 */
public class FieldDesc {
	String name;//字段名
	String type;//字段类型
	String collation;//字符集 
	boolean isNull;//是否可空
	String keyType;//键类型
	String defaultValue;//默认值
	String extra;
	String privileges;//权限
	String comment;//注释
	ForeignKey foreignKey;
	boolean autoIncrement;
	String javaType;//java中对应类型
	boolean jsonIgnore;

	public boolean isJsonIgnore() {
		return jsonIgnore;
	}

	public void setJsonIgnore(boolean jsonIgnore) {
		this.jsonIgnore = jsonIgnore;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public ForeignKey getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(ForeignKey foreignKey) {
		this.foreignKey = foreignKey;
	}

	public String getCollation() {
		return collation;
	}

	public void setCollation(String collation) {
		this.collation = collation;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return UJson.toString(this);
	}
}
