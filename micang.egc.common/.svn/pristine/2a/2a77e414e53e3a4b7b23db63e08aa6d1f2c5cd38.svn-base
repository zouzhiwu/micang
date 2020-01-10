package com.common.enumerate;

public enum MailOperationType {
	delMail("删除", 1), receiveMail("领取", 2);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private MailOperationType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (MailOperationType dt : MailOperationType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static MailOperationType getType(byte index) {
		for (MailOperationType dt : MailOperationType.values()) {
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
