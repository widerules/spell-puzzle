package com.james.fa.vo;

public class ReportUnitVo {

	private String date;
	private int type;
	private String consumeTypeId;
	private String target;
	private int amount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
