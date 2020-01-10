package com.cb.util;

public enum DeviceType {
	Nothing("未知", (byte)0), Ios("苹果机", (byte)1), Android("安卓机", (byte)2), Browser("浏览器", (byte)3);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private DeviceType(String name, byte index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (DeviceType dt : DeviceType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static DeviceType getDeviceType(byte index) {
		for (DeviceType dt : DeviceType.values()) {
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

	public int getIndex() {
		return index;
	}

	public void setIndex(byte index) {
		this.index = index;
	}
}
