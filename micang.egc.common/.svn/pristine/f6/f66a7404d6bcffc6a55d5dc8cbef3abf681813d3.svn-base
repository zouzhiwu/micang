package com.common.enumerate;

public enum TiliRelationType implements BaseEnum  {
	notGiving("互相不送", 0),
	aGivingB("a送b体力", 1),
	bGivingA("b送a体力", 2), 
	eachGiving("互送体力", 3);
	
	// 成员变量
	private String name;
	private byte index;
	
	// 构造方法
	private TiliRelationType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (TiliRelationType dt : TiliRelationType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static TiliRelationType getType(Integer index) {
		for (TiliRelationType dt : TiliRelationType.values()) {
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
