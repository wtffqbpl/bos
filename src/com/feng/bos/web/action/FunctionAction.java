package com.feng.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.domain.Function;
import com.feng.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{
	public String pageQuery() throws IOException{
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		this.functionService.pageQuery(pageBean);
		String[] excludes=new String[]{"currentPage","pageSize","detachedCriteria","functions","function","roles"};
		this.writePageBean2Json(pageBean, excludes);
		return "none";
	}
	public String listajax() throws IOException{
		List<Function> list=functionService.findAll();
		String[] excludes=new String[]{"functions","function","roles"};
		this.writeListAjax2Json(list, excludes);
		return NONE;
	}
	public String add(){
		functionService.save(model);
		return "list";
	}

	public String findMenu() throws IOException{
		List<Function> list=this.functionService.findMenu();
		String[] excludes=new String[]{"functions","function","roles"};
		this.writeListAjax2Json(list, excludes);
		return NONE;
	}
}
