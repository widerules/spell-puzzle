package com.james.fa.daos;

import java.util.List;

import com.james.fa.po.Tag;

public interface TagDao {

	List<Tag> findAll();

	String insert(Tag tag);

	String delete(String name);

	Tag find(String name);

}
