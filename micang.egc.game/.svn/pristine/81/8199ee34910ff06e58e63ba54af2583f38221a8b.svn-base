package com.game.entity;

import java.io.Serializable;
/**
 *	道具对象实体
 *	创建道具时，如果道具是可强化道具，生成唯一id存入boxId属性，否则boxId和templateId相同
 * @author gf
 *
 */
public class Item implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	public Item(Integer templateId, Integer count) {
		super();
		this.templateId = templateId;
		this.count = count;
	}
	
	public Item() {
		super();
	}

	/** 道具模板id**/
	private Integer templateId;
	
	/**道具数量**/
	private Integer count;
	
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
