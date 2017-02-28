package com.feng.bos.dao;

import java.util.List;

import com.feng.bos.dao.base.BaseDao;
import com.feng.bos.domain.Region;

public interface RegionDao extends BaseDao<Region> {

	public List<Region> findByQ(String q);

	

}
