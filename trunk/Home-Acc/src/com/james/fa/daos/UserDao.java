package com.james.fa.daos;

import com.james.fa.po.User;

public interface UserDao {

	User findByUserNamePassword(String username, String password);

}
