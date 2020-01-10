package com.common.entity;

import java.util.List;

public class Hero {
	
	private Long accountId;
	private byte[] heroBytes;
	private List<Integer> heroTemplateIdList;
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public byte[] getHeroBytes() {
		return heroBytes;
	}
	public void setHeroBytes(byte[] heroBytes) {
		this.heroBytes = heroBytes;
	}
	public List<Integer> getHeroTemplateIdList() {
		return heroTemplateIdList;
	}
	public void setHeroTemplateIdList(List<Integer> heroTemplateIdList) {
		this.heroTemplateIdList = heroTemplateIdList;
	}
}
