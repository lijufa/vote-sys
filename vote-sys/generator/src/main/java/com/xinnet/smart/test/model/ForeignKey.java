package com.xinnet.smart.test.model;

import com.xinnet.smart.base.util.UJson;

/**
 * 外键信息
 * @author meitao
 * @date 2014-8-14 下午6:53:45
 */
public class ForeignKey {
	String tableName;//所在表名
	String fieldName;//字段名
	String refTableName;//关联表名
	String refFieldName;//关联字段名
	String constraint;//外键约束名

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getRefTableName() {
		return refTableName;
	}

	public void setRefTableName(String refTableName) {
		this.refTableName = refTableName;
	}

	public String getRefFieldName() {
		return refFieldName;
	}

	public void setRefFieldName(String refFieldName) {
		this.refFieldName = refFieldName;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	@Override
	public String toString() {
		return UJson.toString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ForeignKey) {
			ForeignKey foreignKey = (ForeignKey) obj;
			if (this.getConstraint().equals(foreignKey.getConstraint())) {
				if (this.getTableName().equals(foreignKey.getTableName())) {
					if (this.getFieldName().equals(foreignKey.getFieldName())) {
						if (this.getRefTableName().equals(foreignKey.getRefTableName())) {
							if (this.getRefFieldName().equals(foreignKey.getRefFieldName())) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
