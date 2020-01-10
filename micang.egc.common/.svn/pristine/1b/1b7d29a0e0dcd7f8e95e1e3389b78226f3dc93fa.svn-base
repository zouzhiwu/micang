package com.common.enumerate;

public enum ChatMesgType {
	text("文本", 0), img("图片", 1), voice("语音", 2);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private ChatMesgType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (ChatMesgType dt : ChatMesgType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static ChatMesgType getType(int index) {
		for (ChatMesgType dt : ChatMesgType.values()) {
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
