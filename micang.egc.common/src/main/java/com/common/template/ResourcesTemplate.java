package com.common.template;

public class ResourcesTemplate {

	private Integer id;

	private Byte type;

	private Integer itemId;

	private String name;

	private Integer number;

	public ResourcesTemplate(Integer id, Byte type, Integer itemId, String name, Integer number) {
		super();
		this.id = id;
		this.type = type;
		this.itemId = itemId;
		this.name = name;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public Byte getType() {
		return type;
	}



	public Integer getItemId() {
		return itemId;
	}

	public String getName() {
		return name;
	}

	public Integer getNumber() {
		return number;
	}

}
