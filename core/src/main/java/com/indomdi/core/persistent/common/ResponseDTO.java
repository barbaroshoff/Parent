package com.indomdi.core.persistent.common;

import java.util.List;

public class ResponseDTO<T> extends BaseDTO {
	private T data;
	private List<ErrorInfo> errors;

	public ResponseDTO() {
	}
	
	public List<ErrorInfo> getErrors() {
		return errors;
	}

	public ResponseDTO<T> setErrors(List<ErrorInfo> errors) {
		this.errors = errors;
		return this;
	}

	public T getData() {
		return data;
	}

	public ResponseDTO<T> setData(T data) {
		this.data = data;
		return this;
	}

	public static ResponseDTO<?> of(Object data) {
		return new ResponseDTO<>().setData(data);
	}

	public static ResponseDTO<?> empty() {
		return new ResponseDTO<>();
	}

}
