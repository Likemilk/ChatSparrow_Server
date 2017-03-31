package com.chat.context;

import java.util.Date;

public class ChatContext {

	public static final int PORT = 8083;
	
	
	//JDBC �뿉 �젒 �냽�떆 �븘�슂�븳 �꽕�엫
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String JDBC_URL ="jdbc:mysql://==========:==========/chatsparrow?useUnicode=true&characterEncoding=utf8";
	public static final String JDBC_ID="===================";
	public static final String JDBC_PASSWORD="===============";
	
	
	//濡쒓렇�씤�뿉 �궗�슜�릺�뒗 寃� .
	public static final String FUNC_LOGIN 			=	"FUNC_LOGIN";
	public static final String FUNC_LOGIN_YES		=	"FUNC_LOGIN_YES";
	public static final String FUNC_LOGIN_NO		=	"FUNC_LOGIN_NO";
	public static final String FUNC_LOGIN_NETWORK	=	"FUNC_LOGIN_NETWORK";
	public static final String FUNC_LOGIN_CODE		=	"FUNC_LOGIN_CODE";
	public static final String FUNC_LOGIN_SUCCESS 	=	"FUNC_LOGIN_SUCCESS";
	public static final String FUNC_LOGIN_SESSION	=	"FUNC_LOGIN_SESSION";
	
	//�쉶�썝媛��엯�뿉 �궗�슜�릺�뒗 寃껊뱾 .
	public static final String FUNC_JOIN 				=	"FUNC_JOIN";
	public static final String FUNC_JOIN_YES			=	"FUNC_JOIN_YES";
	public static final String FUNC_JOIN_ID				=	"FUNC_JOIN_ID";
	public static final String FUNC_JOIN_SQL_CODE		=	"FUNC_JOIN_SQL_CODE";
	public static final String FUNC_JOIN_SQL_ERR		=	"FUNC_JOIN_SQL_ERR";
	public static final String FUNC_JOIN_CODE 			=	"FUNC_JOIN_CODE";
	
	//洹몃９ 紐⑸줉 蹂닿린
	public static final String FUNC_BUDDY				=	"FUNC_BUDDY";
	public static final String FUNC_BUDDY_LIST			=	"FUNC_BUDDY_LIST";
	
	
	//�궡 �빐�쟻�떒 �옲�겕
	public static final String FUNC_PIRATE				=	"FUNC_PIRATE";
	public static final String FUNC_PIRATE_LIST				=	"FUNC_PIRATE_LIST";
	
	
	//諛� �굹�삤湲�
	public static final String FUNC_ENTER_ROOM			=	"FUNC_ENTER_ROOM";
		

	//諛� �굹�삤湲�
	public static final String FUNC_EXIT_ROOM			=	"FUNC_EXIT_ROOM";
	
	//�빐�쟻 諛� 留뚮뱾湲�
	public static final String FUNC_MAKE_ROOM				=	"FUNC_MAKE_ROOM";
	public static final String FUNC_MAKE_ROOM_YES			=	"FUNC_MAKE_ROOM_YES";
	public static final String FUNC_MAKE_ROOM_NO			=	"FUNC_MAKE_ROOM_NO";
	
	//�꽭�뀡醫낅즺�뿉 �궗�슜�릺�뒗 
	public static final String FUNC_SESSION_OUT 			=	"FUNC_SESSION_OUT";

	//諛⑸뱾�쓽 �젙蹂대�� �닔�떊諛쏆뒿�땲�떎.
	public static final String FUNC_INFO_ROOM_LIST			=	"FUNC_INFO_ROOM_LIST";
	//諛⑹쓽 �젙蹂대�� �닔�떊諛쏆뒿�땲�떎.
	public static final String FUNC_INFO_ROOM_USER_LIST		=	"FUNC_INFO_ROOM_USER_LIST"; 
	
	
	//硫붿꽭吏�瑜� �넚�떊�빀�땲�떎.
	public static final String FUNC_MSG_REQUEST			=	"FUNC_MSG_REQUEST";
	
	//硫붿꽭吏�瑜� �닔�떊�빀�땲�떎.
	public static final String FUNC_MSG_RESPONSE		=	"FUNC_MSG_RESPONSE";
	
	//硫붿꽭吏�瑜� �닔�떊�븯怨� �쟾�떖 �빀�땲�떎.
	public static final String MSG_TYPE_WHISPER			=	"MSG_TYPE_WHISPER";
	public static final String MSG_TYPE_WHISPER_FAIL	=	"MSG_TYPE_WHISPER_FAIL";
	public static final String MSG_TYPE_TOKEN_WHISPER	=	"/w";
	public static final String MSG_TYPE_WHISPER_SELF	=	"MSG_TYPE_WHISPER_SELF";
	
	public static final String MSG_TYPE_ALL				=	"MSG_TYPE_ALL";
	public static final String MSG_TYPE_TOKEN_ALL		=	"/!";
	
	public static final String MSG_TYPE_NORMAL			=	"MSG_TYPE_NORMAL";
	
	public static void println(String className,String msg){
		System.out.println(className+"====="+new Date()+"====="+msg);
	}
}
