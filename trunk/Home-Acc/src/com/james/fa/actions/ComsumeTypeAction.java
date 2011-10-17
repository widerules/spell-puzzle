package com.james.fa.actions;

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

	public void setConsumeTypeService(ConsumeTypeService consumeTypeService) {
		this.consumeTypeService = consumeTypeService;
	}
}
