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
/**
 * Vault
 *@author YAO SHUI HE
 */
public class Vault {
	private static Vault vault=null;
	
	@SuppressWarnings("deprecation")
	private HttpClient client=new DefaultHttpClient();
	private HttpPost post=new HttpPost();

	
	private Vault(){
		try{
			post.setURI(new URI("http://192.168.1.118:8080/Vault/VaultController"));
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
	/**
	 *以pan，trId为索引，获取token
	 *@param mappingData,包含pan,trId
	 *@return 返回码：
	 *<br>UNKNOW_ERROR: 获取出错
	 *<br>SUCCESS:获取成功
	 *<br>返回值： 
	 *<br>返回值为JSON对象，包含了token的值
	 *<br>JSONObject res=vault.getTokenStatus(mappingData);
	 *<br>int code=res.getInt("responseCode");
	 *<br>JSONObject tokenObj=res.getJSONObject("responseContent");
	 *<br>String token=stokenObj.getString("token");
	 */
	public JSONObject getToken(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.GET_TOKEN_BY_PAN_TRID,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *以token,trId为索引。获取token的状态
	 *@param mappingData 包含映射信息的JSON对象，至少包含token,trId
	 *@return 返回码:
	 *<br>UNKNOW_ERROR: 获取出错
	 *<br>SUCCESS:获取成功
	 *<br>返回值：
	 *<br>返回值为JSON对象，其中包含了token的状态:SUSPEND/ACTIVATE
	 *<br>JSONObject res=vault.getTokenStatus(mappingData);
	 *<br>int code=res.getInt("responseCode");
	 *<br>JSONObject statusObj=res.getJSONObject("responseContent");
	 *<br>String tokenStatus=statusObj.getString("tokenStatus");
	 */
	public JSONObject getTokenStatus(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.GET_TOKEN_STATUS,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *以token，trId为索引，接触token和pan的绑定，即删除该条映射信息
	 *@param mappingData 包含 映射信息的JSON对象，至少包含token,trId
	 *@return 返回码:
	 *<br>TOKEN_UNLINK_SUCCESS 绑定解除成功
	 *<br>TOKEN_UNLINK_FAILURE 绑定解除失败
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject unlinkToken(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.UNLINK_TOKEN,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *以token,trId为索引，更新token的状态(SUSPEND/ACTIVATE)
	 *@param mappingData,包含映射信息的JSON对象，至少包含token,trId,新的token状态
	 *@return 返回码:
	 *<br>TOKEN_STATUS_UPDATE_SUCCESS token状态更新成功
	 *<br>TOKEN_STATUS_UPDATE_FAILURE token状态更新失败
	 *<br>返回值:
	 *<br>null 
	 */
	public JSONObject updateTokenStatus(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.UPDATE_TOKEN_STATE,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *以token,trId为索引，更新token的有效期
	 *@param mappingData,包含映射信息的JSON对象，至少包含token,trId,新的token有效时间
	 *@return 返回码:
	 *<br>TOKEN_EXPIRY_UPDATE_SUCCESS token有效期更新成功
	 *<br>TOKEN_EXPIRY_UPDATE_FAILURE token有效期更新失败
	 *<br>返回值:
	 *<br>null 
	 */
	public JSONObject updateTokenExpiryTime(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.UPDATE_TOKEN_EXPIRY_TIME,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *以token,trId为索引，获取pan,panExpiryTime
	 *@param mappingData 包含映射信息的JSON对象,至少包含token,trId
	 *@return 返回码:
	 *<br>UNKNOWN_ERROR:查找失败
	 *<br>SUCCESS:查找成功
	 *<br>返回值：
	 *<br>包含pan和panExpiry的JSON对象
	 *
	 *<br>JSONObject res=getPanAndExpiryTime(mappingData){
	 *<br>	int code=res.getInt("responseCode");//获取返回码
	 *<br>	JSONObject content=res.getJSONObject("responseContent");
	 *<br>	String pan=content.getString("pan");//获取pan
	 *<br>	String panExpiryTime=content.getString("panExpiryTime");//获取panExpiryTime
	 *<br>}
	 */
	public JSONObject getPanAndPanExpiryTime(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.GET_PAN_PAN_EXPIRY,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	public JSONObject setSharedKey(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.SET_SHARED_KEY,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *以token,trId为索引，查找sharedKey
	 *@param mappingData  包含映射信息的JSON对象,至少包含token,trId
	 *@return 返回码:
	 *<br>UNKNOWN_ERROR:查找失败
	 *<br>SUCCESS:查找成功
	 *<br>返回值:
	 *<br>包含sharedKey的JSON对象
	 *<br>JSONObject res=getSharedKey(mappingData){
	 *<br>	int code=res.getInt("responseCode");//获取返回码
	 *<br>	JSONObject content=res.getJSONObject("responseContent");
	 *<br>	String pan=content.getString("sharedKey");//获取sharedKey
	 *<br>}
	 */
	public JSONObject getSharedKey(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.GET_SHARED_KEY,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
//	/**
//	 *以token和trId为索引，查询其持卡人设备信息是否匹配
//	 *@param mappingData 包含映射信息的JSON对象,至少包含token ,trId,cardholderDeviceInfo
//	 *@return 返回码:
//	 * <br>CARDHOLDER_DEVICE_MATCH:设备信息并不匹配
//	 *<br> CARDHOLDER_DEVICE_UNMATCH:设备信息匹配
//	 *<br> 返回值：
//	 *<br> null
//	 */
//	public JSONObject checkDeviceByToken(JSONObject mappingData){
//		JSONObject pkg=null;
//		String data=mappingData.toString();
//		String res=executeCmd(TOKEN.CHECK_CARDHOLDER_DEVICE_BY_TOKEN,data);
//
//		if(res!=null){
//			pkg=JSONObject.fromObject(res);
//		}
//		return pkg;
//	}
	
	/**
	 *以token和trId为索引，查询其有效期是否匹配
	 *@param mappingData 包含映射信息的JSON对象,至少包含token ,trId,tokenExpiryTime
	 *@return 返回码:
	 * <br>TOKEN_EXPIRY_TIME_UNMATCH:token有效期并不匹配
	 *<br> TOKEN_EXPIRY_TIME_MATCH:token有效期匹配
	 *<br> 返回值：
	 *<br> null
	 */
	public JSONObject checkTokenExpiryTime(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.CHECK_TOKEN_EXPIRY_TIME,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}

	/**
	 *以pan和trId为索引，插入新生成的token,tokenLen
	 *@param mappingData 包含token信息的JSON对象，至少包含pan,trId,token,tokenLen
	 *@return 返回码:
	 *<br>INSERT_TOKEN_TOKENLEN_ERROR:token插入错误
	 *<br>INSERT_TOKEN_TOKENLEN_SUCCESS:token插入成功
	 *<br>
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject insertToken(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.INSERT_TOKEN_TOKENLEN_BY_PAN_TRID,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
//	/**
//	 *给定token,检测除刚才插入的映射信息以外，是否有其他的映射信息中的token和给定的token匹配
//	 *@param mappingData 映射信息，至少包含token
//	 *@return 返回码:
//	 *<br>NO_DUPLICATE_TOKEN_TRID_PAIR:
//	 *<br>FOUND_DUPLICATE_TOKEN_TRID_PAIR:
//	 *<br>返回值:
//	 *<br>当返回码=NO_DUPLICATE_TOKEN_TRID_PAIR时，返回值为null，当返回码=FOUND_DUPLICATE_TOKEN_TRID_PAIR时，
//	 *返回值为一个JSON对象，里面包含了所有除给定token以外的其他匹配token的trId。格式如下:
//	 *<br>{"duplicatedToken":[token#trId1,token#trId2,token#trId3...]}
//	 *<br>解析方式：
//	 *<br>responseContent=pkg.getJSONObject("responseContent");
//	 *<br>List<String> trIds=responseContent.get("duplicatedToken");
//	 *<br>String ids[]=new String[trIds.size()];
//	 *<br>for(int i=0;i&lt;ids.length;i++){
//	 *<br>&nbsp&nbsp ids[i]=trIds.get(i).split("#")[1];
//	 *<br>}
//	 */
//	public JSONObject isToken2TrExist(JSONObject mappingData){
//		JSONObject pkg=null;
//		String data=mappingData.toString();
//		String res=executeCmd(TOKEN.SEARCH_DUPLICATE_TOKEN_TRID_PAIR,data);
//		System.out.println(res);
//		if(res!=null){
//			pkg=JSONObject.fromObject(res);
//		}
//		return pkg;
//	}
	/**
	 *以token和trId为索引，删除该条映射信息
	 *@param  mappingData 包含pan和token的映射信息，至少包含token和trId
	 *<br>MAPPING_DELETE_SUCCESS:删除成功
	 *<br>MAPPING_DELETE_FAILURE:删除失败
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject deleteMappingByToken(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.DELETE_TMAPPING_BY_TOKEN,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *以token和trId为索引，检测Token是否过期 
	 *@param  mappingData 包含pan和token的映射信息，至少包含token和trId
	 *<br>TOKEN_ALREADY_EXPIRY:token已经过期
	 *<br>TOKEN_NOT_EXPIRY:token并没有过期
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject isTokenExpiryByToken(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.IS_TOKEN_EXPIRY_BY_TOKEN,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *给定token和trId,检测该token是否在该trId下申请，即查询(token,trId)对是否存在
	 *@param mappingData 包含pan和token的映射信息，至少包含token和trId
	 *@return 返回码:
	 *<br>TOKEN_TRID_NOT_EXIST:(token,trId)对不存在
	 *<br>TOKEN_TRID_ALREADY_EXIST:(token,trId)对已经存在
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject isToken2TrExist(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.IS_TOKEN_TRID_EXIST,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	
	/**
	 *以pan和trId为索引，删除该条映射信息
	 *@param  mappingData 包含pan和token的映射信息，至少包含pan和trId
	 *<br>MAPPING_DELETE_SUCCESS:删除成功
	 *<br>MAPPING_DELETE_FAILURE:删除失败
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject deleteMapping(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.DELETE_MAPPING,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *以pan和trId为索引，检测Token是否过期 
	 *@param  mappingData 包含pan和token的映射信息，至少包含pan和trId
	 *<br>TOKEN_ALREADY_EXPIRY:token已经过期
	 *<br>TOKEN_NOT_EXPIRY:token并没有过期
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject isTokenExpiry(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.IS_TOKEN_EXPIRY,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *给定pan和trId,检测该pan是否已经在该trId下申请了token，即查询(pan,trId)对是否存在
	 *@param mappingData 包含pan和token的映射信息，至少包含pan和trId
	 *@return 返回码:
	 *<br>PAN_TRID_NOT_EXIST:(pan,trId)对不存在
	 *<br>PAN_TRID_ALREAY_EXIST:(pan,trId)对已经存在
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject isPan2TrExist(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.IS_PAN_TRID_EXIST,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *更新映射信息
	 *@param mappingData 包含pan和token的映射信息
	 *@return 返回码:
	 *<br>PAN_TRID_ALREAY_EXIST:要更新的条目不存在
	 *<br>MAPPING_UPDATE_SUCCESS:更新成功
	 *<br>MAPPING_UPDATE_FAILUER:更新失败
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject updateMapping(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.UPDATE_TOKEN_PAN_MAPPING,data);
		System.out.println(res);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *插入pan和token的映射信息
	 *@param mappingData 包含pan和token的映射信息,其中token,tokenLen,tokenExpiryTime,sharedKey均设置为"-1"
	 *@return 返回码:
	 *<br>MAPPING_INSERT_ERROR:插入数据库时出粗
	 *<br>PAN_TRID_ALREAY_EXIST:映射信息已经存在，即(pan,trId)已经存在
	 *<br>MAPPING_INSERT_SUCCESS:插入成功
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject insertMapping(JSONObject mappingData){
		JSONObject pkg=null;
		String data=mappingData.toString();
		String res=executeCmd(TOKEN.INSERT_TOKEN_PAN_MAPPING,data);
		System.out.println(res);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *插入一条持卡人信息
	 *@param cardholderData 包含持卡人全部信息的JSON对象
	 *@return 返回码:
	 *<br>ARDHOLDER_INSERT_ERROR:插入数据库时出错
	 *<br>CARDHOLDER_ALREADY_EXIST:要插入的持卡人已经存在
	 *<br>CARDHOLDER_INSERT_SUCCESS:持卡人信息插入成功
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject insertCardholderInfo(JSONObject cardholderData){
		JSONObject pkg=null;
		String data=cardholderData.toString();
		String res=executeCmd(TOKEN.INSERT_CARDHOLDER_INFO,data);
		
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *更新持卡人信息
	 *@param cardholderData 包含持卡人信息的JSON对象，至少包含cardholderId,cardholderIp,cardholderLocation,cardholderDeviceInfo
	 *@return 返回码:
	 *<br>CARDHOLDER_UPDATE_ERROR:更新数据库时出错
	 *<br>CARDHOLDER_NOT_EXIST:持卡人并不存在
	 *<br>CARDHOLDER_UPDATE_SUCCESS:插卡人信息更新成功
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject updateCardholderInfo(JSONObject cardholderData){
		JSONObject pkg=null;
		String data=cardholderData.toString();
		String res=executeCmd(TOKEN.UPDATE_CARDHOLDER_INFO,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *检测持卡人设备信息是否匹配
	 *@param cardholderData 包含持卡人信息的JSON对象，至少包含cardholderId,cardholderDeviceInfo
	 *@return 返回码:
	 *<br>CARDHOLDER_DEVICE_MATCH:设备信息匹配
	 *<br>CARDHOLDER_DEVICE_UNMATCH:设备信息不匹配
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject checkDevice(JSONObject cardholderData){
		JSONObject pkg=null;
		String data=cardholderData.toString();
		String res=executeCmd(TOKEN.CHECK_CARDHODER_DEVICE,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *检测持卡人地理位置是否匹配
	 *@param cardholderData 包含持卡人信息的JSON对象，至少包含cardholderId,cardholderLocation
	 *@return 返回码:
	 *<br>CARDHOLDER_LOCATION_MATCH:地理位置匹配
	 *<br>CARDHOLDER_LOCATION_UNMATCH:地理位置不匹配
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject checkLocation(JSONObject cardholderData){
		JSONObject pkg=null;
		String data=cardholderData.toString();
		String res=executeCmd(TOKEN.CHECK_CARDHODER_LOCATION,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *检测持卡人IP地址是否匹配
	 *@param cardholderData 包含持卡人信息的JSON对象，至少包含cardholderId,cardholderIp
	 *@return 返回码:
	 *<br>CARDHOLDER_IP_MATCH:IP地址匹配
	 *<br>CARDHOLDER_IP_UNMATCH:IP地址不匹配
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject checkIp(JSONObject cardholderData){
		JSONObject pkg=null;
		String data=cardholderData.toString();
		String res=executeCmd(TOKEN.CHECK_CARDHODER_IP,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *检测持卡人信息是否正确
	 *@param cardholder:包含持卡人信息的JSON对象，至少包含cardholderId,cardholderIp,cardholderLocation,cardholderDeviceInfo
	 *@return 返回码:
	 *<br>CARDHOLDER_NOT_EXIST:然而持卡人并不存在
	 *<br>INVALID_CARDHOLDER_INFO:持卡人信息不匹配
	 *<br>VALID_CARDHOLDER_INFO:持卡人信息匹配
	 *<br>返回值:
	 *<br>null
	 */
	private JSONObject checkCardholderInfo(JSONObject cardholderData){
		JSONObject pkg=null;
		String data=cardholderData.toString();
		String res=executeCmd(TOKEN.CHECK_CARDHOLDER_INFO,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *检测给定的持卡人是否存在
	 *@param cardholderData 包含持卡人信息的JSON对象，至少包含cardholderId
	 *@return 返回码:
	 *<br>CARDHOLDER_NOT_EXIST:持卡人并不存在
	 *<br>CARDHOLDER_ALREADY_EXIST:持卡人存在
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject isCardholderExist(JSONObject cardholderData){
		JSONObject pkg=null;
		String data=cardholderData.toString();
		String res=executeCmd(TOKEN.CHECK_CARDHOLDER_STATE,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	} 
	/**
	 *检测TR的域控信息是否合法
	 *@param tokenRequestorData 包含TR信息的JSON对象，至少包含trId,trDomainCtrl,posEntryMode,merchantId
	 *@return 返回码：
	 *<br>INVALID_TR_DOMAIN:域控信息有误
	 *<br>VALID_TR_DOMAIN:有效的域控信息
	 *<br>TR_NOT_EXIST:给定的TR并不存在
	 *<br>返回值:
	 *<br>null
	 */
	public JSONObject checkTrDomain(JSONObject tokenRequestorData){
		JSONObject pkg=null;
		String data=tokenRequestorData.toString();

		
		String res=executeCmd(TOKEN.CHECK_TR_DOMAIN,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *检测一个TR是否存在
	 *@param tokenRequestorData 包含TR全部数据的JSON对象,至少包含trId
	 *@return 返回码:
	 *<br>TR_ALREADY_EXISTTR:当前TR已经存在
	 *<br>TR_NOT_EXIST:当前TR不存在
	 *<br>返回报文:
	 *<br>null
	 */
	public JSONObject isTrExist(JSONObject tokenRequestorData){
		JSONObject pkg=null;
		String data=tokenRequestorData.toString();

		
		String res=executeCmd(TOKEN.CHECK_TR_STATE,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}
	/**
	 *注册一个新的TR
	 *@param tokenRequestorData 包含TR全部数据的JSON对象
	 *@return 返回码:
	 *		<br>TR_ALREADY_EXISTTR:当前TR已经存在
	 *		<br>TR_REGIST_SUCCESS:TR注册成功
	 *		<br>TR_INSERT_ERROR:TR插入失败
	 *		<br>返回报文:
	 *		<br>null
	 */
	public JSONObject registerTr(JSONObject tokenRequestorData){
		JSONObject pkg=null;		
		String data=tokenRequestorData.toString();	
		
		String res=executeCmd(TOKEN.TR_REGISTER,data);
		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;

	}
	/**
	 *内部测试专用，请忽略此函数
	 *
	 */
	public JSONObject sqlTest(JSONObject sql){
		JSONObject pkg=null;		
		String data=sql.toString();	
		
		String res=executeCmd(10086,data);

		if(res!=null){
			pkg=JSONObject.fromObject(res);
		}
		return pkg;
	}

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
