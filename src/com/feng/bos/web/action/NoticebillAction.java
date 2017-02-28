package com.feng.bos.web.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.domain.Noticebill;
import com.feng.bos.domain.User;
import com.feng.bos.utils.BOSContext;
import com.feng.bos.web.action.base.BaseAction;

import cn.itcast.crm.domain.Customer;
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{
	
	/**
	 * 调用代理对象,根据手机号查客户信息
	 * @throws IOException 
	 */

	public String findCustomerByPhonenum() throws IOException{
		Customer customer = customerService.findCustomerByPhonenum(model.getTelephone());
		String[] excludes=new String[]{};
		this.writeObject2Json(customer, excludes);
		return NONE;
	}
	/**
	 * 添加业务通知单
	 */
	public String add(){
		User user=BOSContext.getLoginUser();
		model.setUser(user);
		noticebillService.save(model);
		
		
		return "addUI";
	}
}
