#ChatSparrow 소개

참새는 짹짹 하고 울어서 jack 입니다.
그리고 참새들은 jack jack하며 대화하기 때문에 sparrow라는 이름을 붙였습니다.
그래서 대화하는 참새 ChatSparrow입니다. *twitter* 아닙니...ㄷ...ㅏ..

그리고 캐리비언의 해적 주인공 이름은 jack sparrow 이구요...! 재미있게 만들고자...

ChatSparrow의 로고 Jack입니다.

![logo](https://github.com/drake-jin/ChatSparrow/raw/master/docs/images/logo.png)

  - n:m GUI 채팅프로그램입니다.
  - 채팅프로그램의 테마는 해적입니다.
  - 채팅이 곧 전쟁과 약탈이며, 서비스를 이용할 때마다 포인트의 증감이 있습니다.
  - 로그이웃, 방 이탈, 외치기, 귓속말할 때 마다 포인트를 차감시킵니다.
  - 회원가입, 로그인, 방 만들기, 채팅할 때 마다 포인트를 증가시킵니다.
  - 포인트가 0보다 작게되면 죽은것이므로 계정을 삭제시킵니다.
  - 해적단이기 때문에 모든 회원은 해적단의 그룹에 포함되어 있습니다.

# 디렉토리 구조

  - bin 
    - 실행파일과 jar가 들어가있습니다. ChatSparrow.exe는 resources폴더가 있어야... 정상적으로 보입니다.
  - docs 
    - README.md의 이미지와 문서들을 가지고 있습니다.
  - server
    - ChatSparrow의 서버 소스입니다. 나름 세션도 있습니다. 기왕하는거 connection pool도 만들어볼껄 그랬어요.
  - client
    - ChatSparrow의 클라이언트 소스입니다. 더럽지만 잘부탁드립니다.

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


# 산출문서

  - [제안-설계-완료보고서](https://github.com/drake-jin/ChatSparrow/blob/master/docs/DBbackup.sql) 
  - [DB Backup.sql](https://github.com/drake-jin/ChatSparrow/raw/master/docs/%EC%A0%9C%EC%95%88_%EC%84%A4%EA%B3%84_%EC%99%84%EB%A3%8C%EB%B3%B4%EA%B3%A0%EC%84%9C.pdf)

