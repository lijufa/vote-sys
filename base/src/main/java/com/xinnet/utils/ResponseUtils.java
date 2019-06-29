package com.xinnet.utils;

import java.util.List;

import com.xinnet.exception.ResponseMessage;

public class ResponseUtils {
	public static BaseResponse getResponseSuccess() {
		return new BaseResponse();
	}

	public static BaseResponse getResponse(String msgKey) {
		return new BaseResponse(msgKey);
	}

	public static <T> DataResponse<T> getResponse(T data) {
		return new DataResponse<T>(data);
	}

	public static <T> PageResponse<T> getResponse(T data, Object page) {
		return new PageResponse<T>(data, page);
	}

	public static class BaseResponse {
		private boolean success;
		private String code;

		public BaseResponse() {
			this.success = true;
			this.code = "success";
		}

		public BaseResponse(String msgKey) {
			this.success = false;
			this.code = msgKey;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return ResponseMessage.getMessage(code);
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}

	public static class DataResponse<D> extends BaseResponse {
		private D data;

		public DataResponse() {
		}

		public DataResponse(D data) {
			super();
			this.data = data;
		}

		public D getData() {
			return data;
		}

		public void setData(D data) {
			this.data = data;
		}
	}

	public static class ListResponse<T> extends BaseResponse {
		private List<T> data;

		public ListResponse() {
		}

		public ListResponse(List<T> data) {
			super();
			this.data = data;
		}

		public List<T> getData() {
			return data;
		}

		public void setData(List<T> data) {
			this.data = data;
		}
	}

	public static class PageResponse<T> extends DataResponse<T> {
		private Object page;

		public PageResponse(T data, Object page) {
			super(data);
			this.page = page;
		}

		public PageResponse(T data) {
			super(data);
		}

		public Object getPage() {
			return page;
		}

		public void setPage(Object page) {
			this.page = page;
		}
	}

	public static class StateListPageResponse<T, O> extends PageResponse<T> {
		private O state;

		public StateListPageResponse(T data, Object page, O state) {
			super(data, page);
			this.state = state;
		}

		public O getState() {
			return state;
		}

		public void setState(O state) {
			this.state = state;
		}
	}

	public static class StateListResponse<T, O> extends ListResponse<T> {
		private O state;

		public StateListResponse(List<T> data, O state) {
			super(data);
			this.state = state;
		}

		public O getState() {
			return state;
		}

		public void setState(O state) {
			this.state = state;
		}
	}
}
