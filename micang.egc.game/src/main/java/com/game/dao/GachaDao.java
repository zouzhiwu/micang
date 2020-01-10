package com.game.dao;

import com.game.entity.Gacha;

public interface GachaDao {
	public void create(Gacha gacha);
	
	public void update(Gacha gacha);

	public Gacha findById(Long id);
}
