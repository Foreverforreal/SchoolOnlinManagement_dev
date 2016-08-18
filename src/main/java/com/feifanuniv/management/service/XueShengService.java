package com.feifanuniv.management.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feifanuniv.management.dao.SchoolDao;
import com.feifanuniv.management.dao.XueShengDao;
import com.feifanuniv.management.entity.LJson;
import com.feifanuniv.management.entity.XueSheng;
import com.feifanuniv.management.utils.ExcelAndBeanUtils;
import com.feifanuniv.management.utils.PropertityUtils;
import com.feifanuniv.management.utils.WrongException;

public class XueShengService extends BaseService {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	private XueShengDao xueShengDao;
	private SchoolDao schoolDao;
	
	private String fileName;
	private InputStream inputStream;
	private String schoolSymbol;
	private List<XueSheng> allXueSheng;
	private List<XueSheng> allMappingMajorXueSheng;
	private String schoolId;
	
	public int insertXuesheng() {
		dealWithUploadFile();
		createXusehngTalbe();
		return insertXueSheng(allXueSheng);
	}
	
	public void dealWithUploadFile() {
		ExcelAndBeanUtils.setVersionAndReceiveEntity(fileName,XueSheng.class);
		this.allXueSheng=ExcelAndBeanUtils.readExcel(inputStream);
	}
	
	public void createXusehngTalbe(){
		xueShengDao.createXueshengTable();
		log.info("Pro数据库----xuesheng.table 创建成功");
	}
	
	public int insertXueSheng(List<XueSheng> list){
			xueShengDao.insertXuesheng(list);
			log.info("Pro数据库----"+schoolSymbol+" xuesheng.table 导入----"+list.size()+"----名学生");
			return xueShengDao.selectRepeatUserName();			
	}
	
	public void FlitrateAndinsertGlobalUser(){
		downloadExccel();
		allMappingMajorXueSheng=xueShengDao.insertGlobalUser(xueShengDao.selectMappingMajorXueSheng());
		xueShengDao.dropTempuser();
		log.info("Pro-Global数据库----globao_user.table 导入----"+allMappingMajorXueSheng.size()+"----名学生");
	}
	
	public void insertStudent(){
		xueShengDao.insertCenter();
		xueShengDao.updateXueSheng(allMappingMajorXueSheng);
		int index=xueShengDao.insertStudent(allMappingMajorXueSheng);
		log.info("Pro-School数据库----student.table 导入----"+index+"----名学生");
		xueShengDao.dropXuesheng();
	}
	
	public File downloadExccel(){
		List<XueSheng> list=xueShengDao.selectNoMappingMajorXueSheng();
		return recordCannotUploadXueSheng(list);
	}
	
	public File recordCannotUploadXueSheng(List<XueSheng> list){
		if(list.size()>0){
			return ExcelAndBeanUtils.generateExcel(xueShengDao.selectNoMappingMajorXueSheng(), fileName,schoolSymbol,"xuesheng");
		}
		return null;
	}
		
	public void insertUserSchool(){ 
		xueShengDao.insertUserSChool(createUserSChoolLjsonList());
	}
	

	
	public List<LJson> selectNoMappingTeachingPlanXueSheng() {
		return xueShengDao.selectNoMappingTeachingPlanXueSheng();
	}

	public List<XueSheng> selecMappingMajorXueSheng(){
		return xueShengDao.selectNoMappingMajorXueSheng();
	}
	
	public List<LJson> createUserSChoolLjsonList(){
	
		List<LJson> Ljosnlist = new ArrayList<>();
	
		for (XueSheng xueSheng : allMappingMajorXueSheng) {
			LJson ljson = new LJson();
			ljson.setKey(String.valueOf(xueSheng.getKeyid()));
			ljson.setValue(schoolId);
			Ljosnlist.add(ljson);
		}
		return Ljosnlist;
	}

	
	public void modifyXuesheng(HashMap<String, String> map){
		xueShengDao.modifyXueSheng(map);
	}
	
	public List<LJson> getComBoxList(String type,String key){
		switch (type) {
		case "major":
			return xueShengDao.selectMajor();
		case "level":
			return xueShengDao.selectMajorLevel(key);
		case "distinctmajor":
			return xueShengDao.selectDistinctMajor();
		case "distinctlevel":
			return xueShengDao.selectDistinctLevel();
		case "existmajorlevel":
			return xueShengDao.selectExistMajorLevel();
		case "teachingplan":
			return xueShengDao.selectTeachingplan(key);
		default:
			break;
		}
		return null;
	}
	
	public void copyTeachingplan(List<LJson> list){
		for (LJson info : list) {
			xueShengDao.copyTeachingplan_Cousre(xueShengDao.copyTeachingplan(info));
		}
	}
	
	
	public void setFileItemAndSwitchDb(List<FileItem> list) throws WrongException{
		for ( FileItem fileItem : list) {
			if(!fileItem.isFormField()){
				try {
					this.fileName=fileItem.getName();
					this.inputStream=fileItem.getInputStream();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileItem.isFormField()){
				if("schoolSymbol".equals(fileItem.getFieldName())&&fileItem.getString()!=null)
					this.schoolSymbol=fileItem.getString();
			}
		}
		this.schoolId=xueShengDao.selectSchoolId(schoolSymbol);
		if(schoolId!=null){
			super.switchDb(schoolSymbol,schoolDao);
		}else{
			System.out.println("----------------------学校"+schoolSymbol+"不存在于global.school中--------------------");
		}
	}
	



	public void setXueShengDao(XueShengDao xueShengDao) {
		this.xueShengDao = xueShengDao;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public String getSchoolSymbol() {
		return schoolSymbol;
	}


	public void setSchoolSymbol(String schoolSymbol) {
		this.schoolSymbol = schoolSymbol;
	}


	public List<XueSheng> getList() {
		return allXueSheng;
	}


	public void setList(List<XueSheng> list) {
		this.allXueSheng = list;
	}

	public void setSchoolDao(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}
	
			
}
