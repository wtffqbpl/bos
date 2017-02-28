package com.feng.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.feng.bos.dao.RegionDao;
import com.feng.bos.dao.base.impl.BaseDaoImpl;
import com.feng.bos.domain.Region;
import com.feng.bos.utils.PageBean;
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

	public List<Region> findByQ(String q) {
		String hql="from Region where province like ? or city like ? or district like ?";
		
		return this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%");
	}

	
}
