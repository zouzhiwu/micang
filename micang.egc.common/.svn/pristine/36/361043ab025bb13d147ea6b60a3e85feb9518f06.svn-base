package com.common.enumerate;

public enum ItemType {
	Currency("通用货币", 1), Prop("道具", 2), Fragment("碎片", 3), Material("材料", 4), Consumption("消耗", 5), Member("队员", 6), Hero("英雄", 7), Peripheral("外设", 8), Coach("教练", 9);
	
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private ItemType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (ItemType dt : ItemType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static ItemType getType(byte index) {
		for (ItemType dt : ItemType.values()) {
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
