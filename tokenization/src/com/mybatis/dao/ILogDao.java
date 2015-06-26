package com.mybatis.dao;

import java.util.List;

import com.mybatis.model.Log;

public interface ILogDao {

	
		public List<Log> list();
	    public int insert(Log log);
	    public int update(Log log);
	    public int deleteById(int id);
}
