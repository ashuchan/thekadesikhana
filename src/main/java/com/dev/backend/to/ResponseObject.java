package com.dev.backend.to;

public class ResponseObject {

	private String message;
	
	private boolean isSuccess;
	
	private Object data;

	public ResponseObject(String message, boolean isSuccess, Object data) {
		super();
		this.message = message;
		this.isSuccess = isSuccess;
		this.data = data;
	}

	public ResponseObject() {
		message = "Unknown Error Occured";
		isSuccess = false;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
