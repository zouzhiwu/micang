package com.common.template;

public class ItemTemplate extends TemplateBase {

	private Byte type;
	private Byte sorting;
	private Integer num;
	private Integer associated;
	private String icon;
	private Byte paging;
	private Byte quality;
	private Integer sell;
	private int sellType;
	private Byte way1;
	private Byte way2;
	private Byte way3;
	private String effect;
	private Byte useLevel;
	private Byte buyLevel;
	private Byte vipLevel;
	private String content;

	public ItemTemplate(Integer id, String name, Byte type, Byte sorting, Integer num, Integer associated,
			 String icon, Byte paging, Byte quality, Integer sell, int sellType, Byte way1, Byte way2,
			Byte way3, String effect, Byte useLevel, Byte buyLevel, Byte vipLevel, String content) {
		super.setId(id);
		super.setName(name);

		this.type = type;
		this.sorting = sorting;
		this.num = num;
		this.associated = associated;
		this.icon = icon;
		this.paging = paging;
		this.quality = quality;
		this.sell = sell;
		this.sellType = sellType;
		this.way1 = way1;
		this.way2 = way2;
		this.way3 = way3;
		this.effect = effect;
		this.useLevel = useLevel;
		this.buyLevel = buyLevel;
		this.vipLevel = vipLevel;
		this.content = content;
	}

	public Byte getType() {
		return type;
	}

	public Byte getSorting() {
		return sorting;
	}

	public Integer getNum() {
		return num;
	}

	public Integer getAssociated() {
		return associated;
	}


	public String getIcon() {
		return icon;
	}

	public Byte getPaging() {
		return paging;
	}

	public Byte getQuality() {
		return quality;
	}

	public Integer getSell() {
		return sell;
	}

	public int getSellType() {
		return sellType;
	}

	public Byte getWay1() {
		return way1;
	}

	public Byte getWay2() {
		return way2;
	}

	public Byte getWay3() {
		return way3;
	}

	public String getEffect() {
		return effect;
	}

	public Byte getUseLevel() {
		return useLevel;
	}

	public Byte getBuyLevel() {
		return buyLevel;
	}

	public Byte getVipLevel() {
		return vipLevel;
	}

	public String getContent() {
		return content;
	}

}
