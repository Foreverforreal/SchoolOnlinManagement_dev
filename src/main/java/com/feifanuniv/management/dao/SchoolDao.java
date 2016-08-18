package com.feifanuniv.management.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.feifanuniv.management.entity.LJson;

public class SchoolDao {
	private SqlSessionFactory sqlSessionFactory_pro_global;
	
	public void insertSchool(LJson schoolInfo){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		sqlSession.update("global.resetautoincreatement","school");
		sqlSession.update("global.insertSchool",schoolInfo);
	}
	
	public String selectSchoolId(String schoolSymbol){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		return sqlSession.selectOne("global.selectSchoolId",schoolSymbol);
	}
	
	public String selectSchoolUrl(String schoolId){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		return sqlSession.selectOne("global.selectSchoolUrl",schoolId);
	}
	
	public List<LJson> selectAllSymbol(){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		return sqlSession.selectList("global.selectAllSymbol");
	}
	
	public void insertSchool_db_settiong(LJson schoolInfo){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		sqlSession.insert("global.insertDbSetting",schoolInfo);
	}
	public void insertSchool_module(LJson schoolInfo){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		sqlSession.insert("global.insertSchool_module",schoolInfo);
	}
	public void insertGlobal_user(LJson schoolInfo){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		sqlSession.insert("global.insertGlobal_user",schoolInfo);
	}
	public void insertUser_shool(LJson schoolInfo){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		sqlSession.insert("global.insertUser_shool",schoolInfo);
	}
	
	public List<LJson> selectGloabalMajor(){
		SqlSession sqlSession = sqlSessionFactory_pro_global.openSession();
		return sqlSession.selectList("global.selectGloabalMajor");
	}

	

	public SqlSessionFactory getSqlSessionFactory_pro_global() {
		return sqlSessionFactory_pro_global;
	}
	public void setSqlSessionFactory_pro_global(SqlSessionFactory sqlSessionFactory_pro_global) {
		this.sqlSessionFactory_pro_global = sqlSessionFactory_pro_global;
	}
	
}
