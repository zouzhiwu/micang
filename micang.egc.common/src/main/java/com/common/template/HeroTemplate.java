package com.common.template;

import com.common.template.TemplateBase;

public class HeroTemplate extends TemplateBase {
	private int ItemId;
	private String icon;
	private Byte work;
	private Integer hp;
	private Integer pa;
	private Integer ma;
	private Integer pd;
	private Integer md;
	private Short ats;
	private Short aoe;
	private Short mvs;
	private Integer cd;
	private Short pcrt;
	private Short mcrt;
	private Short ppt;
	private Short mpt;
	private Short hi;
	private Short hr;
	private Integer growthHp;
	private Integer growthPa;
	private Integer growthMa;
	private Integer growthPd;
	private Integer growthMd;
	private Short growthAts;
	private Short growthAoe;
	private Short growthMvs;
	private Integer growthCd;
	private Short growthPcrt;
	private Short growthMcrt;
	private Short growthPpt;
	private Short growthMpt;
	private Short growthHi;
	private Short growthHr;
	private Integer skillId;
	private String heroAbout;
	private String Description;
	private int HeroEquip;	
	private String Location;
	private short vr;
	
	
	public HeroTemplate(Integer id, String name, int itemId, String icon, Byte work, Integer hp, Integer pa, Integer ma, Integer pd, Integer md,
			Short ats, Short aoe, Short mvs, Integer cd, Short pcrt, Short mcrt, Short ppt, Short mpt,
			Short hi, Short hr, Integer growthHp, Integer growthPa, Integer growthMa, Integer growthPd,
			Integer growthMd, Short growthAts, Short growthAoe, Short growthMvs, Integer growthCd, Short growthPcrt,
			Short growthMcrt, Short growthPpt, Short growthMpt, Short growthHi, Short growthHr, Integer skillId,
			String heroAbout, String description, int heroEquip, String location, short vr) {
		super.setId(id);
		super.setName(name);
		ItemId = itemId;
		this.icon = icon;
		this.work = work;
		this.hp = hp;
		this.pa = pa;
		this.ma = ma;
		this.pd = pd;
		this.md = md;
		this.ats = ats;
		this.aoe = aoe;
		this.mvs = mvs;
		this.cd = cd;
		this.pcrt = pcrt;
		this.mcrt = mcrt;
		this.ppt = ppt;
		this.mpt = mpt;
		this.hi = hi;
		this.hr = hr;
		this.growthHp = growthHp;
		this.growthPa = growthPa;
		this.growthMa = growthMa;
		this.growthPd = growthPd;
		this.growthMd = growthMd;
		this.growthAts = growthAts;
		this.growthAoe = growthAoe;
		this.growthMvs = growthMvs;
		this.growthCd = growthCd;
		this.growthPcrt = growthPcrt;
		this.growthMcrt = growthMcrt;
		this.growthPpt = growthPpt;
		this.growthMpt = growthMpt;
		this.growthHi = growthHi;
		this.growthHr = growthHr;
		this.skillId = skillId;
		this.heroAbout = heroAbout;
		Description = description;
		HeroEquip = heroEquip;
		Location = location;
		this.vr = vr;
	}
	public String getIcon() {
		return icon;
	}
	public Byte getWork() {
		return work;
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
	public Short getMvs() {
		return mvs;
	}
	public Integer getCd() {
		return cd;
	}
	public Short getPcrt() {
		return pcrt;
	}
	public Short getMcrt() {
		return mcrt;
	}
	public Short getPpt() {
		return ppt;
	}
	public Short getMpt() {
		return mpt;
	}
	public Short getHi() {
		return hi;
	}
	public Short getHr() {
		return hr;
	}
	public Integer getGrowthHp() {
		return growthHp;
	}
	public Integer getGrowthPa() {
		return growthPa;
	}
	public Integer getGrowthMa() {
		return growthMa;
	}
	public Integer getGrowthPd() {
		return growthPd;
	}
	public Integer getGrowthMd() {
		return growthMd;
	}
	public Short getGrowthAts() {
		return growthAts;
	}
	public Short getGrowthAoe() {
		return growthAoe;
	}
	public Short getGrowthMvs() {
		return growthMvs;
	}
	public Integer getGrowthCd() {
		return growthCd;
	}
	public Short getGrowthPcrt() {
		return growthPcrt;
	}
	public Short getGrowthMcrt() {
		return growthMcrt;
	}
	public Short getGrowthPpt() {
		return growthPpt;
	}
	public Short getGrowthMpt() {
		return growthMpt;
	}
	public Short getGrowthHi() {
		return growthHi;
	}
	public Short getGrowthHr() {
		return growthHr;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public String getHeroAbout() {
		return heroAbout;
	}
	public int getItemId() {
		return ItemId;
	}
	public String getDescription() {
		return Description;
	}
	public int getHeroEquip() {
		return HeroEquip;
	}
	public String getLocation() {
		return Location;
	}
	
	public short getVr() {
		return vr;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("id=" + super.getId());
		sb.append(" name=" + super.getName());
		sb.append(" icon=" + icon);
		sb.append(" route=" + work);
		sb.append(" hp=" + hp);
		sb.append(" pa=" + pa);
		sb.append(" ma=" + ma);
		sb.append(" pd=" + pd);
		sb.append(" md=" + md);
		sb.append(" ats=" + ats);
		sb.append(" aoe=" + aoe);
		sb.append(" mvs=" + mvs);
		sb.append(" cd=" + cd);
		sb.append(" pcrt=" + pcrt);
		sb.append(" mcrt=" + mcrt);
		sb.append(" ppt=" + ppt);
		sb.append(" mpt=" + mpt);
		sb.append(" hi=" + hi);
		sb.append(" hr=" + hr);
		sb.append(" growthHp=" + 	growthHp);
		sb.append(" growthPa=" + 	growthPa);
		sb.append(" growthMa=" + 	growthMa);
		sb.append(" growthPd=" + 	growthPd);
		sb.append(" growthMd=" + 	growthMd);
		sb.append(" growthAts=" + 	growthAts);
		sb.append(" growthAoe=" + 	growthAoe);
		sb.append(" growthMvs=" + 	growthMvs);
		sb.append(" growthCd=" + 	growthCd);
		sb.append(" growthPcrt=" + 	growthPcrt);
		sb.append(" growthMcrt=" + 	growthMcrt);
		sb.append(" growthPpt=" + 	growthPpt);
		sb.append(" growthMpt=" + 	growthMpt);
		sb.append(" growthHi=" + 	growthHi);
		sb.append(" growthHr=" + 	growthHr); 
		sb.append(" skillId=" + skillId);
		sb.append(" heroAbout=" + heroAbout);
		sb.append("]");
		return sb.toString();
	}
}