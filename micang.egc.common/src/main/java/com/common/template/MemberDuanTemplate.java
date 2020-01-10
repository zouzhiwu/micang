package com.common.template;

public class MemberDuanTemplate extends TemplateBase {

	private Byte quality;
	private Byte ascending;
	private String material1;
	private String number;
	
	private Short y1;
	private Short v1;
	private Short y2;
	private Short v2;
	private Short y3;
	private Short v3;
	private Short y4;
	private Short v4;
	private Short y5;
	private Short v5;
	
	private Short grade;
	private String txt;
	private int coin;

	public MemberDuanTemplate(Integer id, String name, Byte quality, Byte ascending, String material1, String number, Short y1, Short v1,
			Short y2, Short v2, Short y3, Short v3, Short y4, Short v4, Short y5, Short v5, Short grade, String txt, int coin) {
		super.setId(id);
		super.setName(name);
		
		this.quality = quality;
		this.ascending = ascending;
		this.material1 = material1;
		this.number = number;
		this.y1 = y1;
		this.v1 = v1;
		this.y2 = y2;
		this.v2 = v2;
		this.y3 = y3;
		this.v3 = v3;
		this.y4 = y4;
		this.v4 = v4;
		this.y5 = y5;
		this.v5 = v5;
		this.grade = grade;
		this.txt = txt;
		this.coin = coin;
	}

	public Byte getQuality() {
		return quality;
	}

	public Byte getAscending() {
		return ascending;
	}
	public String getMaterial1() {
		return material1;
	}
	public String getNumber() {
		return number;
	}
	public Short getY1() {
		return y1;
	}
	public Short getV1() {
		return v1;
	}
	public Short getY2() {
		return y2;
	}
	public Short getV2() {
		return v2;
	}
	public Short getY3() {
		return y3;
	}
	public Short getV3() {
		return v3;
	}
	public Short getY4() {
		return y4;
	}
	public Short getV4() {
		return v4;
	}
	public Short getY5() {
		return y5;
	}
	public Short getV5() {
		return v5;
	}
	public Short getGrade() {
		return grade;
	}
	public String getTxt() {
		return txt;
	}

	public int getCoin() {
		return coin;
	}
	
	
	
	
	
	
	
	
}
