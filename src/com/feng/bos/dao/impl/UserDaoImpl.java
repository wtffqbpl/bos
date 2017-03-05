package com.feng.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.feng.bos.dao.UserDao;
import com.feng.bos.dao.base.impl.BaseDaoImpl;
import com.feng.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	/**
	 * 根据用户名和密码查询用户
	 */
	public User findByUsernameAndPassword(String username, String password) {
		String hql="from User u where u.username=? and u.password=?";
		List<User> list = this.getHibernateTemplate().find(hql,username,password);
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public User findUserByUsername(String username) {
		String hql="from User where username=?";
		// TODO Auto-generated method stub
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
