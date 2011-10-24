package com.james.fa.actions;

import java.util.ArrayList;
import java.util.List;

import com.james.fa.po.Tag;
import com.james.fa.services.TagService;

public class TagsAction extends BasicAction {

	private static final long serialVersionUID = 6144959325604031608L;

	private TagService tagService;

	public String execute() {
		List<Tag> retrieveTags = tagService.retrieveTags();
		List<String> tagList = new ArrayList<String>();
		
		for (Tag t : retrieveTags) {
			tagList.add(t.getName());
		}
		setJsonObj(tagList);
		return jsonReturn();
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
}
