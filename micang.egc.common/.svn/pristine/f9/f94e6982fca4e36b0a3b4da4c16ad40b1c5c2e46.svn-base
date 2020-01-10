package com.common.enumerate;

public enum ItemChangeEnum {

	Coin("通用货币", 1), Item("背包道具", 2), Member("队员", 3);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private ItemChangeEnum(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}

	public static String getName(int index) {
		for (ItemChangeEnum dt : ItemChangeEnum.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static ItemChangeEnum getType(int index) {
		for (ItemChangeEnum dt : ItemChangeEnum.values()) {
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
