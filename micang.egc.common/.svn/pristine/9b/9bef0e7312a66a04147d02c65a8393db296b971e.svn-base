package com.common.enumerate;

public enum MailType {
	pub("公共邮件", 1), pri("私有邮件", 2);
	// 成员变量
	private String name;
	private byte index;

	// 构造方法
	private MailType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	public static String getName(int index) {
		for (MailType dt : MailType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static MailType getType(byte index) {
		for (MailType dt : MailType.values()) {
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
