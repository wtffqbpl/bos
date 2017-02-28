package com.feng.bos.service;

import com.feng.bos.domain.User;

public interface UserService {

	public User login(User model);

	public void editPassword(String password, String id);

}
