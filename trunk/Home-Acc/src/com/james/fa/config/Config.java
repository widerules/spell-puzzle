package com.james.fa.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.james.fa.actions.client.TreeNode;

public class Config {

	public static TreeNode TREE_STORE;
	public static JsonConfig JSON_CONFIG;

	static {
		TREE_STORE = retriveTreeStore();
		JSON_CONFIG = defaultJsonConfig();
	}

	private static JsonConfig defaultJsonConfig() {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new JsonValueProcessor() {
					private final String format = "MM/dd/yyyy";

					public Object processObjectValue(String key, Object value,
							JsonConfig arg2) {
						if (value == null)
							return "";
						if (value instanceof Date) {
							String str = new SimpleDateFormat(format)
									.format((Date) value);
							return str;
						}
						return value.toString();
					}

					public Object processArrayValue(Object value,
							JsonConfig arg1) {
						return null;
					}
				});
		return cfg;
	}

	private static TreeNode retriveTreeStore() {
		TreeNode root = new TreeNode();
		root.setRoot(true);

		TreeNode node1 = new TreeNode("Accounting", "/app/test.action");
		node1.addChild(new TreeNode("Input", "app/test.action"));
		node1.addChild(new TreeNode("Details", "app/test.action"));
		node1.addChild(new TreeNode("Report", "app/test.action"));
		
		root.addChild(node1);

		return root;
	}
}
