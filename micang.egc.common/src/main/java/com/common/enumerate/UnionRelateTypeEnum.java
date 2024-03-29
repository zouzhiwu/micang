package com.common.enumerate;

public enum UnionRelateTypeEnum {

	UnionRelateTypeApply("申请", 1), UnionRelateTypeContribute("贡献", 2), UnionRelateTypeBox("领取宝箱", 3);

	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private UnionRelateTypeEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (UnionRelateTypeEnum dt : UnionRelateTypeEnum.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}

	public static UnionRelateTypeEnum getType(int index) {
		for (UnionRelateTypeEnum dt : UnionRelateTypeEnum.values()) {
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
