package com.common.template;

public class SlogansTemplate  extends TemplateBase {
	
	private String text;

	public SlogansTemplate(Integer id, String text) {
		super.setId(id);
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
