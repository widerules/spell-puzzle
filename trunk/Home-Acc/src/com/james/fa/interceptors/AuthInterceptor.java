package com.james.fa.interceptors;
import com.james.fa.po.User;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;


public class AuthInterceptor implements Interceptor {
	
	private static final long serialVersionUID = -5622919597102245288L;
	
	public static final String LOGIN = "login";

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute(User.STORAGE_KEY);
		if (user == null){
			return LOGIN;
		}
		
		return invocation.invoke();
	}

}
