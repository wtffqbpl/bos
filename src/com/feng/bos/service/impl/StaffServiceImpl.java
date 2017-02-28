package com.feng.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.bos.dao.StaffDao;
import com.feng.bos.domain.Staff;
import com.feng.bos.service.StaffService;
import com.feng.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	
	
	@Autowired
	private StaffDao staffDao;

	public void save(Staff model) {
		staffDao.save(model);
	}

	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	/**
	 * 批量删除
	 */
	public void delete(String ids) {

		String[] staffIds=ids.split(",");
		for (String id : staffIds) {
			staffDao.executeUpdate("staff.delete", id);
		}
	}

	public Staff findById(String id) {
		return staffDao.findById(id);
	}

	public void update(Staff staff) {
		staffDao.update(staff);
	}

	public List<Staff> findByNoDelete() {
		DetachedCriteria criteria=DetachedCriteria.forClass(Staff.class);
		criteria.add(Restrictions.eq("deltag", "0"));
		
		return staffDao.findByCriteria(criteria);
	}

	
}
