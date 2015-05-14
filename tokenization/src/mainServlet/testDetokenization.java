package mainServlet;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.sun.org.apache.xerces.internal.util.URI;



public class testDetokenization {
	
	public static void main(String args[]){
	String uri = "http://192.168.1.132:8080/tokenization/mainEntry?reqType=103&deTokenJSON=";
	String tokenKey = "[108,87,122,50,86,122,84,97,117,76,115,102,118,121,79,86,90,52,88,109,67,103,61,61]";
	String result = null;
  
	JSONObject jo = new JSONObject();
	jo.put("tokenStr", "2456232976636570080");
	jo.put("trId", "30715775744");
	jo.put("tokenExpDate", 1503);
	jo.put("devInfo", "xiaomi");
	jo.put("cardhldId", 123);
	jo.put("tokenKey", tokenKey);
	
	uri = uri+jo.toString();
	/*try{ 
		HttpClient httpClient = new DefaultHttpClient(); 
		//仿地址链接直接跟参数，如：http://127.0.0.1:8080/test/test.php?name=; 
		 HttpGet request = new HttpGet();  
		 request.setURI(new URI(uri));  
         HttpResponse response = httpClient.execute(request); 
         
		HttpResponse httpResponse = httpClient.execute(httpGet); 
		if(httpResponse.getStatusLine().getStatusCode()==200){ 
		result = EntityUtils.toString(httpResponse.getEntity()); 
		} 
		}catch(Exception e){
			System.out.println("error");
		} 

		   */     
		   System.out.println("result:"+result);     
		        
	}

}
