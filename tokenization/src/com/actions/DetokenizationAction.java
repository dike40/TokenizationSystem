package com.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.service.DetokenizationService;

@SuppressWarnings("serial")
public class DetokenizationAction extends ActionSupport implements ServletResponseAware{
	
	private DetokenizationService detokenizationService;
	private String detokenJsonString;
	private HttpServletResponse response;
	
	public void setDetokenizationService(DetokenizationService detokenizationService){
		this.detokenizationService = detokenizationService;
	}
	public DetokenizationService getDetokenizationService(){
		return this.detokenizationService;
	}
	public void setDetokenJsonString(String detokenJsonString){
		this.detokenJsonString = detokenJsonString;
	} 
	public String getDetokenJsonString(){
		return this.detokenJsonString;
	} 
	public String execute() throws Exception{
		 
		String resultString = null;
		try{
			JSONObject jo = JSONObject.fromObject(detokenJsonString);
			resultString = this.detokenizationService.deTokenization(jo);
			
		}catch (Exception e) {
			System.out.println("error from DetokenizationAction:"+e.toString());
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
