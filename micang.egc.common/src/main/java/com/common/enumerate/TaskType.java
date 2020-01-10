package com.common.enumerate;

public enum TaskType {
	Free("空闲", 1), Wait("等待", 2), Move("移动", 3), Attack("攻击", 4);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private TaskType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (TaskType dt : TaskType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static TaskType getType(int index) {
		for (TaskType dt : TaskType.values()) {
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
