package com.feng.bos.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.dao.UserDao;
import com.feng.bos.domain.User;
import com.feng.bos.service.UserService;
import com.feng.bos.utils.MD5Utils;
import com.feng.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

import cn.itcast.crm.domain.Customer;
import crm.CustomerService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	//
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String login(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String key = (String) session.get("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)){
			User user=userService.login(model);
			if(user !=null){
				// 登录成功,把user放入session域中,跳转首页
				session.put("loginuser", user);
				
				return "home";
			}else{
				//登录失败,返回
				this.addActionError(this.getText("loginError"));
				return "login";
			}
		}else{
			//验证码错误,返回
			this.addActionError(this.getText("validateCodeError"));
			return "login";
			
		}
	}
	
	/**
	 * 用户推出
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	/**
	 *用户修改密码
	 * @throws IOException 
	 * 
	 */
	public String editPassword() throws IOException{
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("loginuser");
		String password = model.getPassword();
		 password = MD5Utils.md5(password);
		 String flag="1";
		 
		try {
			userService.editPassword(password,user.getId());
		} catch (Exception e) {
		
			flag="0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
}
