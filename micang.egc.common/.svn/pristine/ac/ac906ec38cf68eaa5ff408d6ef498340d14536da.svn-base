package com.common.enumerate;

public enum GrandType {
	collection("操作类型 收藏战报，战报类型 最近的战报", 0), cancel("操作类型 取消收藏，战报类型 收藏的战报", 1);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private GrandType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (GrandType dt : GrandType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static GrandType getType(byte index) {
		for (GrandType dt : GrandType.values()) {
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
