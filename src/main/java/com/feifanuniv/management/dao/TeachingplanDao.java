package com.feifanuniv.management.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.feifanuniv.management.entity.Jxjh;
import com.feifanuniv.management.entity.LJson;
import com.feifanuniv.management.entity.Major;

public class TeachingplanDao {
	private SqlSessionFactory sqlSessionFactory_pro_school;
	private SqlSessionFactory sqlSessionFactory_pro_global;
	
	public void insertTeacher(LJson schoolInfo){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		session.insert("teachingplan.insertTeacher",schoolInfo);
	}
	
	public void createJxjh(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		session.update("teachingplan.creatJxjh");
	}
	
	public void insertJxjh(List<Jxjh> jxjh){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		session.insert("teachingplan.insertJxjh",jxjh);
		session.update("teachingplan.setLevelAndUrl");
	}
	public List<Jxjh> selectBreakJxjh(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		List<Jxjh> breakJxjh=session.selectList("teachingplan.selectRepeatJxjh");
		for (Jxjh jxjh1 : breakJxjh) {
			jxjh1.setStatus("repeat");
		}
		List<Jxjh> missFiledsJxjh=session.selectList("teachingplan.selectMissFiledsJxjh");
		List<Jxjh> wrongLevel=session.selectList("teachingplan.selectWrongLevel");
//		List<Jxjh> repeatCourse=session.selectList("teachingplan.selectRepeatCousre");
		breakJxjh.addAll(missFiledsJxjh);
		breakJxjh.addAll(wrongLevel);
//		breakJxjh.addAll(repeatCourse);
		return breakJxjh;
	}
	public void modifyJxjh(Map<String,String> map){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		System.out.println(map);
		session.update("teachingplan.modifyJxjh",map);
	}
	public void insertCourse(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		session.update("teachingplan.resetautoincreatement","course");
		session.update("teachingplan.syncCourse");
		session.insert("teachingplan.insertCourse");
		session.update("teachingplan.updateCourse");
	}
	public List<Major> selectNewDistinctMajor(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectNewDistinctMajor");
	}
	public Major selectMappingGlobalIdMajor(Major major){
		SqlSession session=sqlSessionFactory_pro_global.openSession();
		return session.selectOne("global.selectMappingGlobalIdMajor",major);
	}
	public void insertGlobalMajor(List<Major> noMappingMajor){
		SqlSession session=sqlSessionFactory_pro_global.openSession();
		session.insert("global.insertGlobalMajor",noMappingMajor);
	}
	public void insertSchoolMajor(List<Major> mappingMajor){
		SqlSession session=sqlSessionFactory_pro_global.openSession();
		session.insert("global.insertSchoolMajor",mappingMajor);
	}
	public void insertMajor(List<Major> major){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		session.update("teachingplan.resetautoincreatement","major");
		session.insert("teachingplan.insertMajor",major);
	}
	public int insertTeachingplan(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		session.update("teachingplan.resetautoincreatement","teachingplan");
		return session.insert("teachingplan.insertTeachingplan");
	}
	public List<LJson> selectExsitTeachingplanCourse(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectExsitTeachingplanCourse");
	}
	public int insertTeachingplanCourse(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		session.update("teachingplan.resetautoincreatement","teachingplan_course");
		int count= session.insert("teachingplan.insertTeachingplanCourse");
		session.update("teachingplan.dropJxjh");
		return count;
	}
	
	public List<LJson> selectDistinctJxjhMajor(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectDistinctJxjhMajor");
	}
	public List<LJson> selectDistinctJxjhCourse(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectDistinctJxjhCourse");
	}
	public List<LJson> selectDistinctJxjhLevel(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectDistinctJxjhLevel");
	}
	public List<LJson> selectDistinctJxjhYear(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectDistinctJxjhYear");
	}
	
	public List<Major> selectAllMajor(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectAllMajor");
	}
	
	public void updateCourse(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		 session.update("teachingplan.syncCourse");
	}

	public List<Jxjh> selectTeachingplan(LJson ljson){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectAllTeachingplan",ljson);
	}
	
	public List<LJson> selectTeachingplanMajor(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectTeachingplanMajor");
	}
	public List<LJson> selectTeachingplanLevel(String major){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectTeachingplanLevel",major);
	}
	public List<LJson> selectTeachingplanTerm(){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectTeachingplanTerm");
	}
	public List<LJson> selectTeachingplanYear(String majorId){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectTeachingplanYear",majorId);
	}
	
	public List<LJson> selectCommon(String schoolName){
		SqlSession session=sqlSessionFactory_pro_school.openSession();
		return session.selectList("teachingplan.selectCommon",schoolName);
	}


	public void setSqlSessionFactory_pro_school(SqlSessionFactory sqlSessionFactory_pro_school) {
		this.sqlSessionFactory_pro_school = sqlSessionFactory_pro_school;
	}

	public void setSqlSessionFactory_pro_global(SqlSessionFactory sqlSessionFactory_pro_global) {
		this.sqlSessionFactory_pro_global = sqlSessionFactory_pro_global;
	}

}
