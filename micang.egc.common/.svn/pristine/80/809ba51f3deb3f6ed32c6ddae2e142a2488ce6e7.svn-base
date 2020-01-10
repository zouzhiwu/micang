package com.common.enumerate;

public enum CharacterSeat {
	Seat1("性格位置1", 1), Seat2("性格位置2", 2);
	// 成员变量
	private String name;
	private Integer index;

	// 构造方法
	private CharacterSeat(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (CharacterSeat dt : CharacterSeat.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}

	public static CampType getType(int index) {
		for (CampType dt : CampType.values()) {
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
