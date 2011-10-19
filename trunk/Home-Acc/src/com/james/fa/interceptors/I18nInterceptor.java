package com.james.fa.interceptors;

import java.util.Locale;
import java.util.Map;

import com.james.skeleton.util.Validators;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class I18nInterceptor implements Interceptor {

	private static final long serialVersionUID = -2264160199305076364L;

	public static final String I18N_STORAGE_KEY = "i18n_locale_key";
	public static final String I18N_PARAM_NAME = "request_locale";

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		Map<?, ?> params = invocation.getInvocationContext().getParameters();
		String requested_locale = (String) params.get(I18N_PARAM_NAME);
		if (!Validators.isEmpty(requested_locale)) {
			Locale locale = localeFromString(requested_locale,
					Locale.getDefault());
			invocation.getInvocationContext().setLocale(locale);
			ServletActionContext.getRequest().getSession()
					.setAttribute(I18N_STORAGE_KEY, locale);
		} else {
			Locale storedLocale = (Locale) ServletActionContext.getRequest()
					.getSession().getAttribute(I18N_STORAGE_KEY);

			if (storedLocale != null) {
				invocation.getInvocationContext().setLocale(storedLocale);
			}
		}

		return invocation.invoke();
	}

	public static Locale localeFromString(String localeStr, Locale defaultLocale) {
		if (localeStr == null || localeStr.trim().length() == 0
				|| localeStr.equals("_"))
			return defaultLocale;

		int index = localeStr.indexOf('_');
		if (index < 0)
			return new Locale(localeStr);
		String language = localeStr.substring(0, index);
		if (index == localeStr.length())
			return new Locale(language);
		localeStr = localeStr.substring(index + 1);
		index = localeStr.indexOf('_');
		if (index < 0)
			return new Locale(language, localeStr);
		String country = localeStr.substring(0, index);
		if (index == localeStr.length()) {
			return new Locale(language, country);
		} else {
			localeStr = localeStr.substring(index + 1);
			return new Locale(language, country, localeStr);
		}
	}

}
