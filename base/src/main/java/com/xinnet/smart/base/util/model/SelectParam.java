package com.xinnet.smart.base.util.model;

/**
 * 复杂查询参数
 * @author meitao
 * @date 2014-8-14 下午2:13:57
 */
public class SelectParam {
	String orderBy;//排序
	String groupBy;//分组
	Long limit;//分页

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
}
