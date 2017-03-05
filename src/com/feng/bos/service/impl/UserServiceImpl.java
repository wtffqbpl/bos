package com.feng.bos.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.bos.dao.UserDao;
import com.feng.bos.domain.Role;
import com.feng.bos.domain.User;
import com.feng.bos.service.UserService;
import com.feng.bos.utils.MD5Utils;
import com.feng.bos.utils.PageBean;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public User login(User user) {

		String username=user.getUsername();
		String password=user.getPassword();
		password = MD5Utils.md5(password);
		
		return userDao.findByUsernameAndPassword(username,password);
	}

	public void editPassword(String password, String id) {
		userDao.executeUpdate("editPassword", password,id);
	}

	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}

	public void save(User model, String[] roleIds) {
		String password = model.getPassword();
		password=MD5Utils.md5(password);
		model.setPassword(password);
		userDao.save(model);
		Role role=new Role();
		Set<Role> roles = model.getRoles();
		for (String  roleId : roleIds) {
			role.setId(roleId);
			roles.add(role);
		}
	}

}
