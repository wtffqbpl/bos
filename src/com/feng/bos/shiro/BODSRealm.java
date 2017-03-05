package com.feng.bos.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.feng.bos.dao.FunctionDao;
import com.feng.bos.dao.UserDao;
import com.feng.bos.dao.impl.UserDaoImpl;
import com.feng.bos.domain.Function;
import com.feng.bos.domain.User;

public class BODSRealm extends AuthorizingRealm{
	@Resource
	FunctionDao functionDao;
	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		
		User user = (User) principals.getPrimaryPrincipal();
		List<Function> list=null;
		if(user.getUsername().equals("admin")){
			list = functionDao.findAll();
			
			
		}else{
			list=functionDao.findByUserId(user.getId());
		}
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		
		return info;
	}
	@Resource
	UserDao userDao;
	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken passwordToken=(UsernamePasswordToken) token;
		String username = passwordToken.getUsername();
		
		User user=userDao.findUserByUsername(username);
		if(user==null){
			
			return null;
		}else{
			String password = user.getPassword();
			/**
			 * 参数1,签名,程序可以在任意位置获取当前的放入对象
			 * 参数2,从数据库查询出的密码
			 * 参数3,当前realm的名称
			 */
			SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user, password, this.getClass().getSimpleName());
			
			System.out.println(this.getClass().getSimpleName());
			return info;
		}
	}

}
