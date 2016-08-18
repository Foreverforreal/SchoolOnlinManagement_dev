package com.feifanuniv.management.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertityUtils {
	private static Logger log = LoggerFactory.getLogger(PropertityUtils.class); 
	private static Properties p = new Properties();
	private static String dbConfigPath="D:\\apache-tomcat-8.0.28\\wtpwebapps\\SchoolOnlinManagement_dev\\WEB-INF\\classes\\db-pro.properties";
	

	public static void loadProperties(){
		try {
			p.load(new FileReader(dbConfigPath));
			log.debug("------------------读取数据库配置文件"+dbConfigPath+"--------------------------");
		} catch (IOException e) {
			log.error("读取数据库配置文件出错");
			e.printStackTrace();
		}
	}
	
	public static void setProSchoolUrl(String schoolUrl){
			loadProperties();
			String finalurl=null;
			if(schoolUrl.length()>10){
				finalurl=schoolUrl+"&allowMultiQueries=true";
			}else{
				String oldUrl=getConfingInfo("jdbc1.url");
				finalurl=oldUrl.substring(0, oldUrl.indexOf("qingshu_")+8)+schoolUrl+oldUrl.substring(oldUrl.indexOf("?autoReconnect"));
			}
				Map<String,String> map =new HashMap<>();
				map.put("jdbc1.driver", getConfingInfo("jdbc1.driver"));
				map.put("jdbc1.url",finalurl);
				map.put("jdbc1.password",getConfingInfo("jdbc1.password"));
				map.put("jdbc1.username", getConfingInfo("jdbc1.username"));
				//标记配置文件状态修改,重新获取datasource
				DynamicDataSource.setChanged(true,map);
			
	}
	
	public static String getConfingInfo(String key){
		return p.getProperty(key);
	}
	public static void setDbConfigPath(String path){
		dbConfigPath=path;
	}
	
}
