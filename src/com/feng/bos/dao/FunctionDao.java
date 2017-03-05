package com.feng.bos.dao;

import java.util.List;

import com.feng.bos.dao.base.BaseDao;
import com.feng.bos.domain.Function;

public interface FunctionDao extends BaseDao<Function>{

	public List<Function> findByUserId(String id);

	public List<Function> fingMenuByUserId(String id);

}
