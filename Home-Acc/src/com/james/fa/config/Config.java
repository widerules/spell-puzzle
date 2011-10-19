package com.james.fa.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.james.fa.po.MenuItem;

public class Config {

	public static MenuItem TREE_STORE;
	public static JsonConfig JSON_CONFIG;

	static {
		TREE_STORE = retriveMenuItemStore();
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

	private static MenuItem retriveMenuItemStore() {
		MenuItem root = new MenuItem();
		root.setRoot(true);

		MenuItem node1 = new MenuItem("menu.accounting", "accounting", "/app/test.action");
		node1.setExpanded(true);
		node1.addChild(new MenuItem("menu.accounting.input", "accounting/input", "app/test.action"));
		node1.addChild(new MenuItem("menu.accounting.details", "accounting/details", "app/test.action"));
		node1.addChild(new MenuItem("menu.accounting.report", "accounting/report", "app/test.action"));

		root.addChild(node1);

		return root;
	}
}
