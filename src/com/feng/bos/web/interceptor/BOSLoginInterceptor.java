package com.feng.bos.web.interceptor;

import com.feng.bos.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 自定义一个struts2的拦截器,实现用户未登陆,自动跳转到登录页面
 * 
 * @author admin
 *
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {

	// 拦截方法
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		User user = (User) ActionContext.getContext().getSession().get("loginuser");
		if(user ==null){
			return "login";
		}
		return invocation.invoke();
	}

}
