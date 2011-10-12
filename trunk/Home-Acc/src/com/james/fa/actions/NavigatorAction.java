package com.james.fa.actions;

import com.james.fa.config.Config;

public class NavigatorAction extends BasicAction {

	private static final long serialVersionUID = 3246278473499202599L;

	public String execute(){
		
		setJsonObj(Config.TREE_STORE);
		
		return jsonReturn();
	}
}
