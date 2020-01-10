package com.common.enumerate;

public enum TacticsType {
	Alone("分推", 1), Group("中团", 2);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private TacticsType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (TacticsType dt : TacticsType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static TacticsType getType(int index) {
		for (TacticsType dt : TacticsType.values()) {
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

	public void setIndex(int index) {
		this.index = index;
	}
}
