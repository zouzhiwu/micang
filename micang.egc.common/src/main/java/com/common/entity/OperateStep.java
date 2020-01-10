package com.common.entity;

import com.common.enumerate.OperateType;

public class OperateStep {
	private Long accountId;
	private OperateType operateType;
	private boolean isoperated;
	
	public OperateStep(Long accountId, OperateType operateType) {
		this.accountId = accountId;
		this.operateType = operateType;
		this.isoperated = false;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public OperateType getOperateType() {
		return operateType;
	}
	public void setOperateType(OperateType operateType) {
		this.operateType = operateType;
	}
	public boolean isOperated() {
		return isoperated;
	}
	public void setIsoperated(boolean isoperated) {
		this.isoperated = isoperated;
	}
}
