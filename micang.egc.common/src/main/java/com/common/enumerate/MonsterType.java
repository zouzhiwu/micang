package com.common.enumerate;

public enum MonsterType {
	SamllDragon("小龙", 1), BigDragon("大龙", 2), BlueBird("蓝区巨鸟怪", 3), BlueToad("蓝区蛤蟆怪", 4), BlueWolf("蓝区小狼怪", 5)
	, BlueStone("蓝区石头人", 6), BlueRbuff("蓝区红爸爸", 7), BlueBbuff("蓝区蓝爸爸", 8), RedBird("红区巨鸟怪", 9), RedToad("红区蛤蟆怪", 10)
	, RedWolf("红区小狼怪", 11), RedStone("红区石头人", 12), RedRbuff("红区红爸爸", 13), RedBbuff("红区蓝爸爸", 14);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private MonsterType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (MonsterType dt : MonsterType.values()) {
			if (dt.getIndex() == index) {
				return dt.name;
			}
		}
		return null;
	}
	
	public static MonsterType getType(int index) {
		for (MonsterType dt : MonsterType.values()) {
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
