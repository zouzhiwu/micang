package com.game.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.entity.Hero;

public interface HeroDao {

	public void add(Hero hero);

	public Hero getById(Long id);
	
	public List<Hero> getByList(Long accountId);
	
	public Boolean isExistHero(@Param("accountId") Long accountId, @Param("heroTemplateId") Integer heroTemplateId);
	
	public Hero getByTemplateId(@Param("accountId") Long accountId, @Param("heroTemplateId") Integer heroTemplateId);

}