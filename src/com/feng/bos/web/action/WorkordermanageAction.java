package com.feng.bos.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.domain.Workordermanage;
import com.feng.bos.web.action.base.BaseAction;

/**
 * 工作单管理
 * @author admin
 *
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage>{
	

	public String add(){
		workordermanageService.save(model);
		return NONE;
	}
}
