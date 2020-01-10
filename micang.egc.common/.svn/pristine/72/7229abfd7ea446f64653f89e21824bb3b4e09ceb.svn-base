package com.common.template;

import com.common.template.TemplateBase;

public class AttrConvertTemplate extends TemplateBase {

	private Integer OneAttrId;
	private Float value;

	public AttrConvertTemplate(Integer twoAttrId, Integer OneAttrId, Float value) {
		super.setId(twoAttrId);
		super.setName("二级属性" + twoAttrId);
		this.OneAttrId = OneAttrId;
		this.value = value;
	}
	public Integer getOneAttrId() {
		return OneAttrId;
	}
	public void setOneAttrId(Integer oneAttrId) {
		OneAttrId = oneAttrId;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("id=" + super.getId());
		sb.append(" name=" + super.getName());
		sb.append(" OneAttrId=" + OneAttrId);
		sb.append(" value=" + value);
		sb.append("]");
		return sb.toString();
	}
}