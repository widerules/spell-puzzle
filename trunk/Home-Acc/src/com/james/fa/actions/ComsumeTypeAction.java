package com.james.fa.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.james.fa.actions.client.TreeNode;
import com.james.fa.po.ConsumeType;
import com.james.fa.services.ConsumeTypeService;

public class ComsumeTypeAction extends BasicAction {

	private static final long serialVersionUID = 4026537249101395631L;

	private ConsumeTypeService consumeTypeService;

	public String execute() {
		List<ConsumeType> types = consumeTypeService.getAll();

		ConsumeType t = new ConsumeType();
		t.setId(TreeNode.ROOT_ID);
		t.setText("ROOT");
		t.setRoot(true);
		types.add(t);

		Map<String, ConsumeType> map = new HashMap<String, ConsumeType>();
		for (ConsumeType type : types) {
			map.put(type.getId(), type);
		}

		for (Entry<String, ConsumeType> entry : map.entrySet()) {
			if (entry.getKey().equals(TreeNode.ROOT_ID)) {
				continue;
			}

			if (map.containsKey(entry.getValue().getParentId())) {
				map.get(entry.getValue().getParentId()).addChild(
						entry.getValue());
			}
		}

		setJsonObj(t);
		return jsonReturn();
	}

	public String buildCascadeList() {
		List<ConsumeType> types = consumeTypeService.getAll();

		ConsumeType t = new ConsumeType();
		t.setId(TreeNode.ROOT_ID);
		t.setText("ROOT");
		t.setRoot(true);
		types.add(t);

		Map<String, ConsumeType> map = new HashMap<String, ConsumeType>();
		for (ConsumeType type : types) {
			map.put(type.getId(), type);
		}

		for (Entry<String, ConsumeType> entry : map.entrySet()) {
			if (entry.getKey().equals(TreeNode.ROOT_ID)) {
				continue;
			}

			if (map.containsKey(entry.getValue().getParentId())) {
				map.get(entry.getValue().getParentId()).addChild(
						entry.getValue());
			}
		}

		String tab = "--";

		List<IdTextJson> typeList = new ArrayList<IdTextJson>();

		typeList.add(new IdTextJson("", getText("accounting.common.all"), ""));
		if (t.hasChild()) {
			typeList = buildObj(t.getChildren(), tab, 0);
		}

		setJsonObj(typeList);
		return jsonReturn();
	}

	private List<IdTextJson> buildObj(List<TreeNode> typeList, String sep,
			int repeat) {
		List<IdTextJson> valueList = new ArrayList<IdTextJson>();

		String fullSep = "";
		for (int i = 1; i <= repeat; i++) {
			fullSep += sep;
		}

		for (TreeNode type : typeList) {
			IdTextJson json = new IdTextJson(type.getId(), fullSep
					+ ((ConsumeType) type).getText(), ((ConsumeType) type).getText());
			valueList.add(json);
			if (type.hasChild()) {
				valueList.addAll(buildObj(type.getChildren(), sep, repeat + 1));
			}
		}

		return valueList;
	}

	public static class IdTextJson {

		private String id;
		private String text;
		private String originValue;

		public IdTextJson(String id, String text, String ori) {
			this.id = id;
			this.text = text;
			this.originValue = ori;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getOriginValue() {
			return originValue;
		}

		public void setOriginValue(String originValue) {
			this.originValue = originValue;
		}
	}

	public void setConsumeTypeService(ConsumeTypeService consumeTypeService) {
		this.consumeTypeService = consumeTypeService;
	}
}
