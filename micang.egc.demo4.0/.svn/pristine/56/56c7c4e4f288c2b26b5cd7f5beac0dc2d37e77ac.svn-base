package com.game.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.entity.FriendRelation;

public interface FriendRelationDao {

	public void create(FriendRelation friend);
	
	public void update(FriendRelation friend);

	public FriendRelation findById(@Param("accountId1")Long accountId1, @Param("accountId2")Long accountId2);
	
	public void delById(@Param("accountId1")Long accountId1, @Param("accountId2")Long accountId2);

	public List<FriendRelation> findFriendByRelation(@Param("accountId")Long acountId,@Param("relation") Integer relation);
	
	public Integer countFriend(@Param("accountId")Long acountId);
	
	public Integer countApply(@Param("accountId")Long acountId);

	public int coountBlackList(Long accountId);
}