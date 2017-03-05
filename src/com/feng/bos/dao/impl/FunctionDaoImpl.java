package com.feng.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.feng.bos.dao.FunctionDao;
import com.feng.bos.dao.base.impl.BaseDaoImpl;
import com.feng.bos.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao{

	public List<Function> findByUserId(String id) {
		String hql="select distinct f from Function f left outer join f.roles r "
				+ "left outer join r.users u where u.id=?";
		return this.getHibernateTemplate().find(hql,id);
	}

	public List<Function> fingMenuByUserId(String id) {
		String hql="select distinct f from Function f left outer join f.roles r "
				+ "left outer join r.users u where f.generatemenu='1' and u.id=? order by f.zindex desc";
		
		return this.getHibernateTemplate().find(hql,id);
	}
	

}
