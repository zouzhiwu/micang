package com.common.enumerate;

public enum TargetType {
	Location("坐标", 1), Node("节点", 2), Template("模板", 3);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private TargetType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (TargetType dt : TargetType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static TargetType getType(int index) {
		for (TargetType dt : TargetType.values()) {
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
