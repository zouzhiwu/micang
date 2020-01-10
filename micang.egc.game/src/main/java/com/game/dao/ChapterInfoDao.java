package com.game.dao;

import java.util.List;

import com.game.entity.ChapterInfo;

public interface ChapterInfoDao {
	
	public void create(ChapterInfo chapterInfo);
	
	public void update(ChapterInfo chapterInfo);

	public List<ChapterInfo> findByChapterInfo(ChapterInfo chapterInfo);
	
	public void delById(Long id);

	public ChapterInfo findById(Long id);
}
