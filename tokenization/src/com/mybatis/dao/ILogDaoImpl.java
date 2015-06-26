package com.mybatis.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;


import com.mybatis.model.Log;

public class ILogDaoImpl extends SqlSessionDaoSupport implements ILogDao{

	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return this.getSqlSession().delete("com.mybatis.model.Log.deleteLog",id);
	}

	public int insert(Log log) {
		// TODO Auto-generated method stub
		 return this.getSqlSession().insert("com.mybatis.model.Log.addLog", log);
	}

	public List<Log> list() {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList("com.mybatis.model.Log.listAll");
	}

	public int update(Log log) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("com.mybatis.model.Log.updateLog",log);
	}

	

}
