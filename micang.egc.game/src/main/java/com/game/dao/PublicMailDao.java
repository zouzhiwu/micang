package com.game.dao;

import java.util.List;

import com.game.entity.PublicMail;

public interface PublicMailDao {
	
	public void create(PublicMail publicMail);
	
	public void update(PublicMail publicMail);

	public PublicMail findById(Long id);
	
	public List<PublicMail> findByAccountId(Long accountId);
	
	public void delById(Long id);

	public List<PublicMail> findByCreateTime(Integer time);
}
