package com.common.template;

public class CheckPointTemplate  {
	private Integer id;
	private Integer chapterId;
	private Byte diffculty;
	private Short plotId;
	private String image;
	private String icon;
	private String title;
	private String chapterContent;
	private Byte teamLineup;
	private Short exp;
	private Short gold;
	private Integer reward;
	private Integer lastLevelId;
	private Integer nextLevelId;
	private Short newspaperId;
	private Integer posX;
	private Integer posY;
	private String npc;
	private Integer task1;
	private String taskTxt1;
	private Integer task2;
	private String taskTxt2;
	private Integer task3;
	private String taskTxt3;
	private Short memberExp;

	public CheckPointTemplate(Integer id, Integer chapterId, Byte diffculty, Short plotId, String image, String icon,
			String title, String chapterContent, Byte teamLineup, Short exp, Short gold, Integer reward,
			Integer lastLevelId, Integer nextLevelId, Short newspaperId, Integer posX, Integer posY, String npc,
			Integer task1, String taskTxt1, Integer task2, String taskTxt2, Integer task3, String taskTxt3,
			Short memberExp) {
		super();
		this.id = id;
		this.chapterId = chapterId;
		this.diffculty = diffculty;
		this.plotId = plotId;
		this.image = image;
		this.icon = icon;
		this.title = title;
		this.chapterContent = chapterContent;
		this.teamLineup = teamLineup;
		this.exp = exp;
		this.gold = gold;
		this.reward = reward;
		this.lastLevelId = lastLevelId;
		this.nextLevelId = nextLevelId;
		this.newspaperId = newspaperId;
		this.posX = posX;
		this.posY = posY;
		this.npc = npc;
		this.task1 = task1;
		this.taskTxt1 = taskTxt1;
		this.task2 = task2;
		this.taskTxt2 = taskTxt2;
		this.task3 = task3;
		this.taskTxt3 = taskTxt3;
		this.memberExp = memberExp;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public Byte getDiffculty() {
		return diffculty;
	}
	public Short getPlotId() {
		return plotId;
	}
	public String getImage() {
		return image;
	}
	public String getIcon() {
		return icon;
	}
	public String getTitle() {
		return title;
	}
	public String getChapterContent() {
		return chapterContent;
	}
	public Byte getTeamLineup() {
		return teamLineup;
	}
	public Short getExp() {
		return exp;
	}
	public Short getGold() {
		return gold;
	}
	public Integer getReward() {
		return reward;
	}
	public Integer getLastLevelId() {
		return lastLevelId;
	}
	public Integer getNextLevelId() {
		return nextLevelId;
	}
	public Short getNewspaperId() {
		return newspaperId;
	}
	public Integer getPosX() {
		return posX;
	}
	public Integer getPosY() {
		return posY;
	}
	public String getNpc() {
		return npc;
	}
	public Integer getTask1() {
		return task1;
	}
	public String getTaskTxt1() {
		return taskTxt1;
	}
	public Integer getTask2() {
		return task2;
	}
	public String getTaskTxt2() {
		return taskTxt2;
	}
	public Integer getTask3() {
		return task3;
	}
	public String getTaskTxt3() {
		return taskTxt3;
	}
	public Short getMemberExp() {
		return memberExp;
	}


}