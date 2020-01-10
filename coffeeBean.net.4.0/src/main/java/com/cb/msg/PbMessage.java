package com.cb.msg;

import com.cb.util.DeviceType;
import com.google.protobuf.GeneratedMessageV3;

public class PbMessage<T extends GeneratedMessageV3> {
	private short msgcd;
	private Long accountId = 0L;
	private DeviceType deviceType = DeviceType.Nothing;
	private T body;
	private short errorCode;
	private String errorInfo = "";
	
	public short getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(short errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public short getMsgcd() {
		return msgcd;
	}
	public void setMsgcd(short msgcd) {
		this.msgcd = msgcd;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
}
