package com.mybatis.model;

public class Log {
	
	private int id;
	private String logbegintime;
	private String logendtime;
	private String content;
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getLogbegintime(){
		return this.logbegintime;
	}
	public void setLogbegintime(String logbegintime){
		this.logbegintime = logbegintime;
	}
	
	public String getLogendtime(){
		return this.logendtime;
	}
	public void setLogendtime(String logendtime){
		this.logendtime = logendtime;
	}
	
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content = content;
	}
}
