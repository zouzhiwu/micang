package com.game.entity;

public class PublicMail {
	
	private Long id;
	
	/**邮件标题*/
	private String title;
	
	/**邮件名*/
	private String name;
	
	/**邮件内容*/
	private String content;
	
	/**附件*/
	private byte[] itemList;
	
	/**创建时间*/
	private int createTime;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public byte[] getItemList() {
		return itemList;
	}
	public void setItemList(byte[] itemList) {
		this.itemList = itemList;
	}
	public int getCreateTime() {
		return createTime;
	}
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
}
