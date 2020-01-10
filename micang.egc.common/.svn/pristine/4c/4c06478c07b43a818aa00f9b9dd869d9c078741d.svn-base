package com.common.enumerate;

public enum ReadType {
	read("已阅读", 0), aNoRead("玩家1未阅读", 1), bNoRead("玩家二未阅读", 2);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private ReadType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (ReadType dt : ReadType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static ReadType getType(byte index) {
		for (ReadType dt : ReadType.values()) {
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
