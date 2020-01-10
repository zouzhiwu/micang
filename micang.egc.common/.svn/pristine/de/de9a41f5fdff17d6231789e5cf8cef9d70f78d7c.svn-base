package com.common.enumerate;

public enum WayType {
	Top("上路", 1), Mid("中路", 2), Down("下路", 3);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private WayType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (WayType dt : WayType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static WayType getType(int index) {
		for (WayType dt : WayType.values()) {
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
