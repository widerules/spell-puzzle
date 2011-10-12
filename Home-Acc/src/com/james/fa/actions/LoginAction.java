package com.james.fa.actions;

import com.james.fa.po.User;
import com.james.fa.services.UserService;
import com.opensymphony.webwork.ServletActionContext;

public class LoginAction extends BasicAction {

	private static final long serialVersionUID = 4990442376231528503L;

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String username;
	private String password;

	public String execute() {
		User user = userService.login(username, password);
		if (user != null) {
			ServletActionContext.getRequest().getSession()
					.setAttribute(User.STORAGE_KEY, user);
			setMsg("Login Success!!");
		} else {
			makeFailure();
			setMsg("Failed!!");
		}
		return ajaxReturn();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
