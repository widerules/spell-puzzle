package com.james.fa.actions;

import java.util.List;

import com.james.fa.actions.client.TreeNode;
import com.james.fa.config.Config;
import com.james.fa.po.MenuItem;

public class NavigatorAction extends BasicAction {

	private static final long serialVersionUID = 3246278473499202599L;

	public String execute() {
		Config.TREE_STORE.setText(getText(Config.TREE_STORE.getText()));
		if (Config.TREE_STORE.hasChild()) {
			fixI18nText(Config.TREE_STORE.getChildren());
		}
		setJsonObj(Config.TREE_STORE);

		return jsonReturn();
	}

	private void fixI18nText(List<TreeNode> nodeList) {
		for (TreeNode n : nodeList) {
			if (n instanceof MenuItem) {
				((MenuItem) n).setText(getText(((MenuItem) n).getText()));
			}

			if (n.hasChild()) {
				fixI18nText(n.getChildren());
			}
		}
	}
}
