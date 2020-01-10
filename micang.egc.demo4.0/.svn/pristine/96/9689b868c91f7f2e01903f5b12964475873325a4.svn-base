package com.game.dao;

import org.apache.ibatis.annotations.Param;

import com.game.entity.PublicMailAccount;

public interface PublicMailAccountDao {
	/***添加邮件和玩家关系*/
	public void create(PublicMailAccount publicMailAccount);
	
	/**删除邮件和玩家关系**/
	public void delByMailId(@Param("mailId")Long id);
}
