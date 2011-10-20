package com.james.fa.actions;

import com.james.fa.po.Record;
import com.james.fa.services.RecordService;

public class DetailsAction extends BasicAction {

	private static final long serialVersionUID = 8104630171217587718L;

	private String type;
	private String consumeDate;
	private String target;
	private float amount;
	private String consumeTypeId;

	private RecordService recordService;

	public String execute() {
		Record record = new Record();
		record.setType(Integer.parseInt(type));
		record.setAmount((int) (amount * 100));
		record.setTarget(target);
		record.setConsumeTypeId(consumeDate);
		recordService.addRecord(record);
		return ajaxReturn();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getConsumeTypeId() {
		return consumeTypeId;
	}

	public void setConsumeTypeId(String consumeTypeId) {
		this.consumeTypeId = consumeTypeId;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}
}
