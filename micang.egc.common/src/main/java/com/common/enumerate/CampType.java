package com.common.enumerate;

public enum CampType {
	Blue("蓝方", 1), Red("红方", 2), Neutral("中立", 3), All("所有", 9);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private CampType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}

	public static String getName(int index) {
		for (CampType dt : CampType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static CampType getType(int index) {
		for (CampType dt : CampType.values()) {
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
