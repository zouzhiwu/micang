package com.common.enumerate;

public enum AccountType {
	test("测试人员", 1), robot("机器人", 2), player("玩家", 3);
	
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private AccountType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(byte index) {
		for (AccountType dt : AccountType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static AccountType getType(byte index) {
		for (AccountType dt : AccountType.values()) {
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
