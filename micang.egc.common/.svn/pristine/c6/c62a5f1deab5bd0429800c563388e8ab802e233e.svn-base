package com.common.enumerate;

public enum MesgType {
	world("通用货币", 0), union("联盟", 1);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private MesgType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}

	public static String getName(int index) {
		for (MesgType dt : MesgType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static MesgType getType(int index) {
		for (MesgType dt : MesgType.values()) {
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
