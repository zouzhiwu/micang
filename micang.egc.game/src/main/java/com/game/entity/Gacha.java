package com.game.entity;

/**
 * 
 * @author 用户抽奖记录实体表
 *
 */
public class Gacha {
	
	private Long accountId;//用户编号
	
	private Integer gachaCount;//用户钻石抽奖次数
	
	private Integer  lastDiamondNow;//用户最后一次免费钻石的时间
	
	private Integer  lastHalfDiamondNow;//用户最后一次半价钻石的时间
	
	private Integer  lastGoldNow;//用户最后一场抽取免费金币的时间
	
	private Integer  lastTenGold;//用户最后一次金币十连的时间
	
	private Integer freeGold;//剩余免费次数-金币
	
	private Integer freeDiamond;//剩余免费次数-钻石
	
	private Integer halfDiamond;//剩余半价次数钻石
	
	private Integer tenGold;//剩余金币十连抽次数

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getGachaCount() {
		return gachaCount;
	}

	public void setGachaCount(Integer gachaCount) {
		this.gachaCount = gachaCount;
	}

	public Integer getLastDiamondNow() {
		return lastDiamondNow;
	}

	public void setLastDiamondNow(Integer lastDiamondNow) {
		this.lastDiamondNow = lastDiamondNow;
	}

	public Integer getLastGoldNow() {
		return lastGoldNow;
	}

	public void setLastGoldNow(Integer lastGoldNow) {
		this.lastGoldNow = lastGoldNow;
	}

	public Integer getLastTenGold() {
		return lastTenGold;
	}

	public void setLastTenGold(Integer lastTenGold) {
		this.lastTenGold = lastTenGold;
	}

	public Integer getFreeGold() {
		return freeGold;
	}

	public void setFreeGold(Integer freeGold) {
		this.freeGold = freeGold;
	}

	public Integer getFreeDiamond() {
		return freeDiamond;
	}

	public void setFreeDiamond(Integer freeDiamond) {
		this.freeDiamond = freeDiamond;
	}

	public Integer getHalfDiamond() {
		return halfDiamond;
	}

	public void setHalfDiamond(Integer halfDiamond) {
		this.halfDiamond = halfDiamond;
	}

	public Integer getTenGold() {
		return tenGold;
	}

	public void setTenGold(Integer tenGold) {
		this.tenGold = tenGold;
	}

	public Integer getLastHalfDiamondNow() {
		return lastHalfDiamondNow;
	}

	public void setLastHalfDiamondNow(Integer lastHalfDiamondNow) {
		this.lastHalfDiamondNow = lastHalfDiamondNow;
	}
}
