package com.feifanuniv.management.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.feifanuniv.management.entity.LJson;
import com.feifanuniv.management.entity.Major;
import com.feifanuniv.management.entity.XueSheng;

public class XueShengDao  {
	private SqlSessionFactory sqlSessionFactory_pro_global;
	private SqlSessionFactory sqlSessionFactory_pro_school;
	
	public void createXueshengTable() {
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		sqlSession.update("xuesheng.creatXueshengTable");
	}
	public void dropXuesheng(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		sqlSession.update("xuesheng.dropXuesheng");
	}
	
	public int selectRepeatUserName(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		List<XueSheng>	list=sqlSession.selectList("xuesheng.selectRepeatUserName");
		System.out.println(list);
		return list.size();
	}
	
	public void insertXuesheng(List<XueSheng> list) {
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		System.out.println(list.size());
		sqlSession.insert("xuesheng.insertXueSheng",list);
		sqlSession.update("xuesheng.updateXueShengLevel");
	}
	public void createTempUser(){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		sqlSession.update("global.creatTempUser");
	}
	
	public List<XueSheng> insertGlobalUser(List<XueSheng> mappingXueSheng){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		LJson ljson= new LJson();
		sqlSession.update("xuesheng.creatTempUser");
		sqlSession.insert("xuesheng.insertTempUser",mappingXueSheng);
		sqlSession.update("xuesheng.insertGlobalUserTempkey",ljson);
		sqlSession.update("xuesheng.updateTempuserKeyid",ljson);
		sqlSession.update("xuesheng.updateGlobalUser");
		return sqlSession.selectList("xuesheng.selectAllTempuser");
	}
	
	public int dropTempuser(){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		return sqlSession.update("xuesheng.dropTempuser");
	}
	public int insertUserSChool(List<LJson> userSchoolLjson){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		return sqlSession.insert("xuesheng.insertUserSchool",userSchoolLjson);
	}
	
	public int insertStudent(List<XueSheng> mappingXueSheng){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		sqlSession.update("xuesheng.resetautoincreatement","student");
		return sqlSession.insert("xuesheng.insertStudent",mappingXueSheng);
	}
	
	public void insertCenter(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		sqlSession.insert("xuesheng.insertCenter");
	}
	
	public void modifyXueSheng(HashMap<String, String> map){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		sqlSession.update("xuesheng.modifyXuesheng",map);
	}
	public void updateXueSheng(List<XueSheng> MappingXueSheng){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		sqlSession.update("xuesheng.updateXuesheng",MappingXueSheng);
	}
	public List<XueSheng>  selectAllXueSheng() throws SQLException{
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.insertXueSheng");
	}
	
	public List<XueSheng> selectMappingMajorXueSheng(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.selectMappingXueSheng");
	}
	
	public List<XueSheng> selectNoMappingMajorXueSheng(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.selectNoMappingMajorXueSheng");
	}
	public List<LJson> selectNoMappingTeachingPlanXueSheng(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.selectNoMappingTeachingPlanXueSheng");
	}
	
	public List<Major> selectCountNoMappingMajor(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.selectCountNoMappingMajor");
	}
	
	
	public List<LJson> selectDistinctMajor(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.selectXueShengMajor");
	}
	public List<LJson> selectMajor(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.selectMajor");
	}
	public List<LJson> selectMajorLevel(String major){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.selectMajorLevel",major);
	}
	public List<LJson> selectDistinctLevel(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.selectXueShengLevel");
	}
	public List<LJson> selectExistMajorLevel(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("xuesheng.selectExistMajorLevel");
	}
	public String selectSchoolId(String schoolSymbol){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		return sqlSession.selectOne("xuesheng.selectSchoolId",schoolSymbol);
	}
	public List<LJson> selectTeachingplan(String id){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("teachingplan.selectTeachingplan",id);
	}
	public LJson copyTeachingplan(LJson packegeInfo){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		sqlSession.insert("teachingplan.copyTeachingplan",packegeInfo);
		return packegeInfo;
	}
	public void copyTeachingplan_Cousre(LJson packegeInfo){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		sqlSession.insert("teachingplan.copyTeachingplan_Cousre",packegeInfo);
	}
	
	
	
	public void setSqlSessionFactory_pro_global(SqlSessionFactory sqlSessionFactory_pro_global) {
		this.sqlSessionFactory_pro_global = sqlSessionFactory_pro_global;
	}
	public void setSqlSessionFactory_pro_school(SqlSessionFactory sqlSessionFactory_pro_school) {
		this.sqlSessionFactory_pro_school = sqlSessionFactory_pro_school;
	}
	
	
}
