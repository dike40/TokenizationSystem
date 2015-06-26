package com.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.service.TokenizationService;

@SuppressWarnings("serial")
public class TokenizationAction extends ActionSupport implements ServletResponseAware{
	
	private TokenizationService tokenizationService;
	private String tokenJsonString;
	private HttpServletResponse response;
	
	public void setTokenizationService(TokenizationService tokenizationService){
		this.tokenizationService = tokenizationService;
	}
	public TokenizationService getTokenizationService(){
		return this.tokenizationService;
	}
	public void setTokenJsonString(String tokenJsonString){
		this.tokenJsonString = tokenJsonString;
	} 
	public String getTokenJsonString(){
		return this.tokenJsonString;
	} 
	public String execute() throws Exception{
		 
		String resultString = null;
		try{
			JSONObject jo = JSONObject.fromObject(tokenJsonString);
			resultString = this.tokenizationService.tokenization(jo);
			
		}catch (Exception e) {
			System.out.println("error from TokenizationAction:"+e.toString());
			resultString += "error from TokenizationAction:"+e.toString();
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
