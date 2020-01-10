package com.common.enumerate;

public enum WorkType implements BaseEnum {
	Off("下阵", (byte)0), Top("上单", (byte)1), Mid("中单", (byte)2), Jun("打野", (byte)3), Sup("辅助", (byte)4), Adc("输出", (byte)5);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private WorkType(String name, byte index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(byte index) {
		for (WorkType dt : WorkType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static WorkType getType(byte index) {
		for (WorkType dt : WorkType.values()) {
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
