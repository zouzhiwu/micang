package com.common.enumerate;

public enum FightingType {
	battle("战斗中", 1), endBattle("结束战斗", 0);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private FightingType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (FightingType dt : FightingType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static FightingType getType(byte index) {
		for (FightingType dt : FightingType.values()) {
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
