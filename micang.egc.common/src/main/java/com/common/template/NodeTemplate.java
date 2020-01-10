package com.common.template;

import com.common.template.TemplateBase;

public class NodeTemplate extends TemplateBase {

	private String icon;
	private Byte type;
	private Byte campType;
	private Byte wayType;
	private Byte armsType;
	private Byte monsterType;
	private Byte towerCode;
	private Byte skillTarget;
	private Integer hp;
	private Integer pa;
	private Integer ma;
	private Integer pd;
	private Integer md;
	private Short ats;
	private Short aoe;
	private Short vr;
	private Short mvs;
	private Short gold;
	private Short exp;
	private Integer growthHP;
	private Integer growthPA;
	private Integer growthMA;
	private Integer growthGold;
	private Integer growthExp;
	private Integer buff;
	private Integer growthInterval;
	private Integer endGrowthTime;
	private Short x;
	private Short y;
	

	public NodeTemplate(Integer id, String name, String icon, Byte type, Byte campType, Byte wayType, Byte armsType, Byte monsterType, Byte towerCode, Byte skillTarget, Integer hp
			, Integer pa, Integer ma, Integer pd, Integer md, Short ats, Short aoe, Short vr, Short mvs, Short gold, Short exp, Integer growthHP, Integer growthPA
			, Integer growthMA, Integer growthGold, Integer growthExp, Integer buff, Integer growthInterval, Integer endGrowthTime, Short x, Short y) {
		super.setId(id);
		super.setName(name);
		this.icon = icon;
		this.type = type;
		this.campType = campType;
		this.wayType = wayType;
		this.armsType = armsType;
		this.monsterType = monsterType;
		this.towerCode = towerCode;
		this.skillTarget = skillTarget;
		this.hp = hp;
		this.pa = pa;
		this.ma = ma;
		this.pd = pd;
		this.md = md;
		this.ats = ats;
		this.aoe = aoe;
		this.vr = vr;
		this.mvs = mvs;
		this.gold = gold;
		this.exp = exp;
		this.growthHP = growthHP;
		this.growthPA = growthPA;
		this.growthMA = growthMA;
		this.growthGold = growthGold;
		this.growthExp = growthExp;
		this.buff = buff;
		this.growthInterval = growthInterval;
		this.endGrowthTime = endGrowthTime;
		this.x = x;
		this.y = y;
	}
	public String getIcon() {
		return icon;
	}
	public Byte getType() {
		return type;
	}
	public Byte getCampType() {
		return campType;
	}
	public Byte getWayType() {
		return wayType;
	}
	public Byte getArmsType() {
		return armsType;
	}
	public Byte getMonsterType() {
		return monsterType;
	}
	public Byte getSkillTarget() {
		return skillTarget;
	}
	public Integer getHp() {
		return hp;
	}
	public Integer getPa() {
		return pa;
	}
	public Integer getMa() {
		return ma;
	}
	public Integer getPd() {
		return pd;
	}
	public Integer getMd() {
		return md;
	}
	public Short getAts() {
		return ats;
	}
	public Short getAoe() {
		return aoe;
	}
	public Short getVr() {
		return vr;
	}
	public Short getMvs() {
		return mvs;
	}
	public Short getGold() {
		return gold;
	}
	public Short getExp() {
		return exp;
	}
	public Integer getGrowthHP() {
		return growthHP;
	}
	public Integer getGrowthPA() {
		return growthPA;
	}
	public Integer getGrowthMA() {
		return growthMA;
	}
	public Integer getGrowthGold() {
		return growthGold;
	}
	public Integer getGrowthExp() {
		return growthExp;
	}
	public Integer getBuff() {
		return buff;
	}
	public Integer getGrowthInterval() {
		return growthInterval;
	}
	public Integer getEndGrowthTime() {
		return endGrowthTime;
	}
	public Short getX() {
		return x;
	}
	public Short getY() {
		return y;
	}
	public Byte getTowerCode() {
		return towerCode;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("id=" + super.getId());
		sb.append(" name=" + super.getName());
		sb.append(" icon=" + icon);
		sb.append(" type=" + type);
		sb.append(" campType=" + campType);
		sb.append(" wayType=" + wayType);
		sb.append(" armsType=" + armsType);
		sb.append(" monsterType=" + monsterType);
		sb.append(" skillTarget=" + skillTarget);
		sb.append(" hp=" + hp);
		sb.append(" pa=" + pa);
		sb.append(" ma=" + ma);
		sb.append(" ats=" + ats);
		sb.append(" aoe=" + aoe);
		sb.append(" vr=" + vr);
		sb.append(" mvs=" + mvs);
		sb.append(" gold=" + gold);
		sb.append(" exp=" + exp);
		sb.append(" growthHP=" + growthHP);
		sb.append(" growthPA=" + growthPA);
		sb.append(" growthMA=" + growthMA);
		sb.append(" growthGold=" + growthGold);
		sb.append(" growthExp=" + growthExp);
		sb.append(" buff=" + buff);
		sb.append(" growthInterval=" + growthInterval);
		sb.append(" endGrowthTime=" + endGrowthTime);
		sb.append(" x=" + x);
		sb.append(" y=" + y);
		sb.append(" towerCode=" + towerCode);
		sb.append("]");
		return sb.toString();
	}
}