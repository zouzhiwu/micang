package com.common.template;

public class UnionJobTemplate {

	private Integer id;
	private String name;
	private Byte jobType;
	private Byte sort;
	private Byte dismissUnionModify;
	private Byte unionNameModify;
	private Byte teammarkModify;
	private Byte modify2;
	private Byte modify3;
	private Byte applyModify;
	private Byte removeModify;

	public UnionJobTemplate(Integer id, String name, Byte jobType, Byte sort, Byte dismissUnionModify,
			Byte unionNameModify, Byte teammarkModify, Byte modify2, Byte modify3, Byte applyModify,
			Byte removeModify) {
		super();
		this.id = id;
		this.name = name;
		this.jobType = jobType;
		this.sort = sort;
		this.dismissUnionModify = dismissUnionModify;
		this.unionNameModify = unionNameModify;
		this.teammarkModify = teammarkModify;
		this.modify2 = modify2;
		this.modify3 = modify3;
		this.applyModify = applyModify;
		this.removeModify = removeModify;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Byte getJobType() {
		return jobType;
	}

	public Byte getSort() {
		return sort;
	}

	public Byte getDismissUnionModify() {
		return dismissUnionModify;
	}

	public Byte getUnionNameModify() {
		return unionNameModify;
	}

	public Byte getTeammarkModify() {
		return teammarkModify;
	}

	public Byte getModify2() {
		return modify2;
	}

	public Byte getModify3() {
		return modify3;
	}

	public Byte getApplyModify() {
		return applyModify;
	}

	public Byte getRemoveModify() {
		return removeModify;
	}

	

}
