package com.feifanuniv.management.web;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.feifanuniv.management.utils.PropertityUtils;

public class BaseController {
	private static  ApplicationContext applicationContext;
	private static String congfigPath="file:E:\\workspace-sts-3.7.1.RELEASE\\SchoolOnlinManagement_dev\\src\\main\\resources\\spring\\application-pro.xml";
	
	
	public static  ApplicationContext getApplicationContext(){
		applicationContext=new ClassPathXmlApplicationContext(congfigPath);
		return applicationContext;
	}
	public void switchDbconfig(String schoolSymbol){
		PropertityUtils.setProSchoolUrl(schoolSymbol);
	}
	public static String setconfig(String env){
		switch (env) {
		case "pro":
			congfigPath="file:E:\\workspace-sts-3.7.1.RELEASE\\SchoolOnlinManagement_dev\\src\\main\\resources\\spring\\application-pro.xml";
			return "D:\\apache-tomcat-8.0.28\\wtpwebapps\\SchoolOnlinManagement_dev\\WEB-INF\\classes\\db-pro.properties";
		case "dev":
			congfigPath="file:E:\\workspace-sts-3.7.1.RELEASE\\SchoolOnlinManagement_dev\\src\\main\\resources\\spring\\application-dev.xml";
			return "D:\\apache-tomcat-8.0.28\\wtpwebapps\\SchoolOnlinManagement_dev\\WEB-INF\\classes\\db-dev.properties";
		case "loc":
			congfigPath="file:E:\\workspace-sts-3.7.1.RELEASE\\SchoolOnlinManagement_dev\\src\\main\\resources\\spring\\application-loc.xml";
			return "D:\\apache-tomcat-8.0.28\\wtpwebapps\\SchoolOnlinManagement_dev\\WEB-INF\\classes\\db-loc.properties";
		default:
			congfigPath="file:E:\\workspace-sts-3.7.1.RELEASE\\SchoolOnlinManagement_dev\\src\\main\\resources\\spring\\application-pro.xml";
			break;
		}
		return congfigPath;
	}
	
	public ServletFileUpload getFileupload(){
		DiskFileItemFactory diskFileItemFactory= new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		fileUpload.setHeaderEncoding("utf-8");
		return fileUpload;
	}
	
}


