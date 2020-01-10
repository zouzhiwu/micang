package com.common.enumerate;

public enum MapNodeType {
	White("白色像素点，英雄可行走区", 0), Black("路障物像素点", 1), PATH("已走过的结点", 2), START("开始结点", 3), END("结束结点", 4), Gray("灰色像素点，AI可行走去", 5);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private MapNodeType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (MapNodeType dt : MapNodeType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static MapNodeType getType(int index) {
		for (MapNodeType dt : MapNodeType.values()) {
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
