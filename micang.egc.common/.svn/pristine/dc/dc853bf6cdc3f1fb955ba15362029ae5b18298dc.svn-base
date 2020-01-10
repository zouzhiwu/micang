package com.common.enumerate;

public enum ResourcesTypeEnum {

	item("道具类型", 1), member("队员", 2);
	private String name;
	private int index;

	// 构造方法
	private ResourcesTypeEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (ResourcesTypeEnum dt : ResourcesTypeEnum.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}

	public static ResourcesTypeEnum getType(int index) {
		for (ResourcesTypeEnum dt : ResourcesTypeEnum.values()) {
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
