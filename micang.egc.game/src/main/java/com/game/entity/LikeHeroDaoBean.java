package com.game.entity;

public class LikeHeroDaoBean {

	private int heroTemplateId;
	// 熟练度
	private int likeVal;
	// 位置 (0代表下位了)
	private Integer seat;
	// 等级
	private int heroLevel;

	public int getHeroTemplateId() {
		return heroTemplateId;
	}

	public void setHeroTemplateId(int heroTemplateId) {
		this.heroTemplateId = heroTemplateId;
	}

	public int getLikeVal() {
		return likeVal;
	}

	public void setLikeVal(int likeVal) {
		this.likeVal = likeVal;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getHeroLevel() {
		return heroLevel;
	}

	public void setHeroLevel(int heroLevel) {
		this.heroLevel = heroLevel;
	}



}
