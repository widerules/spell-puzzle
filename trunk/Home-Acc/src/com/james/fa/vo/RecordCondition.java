package com.james.fa.vo;

import java.util.Date;

public class RecordCondition {

	private int type;
	private Date startDate;
	private Date endDate;
	private int startAmount;
	private int endAmount;

	private String consumeTypeId;
	private String target;
	private String desc;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(int startAmount) {
		this.startAmount = startAmount;
	}

	public int getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(int endAmount) {
		this.endAmount = endAmount;
	}

	public String getConsumeTypeId() {
		return consumeTypeId;
	}

	public void setConsumeTypeId(String consumeTypeId) {
		this.consumeTypeId = consumeTypeId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
