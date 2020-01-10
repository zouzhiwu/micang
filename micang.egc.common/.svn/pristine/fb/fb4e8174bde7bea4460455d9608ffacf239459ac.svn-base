package com.common.enumerate;

public enum MemberAttrEnum {
	Dynamic("活力", 1), Operation("操作", 2), Potential("潜能", 3), Tough("坚韧", 4), Focus("专注", 5);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private MemberAttrEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (MemberAttrEnum dt : MemberAttrEnum.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static MemberAttrEnum getType(int index) {
		for (MemberAttrEnum dt : MemberAttrEnum.values()) {
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
