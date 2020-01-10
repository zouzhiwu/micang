package com.game.entity;

public class FriendRelation {
	
	 /**玩家1的id**/
	private Long accountId1;
	
	/**玩家2 的id**/
	private Long accountId2;
	
	/**玩家关系 1-好友 2-玩家a拉黑玩家b 3-玩家b拉黑玩家a  4-a申请b加好友 5-b申请a当好友 6-ab互相申请当好友**/
	private Integer relation;
	
	/**体力赠送关系 0.a-b 1-a->b 2.a<-b 3.a<->b**/
	private Integer giving;
	
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
	public Integer getRelation() {
		return relation;
	}
	public void setRelation(Integer relation) {
		this.relation = relation;
	}
	public Integer getGiving() {
		return giving;
	}
	public void setGiving(Integer giving) {
		this.giving = giving;
	}
	 
}
