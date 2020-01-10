package com.common.enumerate;

public enum CombatResults {
	victory("胜利", 0), failure("失败", 1);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private CombatResults(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (CombatResults dt : CombatResults.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static CombatResults getType(byte index) {
		for (CombatResults dt : CombatResults.values()) {
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
