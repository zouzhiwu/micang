package com.common.enumerate;

public enum CommodityType {
	CD("重置冷却", 1), ChallengeOpportunity("挑战机会", 2);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private CommodityType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (CommodityType dt : CommodityType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static CommodityType getType(byte index) {
		for (CommodityType dt : CommodityType.values()) {
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
