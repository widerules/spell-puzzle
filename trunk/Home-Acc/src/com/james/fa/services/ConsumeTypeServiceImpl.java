package com.james.fa.services;

import java.util.List;

import com.james.fa.daos.ConsumeTypeDao;
import com.james.fa.po.ConsumeType;

public class ConsumeTypeServiceImpl implements ConsumeTypeService {
	private ConsumeTypeDao consumeTypeDao;

	public List<ConsumeType> getAll() {
		return consumeTypeDao.findAll();
	}

	public void setConsumeTypeDao(ConsumeTypeDao consumeTypeDao) {
		this.consumeTypeDao = consumeTypeDao;
	}
}
