package com.TokenizationSys.DB;

public class ResponseCode {
	public static int SUCCESS=0;
	public static int UNKNOWN_ERROR=3;
	
	public static int TR_NOT_EXIST=4;
	public static int TR_ALREADY_EXIST=1;
	public static int TR_REGIST_SUCCESS=2;
	public static int TR_INSERT_ERROR=5;
	public static final int INVALID_TR_DOMAIN=6;
	public static final int VALID_TR_DOMAIN=7;
	
	public static final int CARDHOLDER_NOT_EXIST=8;
	public static final int CARDHOLDER_ALREADY_EXIST=9;
	public static final int INVALID_CARDHOLDER_INFO=10;
	public static final int VALID_CARDHOLDER_INFO=11;
	public static final int CARDHOLDER_INSERT_ERROR=12;
	public static final int CARDHOLDER_INSERT_SUCCESS=13;
	public static final int CARDHOLDER_UPDATE_SUCCESS=14;
	public static final int CARDHOLDER_UPDATE_ERROR=15;
	
	public static final int PAN_TRID_ALREAY_EXIST=23;//
	public static final int PAN_TRID_NOT_EXIST=24;//
	public static final int MAPPING_INSERT_ERROR=16;//
	public static final int MAPPING_INSERT_SUCCESS=17;//
	public static final int NO_DUPLICATE_TOKEN_TRID_PAIR=19;//
	public static final int FOUND_DUPLICATE_TOKEN_TRID_PAIR=25;//
	public static final int SEARCH_TR_DOMAIN_ERROR=20;
	public static final int INSERT_TOKEN_TOKENLEN_ERROR=21;
	public static final int INSERT_TOKEN_TOKENLEN_SUCCESS=22;
	
	public static final int CARDHOLDER_IP_MATCH=26;
	public static final int CARDHOLDER_IP_UNMATCH=27;
	
	public static final int CARDHOLDER_LOCATION_MATCH=28;
	public static final int CARDHOLDER_LOCATION_UNMATCH=29;
	
	public static final int CARDHOLDER_DEVICE_MATCH=30;
	public static final int CARDHOLDER_DEVICE_UNMATCH=31;
	
	public static final int TOKEN_ALREADY_EXPIRY=32;
	public static final int TOKEN_NOT_EXPIRY=33;
	
	public static final int MAPPING_DELETE_SUCCESS=34;
	public static final int MAPPING_DELETE_FAILURE=35;
	
	public static final int TOKEN_TRID_NOT_EXIST=36;
	public static final int TOKEN_TRID_ALREADY_EXIST=37;
	
	public static final int MAPPING_UPDATE_SUCCESS=38;
	public static final int MAPPING_UPDATE_FAILUER=39;
	
	public static final int TOKEN_EXPIRY_TIME_UNMATCH=40;
	public static final int TOKEN_EXPIRY_TIME_MATCH=41;
	
	
	
	
}
