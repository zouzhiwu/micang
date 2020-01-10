package com.common.template;

public class ChapterStarGiftTemplate extends TemplateBase {
	
	
	private Integer chapterId;
	
	private String star;
	
	private String reward1;
	
	private String num1;
	
	private String reward2;
	
	private String num2;
	
	private String reward3;
	
	private String num3;



	public ChapterStarGiftTemplate(Integer id, Integer chapterId, String star, String reward1, String num1, String reward2,
			String num2, String reward3, String num3) {
		super.setId(id);
		super.setName("name" + id);
		
		this.chapterId = chapterId;
		this.star = star;
		this.reward1 = reward1;
		this.num1 = num1;
		this.reward2 = reward2;
		this.num2 = num2;
		this.reward3 = reward3;
		this.num3 = num3;
	}

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getReward1() {
		return reward1;
	}

	public void setReward1(String reward1) {
		this.reward1 = reward1;
	}

	public String getNum1() {
		return num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getReward2() {
		return reward2;
	}

	public void setReward2(String reward2) {
		this.reward2 = reward2;
	}

	public String getNum2() {
		return num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

	public String getReward3() {
		return reward3;
	}

	public void setReward3(String reward3) {
		this.reward3 = reward3;
	}

	public String getNum3() {
		return num3;
	}

	public void setNum3(String num3) {
		this.num3 = num3;
	}
	
	
	
	

}
