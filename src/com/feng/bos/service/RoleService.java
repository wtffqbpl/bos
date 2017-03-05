package com.feng.bos.service;

import java.util.List;

import com.feng.bos.domain.Role;

public interface RoleService {

	public void save(Role model, String ids);

	public List<Role> findAll();

}
