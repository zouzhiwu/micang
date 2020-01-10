package com.game.entity;

public class ChatGroup {
	private Long owner;
	private String tname;
	private Integer maxusers;
	private String tid;
	private Integer size;
	private String custom;
	
	public Long getOwner() {
		return owner;
	}
	public void setOwner(Long owner) {
		this.owner = owner;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Integer getMaxusers() {
		return maxusers;
	}
	public void setMaxusers(Integer maxusers) {
		this.maxusers = maxusers;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
}
