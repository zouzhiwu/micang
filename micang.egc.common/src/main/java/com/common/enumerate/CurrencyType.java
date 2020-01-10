package com.common.enumerate;

public enum CurrencyType {
	Gb("金币", 1), Jb("钻石", 2);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private CurrencyType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (CurrencyType dt : CurrencyType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static CurrencyType getType(int index) {
		for (CurrencyType dt : CurrencyType.values()) {
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
