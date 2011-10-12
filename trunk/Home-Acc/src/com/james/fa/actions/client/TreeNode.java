package com.james.fa.actions.client;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private boolean root;
	private boolean leaf = true;
	private boolean expanded;
	private boolean expandable = true;

	private String text;
	private String url;

	private List<TreeNode> children = new ArrayList<TreeNode>();

	public TreeNode() {
	}

	public TreeNode(String text) {
		this.text = text;
	}

	public TreeNode(String text, String url) {
		this.text = text;
		this.url = url;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isExpandable() {
		return expandable;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public TreeNode addChild(TreeNode child) {
		leaf = false;
		this.children.add(child);
		return this;
	}
}
