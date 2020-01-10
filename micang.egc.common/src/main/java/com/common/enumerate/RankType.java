package com.common.enumerate;

public enum RankType {
	top10("前十的玩家", 0), top50("前50的玩家", 1);
	
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private RankType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (RankType dt : RankType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static RankType getType(byte index) {
		for (RankType dt : RankType.values()) {
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
