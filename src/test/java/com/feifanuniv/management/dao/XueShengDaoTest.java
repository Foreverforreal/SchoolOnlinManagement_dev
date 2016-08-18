package com.feifanuniv.management.dao;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.feifanuniv.management.dao.XueShengDao;
import com.feifanuniv.management.entity.XueSheng;
import com.feifanuniv.management.utils.ExcelAndBeanUtils;

public class XueShengDaoTest {
	private FileInputStream fileInputStream;
	private ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		ExcelAndBeanUtils.setVersionAndReceiveEntity("1.xlsx", XueSheng.class);
		this.fileInputStream = new FileInputStream("C:\\Users\\zhu\\Desktop\\test\\学生信息.xlsx");
		applicationContext=new ClassPathXmlApplicationContext("file:E:\\workspace-sts-3.7.1.RELEASE\\SchoolOnlinManagement\\src\\main\\resources\\spring\\application-config.xml");
		
	}

	@Test
	public void testCreateXuesheng() throws SQLException {
		XueShengDao xueShengDao = (XueShengDao) applicationContext.getBean("xueShengDao");
		xueShengDao.createXueshengTable();
	}
	@Test
	public void testInsertXuesheng() throws Exception {
	List<XueSheng> list = null;
		
		if (fileInputStream != null) {
			list = ExcelAndBeanUtils.readExcel(fileInputStream);
		}
	
		XueShengDao xueShengDao = (XueShengDao) applicationContext.getBean("xueShengDao");
		xueShengDao.insertXuesheng(list);
	}

}
