package com.feng.bos.service;

import java.util.List;

import com.feng.bos.domain.Subarea;
import com.feng.bos.utils.PageBean;

public interface SubareaService {

	public void save(Subarea model);

	public void pageQuery(PageBean pageBean);

	public List<Subarea> findAll();

	public List<Subarea> findListNotAssociation();

}
