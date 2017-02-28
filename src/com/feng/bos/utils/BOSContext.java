package com.feng.bos.utils;

import org.apache.struts2.ServletActionContext;

import com.feng.bos.domain.User;

public class BOSContext {

	public static User getLoginUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginuser");
	}
}
