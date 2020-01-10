package com.common.enumerate;

public enum OperateType {
	Choose("选择英雄", 1), Ban("禁用英雄", 2);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private OperateType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}

	public static String getName(int index) {
		for (OperateType dt : OperateType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static OperateType getType(int index) {
		for (OperateType dt : OperateType.values()) {
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
