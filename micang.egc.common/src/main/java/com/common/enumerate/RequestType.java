package com.common.enumerate;

public enum RequestType {
	logo("登录", 0), inABate("换一批", 1);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private RequestType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (RequestType dt : RequestType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static RequestType getType(byte index) {
		for (RequestType dt : RequestType.values()) {
			if (dt.getIndex() == index) {
				return dt;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getIndex() {
		return index;
	}

	public void setIndex(byte index) {
		this.index = index;
	}
}
