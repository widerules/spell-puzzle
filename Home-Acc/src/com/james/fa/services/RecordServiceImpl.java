package com.james.fa.services;

import java.util.List;

import com.james.fa.actions.conditions.RecordCondition;
import com.james.fa.daos.RecordDao;
import com.james.fa.po.Record;

public class RecordServiceImpl implements RecordService {

	private RecordDao recordDao;
	private TagService tagService;

	@Override
	public String addRecord(Record record) {

		if (!tagService.contains(record.getTarget())) {
			tagService.addTag(record.getTarget());
		}

		recordDao.insert(record);
		return record.getId();
	}

	@Override
	public List<Record> searchRecord(RecordCondition condition) {
		return recordDao.findByCondition(condition);
	}

	@Override
	public String modifyRecord(Record record) {
		if (!tagService.contains(record.getTarget())) {
			tagService.addTag(record.getTarget());
		}
		return recordDao.update(record);
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
}
