package com.common.enumerate;

public enum StrategyType {
	Radical("激进", 1), Conservative("保守", 2);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private StrategyType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (StrategyType dt : StrategyType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static StrategyType getType(int index) {
		for (StrategyType dt : StrategyType.values()) {
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
