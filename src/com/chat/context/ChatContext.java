package com.chat.context;

import java.util.Date;

public class ChatContext {

	public static final int PORT = 27017;
	
	
	//JDBC 에 접 속시 필요한 네임
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String JDBC_URL ="jdbc:mysql://dm1425968007895.fun25.co.kr:15406/chatsparrow?useUnicode=true&characterEncoding=utf8";
	public static final String JDBC_ID="dydwls121200";
	public static final String JDBC_PASSWORD="dydrkf45";
	
	
	//로그인에 사용되는 것 .
	public static final String FUNC_LOGIN 			=	"FUNC_LOGIN";
	public static final String FUNC_LOGIN_YES		=	"FUNC_LOGIN_YES";
	public static final String FUNC_LOGIN_NO		=	"FUNC_LOGIN_NO";
	public static final String FUNC_LOGIN_NETWORK	=	"FUNC_LOGIN_NETWORK";
	public static final String FUNC_LOGIN_CODE		=	"FUNC_LOGIN_CODE";
	public static final String FUNC_LOGIN_SUCCESS 	=	"FUNC_LOGIN_SUCCESS";
	public static final String FUNC_LOGIN_SESSION	=	"FUNC_LOGIN_SESSION";
	
	//회원가입에 사용되는 것들 .
	public static final String FUNC_JOIN 				=	"FUNC_JOIN";
	public static final String FUNC_JOIN_YES			=	"FUNC_JOIN_YES";
	public static final String FUNC_JOIN_ID				=	"FUNC_JOIN_ID";
	public static final String FUNC_JOIN_SQL_CODE		=	"FUNC_JOIN_SQL_CODE";
	public static final String FUNC_JOIN_SQL_ERR		=	"FUNC_JOIN_SQL_ERR";
	public static final String FUNC_JOIN_CODE 			=	"FUNC_JOIN_CODE";
	
	//그룹 목록 보기
	public static final String FUNC_BUDDY				=	"FUNC_BUDDY";
	public static final String FUNC_BUDDY_LIST			=	"FUNC_BUDDY_LIST";
	
	
	//내 해적단 랭크
	public static final String FUNC_PIRATE				=	"FUNC_PIRATE";
	public static final String FUNC_PIRATE_LIST				=	"FUNC_PIRATE_LIST";
	
	
	//방 나오기
	public static final String FUNC_ENTER_ROOM			=	"FUNC_ENTER_ROOM";
		

	//방 나오기
	public static final String FUNC_EXIT_ROOM			=	"FUNC_EXIT_ROOM";
	
	//해적 방 만들기
	public static final String FUNC_MAKE_ROOM				=	"FUNC_MAKE_ROOM";
	public static final String FUNC_MAKE_ROOM_YES			=	"FUNC_MAKE_ROOM_YES";
	public static final String FUNC_MAKE_ROOM_NO			=	"FUNC_MAKE_ROOM_NO";
	
	//세션종료에 사용되는 
	public static final String FUNC_SESSION_OUT 			=	"FUNC_SESSION_OUT";

	//방들의 정보를 수신받습니다.
	public static final String FUNC_INFO_ROOM_LIST			=	"FUNC_INFO_ROOM_LIST";
	//방의 정보를 수신받습니다.
	public static final String FUNC_INFO_ROOM_USER_LIST		=	"FUNC_INFO_ROOM_USER_LIST"; 
	
	
	//메세지를 송신합니다.
	public static final String FUNC_MSG_REQUEST			=	"FUNC_MSG_REQUEST";
	
	//메세지를 수신합니다.
	public static final String FUNC_MSG_RESPONSE		=	"FUNC_MSG_RESPONSE";
	
	//메세지를 수신하고 전달 합니다.
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
