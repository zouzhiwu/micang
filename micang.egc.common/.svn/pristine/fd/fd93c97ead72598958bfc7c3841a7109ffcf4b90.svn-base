package com.common.enumerate;

public enum ArmsType {
	Small("小兵", 1), Big("炮兵", 2), Super("超级", 3);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private ArmsType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (ArmsType dt : ArmsType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static ArmsType getType(int index) {
		for (ArmsType dt : ArmsType.values()) {
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
