package com.feng.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.bos.dao.RegionDao;
import com.feng.bos.domain.Region;
import com.feng.bos.service.RegionService;
import com.feng.bos.utils.PageBean;
@Service
@Transactional
public class RegionServiceImpl implements RegionService{

	@Resource
	private RegionDao regionDao;

	public void saveBatch(List<Region> list) {
		for (Region region : list) {
			
			regionDao.saveOrUpdate(region);
		}
		
		
	}

	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}

	public List<Region> findAll() {
		return regionDao.findAll();
	}

	public List<Region> findByQ(String q) {
		return regionDao.findByQ(q);
	}
}
