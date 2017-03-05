package com.feng.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.bos.dao.FunctionDao;
import com.feng.bos.domain.Function;
import com.feng.bos.domain.User;
import com.feng.bos.service.FunctionService;
import com.feng.bos.utils.BOSContext;
import com.feng.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService{
	@Resource
	private FunctionDao functionDao;

	public void pageQuery(PageBean pageBean) {

		this.functionDao.pageQuery(pageBean);
	}

	public List<Function> findAll() {
		return this.functionDao.findAll();
	}

	public void save(Function model) {

		Function function = model.getFunction();
		if(function!=null && function.equals("")){
			model.setFunction(null);
		}
		functionDao.save(model);
	}

	public List<Function> findMenu() {
		// TODO Auto-generated method stub
		User user = BOSContext.getLoginUser();
		
		return functionDao.fingMenuByUserId(user.getId());
	}
}
