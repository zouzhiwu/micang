package com.game.dao;

import org.apache.ibatis.annotations.Param;

import com.common.entity.Role;

public interface RoleDao {
	
	public void add(Role role);
	
	public void update(Role role);
	
	public Role getById(Long id);

	public void updateJb(@Param("accountId")long accountId, @Param("add")Long add);
	
	public void updateGb(@Param("accountId")long accountId, @Param("add")Long add);
}
