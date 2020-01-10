package com.common.enumerate;

public enum LevelType {
	Member("队员", 1), Hero("英雄", 2), Club("俱乐部", 3);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private LevelType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (LevelType dt : LevelType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static LevelType getType(int index) {
		for (LevelType dt : LevelType.values()) {
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
		return (byte)index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
