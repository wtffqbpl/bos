package com.feng.bos.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.bos.dao.DecidedzoneDao;
import com.feng.bos.dao.NoticebillDao;
import com.feng.bos.dao.WorkbillDao;
import com.feng.bos.domain.Decidedzone;
import com.feng.bos.domain.Noticebill;
import com.feng.bos.domain.Workbill;
import com.feng.bos.service.DecidedzoneService;
import com.feng.bos.service.NoticebillService;

import crm.CustomerService;

@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {
	@Resource
	private NoticebillDao noticebillDao;
	@Autowired
	private CustomerService customerService;
	@Resource
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private WorkbillDao workbillDao;

	public void save(Noticebill model) {
		
		noticebillDao.save(model);
		String pickaddress = model.getPickaddress();
		String did = customerService.findDecidedzoneIdByPickaddress(pickaddress);
		if(did!=null){
			//查询对应定区
			Decidedzone decidedzone = decidedzoneDao.findById(did);
			model.setStaff(decidedzone.getStaff());
			model.setOrdertype("自动");
			//生成工单
			Workbill workbill=new Workbill();
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workbill.setNoticebill(model);
			workbill.setPickstate("未取件");
			workbill.setRemark(model.getRemark());
			workbill.setStaff(model.getStaff());
			workbill.setType("新单");
			
			workbillDao.save(workbill);//保存工单
			//调用短信平台,给取派员发送短信
			
		}else{
			
			model.setOrdertype("人工");
		}
	}
}
