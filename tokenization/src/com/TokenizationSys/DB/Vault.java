package com.TokenizationSys.DB;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class Vault {
	private static Vault vault=null;
	
	@SuppressWarnings("deprecation")
	private HttpClient client=new DefaultHttpClient();
	private HttpPost post=new HttpPost();

	
	private Vault(){
		try{
			post.setURI(new URI("http://asgard.sinaapp.com/Vault"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static Vault getVault(){
		if(null==vault){
			vault=new Vault();
		}
		return vault;
	}
	
	public JSONObject isTrExist(JSONObject tokenRequestorData){
		JSONObject pkg=null;
		String data=tokenRequestorData.toString();

		
		String res=executeCmd(TOKEN.CHECK_TR_STATE,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	public JSONObject registerTR(JSONObject tokenRequestorData){
		JSONObject pkg=null;		
		String data=tokenRequestorData.toString();	
		
		String res=executeCmd(TOKEN.TR_REGISTER,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;

	}
//	public int registerTR(JSONObject tokenRequestorData){
//		String data=tokenRequestorData.toString();
//		int responseCode=ResponseCode.UNKNOWN_ERROR;
//		String resCodeStr=executeCmd(TOKEN.TR_REGISTER,data);
//		if(resCodeStr!=null){
//			responseCode=Integer.parseInt(resCodeStr);
//		}
//		return responseCode;
//
//	}
	private String executeCmd(int type,String jsonData){
		String resCodeStr=null;
		try{
			List<NameValuePair> postData=new ArrayList<NameValuePair>();
			postData.add(new BasicNameValuePair("type",Integer.toString(type)));
			postData.add(new BasicNameValuePair("data",jsonData));		
			UrlEncodedFormEntity e=new  UrlEncodedFormEntity(postData,"UTF-8");
			post.setEntity(e);
			HttpResponse r=client.execute(post);
			InputStream is=r.getEntity().getContent();
			
			int length = 0;
			byte buff[] = new byte[512];
			byte execResult[];
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();	
			while ((length = is.read(buff))!=-1) {
				byteOutput.write(buff, 0, length);	
			}
			execResult = byteOutput.toByteArray();
			resCodeStr=new String(execResult);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resCodeStr;
		
	}
}
