package com.common.enumerate;

public enum NodeType {
	Home("基地", 1), Crystal("水晶", 2), Door("门牙塔", 3), Tower("防御塔", 4), Monster("野怪，包括野怪大小龙", 5), Soldier("兵，包括小兵，炮兵，超级兵", 6), Hero("英雄", 7);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private NodeType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (NodeType dt : NodeType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static NodeType getType(int index) {
		for (NodeType dt : NodeType.values()) {
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
		return (byte)index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
