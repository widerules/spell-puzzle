package com.james.fa.services;

import com.james.fa.daos.UserDao;
import com.james.fa.po.User;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Override
	public User login(String username, String password) {
		return userDao.findByUserNamePassword(username, password);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
