package com.feng.bos.web.action;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.domain.User;
import com.feng.bos.utils.MD5Utils;
import com.feng.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	//
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String login() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String key = (String) session.get("key");
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
			Subject subject = SecurityUtils.getSubject();
			String password = model.getPassword();
			password = MD5Utils.md5(password);
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), password);

			try {
				subject.login(token);
			} catch (UnknownAccountException e) {
				this.addActionError(this.getText("usernamenotfound"));
				return "login";
			} catch (Exception e) {
				this.addActionError(this.getText("loginError"));
				return "login";
			}
			User user = (User) subject.getPrincipal();
			session.put("loginuser", user);
			return "home";

		} else {
			// 验证码错误,返回
			this.addActionError(this.getText("validateCodeError"));
			return "login";

		}
	}

	public String login_bace() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String key = (String) session.get("key");
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
			User user = userService.login(model);
			if (user != null) {
				// 登录成功,把user放入session域中,跳转首页
				session.put("loginuser", user);

				return "home";
			} else {
				// 登录失败,返回
				this.addActionError(this.getText("loginError"));
				return "login";
			}
		} else {
			// 验证码错误,返回
			this.addActionError(this.getText("validateCodeError"));
			return "login";

		}
	}

	/**
	 * 用户推出
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}

	/**
	 * 用户修改密码
	 * 
	 * @throws IOException
	 * 
	 */
	public String editPassword() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("loginuser");
		String password = model.getPassword();
		password = MD5Utils.md5(password);
		String flag = "1";

		try {
			userService.editPassword(password, user.getId());
		} catch (Exception e) {

			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	public String pageQuery() throws IOException{
		this.userService.pageQuery(pageBean);
		String[] excludes=new String[]{"currentPage","pageSize","detachedCriteria","noticebills","roles"};
		this.writePageBean2Json(pageBean, excludes);
		return NONE;
	}
	
	private String[] roleIds;
	
	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String add(){
		this.userService.save(model,roleIds);
		return "list";
	}
}
