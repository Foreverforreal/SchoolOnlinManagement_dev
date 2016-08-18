package com.feifanuniv.management.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feifanuniv.management.dao.SchoolDao;
import com.feifanuniv.management.dao.TeachingplanDao;
import com.feifanuniv.management.entity.Base;
import com.feifanuniv.management.entity.Jxjh;
import com.feifanuniv.management.entity.LJson;
import com.feifanuniv.management.entity.Major;
import com.feifanuniv.management.utils.ExcelAndBeanUtils;
import com.feifanuniv.management.utils.WrongException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class TeachingPlanService extends BaseService {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	private SchoolDao schoolDao;
	private TeachingplanDao teachingplanDao;
	
	private InputStream inputStream;
	
	private String fileName;
	private LJson schoolInfo;
	private List<Jxjh> jxjh;
	private List<Major> noMappingMajor;
	private List<Major> mappingMajor;
	private List<Jxjh> teachingplan;
	private List<LJson> tempContainer=new ArrayList<>();
	private int insertTeachingplanCourseCount;
	
	public boolean addSchool(){
		boolean flag=true;
		if(fileName==null||fileName.equals("")){
			flag=false;
		}else{
			dealWithUploadFile();
		}
		schoolDao.insertSchool(schoolInfo);
		schoolDao.insertSchool_db_settiong(schoolInfo);
		schoolDao.insertSchool_module(schoolInfo);
		schoolDao.insertGlobal_user(schoolInfo);
		schoolDao.insertUser_shool(schoolInfo);
		teachingplanDao.insertTeacher(schoolInfo);
		return flag;
	}
	
	public void createAndInsertJxjh(){
		teachingplanDao.createJxjh();
		teachingplanDao.insertJxjh(jxjh);
	}
	@SuppressWarnings("unchecked")
	public void insertTeachingplan(){
		teachingplanDao.insertCourse();
		if(noMappingMajor.size()>0){
			teachingplanDao.insertGlobalMajor(removeRepeatMajorName((List<Major>) bindKeyId(noMappingMajor)));
		}
		if(makeSureMajor().size()==0){
			teachingplanDao.insertMajor(mappingMajor);
			teachingplanDao.insertSchoolMajor(removeRepeatGlobalMajorId((List<Major>) bindKeyId(mappingMajor)));
			teachingplanDao.insertTeachingplan();
			teachingplanDao.insertTeachingplanCourse();
		}else{
			System.out.println("还有专业没有添加到globalmajor中");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<LJson> addTeachingplan(){
		schoolInfo.setId(schoolDao.selectSchoolId(schoolInfo.getValue()));
		teachingplanDao.insertCourse();
	
		if(noMappingMajor.size()>0){
			teachingplanDao.insertGlobalMajor(removeRepeatMajorName((List<Major>) bindKeyId(noMappingMajor)));
		}
		if(makeSureMajor().size()==0){
//			List<Major> newMajor=removeExistMajor(mappingMajor);
			if(mappingMajor.size()>0){
				teachingplanDao.insertMajor(mappingMajor);
				teachingplanDao.insertSchoolMajor(removeRepeatGlobalMajorId((List<Major>) bindKeyId(mappingMajor)));
			}
			teachingplanDao.insertTeachingplan();
			
			List<LJson> exsitTeachingplanCourse=teachingplanDao.selectExsitTeachingplanCourse();
			if(exsitTeachingplanCourse.size()>0){
				return exsitTeachingplanCourse;
			};
			
			insertTeachingplanCourseCount=teachingplanDao.insertTeachingplanCourse();
		}else{
			System.out.println("还有专业没有添加到globalmajor中");
		}
		return null;
	}
	
	public List<Jxjh> getBreakJxjh(){
		return teachingplanDao.selectBreakJxjh();
	}
	
	public void resetConneUrl(String schoolSymbol){
		switchDb(schoolSymbol,schoolDao);
	}
	public List<Jxjh> getTeachingplan(LJson ljson){
		teachingplan=teachingplanDao.selectTeachingplan(ljson);
		return teachingplan;
	}
	public File downloadTeachingplan(String schoolSymbol){
		return ExcelAndBeanUtils.generateExcel(teachingplan, "教学计划",schoolSymbol,"teachingplan");
	}
	
	   
	public File downloadAllTeachingplan(String schoolSymbol){
		List<Jxjh> jxjh=teachingplanDao.selectTeachingplan(null);
		
		return ExcelAndBeanUtils.generateExcel(teachingplan, "教学计划",schoolSymbol,"teachingplan");
	}
	
	
	public void modifyJxjh(HashMap<String, String> map){
		teachingplanDao.modifyJxjh(map);
	}
	public List<Major> makeSureMajor(){
		List<Major> allMajor=teachingplanDao.selectNewDistinctMajor();
		this.noMappingMajor=new ArrayList<Major>();
		this.mappingMajor=new ArrayList<Major>();
		if(allMajor.size()>0){
			for (Major major : allMajor) {
				Major mapping=teachingplanDao.selectMappingGlobalIdMajor(major);
				if(mapping==null){
					noMappingMajor.add(major);
				}else{
					//部分专业是去除函授,业余分级查询的,导入专业时还是使用原始专业名
					mapping.setName(major.getName());
					mappingMajor.add(mapping);
				}
			}
		}
		
		return noMappingMajor;
	}
	
	
	public List<LJson> getComBoxList(String s,String key){
		switch (s) {
		case "globalmajor":
			return schoolDao.selectGloabalMajor();
		case "major":
			return teachingplanDao.selectDistinctJxjhMajor();
		case "course":
			return teachingplanDao.selectDistinctJxjhCourse();
		case "level":
			return teachingplanDao.selectDistinctJxjhLevel();
		case "enroll_year":
			return teachingplanDao.selectDistinctJxjhYear();
		case "schoolSymbol":
			return schoolDao.selectAllSymbol();
		case "t_major":
			return teachingplanDao.selectTeachingplanMajor();
		case "t_term":
			return teachingplanDao.selectTeachingplanTerm();
		case "t_level":
			return teachingplanDao.selectTeachingplanLevel(key);
		case "t_year":
			return teachingplanDao.selectTeachingplanYear(key);
		default:
			break;
		}
		return null;
	}
	

	
	public void setFileItemAndSwitchDb(List<FileItem> list) throws WrongException {
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
				if(fileItem.getString()!=null){
					if("schoolName".equals(fileItem.getFieldName())){
						try {
							schoolInfo.setKey(fileItem.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					if("schoolSymbol".equals(fileItem.getFieldName())){
						schoolInfo.setValue(fileItem.getString());
					}
				}else{
					System.out.println("----------------------表单信息缺少学校必要信息--------------------");
				}
			}
		}
		switchDb(schoolInfo.getValue(),schoolDao);
	}
	
	public void switchDb(String schoolSymbol) {
		switchDb(schoolSymbol,schoolDao);
	}
	public void commonSearch(String schoolName) throws MySQLSyntaxErrorException {
		List<LJson> list= teachingplanDao.selectCommon(schoolName);
		tempContainer.addAll(list);  
	}
	public void printMajorStudent(String page) {
		ExcelAndBeanUtils.generateExcel(tempContainer, "专业学生信息"+page,"所有","xuesheng");
		tempContainer.clear();
	}
	
	public void dealWithUploadFile() {
		ExcelAndBeanUtils.setVersionAndReceiveEntity(fileName,Jxjh.class);
		this.jxjh=ExcelAndBeanUtils.readExcel(inputStream);
		for (Jxjh j : jxjh) {
			String enroll_year=j.getEnroll_year();
			if(enroll_year.length()==6){
				j.setEnroll_year(enroll_year.substring(0, 4));
			}
		}
		
	}
	
	public<T> List<? extends Base> bindKeyId(List<? extends Base> type){
		for (Base base : type) {
			base.keyid=schoolInfo.getId();
		}
		return type;
	}
	public List<Major> removeRepeatMajorName(List<Major> noMappingRepeatMajor){
		List<Major> noRepeatMajorName = new ArrayList<>();
		noRepeatMajorName.add(noMappingRepeatMajor.get(0));
		for (int i = 1; i < noMappingRepeatMajor.size(); i++) {
			boolean flag=true;
			for (int j =0; j <i; j++){
				if (noMappingRepeatMajor.get(i).getName().equals(noMappingRepeatMajor.get(j).getName())){
					flag=false;
				}
			}
			if(flag){
				noRepeatMajorName.add(noMappingRepeatMajor.get(i));
			}
		}
		return noRepeatMajorName;
	}
	public List<Major> removeRepeatGlobalMajorId(List<Major> mappingMajor){
		List<Major> noRepeatGolbalIdMajor = new ArrayList<>();
		noRepeatGolbalIdMajor.add(mappingMajor.get(0));
		for (int i = 1; i < mappingMajor.size(); i++) {
			boolean flag=true;
			for (int j =0; j <i; j++){
				if (mappingMajor.get(i).getGlobal_major_id().equals(mappingMajor.get(j).getGlobal_major_id())){
					flag=false;
				}
			}
			if(flag){
				noRepeatGolbalIdMajor.add(mappingMajor.get(i));
			}
		}
		return noRepeatGolbalIdMajor;
	}
	
	public List<Major> removeExistMajor(List<Major> mappingMajor){
		List<Major> existMajor=teachingplanDao.selectAllMajor();
		List<Major> newMajor=new ArrayList<>();
		for (Major majoradd : mappingMajor) {
			boolean flag=true;
			for (Major majorexist : existMajor) {
				if(majoradd.getName().equals(majorexist.getName())&&majoradd.getLevel().equals(majorexist.getLevel())){
					flag=false;
				}
			}
			if(flag){
				newMajor.add(majoradd);
			}
		}
		return newMajor;
	}
	
	public SchoolDao getSchoolDao() {
		return schoolDao;
	}

	public void setSchoolDao(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	public TeachingplanDao getTeachingplanDao() {
		return teachingplanDao;
	}

	public void setTeachingplanDao(TeachingplanDao teachingplanDao) {
		this.teachingplanDao = teachingplanDao;
	}

	public LJson getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(LJson schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	public int getInsertTeachingplanCourseCount() {
		return insertTeachingplanCourseCount;
	}

	public void setInsertTeachingplanCourseCount(int insertTeachingplanCourseCount) {
		this.insertTeachingplanCourseCount = insertTeachingplanCourseCount;
	}
	

}
