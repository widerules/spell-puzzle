package com.james.fa.services;

import java.util.List;

import com.james.fa.po.Tag;

public interface TagService {

	List<Tag> retrieveTags();
	
	String addTag(String tag);

	boolean contains(String tag);
}
