package com.feng.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.domain.Staff;
import com.feng.bos.service.StaffService;
import com.feng.bos.utils.PageBean;
import com.feng.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 取派员管理
 * @author admin
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Resource
	private StaffService staffService;
	
	/**
	 * 添加取派员
	 */
	public String add(){
		staffService.save(model);
		return "list";
	}
	
	/**
	 * 分页查询
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		
		staffService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"decidedzones","currentPage","pageSize","detachedCriteria"});
		return NONE;
	}
	//---------------------
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	/**
	 * 员工删除(逻辑删除)
	 */
	@RequiresPermissions(value="staff")
	public String delete(){
		staffService.delete(ids);
		return "list";
	}
	/**
	 * 修改取派员信息
	 */
	public String edit(){
		//不能直接通过model更改,因为穿过来的数据不全,直接更改会丢失数据
		//查询数据库中的原始数据
		Staff staff = staffService.findById(model.getId());
		//在按照页面提交的参数进行覆盖
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staff.setHaspda(model.getHaspda());
		
		staffService.update(staff);
		return "list";
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public String listAjax() throws IOException{
		
		List<Staff> list=staffService.findByNoDelete();
		this.writeListAjax2Json(list, new String[]{"telephone","haspda","deltag","station","standard","decidedzones"});
		return NONE;
	}
}
