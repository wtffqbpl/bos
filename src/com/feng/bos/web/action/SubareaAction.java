package com.feng.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.domain.Region;
import com.feng.bos.domain.Subarea;
import com.feng.bos.service.SubareaService;
import com.feng.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	@Resource
	private SubareaService subareaService;

	/**
	 * 添加分区
	 * 
	 * @return
	 */
	public String add() {
		subareaService.save(model);
		return "list";
	}

	/**
	 * 分页查询
	 * 
	 * @throws IOException
	 */
	public String pageQuery() throws IOException {
		DetachedCriteria detachedCriteria2 = pageBean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		if (StringUtils.isNotBlank(addresskey)) {

			detachedCriteria2.add(Restrictions.like("addresskey", addresskey));
		}
		Region region = model.getRegion();

		if (region != null) {
			detachedCriteria2.createAlias("region", "r");
			String city = region.getCity();
			String province = region.getProvince();
			String district = region.getDistrict();
			if (StringUtils.isNotBlank(province)) {
				detachedCriteria2.add(Restrictions.like("r.province", "%" + province + "%"));
			}
			if (StringUtils.isNotBlank(city)) {
				detachedCriteria2.add(Restrictions.like("r.city", "%" + city + "%"));
			}
			if (StringUtils.isNotBlank(district)) {
				detachedCriteria2.add(Restrictions.like("r.district", "%" + district + "%"));
			}

		}
		subareaService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean,
				new String[] { "currentPage", "pageSize", "detachedCriteria", "subareas", "decidedzone" });
		return NONE;
	}

	/**
	 * 导出文件
	 * @throws IOException 
	 * 
	 */
	public String exportXls() throws IOException {
		List<Subarea> list = subareaService.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("分区数据");
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("区域编号");
		headRow.createCell(2).setCellValue("地址关键字");
		headRow.createCell(3).setCellValue("省市区");
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
			dataRow.createCell(2).setCellValue(subarea.getAddresskey());
			Region region = subarea.getRegion();
			
			dataRow.createCell(3).setCellValue(region.getProvince()+region.getCity()+region.getDistrict());
		}
		String filename="acx.xls";
		String type = ServletActionContext.getServletContext().getMimeType(filename);
		
		ServletActionContext.getResponse().setContentType(type);
		ServletActionContext.getResponse().setHeader("content-disposition", "attchment;filename="+filename);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		workbook.write(out);
		return NONE;
	}
	
	/**
	 * 查询未被关联到定区的数据
	 * @throws IOException 
	 */

	public String listAjax() throws IOException{
		List<Subarea> list=subareaService.findListNotAssociation();
		this.writeListAjax2Json(list, new String[]{"decidedzone","region"});
		return NONE;
	}
}
