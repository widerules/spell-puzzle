package com.james.fa.daos;

import java.util.List;

import com.james.fa.actions.conditions.RecordCondition;
import com.james.fa.po.Record;

public interface RecordDao {

	List<Record> findAll();

	String insert(Record record);

	String delete(String recordId);

	String update(Record record);

	Record findById(String recordId);

	List<Record> findByCondition(RecordCondition condition);

}
