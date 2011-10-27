package com.james.fa.services;

import java.util.List;

import com.james.fa.actions.conditions.RecordCondition;
import com.james.fa.po.Record;

public interface RecordService {

	String addRecord(Record record);

	List<Record> searchRecord(RecordCondition condition);

}
