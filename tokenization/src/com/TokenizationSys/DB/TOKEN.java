package com.TokenizationSys.DB;

public class TOKEN {
	//tr register
	public static final int TR_REGISTER=4;
	public static final int CHECK_TR_STATE=5;
	public static final int CHECK_TR_DOMAIN=1;
	
	public static final int CHECK_CARDHOLDER_STATE=2;
	public static final int CHECK_CARDHOLDER_INFO=3;
	public static final int INSERT_CARDHOLDER_INFO=6;
	public static final int UPDATE_CARDHOLDER_INFO=7;
	
	public static final int CHECK_CARDHODER_IP=14;
	public static final int CHECK_CARDHODER_LOCATION=15;
	public static final int CHECK_CARDHODER_DEVICE=16;
	
	public static final int IS_PAN_TRID_EXIST=13;//
	public static final int IS_TOKEN_TRID_EXIST=21;
	public static final int INSERT_TOKEN_PAN_MAPPING=8;//
	public static final int UPDATE_TOKEN_PAN_MAPPING=22;
	public static final int IS_TOKEN_EXIST=9;
	public static final int SEARCH_TR_DOMAIN_BY_ID=10;
	public static final int SEARCH_DUPLICATE_TOKEN_TRID_PAIR=11;//
	public static final int INSERT_TOKEN_TOKENLEN_BY_PAN_TRID=12;//
	
	public static final int IS_TOKEN_EXPIRY=17;
	public static final int DELETE_MAPPING=18;
	public static final int IS_TOKEN_EXPIRY_BY_TOKEN=19;
	public static final int DELETE_TMAPPING_BY_TOKEN=20;
	
	public static final int CHECK_TOKEN_EXPIRY_TIME=23;
	public static final int CHECK_CARDHOLDER_DEVICE_BY_TOKEN=24;
	public static final int GET_SHARED_KEY=25;
	public static final int SET_SHARED_KEY=27;
	public static final int GET_PAN_PAN_EXPIRY=26;
	
	public static final int UPDATE_TOKEN_EXPIRY_TIME=28;
	public static final int UPDATE_TOKEN_STATE=29;
	public static final int UNLINK_TOKEN=30;
	public static final int GET_TOKEN_STATUS=31;

}
