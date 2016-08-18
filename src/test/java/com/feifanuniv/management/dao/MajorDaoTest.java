package com.feifanuniv.management.dao;

import org.apache.ibatis.binding.MapperRegistry;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.feifanuniv.management.utils.PropertityUtils;

public class MajorDaoTest {

	private  ClassPathXmlApplicationContext applicationContext;

	@Before
	public  void setUp() throws Exception {
		PropertityUtils.setProSchoolUrl("dhlg");
		this.applicationContext=new ClassPathXmlApplicationContext("file:E:\\workspace-sts-3.7.1.RELEASE\\SchoolOnlinManagement\\src\\main\\resources\\spring\\application-config.xml");
	}

	@Test
	public void testSelectAllMajor() {
		MajorDao majorDao = (MajorDao) applicationContext.getBean("majorDao"); 
		MapperRegistry mapperRegistry = majorDao.getSqlSessionFactory_pro_school().getConfiguration().getMapperRegistry();
		System.out.println(mapperRegistry);
	}
	@Test
	public void testCreateAndInsertTempmajor() {
		MajorDao majorDao = (MajorDao) applicationContext.getBean("majorDao"); 
//		majorDao.createAndInsertTempmajor(majorDao.selectAllMajor());
	}

}
