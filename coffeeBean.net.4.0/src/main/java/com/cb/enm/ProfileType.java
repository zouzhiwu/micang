package com.cb.enm;

public enum ProfileType {
	Development("development", 1), Test("test", 2), Demo("demo", 3), Production("production", 4);
	// 成员变量
	private String name;
	private int index;

	private ProfileType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (ProfileType profile : ProfileType.values()) {
			if (profile.getIndex() == index) {
				return profile.name;
			}
		}
		return null;
	}
	
	public static ProfileType getType(int index) {
		for (ProfileType profile : ProfileType.values()) {
			if (profile.getIndex() == index) {
				return profile;
			}
		}
		return null;
	}
	
	public static ProfileType getType(String name) {
		for (ProfileType profile : ProfileType.values()) {
			if (profile.name.equals(name)) {
				return profile;
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
