package com.common.enumerate;

public enum DoorCode {
	Door1("门牙塔1", 1), Door2("门牙塔2", 2);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private DoorCode(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (DoorCode dt : DoorCode.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static DoorCode getType(int index) {
		for (DoorCode dt : DoorCode.values()) {
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
