package com.mybatis.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mybatis.model.Log;

public class test {

	public static void main(String args[]){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		LogServiceDao us = (LogServiceDao) ac.getBean("logService");
		     
		//System.out.println("Delete result:"+us.deleteById(1));
		      /* Log log = new Log();
		       log.setLogbegintime("234214");
		       log.setLogendtime("343441");
		       log.setContent("test3");
		       us.insert(log);*/
		       
		       List<Log> logs = us.list();
		       System.out.println(logs.size());
	}
}
