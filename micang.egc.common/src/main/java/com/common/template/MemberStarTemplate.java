package com.common.template;

public class MemberStarTemplate extends TemplateBase {
	
	
	private int memberId;
	
	private int consume;
	
	private short y1;
	
	private short v1;
	
	private short y2;
	
	private short v2;
	
	private short y3;
	
	private short v3;
	
	private short y4;
	
	private short v4;
	
	private short y5;
	
	private short v5;
	
	private short y6;
	
	private short v6;
	
	private short y7;
	
	private short v7;
	
	private short y8;
	
	private short v8;
	
	private short y9;
	
	private short v9;
	
	private short y10;
	
	private short v10;
	


	private int relation;


	public MemberStarTemplate(int id, int memberId, int consume, short y1, short v1, short y2, short v2, short y3, short v3,
			short y4, short v4, short y5, short v5, short y6, short v6, short y7, short v7, short y8, short v8,
			short y9, short v9, short y10, short v10, int relation) {

		super.setId(id);
		super.setName("MemberStar" + id);
		
		this.memberId = memberId;
		this.consume = consume;
		this.y1 = y1;
		this.v1 = v1;
		this.y2 = y2;
		this.v2 = v2;
		this.y3 = y3;
		this.v3 = v3;
		this.y4 = y4;
		this.v4 = v4;
		this.y5 = y5;
		this.v5 = v5;
		this.y6 = y6;
		this.v6 = v6;
		this.y7 = y7;
		this.v7 = v7;
		this.y8 = y8;
		this.v8 = v8;
		this.y9 = y9;
		this.v9 = v9;
		this.y10 = y10;
		this.v10 = v10;
		this.relation = relation;
	}


	public int getMemberId() {
		return memberId;
	}


	public int getConsume() {
		return consume;
	}


	public short getY1() {
		return y1;
	}


	public short getV1() {
		return v1;
	}


	public short getY2() {
		return y2;
	}


	public short getV2() {
		return v2;
	}


	public short getY3() {
		return y3;
	}


	public short getV3() {
		return v3;
	}


	public short getY4() {
		return y4;
	}


	public short getV4() {
		return v4;
	}


	public short getY5() {
		return y5;
	}


	public short getV5() {
		return v5;
	}


	public short getY6() {
		return y6;
	}


	public short getV6() {
		return v6;
	}


	public short getY7() {
		return y7;
	}


	public short getV7() {
		return v7;
	}


	public short getY8() {
		return y8;
	}


	public short getV8() {
		return v8;
	}


	public short getY9() {
		return y9;
	}


	public short getV9() {
		return v9;
	}


	public short getY10() {
		return y10;
	}


	public short getV10() {
		return v10;
	}


	public int getRelation() {
		return relation;
	}


}
