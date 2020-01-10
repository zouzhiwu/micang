package com.game.util;

import com.common.constant.ParameterIdConstant;
import com.common.template.ParameterTemplate;
import com.game.config.ParameterConfig;

public class ParameterUtil {
	
	private static int getIntegerValue(int templateId) {
		ParameterTemplate template = ParameterConfig.map.get(templateId);
		int value = Integer.parseInt(template.getValue());
		return value;
	}
	/***
	 * 第一次出兵的游戏时间，单位：毫秒
	 */
	public static int getSoldierBirthTime() {
		int value = getIntegerValue(ParameterIdConstant.soldier_create_time_id);
		int birthTime = gameTime2realTime(value * 1000);
		return birthTime;
	}
	
	/**
	 * 每波兵的间隔时间，单位：毫秒
	 */
	public static int getSoldierPushInterval() {
		int value = getIntegerValue(ParameterIdConstant.soldier_create_interval_id);
		int pushSoldierInterval = gameTime2realTime(value * 1000);
		return pushSoldierInterval;
	}
	
	public static int gameTime2realTime(int millisecond) {
		return (int)(millisecond / ParameterConfig.timeProportion);
	}
	
	/**
	 * 获取小龙第一次出生时间，单位：毫秒
	 */
	public static int getSmallDragonBirthTime() {
		int value = getIntegerValue(ParameterIdConstant.create_small_dragon_time_id);
		int birthTime = gameTime2realTime(value * 1000);
		return birthTime;
	}
	
	/**
	 * 获取大龙第一次出生时间，单位：毫秒
	 */
	public static int getBigDragonBirthTime() {
		int value = getIntegerValue(ParameterIdConstant.create_big_dragon_time_id);
		int birthTime = gameTime2realTime(value * 1000);
		return birthTime;
	}
	
	/**
	 * 获取野怪第一次出生时间，单位：毫秒
	 */
	public static int getMonsterBirthTime() {
		int value = getIntegerValue(ParameterIdConstant.create_monster_time_id);
		int birthTime = gameTime2realTime(value * 1000);
		return birthTime;
	}
	
	/**
	 * 当某英雄在x（单位秒）时间内没有获取经验值时，将按照每秒获得一定量经验值（常量表填写参数）
	 */
	public static int getGiveExpInterval() {
		return getIntegerValue(ParameterIdConstant.give_exp_interval);
	}
	
	/**
	 * 当某英雄在一定量时间内没有获取经验值时，将按照每秒获得x经验值（常量表填写参数）
	 */
	public static int getGiveExp() {
		return getIntegerValue(ParameterIdConstant.give_exp);
	}
	
	/***
	 * 钻石抽所需道具
	 */
	public static int gachaItemIdByJb() {
		return getIntegerValue(ParameterIdConstant.index19);
	}
	
	/**
	 * 每日金币抽奖免费次数
	 */
	public static int getGachaFreeByGb() {
		return getIntegerValue(ParameterIdConstant.index10);
	}
	
	/**
	 * 每日钻石抽奖免费次数
	 */
	public static int getGachaFreeByJb() {
		return getIntegerValue(ParameterIdConstant.index11);
	}
	
	/**
	 * 金币抽免费间隔，单位：秒
	 */
	public static int getGachaFreeIntervalByGb() {
		return getIntegerValue(ParameterIdConstant.index12);
	}
	
	/**
	 * 每日金币10连最大购买次数
	 */
	public static int getGachaFreeMaxByGb() {
		return getIntegerValue(ParameterIdConstant.index13);
	}
	
	/**
	 * 钻石抽顶级产出保底所需次数
	 */
	public static int getGachaBaodiCountByJb() {
		return getIntegerValue(ParameterIdConstant.index14);
	}
	
	/**
	 * 钻石40抽保底掉落组
	 */
	public static int getGacha40GroupIdByJb() {
		return getIntegerValue(ParameterIdConstant.index15);
	}
	
	/**
	 * 钻石抽次顶级产出保底所需次数
	 */
	public static int getGachaBaodiCountByGb() {
		return getIntegerValue(ParameterIdConstant.index16);
	}
	
	/**
	 * 钻石10抽保底掉落组
	 */
	public static int getGacha10GroupIdByJb() {
		return getIntegerValue(ParameterIdConstant.index17);
	}
	
	/**
	 * 每日钻石半价次数
	 */
	public static int getGachaDiscountCnt() {
		return getIntegerValue(ParameterIdConstant.index18);
	}
	
	/**
	 * 钻石抽所需道具
	 */
	public static int getGachaFreeItemTemplateId() {
		return getIntegerValue(ParameterIdConstant.index19);
	}
}
