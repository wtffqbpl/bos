package com.feng.bos.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.bos.dao.WorkordermanageDao;
import com.feng.bos.domain.Workordermanage;
import com.feng.bos.service.WorkordermanageService;
@Service
@Transactional
public class WorkordermanageServiceImpl implements WorkordermanageService{
	@Autowired
	private WorkordermanageDao workordermanageDao;

	public void save(Workordermanage model) {
		model.setUpdatetime(new Date());
		workordermanageDao.save(model);
	}

	
}
