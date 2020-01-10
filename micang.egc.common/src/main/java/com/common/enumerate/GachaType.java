package com.common.enumerate;

public enum GachaType {
	goldOne("金币单抽", 1), goldTen("金币十抽", 2), diamondOne("钻石单抽", 3), diamondTen("钻石十抽", 4);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private GachaType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (GachaType dt : GachaType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static GachaType getType(byte index) {
		for (GachaType dt : GachaType.values()) {
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
