package com.common.enumerate;

public enum TowerCode {
	Tower1("防御塔1", 1), Tower2("防御塔2", 2), Tower3("防御塔3", 3);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private TowerCode(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (TowerCode dt : TowerCode.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static TowerCode getType(int index) {
		for (TowerCode dt : TowerCode.values()) {
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
