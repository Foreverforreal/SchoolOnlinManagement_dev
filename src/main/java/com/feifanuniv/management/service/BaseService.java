package com.feifanuniv.management.service;

import com.feifanuniv.management.dao.SchoolDao;
import com.feifanuniv.management.utils.PropertityUtils;

public class BaseService {
	private SchoolDao schoolDao;
	public void switchDb(String schoolSymbol,SchoolDao schoolDao){
		this.schoolDao=schoolDao;
		PropertityUtils.setProSchoolUrl(getSchoolConnectUrl(schoolSymbol));
	}
	
	public String getSchoolConnectUrl(String schoolSymbol){
		String schoolId=schoolDao.selectSchoolId(schoolSymbol);
		if(schoolId==null){
			return schoolSymbol;
		}
		return schoolDao.selectSchoolUrl(schoolId);
	}

}
