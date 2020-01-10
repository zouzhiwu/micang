package com.common.enumerate;

public enum AccountState implements BaseEnum {
	Offline("离线", 0), Online("在线", 1), Ready("准备", 2), Gaming("游戏", 3), Settlement("结算", 4);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private AccountState(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}

	public static String getName(int index) {
		for (AccountState dt : AccountState.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static AccountState getType(int index) {
		for (AccountState dt : AccountState.values()) {
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

	public void setIndex(int index) {
		this.index = (byte)index;
	}
}
