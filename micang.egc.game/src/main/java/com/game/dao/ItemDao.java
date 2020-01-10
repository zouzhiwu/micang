package com.game.dao;

import com.game.entity.Bag;

public interface ItemDao {

	public void create(Bag bag);
	
	public void update(Bag bag);

	public Bag findById(Long id);
	
	public void delById(Long id);
}