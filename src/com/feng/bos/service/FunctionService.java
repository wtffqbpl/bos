package com.feng.bos.service;

import java.util.List;

import com.feng.bos.domain.Function;
import com.feng.bos.utils.PageBean;

public interface FunctionService {

	public void pageQuery(PageBean pageBean);

	public List<Function> findAll();

	public void save(Function model);

	public List<Function> findMenu();

}
