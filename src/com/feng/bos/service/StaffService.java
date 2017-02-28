package com.feng.bos.service;

import java.util.List;

import com.feng.bos.domain.Staff;
import com.feng.bos.utils.PageBean;

public interface StaffService {

	public void save(Staff model);

	public void pageQuery(PageBean pageBean);

	public void delete(String ids);

	public Staff findById(String id);

	public void update(Staff staff);

	public List<Staff> findByNoDelete();


}
