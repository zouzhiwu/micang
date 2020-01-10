package com.common.template;

import com.common.template.TemplateBase;

public class ConstantTemplate extends TemplateBase {

	private Integer value;

	public ConstantTemplate(Integer id, Integer value) {
		super.setId(id);
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("id=" + super.getId());
		sb.append(" value=" + value);
		sb.append("]");
		return sb.toString();
	}
}