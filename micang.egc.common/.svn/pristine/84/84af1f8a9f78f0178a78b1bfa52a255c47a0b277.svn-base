package com.common.template;

import com.common.template.TemplateBase;

public class ParameterTemplate extends TemplateBase {

	private String value;

	public ParameterTemplate(Integer id, String name, String value) {
		super.setId(id);
		super.setName(name);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("id=" + super.getId());
		sb.append(" name=" + super.getName());
		sb.append(" value=" + value);
		sb.append("]");
		return sb.toString();
	}
}