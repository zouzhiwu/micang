package com.common.template;

public class ChapterTemplate extends TemplateBase {
	private Integer id;
	private Integer difficulty;
	private String levelId;
	private String chapterNo;
	private String title;
	private String address;
	private String boxGift;
	private String num;
	private Byte unlock;
	private Integer chapterId;
	public ChapterTemplate(Integer id, Integer difficulty, String levelId, String chapterNo, String title,
			String address, String boxGift, String num, Byte unlock, Integer chapterId) {
		super();
		this.id = id;
		this.difficulty = difficulty;
		this.levelId = levelId;
		this.chapterNo = chapterNo;
		this.title = title;
		this.address = address;
		this.boxGift = boxGift;
		this.num = num;
		this.unlock = unlock;
		this.chapterId = chapterId;
	}
	public Integer getId() {
		return id;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public String getLevelId() {
		return levelId;
	}
	public String getChapterNo() {
		return chapterNo;
	}
	public String getTitle() {
		return title;
	}
	public String getAddress() {
		return address;
	}
	public String getBoxGift() {
		return boxGift;
	}
	public String getNum() {
		return num;
	}
	public Byte getUnlock() {
		return unlock;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	
}