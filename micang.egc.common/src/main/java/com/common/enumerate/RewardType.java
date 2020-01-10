package com.common.enumerate;

public enum RewardType {
	integral("积分奖励", 0), rank("排名奖励", 1);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private RewardType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (RewardType dt : RewardType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static RewardType getType(byte index) {
		for (RewardType dt : RewardType.values()) {
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
