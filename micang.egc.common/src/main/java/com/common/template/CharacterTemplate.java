package com.common.template;

public class CharacterTemplate extends TemplateBase {
	
	
	private String characterTxt;
	private Integer quality;
	private String characterIcon;
	private String probability1;
	private String character1;
	private String probability2;
	private String character2;
	private Integer constant;
	private String number;
	private String yxt;

	public CharacterTemplate(Integer id, String name, String characterTxt, Integer quality, String characterIcon, String probability1,
			String character1, String probability2, String character2, Integer constant, String number, String yxt) {
		super.setId(id);
		super.setName(name);
		
		this.characterTxt = characterTxt;
		this.quality = quality;
		this.characterIcon = characterIcon;
		this.probability1 = probability1;
		this.character1 = character1;
		this.probability2 = probability2;
		this.character2 = character2;
		this.constant = constant;
		this.number = number;
		this.yxt = yxt;
	}

	public String getCharacterTxt() {
		return characterTxt;
	}

	public Integer getQuality() {
		return quality;
	}

	public String getCharacterIcon() {
		return characterIcon;
	}

	public String getProbability1() {
		return probability1;
	}

	public String getCharacter1() {
		return character1;
	}

	public String getProbability2() {
		return probability2;
	}

	public String getCharacter2() {
		return character2;
	}

	public Integer getConstant() {
		return constant;
	}

	public String getNumber() {
		return number;
	}

	public String getYxt() {
		return yxt;
	}
	
	
	
	
	

}
