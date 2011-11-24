package com.james.fa.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.james.fa.actions.conditions.RecordCondition;
import com.james.fa.po.ConsumeType;
import com.james.fa.po.Record;
import com.james.fa.services.ConsumeTypeService;
import com.james.fa.services.RecordService;
import com.james.fa.vo.RecordVo;
import com.james.skeleton.util.Validators;

public class DetailsAction extends BasicAction {

	private static final long serialVersionUID = 8104630171217587718L;

	private String id;
	private String type;
	private String consumeDate;
	private String target;
	private float amount;
	private String consumeTypeId;
	private String desc;
	private String startAmount;
	private String endAmount;
	private String startDate;
	private String endDate;

	private RecordService recordService;
	private ConsumeTypeService consumeTypeService;

	public String execute() {
		Record record = new Record();
		record.setType(Integer.parseInt(type));
		record.setAmount((int) (amount * 100));
		record.setTarget(target);
		record.setConsumeTypeId(consumeTypeId);
		record.setConsumeDate(consumeDate);
		record.setDesc(desc);

		recordService.addRecord(record);

		return ajaxReturn();
	}

	public String queryRecord() {

		RecordCondition condition = new RecordCondition();
		condition.setType(Integer.parseInt(type));
		condition.setConsumeTypeId(consumeTypeId);
		condition.setTarget(target);
		condition.setDesc(desc);
		condition.setStartAmount(Validators.isEmpty(startAmount) ? -1
				: (int) (Float.parseFloat(startAmount) * 100));
		condition.setEndAmount(Validators.isEmpty(endAmount) ? -1
				: (int) (Float.parseFloat(endAmount) * 100));
		condition.setStartDate(startDate);
		condition.setEndDate(endDate);

		List<Record> searchRecords = recordService.searchRecord(condition);

		if (!searchRecords.isEmpty()) {
			List<RecordVo> resultList = new ArrayList<RecordVo>();

			List<ConsumeType> typeList = consumeTypeService.getAll();
			Map<String, String> typeMap = new HashMap<String, String>();
			for (ConsumeType type : typeList) {
				typeMap.put(type.getId(), type.getText());
			}

			for (Record record : searchRecords) {
				RecordVo vo = new RecordVo(record);
				vo.setAmount(vo.getAmount() / 100);
				vo.setConsumeTypeValue(typeMap.get(vo.getConsumeTypeId()));
				resultList.add(vo);
			}

			getReply().setValue(resultList);
		} else {
			getReply().setValue(searchRecords);
		}

		return ajaxReturn();
	}
	
	public String modifyRecord(){
		Record record = new Record();
		record.setId(id);
		record.setType(Integer.parseInt(type));
		record.setAmount((int) (amount * 100));
		record.setTarget(target);
		record.setConsumeTypeId(consumeTypeId);
		record.setConsumeDate(consumeDate.substring(0, 10));
		record.setDesc(desc);

		recordService.modifyRecord(record);
		return ajaxReturn();
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public void setConsumeTypeService(ConsumeTypeService consumeTypeService) {
		this.consumeTypeService = consumeTypeService;
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
		this.consumeDate = consumeDate.substring(0, 10);
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(String startAmount) {
		this.startAmount = startAmount;
	}

	public String getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(String endAmount) {
		this.endAmount = endAmount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
