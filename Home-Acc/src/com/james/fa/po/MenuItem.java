package com.james.fa.po;

import com.james.fa.actions.client.TreeNode;

public class MenuItem extends TreeNode {

	private String url;
	private String text;

	public MenuItem() {
	}

	public MenuItem(String text, String url) {
		this.text = text;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
