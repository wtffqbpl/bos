package com.feng.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.feng.bos.utils.PinYin4jUtils;

public class PinYin4jTest {

	@Test
	public void demo1(){
		String province="浙江省";
		String city="宁波市";
		String district="余姚市";
		
		//城市编码
		city = city.substring(0,city.length()-1);
		String[] stringToPinyin = PinYin4jUtils.stringToPinyin(city);
		String citycode=StringUtils.join(stringToPinyin, "");
		System.out.println(citycode);
		
		//简码
		province = province.substring(0,province.length()-1);
		district = district.substring(0,district.length()-1);
		String info=province+city+district;
		String[] headByString = PinYin4jUtils.getHeadByString(info);
		String shortcode = StringUtils.join(headByString, "");
		System.out.println(shortcode);
		

	}
}
