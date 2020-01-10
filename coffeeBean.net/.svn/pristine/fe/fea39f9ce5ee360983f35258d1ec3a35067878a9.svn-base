package com.cb.msg;

import java.nio.ByteOrder;

import com.cb.util.DeviceType;

import io.netty.buffer.Unpooled;

public class Message extends BaseMsg {
	private short msgcd;
	private Long accountId = 0L;
//	private String token = "";
	private int token;
	private DeviceType deviceType = DeviceType.Nothing;
	
	public Message() {
		this.buffer = Unpooled.buffer(0).order(ByteOrder.LITTLE_ENDIAN);
		this.bodyLen = 0;
	}
	
	public Message(Message message) {
		this.buffer = Unpooled.buffer(0).order(ByteOrder.LITTLE_ENDIAN);
		this.bodyLen = 0;
		setMsgcd(message.getMsgcd());
		setAccountId(message.getAccountId());
//		setToken(message.getToken());
//		setDeviceType(message.getDeviceType());
	}
	
	public Long getAccountId() {
		return accountId;
	}

	public int getToken() {
		return token;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setToken(int token) {
		this.token = token;
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
	
}
