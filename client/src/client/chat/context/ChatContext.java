package client.chat.context;

import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class ChatContext {
	
	public static final String IP="dm1425968007895.fun25.co.kr";
	public static final int PORT =15419;
	public static final String TITLE = "ChatSparrow  0.0.1v";

	public static final String UI_BACKGROUND 	=	"#FFDF24";
	public static final String UI_BUTTON 		=	"#6F2B00";
	public static final String UI_TEXT_WHITE	=	"#FFFFFF";
	public static final String UI_TEXT_BLACK	=	"#000000";
	
	public static final int UI_LOGIN_WIDTH	=	400;
	public static final int UI_LOGIN_HEIGHT	=	600;
	
	public static final int UI_JOIN_WIDTH	=	400;
	public static final int UI_JOIN_HEIGHT	=	650;
	
	public static final int UI_ROOM_WIDTH	=	400;
	public static final int UI_ROOM_HEIGHT	=	700;
	
	public static final int UI_ROOM_LIST_WIDTH	=	400;
	public static final int UI_ROOM_LIST_HEIGHT	=	200;
	
	public static final int UI_CHAT_WIDTH	=	800;
	public static final int UI_CHAT_HEIGHT	=	600;
	
	
	
	
	public static final int UI_ALERT_WIDTH 	=	350;
	public static final int UI_ALERT_HEIGHT	=	300;
	
	//로그인에 사용되는 것 .
	public static final String FUNC_LOGIN 			=	"FUNC_LOGIN";
	public static final String FUNC_LOGIN_YES		=	"FUNC_LOGIN_YES";
	public static final String FUNC_LOGIN_NO		=	"FUNC_LOGIN_NO";
	public static final String FUNC_LOGIN_SUCCESS 	=	"FUNC_LOGIN_SUCCESS";
	public static final String FUNC_LOGIN_NETWORK	=	"FUNC_LOGIN_NETWORK";
	public static final String FUNC_LOGIN_CODE		=	"FUNC_LOGIN_CODE";
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
	public static final String FUNC_PIRATE_LIST			=	"FUNC_PIRATE_LIST";
	
	
	//해적 방 만들기
	public static final String FUNC_MAKE_ROOM			=	"FUNC_MAKE_ROOM";
	public static final String FUNC_MAKE_ROOM_YES		=	"FUNC_MAKE_ROOM_YES";
	public static final String FUNC_MAKE_ROOM_NO		=	"FUNC_MAKE_ROOM_NO";
	
	
	//방 나오기
	public static final String FUNC_EXIT_ROOM			=	"FUNC_EXIT_ROOM";
	
	//방 들어가기
	public static final String FUNC_ENTER_ROOM			=	"FUNC_ENTER_ROOM";

	
	//table List Model
	public static DefaultTableModel roomListModel		= null;
	
	//세션종료에 사용되는 
	public static final String FUNC_SESSION_OUT 	=	"FUNC_SESSION_OUT";
	
	//방들의 정보를 수신받습니다.
	public static final String FUNC_INFO_ROOM_LIST			=	"FUNC_INFO_ROOM_LIST";
	
	//방의 정보를 수신받습니다.
	public static final String FUNC_INFO_ROOM_USER_LIST		=	"FUNC_INFO_ROOM_USER_LIST"; 
	
	
	//메세지를 송신합니다.
	public static final String FUNC_MSG_REQUEST			=	"FUNC_MSG_REQUEST";
	
	//메세지를 수신합니다.
	public static final String FUNC_MSG_RESPONSE		=	"FUNC_MSG_RESPONSE";
	
	
	//메세지를 송신 합니다.
	public static final String MSG_TYPE_WHISPER			=	"MSG_TYPE_WHISPER";
	public static final String MSG_TYPE_WHISPER_FAIL			=	"MSG_TYPE_WHISPER_FAIL";
	public static final String MSG_TYPE_TOKEN_WHISPER	=	"/w";
	public static final String MSG_TYPE_WHISPER_SELF	=	"MSG_TYPE_WHISPER_SELF";
	
	public static final String MSG_TYPE_ALL				=	"MSG_TYPE_ALL";
	public static final String MSG_TYPE_TOKEN_ALL		=	"/!";
	
	public static final String MSG_TYPE_NORMAL			=	"MSG_TYPE_NORMAL";
	
	
	
	
	//ALERT 다이얼로그 
	public static final String ALERT_ERR_TITLE			=	"ChatSparrow.System.Message";
	public static final String ERR_CODE_UNKNOWN_HOST	=	"서버 ip 주소를 잘 못 입력하였습니다. 개발자에게 문의해주세요";
	public static final String ERR_CODE_IO				=	"서버가 닫혀있습니다 개발자에게 문의하세요.";
	public static final String ERR_CODE_E				=	"개발자의 코드가 이상합니다 ㅡㅡ 잘좀 짜라 용지나";
	
	// 서버로 부터 받은 메세지
	public static final String ERR_LOGIN			=	"아이디 및 비밀번호를 확인해주세요 또는 회원 계정이 사용중입니다. <br/> ㅠㅠ또는 살해 당했거나..";
	public static final String ERR_LOGIN_NETWORK	=	"서버의 상태가 불안정 합니다.";
	public static final String ERR_LOGIN_CODE		=	"코드를 잘 못 짜셨습니다 그려.";
	
	//회원가입- 로컬 예외 처리
	public static final String ERR_JOIN_INPUT				=	"공백 없이 입력해주세요";
	public static final String ERR_JOIN_ID_LENGTH			=	"아이디는 30글자를 넘을 수 없습니다.";
	public static final String ERR_JOIN_PASS_LENGTH			=	"비밀번호는 30글자를 넘을 수 없습니다.";
	public static final String ERR_JOIN_PIRATE_LENGTH		=	"해적단은 30글자를 넘을 수 없습니다.";

	//회원가입 - 서버로 부터 받은 메세지
	public static final String ERR_JOIN_YES			=	"Welcome Bro!!";
	public static final String ERR_JOIN_ID			=	"아이디가 이미 사용중입니다.";
	public static final String ERR_JOIN_SQL_CODE	=	"SQL 코드에 에러가 있습니다.";
	public static final String ERR_JOIN_SQL_ERR		=	"FuncJoinDAO의 join 메서드에 에러가 발생했습니다.";
	public static final String ERR_JOIN_CODE 		=	"코드좀 똑띠 짜라 ㅡㅡ .. ";
	
	//방만들기 yes or no
	public static final String ERR_MAKE_ROOM_YES 		=	"Parrrrrrrrrrleeeeeey!!";
	public static final String ERR_MAKE_ROOM_NO 		=	"Already, Exist Stupid!!";
	
	//이미지 경로
	public static final String RES_IMG_LOGO = "./resources/images/twitter_pirate.png";
	public static final String RES_IMG_ALERT = "./resources/images/alert.png";
	public static final String RES_IMG_JOIN = "./resources/images/join_icon.png";

	
	
	
	public static void println(String className,String msg){
		System.out.println(className+"====="+new Date()+"====="+msg);
	}

}
