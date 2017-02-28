package com.feng.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.bos.dao.DecidedzoneDao;
import com.feng.bos.dao.SubareaDao;
import com.feng.bos.domain.Decidedzone;
import com.feng.bos.domain.Subarea;
import com.feng.bos.service.DecidedzoneService;
import com.feng.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService{
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private SubareaDao subareaDao;

	public void save(Decidedzone model,String[] subareaid) {
		decidedzoneDao.save(model);
//		for (String sid : subareaid) {
//			Subarea subarea = subareaDao.findById(sid);
//			
//			subarea.setDecidedzone(model);
//			
//		}
		for (String sid : subareaid) {
			
			subareaDao.executeUpdate("subarea.adddecidedzone", model,sid);
		}
		
	}

	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
		
	}

}
