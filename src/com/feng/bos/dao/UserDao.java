package com.feng.bos.dao;

import com.feng.bos.dao.base.BaseDao;
import com.feng.bos.domain.User;

public interface UserDao extends BaseDao<User> {

	public User findByUsernameAndPassword(String username, String password);

}
