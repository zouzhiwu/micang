package com.cb.exception;

public class InfoException extends BaseException {
	private static final long serialVersionUID = 1L;
	
	public InfoException(short errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		if (errorCode == SQL_EXCEPTION) {
			this.clientInfo = "数据库异常";
		} else {
			this.clientInfo = message;
		}
	}

	public InfoException(short errorCode, short msgcd, String message) {
		super(message);
		this.errorCode = errorCode;
		this.msgcd = msgcd;
	}

	/*public InfoException(int errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		if (errorCode == SQL_EXCEPTION) {
			this.packgeInstance = "数据库异常";
		} else {
			this.packgeInstance = super.getMessage();
		}
	}

	public InfoException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		if (errorCode == SQL_EXCEPTION) {
			this.packgeInstance = "数据库异常";
		} else {
			this.packgeInstance = super.getMessage();
		}
	}*/
	
}