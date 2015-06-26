package com.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.service.LifeCycleCtrlService;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class LifeCycleCtrlAction extends ActionSupport implements ServletResponseAware{
	
	private LifeCycleCtrlService lifeCycleCtrlService;
	private String lifeJsonString;
	private HttpServletResponse response;
	
	public void setLifeCycleCtrlService(LifeCycleCtrlService lifeCycleCtrlService){
		this.lifeCycleCtrlService = lifeCycleCtrlService;
	}
	public LifeCycleCtrlService getLifeCycleCtrlService(){
		return this.lifeCycleCtrlService;
	}
	public void setLifeJsonString(String lifeJsonString){
		this.lifeJsonString = lifeJsonString;
	} 
	public String getLifeJsonString(){
		return this.lifeJsonString;
	} 
	public String execute() throws Exception{
		 
		String resultString = null;
		try{
			JSONObject jo = JSONObject.fromObject(lifeJsonString);
			//System.out.println("LifeCycleCtrlAction jo:"+lifeJsonString);
			resultString = this.lifeCycleCtrlService.lifeCtrl(jo);
			//System.out.println("LifeCycleCtrlAction result:"+resultString);
			
		}catch (Exception e) {
			System.out.println("error from LifeCycleCtrlAction:"+e);
			throw e;
		}
		
		response.setCharacterEncoding("utf-8");  
        PrintWriter pw=null;  
        try {  
            pw = response.getWriter();  
            pw.write(resultString);  
        } catch (IOException e) {  
           
        }  
        pw.flush();  
        pw.close();  
        return null;			 
	 }
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}
	

}
