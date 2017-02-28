package com.feng.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.feng.bos.domain.Region;
import com.feng.bos.service.DecidedzoneService;
import com.feng.bos.service.NoticebillService;
import com.feng.bos.service.UserService;
import com.feng.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.CustomerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	@Resource
	protected NoticebillService noticebillService;
	@Resource
	protected UserService userService;
	@Autowired
	protected DecidedzoneService decidedzoneService;
	@Autowired
	protected CustomerService customerService;
	protected PageBean pageBean=new PageBean();
	DetachedCriteria detachedCriteria =null;
	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}
	//模型对象
	protected T model;
	/**
	 * 在构造方法中动态获得实现类型,通过反射创建模型对象
	 */
	public BaseAction() {
		
		
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		//获得实体的类型
		 Class<T> entityClass=(Class<T>) actualTypeArguments[0];
		 detachedCriteria = DetachedCriteria.forClass(entityClass);
		 pageBean.setDetachedCriteria(detachedCriteria);
		try {
			//通过反射创建实例对象
			model= entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public T getModel() {
		
		return model;
	}
	public void writePageBean2Json(PageBean pageBean,String[] excludes) throws IOException{
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(excludes);
				
		String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
	
	public void writeListAjax2Json(List list,String[] excludes) throws IOException{
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(excludes);
				
		String json = JSONArray.fromObject(list,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
	
	public void writeObject2Json(Object object,String[] excludes) throws IOException{
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(excludes);
				
		String json = JSONObject.fromObject(object,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}
