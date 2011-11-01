package com.james.fa.vo;

import java.util.Date;

import com.james.fa.po.Record;
import com.james.skeleton.util.DateUtils;

public class RecordVo {
	// 'id', 'type', 'consumeTypeId', 'consumeDate', 'target', {name: 'amount',
	// type: 'int'}, 'consumeTypeValue', 'desc'
	private String id;
	private int type;
	private String consumeTypeId;
	private String target;
	private String consumeDate;
	private float amount;
	private String consumeTypeValue;
	private String desc;

	public RecordVo(Record po) {
		this.id = po.getId();
		this.type = po.getType();
		this.consumeTypeId = po.getConsumeTypeId();
		this.target = po.getTarget();
		this.consumeDate = po.getConsumeDate();
		this.amount = po.getAmount();
		this.desc = po.getDesc();
	}

	// private int
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getConsumeTypeValue() {
		return consumeTypeValue;
	}

	public void setConsumeTypeValue(String consumeTypeValue) {
		this.consumeTypeValue = consumeTypeValue;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
