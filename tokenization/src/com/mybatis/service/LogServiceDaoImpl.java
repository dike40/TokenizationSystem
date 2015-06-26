package com.mybatis.service;

import java.util.List;

import com.mybatis.dao.ILogDao;
import com.mybatis.model.Log;

public class LogServiceDaoImpl implements LogServiceDao {
	
	private ILogDao logDao;
	
	public void setLogDao(ILogDao logDao){
		this.logDao = logDao;
	}

	public int deleteById(int id) {
		// TODO Auto-generated method stub
		int i = logDao.deleteById(id);
		                 //≤‚ ‘ ¬ŒÒ
		int j = 1/0;
		return i;
	}

	public int insert(Log log) {
		// TODO Auto-generated method stub
		return logDao.insert(log);
	}

	public List<Log> list() {
		// TODO Auto-generated method stub
		return logDao.list();
	}

	public int update(Log log) {
		// TODO Auto-generated method stub
		return logDao.update(log);
	}

}
