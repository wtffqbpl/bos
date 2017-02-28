package com.feng.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.domain.Decidedzone;
import com.feng.bos.service.DecidedzoneService;
import com.feng.bos.web.action.base.BaseAction;

import cn.itcast.crm.domain.Customer;
/**
 * 定区管理
 * @author admin
 *
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{
	
	
	private  Integer[] customerIds;
	public void setCustomerIds(Integer[] customerIds) {
		this.customerIds = customerIds;
	}
	private String[] subareaid;
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	/**
	 * 添加定区
	 */
	public String add(){
		decidedzoneService.save(model,subareaid);
		return "list";
	}
	/**
	 * 分页查询
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		decidedzoneService.pageQuery(pageBean);
		String[] excludes=new String[]{"currentPage","pageSize","detachedCriteria","subareas","decidedzones"};
		this.writePageBean2Json(pageBean, excludes);
		return NONE;
	}
	/**
	 * 查询未绑定定区客户
	 * @throws IOException 
	 */
	public String findnoassociationCustomers() throws IOException{
		List<Customer> list = customerService.findnoassociationCustomers();
		String[] excludes=new String[]{"station","address"};
		this.writeListAjax2Json(list, excludes);
		return NONE;
	}
	/**
	 * 查询已绑定定区的客户
	 * @throws IOException 
	 */
	public String findhasassociationCustomers() throws IOException{
		List<Customer> list = customerService.findhasassociationCustomers(model.getId());
		String[] excludes=new String[]{"station","address"};
		this.writeListAjax2Json(list, excludes);
		return NONE;
	}
	/**
	 * 绑定定区客户
	 */
	public String assigncustomerstodecidedzone(){
		customerService.assignCustomersToDecidedZone(customerIds, model.getId());
		return "list";
	}
}
