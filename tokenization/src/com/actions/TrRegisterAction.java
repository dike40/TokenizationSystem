package com.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.service.TrRegisterService;

@SuppressWarnings("serial")

public class TrRegisterAction extends ActionSupport implements ServletResponseAware{
	
	private TrRegisterService trRegisterService;
	private String trJsonString;
	private HttpServletResponse response;
	
	public void setTrRegisterService(TrRegisterService trRegisterService){
		this.trRegisterService = trRegisterService;
	}
	public TrRegisterService getTrRegisterService(){
		return this.trRegisterService;
	}
	public void setTrJsonString(String trJsonString){
		this.trJsonString = trJsonString;
	} 
	public String getTrJsonString(){
		return this.trJsonString;
	} 
	public String execute() throws Exception{
		 
		String resultString = null;
		try{
			JSONObject jo = JSONObject.fromObject(trJsonString);
			resultString = this.trRegisterService.register(jo);
			
		}catch (Exception e) {
			System.out.println("error from TrRegisterAction:"+e.toString());
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
