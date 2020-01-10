package com.common.enumerate;

public enum UnionJobType {
	/**
	  * 联盟长
	 * 副联盟长
	 * 护法
	 * 普通
	 */
	UnionJob1("联盟长", 1), UnionJob2("副联盟长", 2),UnionJob3("护法", 3),UnionJob4("普通", 4);
	
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private UnionJobType(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public static String getName(int index) {
		for (UnionJobType dt : UnionJobType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static UnionJobType getType(int index) {
		for (UnionJobType dt : UnionJobType.values()) {
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
