package com.common.template;

import com.common.template.TemplateBase;

public class LevelTemplate extends TemplateBase {

	private Byte lv;
	private Byte type;
	private Integer exp;

	public Byte getLv() {
		return lv;
	}

	public Byte getType() {
		return type;
	}

	public Integer getTotalExp() {
		return totalExp;
	}

	private Integer totalExp;

	public Integer getExp() {
		return exp;
	}

	public LevelTemplate(Integer id, Byte type, Byte lv, Integer exp, Integer totalExp) {
		super.setId(id);
		super.setName("name" + id);
		this.lv = lv;
		this.type = type;
		this.exp = exp;
		this.totalExp = totalExp;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("id=" + super.getId());
		sb.append(" name=" + super.getName());
		sb.append(" lv=" + lv);
		sb.append(" type=" + type);
		sb.append(" exp=" + exp);
		sb.append(" totalExp=" + totalExp);
		sb.append("]");
		return sb.toString();
	}
}