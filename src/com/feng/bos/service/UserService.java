package com.feng.bos.service;

import com.feng.bos.domain.User;
import com.feng.bos.utils.PageBean;

public interface UserService {

	public User login(User model);

	public void editPassword(String password, String id);

	public void pageQuery(PageBean pageBean);

	public void save(User model, String[] roleIds);

}
