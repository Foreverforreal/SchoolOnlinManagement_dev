package com.feifanuniv.management.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.feifanuniv.management.entity.Major;

/*public class MajorDao extends SqlSessionDaoSupport  {
	private SqlSessionFactory sqlSessionFactory1;
	private SqlSessionFactory sqlSessionFactory2;
	public List<Major> selectAllMajor(){
		SqlSession sqlSession1 = sqlSessionFactory1.openSession();
		SqlSession sqlSession2 = sqlSessionFactory2.openSession();
		sqlSession1.selectList("major.selectaAllMajor");
		return null;
	}
	

}*/
public class MajorDao   {
	private SqlSessionFactory sqlSessionFactory_pro_school;
	
	public List<Major> selectAllMajor(){
		SqlSession sqlSession = sqlSessionFactory_pro_school.openSession();
		return sqlSession.selectList("major.selectaAllMajor");
	}
	
	public void setSqlSessionFactory_pro_school(SqlSessionFactory sqlSessionFactory2) {
		this.sqlSessionFactory_pro_school = sqlSessionFactory2;
	}
	
	public SqlSessionFactory getSqlSessionFactory_pro_school() {
		return this.sqlSessionFactory_pro_school;
	}
	
}
