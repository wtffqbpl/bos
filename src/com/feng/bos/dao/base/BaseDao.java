package com.feng.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.feng.bos.domain.Region;
import com.feng.bos.utils.PageBean;

/**
 * 抽取持久层通用方法
 * @author admin
 *
 * @param <T>
 */
public interface BaseDao<T> {
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public void saveOrUpdate(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	
	public void executeUpdate(String queryName,Object...objects);
	public void pageQuery(PageBean pageBean);
	public List<T> findByCriteria(DetachedCriteria criteria);
}
