package com.james.fa.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.james.fa.daos.TagDao;
import com.james.fa.po.Tag;
import com.james.skeleton.cache.Cache;
import com.james.skeleton.cache.CacheManager;
import com.james.skeleton.cache.CacheProvider;
import com.james.skeleton.cache.IntervalCacheProvider;

public class TagServiceImpl implements TagService, Cache {

	private TagDao tagDao;

	private CacheProvider<TagServiceImpl> cacheProvider = new IntervalCacheProvider<TagServiceImpl>(
			this, 3600);
	private List<Tag> tagList = new ArrayList<Tag>();

	public List<Tag> retrieveTags() {
		Cache c = CacheManager.getCache(this);
		if (c == null) {
			CacheManager.putCache(this, cacheProvider);
			c = CacheManager.getCache(this);
		}
		return new ArrayList<Tag>(((TagServiceImpl) c).tagList);
	}

	@Override
	public boolean contains(String name) {
		List<Tag> tagList = retrieveTags();
		Set<String> nameSet = new HashSet<String>();
		for (Tag tag : tagList) {
			nameSet.add(tag.getName());
		}
		return nameSet.contains(name);
	}

	@Override
	public String addTag(String tag) {
		String result = tagDao.insert(new Tag(tag));
		cacheProvider.setForceUpdate(true);
		return result;
	}

	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	public static final String CACHE_KEY = "CACHE:com.james.fa.po.Tag";

	@Override
	public void refresh() {
		tagList = tagDao.findAll();
	}

	@Override
	public String getKey() {
		return CACHE_KEY;
	}
}
