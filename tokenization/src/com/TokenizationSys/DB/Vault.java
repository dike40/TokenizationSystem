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
	 *��pan��trIdΪ��������ȡtoken
	 *@param mappingData,����pan,trId
	 *@return �����룺
	 *<br>UNKNOW_ERROR: ��ȡ����
	 *<br>SUCCESS:��ȡ�ɹ�
	 *<br>����ֵ�� 
	 *<br>����ֵΪJSON���󣬰�����token��ֵ
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
	 *��token,trIdΪ��������ȡtoken��״̬
	 *@param mappingData ����ӳ����Ϣ��JSON�������ٰ���token,trId
	 *@return ������:
	 *<br>UNKNOW_ERROR: ��ȡ����
	 *<br>SUCCESS:��ȡ�ɹ�
	 *<br>����ֵ��
	 *<br>����ֵΪJSON�������а�����token��״̬:SUSPEND/ACTIVATE
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
	 *��token��trIdΪ�������Ӵ�token��pan�İ󶨣���ɾ������ӳ����Ϣ
	 *@param mappingData ���� ӳ����Ϣ��JSON�������ٰ���token,trId
	 *@return ������:
	 *<br>TOKEN_UNLINK_SUCCESS �󶨽���ɹ�
	 *<br>TOKEN_UNLINK_FAILURE �󶨽��ʧ��
	 *<br>����ֵ:
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
	 *��token,trIdΪ����������token��״̬(SUSPEND/ACTIVATE)
	 *@param mappingData,����ӳ����Ϣ��JSON�������ٰ���token,trId,�µ�token״̬
	 *@return ������:
	 *<br>TOKEN_STATUS_UPDATE_SUCCESS token״̬���³ɹ�
	 *<br>TOKEN_STATUS_UPDATE_FAILURE token״̬����ʧ��
	 *<br>����ֵ:
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
	 *��token,trIdΪ����������token����Ч��
	 *@param mappingData,����ӳ����Ϣ��JSON�������ٰ���token,trId,�µ�token��Чʱ��
	 *@return ������:
	 *<br>TOKEN_EXPIRY_UPDATE_SUCCESS token��Ч�ڸ��³ɹ�
	 *<br>TOKEN_EXPIRY_UPDATE_FAILURE token��Ч�ڸ���ʧ��
	 *<br>����ֵ:
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
	 *��token,trIdΪ��������ȡpan,panExpiryTime
	 *@param mappingData ����ӳ����Ϣ��JSON����,���ٰ���token,trId
	 *@return ������:
	 *<br>UNKNOWN_ERROR:����ʧ��
	 *<br>SUCCESS:���ҳɹ�
	 *<br>����ֵ��
	 *<br>����pan��panExpiry��JSON����
	 *
	 *<br>JSONObject res=getPanAndExpiryTime(mappingData){
	 *<br>	int code=res.getInt("responseCode");//��ȡ������
	 *<br>	JSONObject content=res.getJSONObject("responseContent");
	 *<br>	String pan=content.getString("pan");//��ȡpan
	 *<br>	String panExpiryTime=content.getString("panExpiryTime");//��ȡpanExpiryTime
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
	 *��token,trIdΪ����������sharedKey
	 *@param mappingData  ����ӳ����Ϣ��JSON����,���ٰ���token,trId
	 *@return ������:
	 *<br>UNKNOWN_ERROR:����ʧ��
	 *<br>SUCCESS:���ҳɹ�
	 *<br>����ֵ:
	 *<br>����sharedKey��JSON����
	 *<br>JSONObject res=getSharedKey(mappingData){
	 *<br>	int code=res.getInt("responseCode");//��ȡ������
	 *<br>	JSONObject content=res.getJSONObject("responseContent");
	 *<br>	String pan=content.getString("sharedKey");//��ȡsharedKey
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
//	 *��token��trIdΪ��������ѯ��ֿ����豸��Ϣ�Ƿ�ƥ��
//	 *@param mappingData ����ӳ����Ϣ��JSON����,���ٰ���token ,trId,cardholderDeviceInfo
//	 *@return ������:
//	 * <br>CARDHOLDER_DEVICE_MATCH:�豸��Ϣ����ƥ��
//	 *<br> CARDHOLDER_DEVICE_UNMATCH:�豸��Ϣƥ��
//	 *<br> ����ֵ��
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
	 *��token��trIdΪ��������ѯ����Ч���Ƿ�ƥ��
	 *@param mappingData ����ӳ����Ϣ��JSON����,���ٰ���token ,trId,tokenExpiryTime
	 *@return ������:
	 * <br>TOKEN_EXPIRY_TIME_UNMATCH:token��Ч�ڲ���ƥ��
	 *<br> TOKEN_EXPIRY_TIME_MATCH:token��Ч��ƥ��
	 *<br> ����ֵ��
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
	 *��pan��trIdΪ���������������ɵ�token,tokenLen
	 *@param mappingData ����token��Ϣ��JSON�������ٰ���pan,trId,token,tokenLen
	 *@return ������:
	 *<br>INSERT_TOKEN_TOKENLEN_ERROR:token�������
	 *<br>INSERT_TOKEN_TOKENLEN_SUCCESS:token����ɹ�
	 *<br>
	 *<br>����ֵ:
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
//	 *����token,�����ղŲ����ӳ����Ϣ���⣬�Ƿ���������ӳ����Ϣ�е�token�͸�����tokenƥ��
//	 *@param mappingData ӳ����Ϣ�����ٰ���token
//	 *@return ������:
//	 *<br>NO_DUPLICATE_TOKEN_TRID_PAIR:
//	 *<br>FOUND_DUPLICATE_TOKEN_TRID_PAIR:
//	 *<br>����ֵ:
//	 *<br>��������=NO_DUPLICATE_TOKEN_TRID_PAIRʱ������ֵΪnull����������=FOUND_DUPLICATE_TOKEN_TRID_PAIRʱ��
//	 *����ֵΪһ��JSON����������������г�����token���������ƥ��token��trId����ʽ����:
//	 *<br>{"duplicatedToken":[token#trId1,token#trId2,token#trId3...]}
//	 *<br>������ʽ��
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
	 *��token��trIdΪ������ɾ������ӳ����Ϣ
	 *@param  mappingData ����pan��token��ӳ����Ϣ�����ٰ���token��trId
	 *<br>MAPPING_DELETE_SUCCESS:ɾ���ɹ�
	 *<br>MAPPING_DELETE_FAILURE:ɾ��ʧ��
	 *<br>����ֵ:
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
	 *��token��trIdΪ���������Token�Ƿ���� 
	 *@param  mappingData ����pan��token��ӳ����Ϣ�����ٰ���token��trId
	 *<br>TOKEN_ALREADY_EXPIRY:token�Ѿ�����
	 *<br>TOKEN_NOT_EXPIRY:token��û�й���
	 *<br>����ֵ:
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
	 *����token��trId,����token�Ƿ��ڸ�trId�����룬����ѯ(token,trId)���Ƿ����
	 *@param mappingData ����pan��token��ӳ����Ϣ�����ٰ���token��trId
	 *@return ������:
	 *<br>TOKEN_TRID_NOT_EXIST:(token,trId)�Բ�����
	 *<br>TOKEN_TRID_ALREADY_EXIST:(token,trId)���Ѿ�����
	 *<br>����ֵ:
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
	 *��pan��trIdΪ������ɾ������ӳ����Ϣ
	 *@param  mappingData ����pan��token��ӳ����Ϣ�����ٰ���pan��trId
	 *<br>MAPPING_DELETE_SUCCESS:ɾ���ɹ�
	 *<br>MAPPING_DELETE_FAILURE:ɾ��ʧ��
	 *<br>����ֵ:
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
	 *��pan��trIdΪ���������Token�Ƿ���� 
	 *@param  mappingData ����pan��token��ӳ����Ϣ�����ٰ���pan��trId
	 *<br>TOKEN_ALREADY_EXPIRY:token�Ѿ�����
	 *<br>TOKEN_NOT_EXPIRY:token��û�й���
	 *<br>����ֵ:
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
	 *����pan��trId,����pan�Ƿ��Ѿ��ڸ�trId��������token������ѯ(pan,trId)���Ƿ����
	 *@param mappingData ����pan��token��ӳ����Ϣ�����ٰ���pan��trId
	 *@return ������:
	 *<br>PAN_TRID_NOT_EXIST:(pan,trId)�Բ�����
	 *<br>PAN_TRID_ALREAY_EXIST:(pan,trId)���Ѿ�����
	 *<br>����ֵ:
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
	 *����ӳ����Ϣ
	 *@param mappingData ����pan��token��ӳ����Ϣ
	 *@return ������:
	 *<br>PAN_TRID_ALREAY_EXIST:Ҫ���µ���Ŀ������
	 *<br>MAPPING_UPDATE_SUCCESS:���³ɹ�
	 *<br>MAPPING_UPDATE_FAILUER:����ʧ��
	 *<br>����ֵ:
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
	 *����pan��token��ӳ����Ϣ
	 *@param mappingData ����pan��token��ӳ����Ϣ,����token,tokenLen,tokenExpiryTime,sharedKey������Ϊ"-1"
	 *@return ������:
	 *<br>MAPPING_INSERT_ERROR:�������ݿ�ʱ����
	 *<br>PAN_TRID_ALREAY_EXIST:ӳ����Ϣ�Ѿ����ڣ���(pan,trId)�Ѿ�����
	 *<br>MAPPING_INSERT_SUCCESS:����ɹ�
	 *<br>����ֵ:
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
	 *����һ���ֿ�����Ϣ
	 *@param cardholderData �����ֿ���ȫ����Ϣ��JSON����
	 *@return ������:
	 *<br>ARDHOLDER_INSERT_ERROR:�������ݿ�ʱ����
	 *<br>CARDHOLDER_ALREADY_EXIST:Ҫ����ĳֿ����Ѿ�����
	 *<br>CARDHOLDER_INSERT_SUCCESS:�ֿ�����Ϣ����ɹ�
	 *<br>����ֵ:
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
	 *���³ֿ�����Ϣ
	 *@param cardholderData �����ֿ�����Ϣ��JSON�������ٰ���cardholderId,cardholderIp,cardholderLocation,cardholderDeviceInfo
	 *@return ������:
	 *<br>CARDHOLDER_UPDATE_ERROR:�������ݿ�ʱ����
	 *<br>CARDHOLDER_NOT_EXIST:�ֿ��˲�������
	 *<br>CARDHOLDER_UPDATE_SUCCESS:�忨����Ϣ���³ɹ�
	 *<br>����ֵ:
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
	 *���ֿ����豸��Ϣ�Ƿ�ƥ��
	 *@param cardholderData �����ֿ�����Ϣ��JSON�������ٰ���cardholderId,cardholderDeviceInfo
	 *@return ������:
	 *<br>CARDHOLDER_DEVICE_MATCH:�豸��Ϣƥ��
	 *<br>CARDHOLDER_DEVICE_UNMATCH:�豸��Ϣ��ƥ��
	 *<br>����ֵ:
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
	 *���ֿ��˵���λ���Ƿ�ƥ��
	 *@param cardholderData �����ֿ�����Ϣ��JSON�������ٰ���cardholderId,cardholderLocation
	 *@return ������:
	 *<br>CARDHOLDER_LOCATION_MATCH:����λ��ƥ��
	 *<br>CARDHOLDER_LOCATION_UNMATCH:����λ�ò�ƥ��
	 *<br>����ֵ:
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
	 *���ֿ���IP��ַ�Ƿ�ƥ��
	 *@param cardholderData �����ֿ�����Ϣ��JSON�������ٰ���cardholderId,cardholderIp
	 *@return ������:
	 *<br>CARDHOLDER_IP_MATCH:IP��ַƥ��
	 *<br>CARDHOLDER_IP_UNMATCH:IP��ַ��ƥ��
	 *<br>����ֵ:
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
	 *���ֿ�����Ϣ�Ƿ���ȷ
	 *@param cardholder:�����ֿ�����Ϣ��JSON�������ٰ���cardholderId,cardholderIp,cardholderLocation,cardholderDeviceInfo
	 *@return ������:
	 *<br>CARDHOLDER_NOT_EXIST:Ȼ���ֿ��˲�������
	 *<br>INVALID_CARDHOLDER_INFO:�ֿ�����Ϣ��ƥ��
	 *<br>VALID_CARDHOLDER_INFO:�ֿ�����Ϣƥ��
	 *<br>����ֵ:
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
	 *�������ĳֿ����Ƿ����
	 *@param cardholderData �����ֿ�����Ϣ��JSON�������ٰ���cardholderId
	 *@return ������:
	 *<br>CARDHOLDER_NOT_EXIST:�ֿ��˲�������
	 *<br>CARDHOLDER_ALREADY_EXIST:�ֿ��˴���
	 *<br>����ֵ:
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
	 *���TR�������Ϣ�Ƿ�Ϸ�
	 *@param tokenRequestorData ����TR��Ϣ��JSON�������ٰ���trId,trDomainCtrl,posEntryMode,merchantId
	 *@return �����룺
	 *<br>INVALID_TR_DOMAIN:�����Ϣ����
	 *<br>VALID_TR_DOMAIN:��Ч�������Ϣ
	 *<br>TR_NOT_EXIST:������TR��������
	 *<br>����ֵ:
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
	 *���һ��TR�Ƿ����
	 *@param tokenRequestorData ����TRȫ�����ݵ�JSON����,���ٰ���trId
	 *@return ������:
	 *<br>TR_ALREADY_EXISTTR:��ǰTR�Ѿ�����
	 *<br>TR_NOT_EXIST:��ǰTR������
	 *<br>���ر���:
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
	 *ע��һ���µ�TR
	 *@param tokenRequestorData ����TRȫ�����ݵ�JSON����
	 *@return ������:
	 *		<br>TR_ALREADY_EXISTTR:��ǰTR�Ѿ�����
	 *		<br>TR_REGIST_SUCCESS:TRע��ɹ�
	 *		<br>TR_INSERT_ERROR:TR����ʧ��
	 *		<br>���ر���:
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
	 *�ڲ�����ר�ã�����Դ˺���
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
