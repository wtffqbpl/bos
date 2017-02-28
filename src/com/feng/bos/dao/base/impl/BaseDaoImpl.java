package com.feng.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.feng.bos.dao.base.BaseDao;
import com.feng.bos.domain.User;
import com.feng.bos.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<T> entityClass;
	
	//使用注释的方法进行依赖注入
	@Resource
	public void mySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 在构造方法中获得操作的实体类型
	 */
	public BaseDaoImpl() {
		//或得父类(basedaoimpl<t>类型
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		entityClass= (Class<T>) actualTypeArguments[0];
	}
	
	public void save(T entity) {
		
		this.getHibernateTemplate().save(entity);
	}

	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
		
		
	}

	public void update(T entity) {
		
		this.getHibernateTemplate().update(entity);
	}

	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
		
	}

	public List<T> findAll() {
		String hql="FROM "+entityClass.getSimpleName();
		return this.getHibernateTemplate().find(hql);
	}
	public void executeUpdate(String queryName, Object... objects) {

		//从本地线程中获得session
		Session session = this.getSession();
		//使用命名查询语句获得一个查询对象
		 Query query = session.getNamedQuery(queryName);
		
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}
	/**
	 * 通用分页查询方法
	 */
	public void pageQuery(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		 int pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		//查询总数据量
		detachedCriteria.setProjection(Projections.rowCount());	//select count(*) from bc_staff
		List<Long> list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long total = list.get(0);
		pageBean.setTotal(total.intValue());

		detachedCriteria.setProjection(null);	//修改sql的形式为select * from 
		//重置表和类的映射关系
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		//当前页展示的数据集合
		int firstResult =(currentPage-1)*pageSize;
		int maxResults=pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
		
	}
	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(entity);
		
	}
	
	public List<T> findByCriteria(DetachedCriteria criteria){
		
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

}
