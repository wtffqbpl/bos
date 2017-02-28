package com.feng.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.feng.bos.domain.Region;
import com.feng.bos.service.RegionService;
import com.feng.bos.utils.PageBean;
import com.feng.bos.utils.PinYin4jUtils;
import com.feng.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import sun.java2d.pipe.RegionSpanIterator;

/**
 * 区域管理
 * @author admin
 *
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	
	private File myFile;
	@Autowired
	private RegionService regionService; 
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	/**
	 * 批量导入
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws Exception{
		String flag="1";
		try {
			HSSFWorkbook workbook =new HSSFWorkbook(new FileInputStream(myFile));
			HSSFSheet sheet = workbook.getSheetAt(0);
			List<Region> list= new ArrayList<Region>();
			for (Row row : sheet) {
				int rowNum=row.getRowNum();
				if(rowNum==0){
					continue;
				}
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				
				Region region=new Region(id, province, city, district, postcode, null, null, null);
				
				city = city.substring(0,city.length()-1);
				String[] stringToPinyin = PinYin4jUtils.stringToPinyin(city);
				String citycode=StringUtils.join(stringToPinyin, "");
				
				//简码
				province = province.substring(0,province.length()-1);
				district = district.substring(0,district.length()-1);
				String info=province+city+district;
				String[] headByString = PinYin4jUtils.getHeadByString(info);
				String shortcode = StringUtils.join(headByString, "");
				region.setCitycode(citycode);
				region.setShortcode(shortcode);
				list.add(region);
			}
			regionService.saveBatch(list);
		} catch (Exception e) {
			flag="0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception 
	 */
	public String pageQuery() throws Exception{
		
		
		
		regionService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage","pageSize","detachedCriteria","subareas"});
		return NONE;
	}
	//------------------
	private String q;
	public void setQ(String q) {
		this.q = q;
	}
	
	/**
	 * 查询列表
	 * @throws Exception 
	 */
	public String listAjax() throws Exception{ 
		List<Region> list=regionService.findAll();
		if(StringUtils.isNotBlank(q)){
			list=regionService.findByQ(q);
		}
		this.writeListAjax2Json(list, new String[]{"province","city","district","postcode","shortcode","citycode","subareas"});
		return NONE;
	}
}
