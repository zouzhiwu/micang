package com.common.template;

public class MailTemplate  extends TemplateBase {
	private String Title;
	private String Name;
	private String Content;
	private String Param;
	private String Reward;
	private String RewardNum;
	
	public MailTemplate(Integer id, String title, String name, String content, String param, String reward,
			String rewardNum) {
		super.setId(id);
		Title = title;
		Name = name;
		Content = content;
		Param = param;
		Reward = reward;
		RewardNum = rewardNum;
	}
	public String getTitle() {
		return Title;
	}
	public String getName() {
		return Name;
	}
	public String getContent() {
		return Content;
	}
	public String getParam() {
		return Param;
	}
	public String getReward() {
		return Reward;
	}
	public String getRewardNum() {
		return RewardNum;
	}
	
}
