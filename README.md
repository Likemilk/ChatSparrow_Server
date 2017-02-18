#ChatSparrow 소개

참새는 짹짹 하고 울어서 jack 입니다.
그리고 참새들은 jack jack하며 대화하기 때문에 sparrow라는 이름을 붙였습니다.
그래서 대화하는 참새 ChatSparrow입니다. *twitter* 아닙니...ㄷ...ㅏ..

그리고 캐리비언의 해적 주인공 이름은 jack sparrow 이구요...! 재미있게 만들고자...

ChatSparrow의 로고 Jack입니다.

![logo](https://github.com/drake-jin/ChatSparrow/raw/master/docs/images/logo.png)


# 프로젝트 특징
 
  - n:m GUI 채팅프로그램입니다.
  - 채팅프로그램의 테마는 해적입니다.
  - 채팅이 곧 전쟁과 약탈이며, 서비스를 이용할 때마다 포인트의 증감이 있습니다.
  - 로그이웃, 방 이탈, 외치기, 귓속말할 때 마다 포인트를 차감시킵니다.
  - 회원가입, 로그인, 방 만들기, 채팅할 때 마다 포인트를 증가시킵니다.
  - 포인트가 0보다 작게되면 죽은것이므로 계정을 삭제시킵니다.
  - 해적단이기 때문에 모든 회원은 해적단의 그룹에 포함되어 있습니다.

# 프로그램 실행하기

 1. 클라이언트 실행[자바가 설치되어 있어야 합니다.]
 
  - exe실행 
   ``` bash  
  git clone https://github.com/drake-jin/ChatSparrow
  ./ChatSparrow/ChatSparrow.exe
   ```
  - console 실행
 
  ``` bash
  git clone https://github.com/drake-jin/ChatSparrow
  javaw -jar ./ChatSparrow/bin/ChatSparrowClient-0.1.1v.jar  
  ```
 2. 서버 실행 
  [주의!! 첨부파일의 DB와 서버와 클라이언트 소스의 ChatContext.java 에서 포트를 수정하셔야 합니다.. 그리고 실행 jar 로 export해주세요]  
  ``` bash  
  git clone https://github.com/drake-jin/ChatSparrow
  javaw -jar ./ChatSparrow/bin/ChatSparrowServer-0.1.1v.jar

  ```


# 디렉토리 구조

  - bin 
    - 실행파일과 jar가 들어가있습니다. resources폴더가 있는 곳에서 실행 시켜야 합니다....
  - docs 
    - README.md의 이미지와 문서들을 가지고 있습니다.
  - server
    - ChatSparrow의 서버 소스입니다. 나름 세션도 있습니다. 기왕하는거 connection pool도 만들어볼껄 그랬어요.
  - client
    - ChatSparrow의 클라이언트 소스입니다.

# 실행 화면
  안이쁘다구요..? 죄송해요.. 나름 이쁘게 해본겁니다.

![전체 화면 소스](https://github.com/drake-jin/ChatSparrow/raw/master/docs/images/ui.JPG)

# 간단한 설계

  1. DataFormat, DFD, UseCase, Architecture
  
  ![all](https://github.com/drake-jin/ChatSparrow/raw/master/docs/images/images.PNG)
  
  2. ClassDiagram
  
  ![class diagram](https://github.com/drake-jin/ChatSparrow/raw/master/docs/images/classD.png)

  3. ActivityDiagram

  ![Activity Diagram](https://github.com/drake-jin/ChatSparrow/raw/master/docs/images/activityD.png)



# 주요 코드

 1. 서버 헤더 읽기
  ``` java
	@Override
	public void run() {
		// ----------------------생략-------------------------
        try{
			while(true){
				req = JSONObject.fromObject(dis.readUTF());
				String message = req.getString("FUNC");
				switch(message){
					//Controller역할을 함  패킷의 FUNC에 따라 서비스 클래스 실행을 구분함. 즉, Mapper  의 역할 
					case ChatContext.FUNC_LOGIN:{
					}
					case ChatContext.FUNC_LOGIN_SUCCESS:{
					}
					case ChatContext.FUNC_SESSION_OUT:{
					}
					case ChatContext.FUNC_JOIN:{
					}
					case ChatContext.FUNC_MAKE_ROOM:{
						if(roomHash.containsKey(pirate)){
							result.put("FUNC", ChatContext.FUNC_MAKE_ROOM_NO);
						}else{
							result.put("FUNC", ChatContext.FUNC_MAKE_ROOM_YES);
						}
					}
					case ChatContext.FUNC_ENTER_ROOM:{
					}
					case ChatContext.FUNC_EXIT_ROOM:{
					}
					case ChatContext.FUNC_MSG_REQUEST:{
						switch(reqData.getString("TYPE")){
							case ChatContext.MSG_TYPE_WHISPER:{
								if(logonHash.containsKey(whisper)){
								    //사용자가 접속중인지를 확인합니다.
								}else{
									//사용자가 접속중이지 않을때.
								}
							}
							case ChatContext.MSG_TYPE_ALL:{
							}
							case ChatContext.MSG_TYPE_NORMAL:{
							}
						}
					}					
				}
			}
		}catch(SocketException se){
			// 사용자의 스레드가 종료되면 에러발생
			sessionCtrl.sessionOut(id);
		}catch(Exception e){
			sessionCtrl.sessionOut(id);
		}
		// ----------------------생략-------------------------
	}	
  ```

 2. 클라이언트 패킷 읽기

  ``` java 
	@Override
	public void run() {
		// ----------------------생략-------------------------
		try{
			Thread currThread = Thread.currentThread();
			while(currThread == thisThread){
				//thisThread = null;
				String recvData = dis.readUTF();
				res = JSONObject.fromObject(recvData);
				String command = res.getString("FUNC");
				ChatContext.println("@@FUNC==["+command+"]", res.toString());
				//패킷을 돌릴때마다 첫번째 메세지 프토로토콜을 검증한다.
				//서버로 부터 전송 받은 메세지를 구분하기 위함.
				switch(command){
					case ChatContext.FUNC_LOGIN_YES:{
					} 
					case ChatContext.FUNC_LOGIN_NO:{
					}
					case ChatContext.FUNC_LOGIN_NETWORK:{
					}
					case ChatContext.FUNC_LOGIN_CODE:{
					}
					case ChatContext.FUNC_LOGIN_SESSION:{
					}
					case ChatContext.FUNC_JOIN_YES:{
					}
					case ChatContext.FUNC_JOIN_ID:{
					}
					case ChatContext.FUNC_JOIN_SQL_CODE:{
					}
					case ChatContext.FUNC_JOIN_SQL_ERR:{
					}
					case ChatContext.FUNC_JOIN_CODE :{
					}
					case ChatContext.FUNC_INFO_ROOM_LIST:{
					}
					case ChatContext.FUNC_INFO_ROOM_USER_LIST:{
					}
					case ChatContext.FUNC_MAKE_ROOM_YES :{
					} 
					case ChatContext.FUNC_MAKE_ROOM_NO :{
					}
					case ChatContext.FUNC_MSG_RESPONSE:{
						switch (resData.getString("TYPE")) {
							case ChatContext.MSG_TYPE_WHISPER: {
							}
							case ChatContext.MSG_TYPE_WHISPER_SELF:{
							}
							case ChatContext.MSG_TYPE_WHISPER_FAIL: {
							}
							case ChatContext.MSG_TYPE_ALL: {
							}
							case ChatContext.MSG_TYPE_NORMAL: {
							}
						}
					}				
				}//Switch 종료
			}//while 종료
		}catch(NullPointerException e){
		}catch(InterruptedException e){
		}catch(Exception e){
		}		
		// ----------------------생략-------------------------
	}
  ```

# 산출문서

  - [제안-설계-완료보고서](https://github.com/drake-jin/ChatSparrow/blob/master/docs/DBbackup.sql) 
  - [DB Backup.sql](https://github.com/drake-jin/ChatSparrow/raw/master/docs/%EC%A0%9C%EC%95%88_%EC%84%A4%EA%B3%84_%EC%99%84%EB%A3%8C%EB%B3%B4%EA%B3%A0%EC%84%9C.pdf)

