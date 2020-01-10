package com.game.entity;

public class Chat implements Comparable<Chat>{
	/**唯一标识**/
	private Long id;
	
	/**用户1**/
	private Long accountId1;
	
	/**用户2**/
	private Long accountId2;
	
	/**内容**/
	private String context;
	
	/**时间**/
	private Integer nowTime;
	
	/**阅读状态**/
	private Byte readType;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId1() {
		return accountId1;
	}

	public void setAccountId1(Long accountId1) {
		this.accountId1 = accountId1;
	}

	public Long getAccountId2() {
		return accountId2;
	}

	public void setAccountId2(Long accountId2) {
		this.accountId2 = accountId2;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getNowTime() {
		return nowTime;
	}

	public void setNowTime(Integer nowTime) {
		this.nowTime = nowTime;
	}

	public Byte getReadType() {
		return readType;
	}

	public void setReadType(byte i) {
		this.readType =  i;
	}

	@Override
	public int compareTo(Chat o) {
		return o.getNowTime() >= this.nowTime ? -1 : 1;
	}
	
}
