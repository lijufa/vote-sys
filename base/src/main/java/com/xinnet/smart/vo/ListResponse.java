package com.xinnet.smart.vo;

import java.util.ArrayList;
import java.util.List;

public class ListResponse<T> extends GenericResponse {
	List<T> data = new ArrayList<T>();

	public final List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
