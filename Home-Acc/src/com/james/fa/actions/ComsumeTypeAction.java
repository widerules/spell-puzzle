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

	private static String tab = "--";

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

		List<KeyValueJson> typeList = new ArrayList<KeyValueJson>();

		typeList.add(new KeyValueJson("", getText("accounting.common.all"), ""));
		if (t.hasChild()) {
			typeList = buildObj(t.getChildren(), "", 0);
		}

		setJsonObj(typeList);
		return jsonReturn();
	}

	private List<KeyValueJson> buildObj(List<TreeNode> typeList, String prefix,
			int repeat) {
		List<KeyValueJson> valueList = new ArrayList<KeyValueJson>();

		// String fullSep = "";
		// for (int i = 1; i <= repeat; i++) {
		// fullSep += sep;
		// }

		for (TreeNode type : typeList) {
			ConsumeType t = ((ConsumeType) type);
			KeyValueJson json = new KeyValueJson(type.getId(), prefix
					+ t.getText(), t.getText());
			valueList.add(json);
			if (type.hasChild()) {
				valueList.addAll(buildObj(type.getChildren(),
						prefix + t.getText() + tab, repeat + 1));
			}
		}

		return valueList;
	}

	public static class RootValue {
		private String name = "root";
		private Object value = null;

		public RootValue(Object value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}
	}

	public static class KeyValueJson {
		private String id;
		private String key;
		private String value;
		private String originValue;

		public KeyValueJson(String key, String value, String originValue) {
			this.id = key;
			this.key = key;
			this.value = value;
			this.originValue = originValue;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getOriginValue() {
			return originValue;
		}

		public void setOriginValue(String originValue) {
			this.originValue = originValue;
		}

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
