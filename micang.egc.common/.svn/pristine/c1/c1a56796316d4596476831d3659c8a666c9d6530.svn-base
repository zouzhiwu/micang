package com.common.template;

import com.common.template.TemplateBase;

public class RoleTemplate extends TemplateBase {

	private Integer hp;
	private Float speedAdd;
	private Integer skillId;
	private Float skillAdd;
	private Float hpAdd;
	private Float tough;
	private Float crit;
	private Float cdtime;
	private Float stealhp;

	public RoleTemplate(Integer id, Integer hp, Float speedAdd, Integer skillId, Float skillAdd, Float hpAdd, Float tough, Float crit, Float cdtime, Float stealhp) {
		super.setId(id);
		super.setName("角色" + id);
		this.hp = hp;
		this.speedAdd = speedAdd;
		this.skillId = skillId;
		this.skillAdd = skillAdd;
		this.hpAdd = hpAdd;
		this.tough = tough;
		this.crit = crit;
		this.cdtime = cdtime;
		this.stealhp = stealhp;
	}

	public Integer getHp() {
		return hp;
	}
	public void setHp(Integer hp) {
		this.hp = hp;
	}
	public Float getSpeedAdd() {
		return speedAdd;
	}
	public void setSpeedAdd(Float speedAdd) {
		this.speedAdd = speedAdd;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public Float getSkillAdd() {
		return skillAdd;
	}
	public void setSkillAdd(Float skillAdd) {
		this.skillAdd = skillAdd;
	}
	public Float getHpAdd() {
		return hpAdd;
	}
	public void setHpAdd(Float hpAdd) {
		this.hpAdd = hpAdd;
	}
	public Float getTough() {
		return tough;
	}
	public void setTough(Float tough) {
		this.tough = tough;
	}
	public Float getCrit() {
		return crit;
	}
	public void setCrit(Float crit) {
		this.crit = crit;
	}
	public Float getCdtime() {
		return cdtime;
	}
	public void setCdtime(Float cdtime) {
		this.cdtime = cdtime;
	}
	public Float getStealhp() {
		return stealhp;
	}
	public void setStealhp(Float stealhp) {
		this.stealhp = stealhp;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("id=" + super.getId());
		sb.append(" name=" + super.getName());
		sb.append(" hp=" + hp);
		sb.append(" speedAdd=" + speedAdd);
		sb.append(" skillId=" + skillId);
		sb.append(" skillAdd=" + skillAdd);
		sb.append(" hpAdd=" + hpAdd);
		sb.append(" tough=" + tough);
		sb.append(" crit=" + crit);
		sb.append(" cdtime=" + cdtime);
		sb.append(" stealhp=" + stealhp);
		sb.append("]");
		return sb.toString();
	}
}