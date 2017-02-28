package com.feng.bos.service;

import java.util.List;

import com.feng.bos.domain.Region;
import com.feng.bos.utils.PageBean;

public interface RegionService {

	public void saveBatch(List<Region> list);

	public void pageQuery(PageBean pageBean);

	public List<Region> findAll();

	public List<Region> findByQ(String q);

}
