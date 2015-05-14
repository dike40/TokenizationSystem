package com.TokenizationSys.DB;
import net.sf.json.JSONObject;
/**
 * 用来构造一个包含用户数据的JSONObject,
 * 使用方法：
 *<br> JSONObject cardholderData=new CardholderInfoBuilder().put("cardholderId", "6d")
 *<br> 														.put("trId", "tr13231")
 *<br> 														.put("cardholderPan", "323132314221")
 *<br> 														.put("cardholderName", "杨箫")
 *<br> 														.put("cardholderLocation", "Sichuan")
 *<br> 														.put("cardholderIp", "192.168.1.1")
 *<br> 														.put("cardholderDeviceInfo", "Nokia")
 *<br> 														.put("extra1", "extra1")
 *<br> 														.put("extra2", "extra2")
 *<br> 														.build();
 */
public class CardholderInfoBuilder{
	private JSONObject data=new JSONObject();
	
	public CardholderInfoBuilder put(String key,Object value){
		//如果为null,则设置为""
			
	    data.put(key, (value==null?"":value));

		return this;
	}
	public JSONObject build(){
		return data;
	}
	public void clear() {
		data.clear();
	}
}
