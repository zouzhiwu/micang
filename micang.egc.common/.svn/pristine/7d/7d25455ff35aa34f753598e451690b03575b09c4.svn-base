package com.common.enumerate;

public enum CoinEnum {

	gb("金币", 1), jb("钻石", 2), tili("体力", 3), xinlifudaoCoin("心动碎片", 6), expPot("通用队员经验", 7), Honor("荣誉", 9);
	
	private String name;
	private Integer index;

	// 构造方法
	private CoinEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (CoinEnum dt : CoinEnum.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}

	public static CoinEnum getType(int index) {
		for (CoinEnum dt : CoinEnum.values()) {
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
