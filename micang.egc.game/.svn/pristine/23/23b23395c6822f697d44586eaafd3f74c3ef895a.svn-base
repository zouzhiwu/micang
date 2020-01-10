package com.game.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.entity.Member;
import com.common.enumerate.WorkType;

public interface MemberDao {

	public void add(Member member);

	public Member getById(Long id);
	
	public Member getByName(String loginName);

	public void update(Member member);
	
	public List<Member> getByList(Long accountId);
	
	public Boolean isExistMember(@Param("accountId") Long accountId, @Param("memberTemplateId") Integer memberTemplateId);
	
	public List<Member> getWorkMemberList(Long accountId);
	
	public Member getByWork(@Param("accountId") Long accountId, @Param("work") WorkType work);
}