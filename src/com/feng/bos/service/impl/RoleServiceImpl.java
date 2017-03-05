package com.feng.bos.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.bos.dao.FunctionDao;
import com.feng.bos.dao.RoleDao;
import com.feng.bos.domain.Function;
import com.feng.bos.domain.Role;
import com.feng.bos.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;
	@Resource
	private FunctionDao functionDao;
	public void save(Role model, String ids) {
		
		String[] fids = ids.split(",");
		roleDao.save(model);
		for (String fid : fids) {
			Function function=new Function(fid);
			model.getFunctions().add(function);
		}
		
	}
	public List<Role> findAll() {
		
		return this.roleDao.findAll();
	}

}
