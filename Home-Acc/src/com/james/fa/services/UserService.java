package com.james.fa.services;

import com.james.fa.po.User;

public interface UserService {

	User login(String username, String password);

}
