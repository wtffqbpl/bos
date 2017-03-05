package com.feng.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.domain.Role;
import com.feng.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	private String ids;
	public String getIds() {
		return ids;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String add(){
		roleService.save(model,ids);
		return "list";
	}

	public String pageQuery() throws IOException{
		this.functionService.pageQuery(pageBean);
		String[] excludes=new String[]{"currentPage","pageSize","detachedCriteria","functions","users"};
		this.writePageBean2Json(pageBean, excludes);
		return "none";
	}
	
	public String listajax() throws IOException{
		List<Role> list=this.roleService.findAll();
		String[] excludes=new String[]{"functions","users"};
		this.writeListAjax2Json(list, excludes);
		return NONE;
	}
	
	
}
