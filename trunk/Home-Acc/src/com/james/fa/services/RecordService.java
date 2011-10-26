package com.james.fa.services;

import java.util.List;

import com.james.fa.po.Record;
import com.james.fa.vo.RecordCondition;

public interface RecordService {

	String addRecord(Record record);

	List<Record> searchRecord(RecordCondition condition);

}
