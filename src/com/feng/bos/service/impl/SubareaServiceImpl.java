package com.feng.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.bos.dao.SubareaDao;
import com.feng.bos.domain.Subarea;
import com.feng.bos.service.SubareaService;
import com.feng.bos.utils.PageBean;
@Service
@Transactional
public class SubareaServiceImpl implements SubareaService{

	@Autowired
	public SubareaDao subareaDao;

	public void save(Subarea model) {
		subareaDao.save(model);
		
	}

	public void pageQuery(PageBean pageBean) {

		subareaDao.pageQuery(pageBean);
	}

	public List<Subarea> findAll() {

		
		return subareaDao.findAll();
	}
	/**
	 * 查询没有关联到定区的分区
	 */
	public List<Subarea> findListNotAssociation() {

		DetachedCriteria criteria=DetachedCriteria.forClass(Subarea.class);
		criteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(criteria);
	}
}
