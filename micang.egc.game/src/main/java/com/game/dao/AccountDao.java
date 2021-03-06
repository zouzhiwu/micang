package com.game.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.entity.Account;

public interface AccountDao {

	public void add(Account account);

	public Account getById(Long id);
	
	public Account getByName(String loginName);

	public void update(Account account);
	
	public List<Account> getByList(List<Long> accountIdList);
	
	public void updateJb(@Param("accountId")Long accountId, @Param("addjb")Long addjb);
	
	public void updateGb(@Param("accountId")Long accountId, @Param("addgb")Long addgb);
	
	public Account findTopOne();
	
	public Account findAfterOne();

}