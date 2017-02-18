package com.chat.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.chat.context.ChatContext;
import com.chat.func.join.FuncJoinCtrl;
import com.chat.func.login.FuncLoginCtrl;
import com.chat.func.money.FuncMoneyCtrl;
import com.chat.func.session.FuncSessionCtrl;

public class ChatThread extends Thread{
	Socket socket = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	StringBuffer buffer = null;
	String nick = null;
	JSONObject req;
	String id = "Anonymous";
	FuncSessionCtrl sessionCtrl =null;
	FuncMoneyCtrl moneyCtrl =null;
	JSONArray userRoomList =null;
	
	public static Hashtable<String,JSONObject> session;
	public static Hashtable<String,ChatThread> logonHash;
	public static Vector<String> logonVector;
	public static Hashtable<String,ArrayList<String>> roomHash;
	public static Vector<String> roomVector;
	public static Date starttime; // 대화방 개설 시각
	
	static{
		session = new Hashtable<String,JSONObject>();	// 사용자의 아이디, 사용자의 세션 정보
		logonHash = new Hashtable<String,ChatThread>(); // 사용자의 아이디, 사용자의 스레드
		logonVector = new Vector<String>();				// 접속한 사용자의 목록,
		roomHash = new Hashtable<String,ArrayList<String>>();	// 방과, 방에 접속한 사용자들의 아이디
		roomVector = new Vector<String>();					//방 제목들
	}
	
	
	
	
	public ChatThread(Socket socket){
		try{
			this.socket = socket;
			dis = new DataInputStream(this.socket.getInputStream());
			dos = new DataOutputStream(this.socket.getOutputStream());
			buffer = new StringBuffer(2048);
			req = new JSONObject();
			userRoomList = new JSONArray();
			sessionCtrl = new FuncSessionCtrl();
			moneyCtrl = new FuncMoneyCtrl();
		}catch(IOException ioe){
			
		}catch(Exception e){
			
		}	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				req = JSONObject.fromObject(dis.readUTF());
				ChatContext.println("[★  "+id+"  ★]"+"@@ FUNC", req.toString());
				String message = req.getString("FUNC");
				switch(message){
					//Controller역할을 함  패킷의 FUNC에 따라 서비스 클래스 실행을 구분함. 즉, Mapper  의 역할 
					case ChatContext.FUNC_LOGIN:{
						JSONObject data = new JSONObject();
						data = req.getJSONObject("DATA");
						id = data.getString("ID");
						String password = data.getString("PASSWORD");
						
						FuncLoginCtrl ctrl = new FuncLoginCtrl();
						send(ctrl.login(id,password));
						
						break;
					}
					
					case ChatContext.FUNC_LOGIN_SUCCESS:{
						JSONObject packet = new JSONObject();
						JSONObject data = sessionCtrl.sessionIn(id);
						ChatContext.println("[★  "+id+"  ★]"+"["+id+"]의 session 정보", data.toString());
						packet.put("FUNC", ChatContext.FUNC_LOGIN_SESSION);
						packet.put("DATA", sessionCtrl.sessionIn(id));
						this.send(packet);
						session.put(id, data);
						logonHash.put(id, this);
						logonVector.add(id);
						
						break;
					}
					
					case ChatContext.FUNC_SESSION_OUT:{
						sessionCtrl.sessionOut(id);
						if(id.equals("Anonymous")){
							this.interrupt();
						}else{
							sessionCtrl.sessionOut(id);
							session.remove(id);
							logonHash.remove(id);
							for(int i=0;i<logonVector.size();i++){
								if(logonVector.get(i).equals(id)){
									logonVector.remove(i);
								}
							}
							//사용자가 접속해있던 방 리스트들
							JSONArray ary = req.getJSONArray("CHAT_USER_LIST");
							this.exitAllChattingRoom();
						}
						break;
					}
					case ChatContext.FUNC_JOIN:{
						JSONObject data = req.getJSONObject("DATA");
						id = data.getString("ID");
						FuncJoinCtrl ctrl =new FuncJoinCtrl();
						send(ctrl.join(data));
						break;
					}
					
					case ChatContext.FUNC_MAKE_ROOM:{
						JSONObject data = session.get(id);
						String pirate = data.getString("PIRATE");
						JSONObject result = new JSONObject();
						
						if(roomHash.containsKey(pirate)){
							result.put("FUNC", ChatContext.FUNC_MAKE_ROOM_NO);
						}else{
							result.put("FUNC", ChatContext.FUNC_MAKE_ROOM_YES);
							this.enterChattingRoom(pirate);
							result.put("DATA", pirate);
							moneyCtrl.makeRoom(id);
						}
						send(result);
						break;
					}
					case ChatContext.FUNC_ENTER_ROOM:{
						String chattingRoom = req.getString("DATA");
						this.enterChattingRoom(chattingRoom);
						moneyCtrl.enterRoom(id);
						
						break;
					}
					case ChatContext.FUNC_EXIT_ROOM:{
						this.exitChattingRoom(req.getString("DATA"));
						this.responseChattingUserListInfo();
						moneyCtrl.chattingExit(id);
						//
						for(int i=0;i<userRoomList.size();i++){
							if(req.getString("DATA").equals(userRoomList.get(i))){
								userRoomList.remove(i);
							}
						}
						break;
					}
					
					case ChatContext.FUNC_MSG_REQUEST:{
						JSONObject reqData = req.getJSONObject("DATA");
						JSONObject packet = new JSONObject();
						JSONObject data = new JSONObject();
						packet.put("FUNC", ChatContext.FUNC_MSG_RESPONSE);
						
						data.put("ID", id);
						data.put("MSG", reqData.getString("MSG"));
						
						switch(reqData.getString("TYPE")){
							case ChatContext.MSG_TYPE_WHISPER:{
								data.put("CHATTINGROOM", reqData.getString("CHATTINGROOM"));
								String whisper = reqData.getString("WHISPER");
								
								//특정 사용자에게만 메세지를 보냅니다.
								if(logonHash.containsKey(whisper)){
								//우선 사용자가 접속중인지를 확인합니다.
									data.put("TYPE", ChatContext.MSG_TYPE_WHISPER);
									packet.put("DATA", data);
									ChatThread thread = logonHash.get(whisper);
									thread.send(packet);
									
									//self whisper 전송
									data.remove("TYPE");
									packet.remove("DATA");
									data.put("TYPE", ChatContext.MSG_TYPE_WHISPER_SELF);
									data.put("WHISPER", whisper);
									packet.put("DATA", data);
									
									this.send(packet);
								
								}else{
									//사용자가 접속중이지 않을때.
									data.put("TYPE", ChatContext.MSG_TYPE_WHISPER_FAIL);
									packet.put("DATA", data);
									packet.put("WHISPER", whisper);
									this.send(packet);
									
								}
								
								
								break;
							}
							case ChatContext.MSG_TYPE_ALL:{
								data.put("TYPE", ChatContext.MSG_TYPE_ALL);
								packet.put("DATA", data);
								
								//사용자 전체에게 보냅니다.
								sendAll(packet);
								break;
							}
							case ChatContext.MSG_TYPE_NORMAL:{
								data.put("TYPE", ChatContext.MSG_TYPE_NORMAL);
								data.put("CHATTINGROOM", reqData.getString("CHATTINGROOM"));
								packet.put("DATA", data);
								
								sendAll(packet);
								break;
							}
						}
						moneyCtrl.chatting(id);
						
						break;
					}					
				}
				this.responseChattingUserListInfo();
				this.responseRoomListInfo();
				this.userSession();
				Thread.sleep(100);
				
				
			}
		}catch(SocketException se){
			// 사용자의 스레드가 종료되면 에러발생
			System.out.println("[★  "+id+"  ★]"+se.toString());
			se.printStackTrace();
			sessionCtrl.sessionOut(id);
			
			this.interrupt();
		}catch(Exception e){
			System.out.println("[★  "+id+"  ★]"+e.toString());
			e.printStackTrace();
			sessionCtrl.sessionOut(id);
			
			ChatContext.println("[★  "+id+"  ★]"+"ChatThread.class",id+"님의 세션에 에러가 발생하거나, 로그아웃 하였습니다.");
		}
	}
	
	public void send(JSONObject packet) throws IOException{
		ChatContext.println("[★  "+id+"  ★]"+"ChatThread == send(JSONObject)", packet.toString());
		synchronized(dos){
			
			dos.writeUTF(packet.toString());
			dos.flush();
		}
	}
	public void userSession() throws IOException{
		JSONObject packet =new JSONObject();
		if(session.containsKey(this.id)){
			packet.put("FUNC", ChatContext.FUNC_LOGIN_SESSION);
			packet.put("DATA", sessionCtrl.sessionIn(this.id));
			this.send(packet);
		}
	}
	public synchronized void sendAll(JSONObject packet){
		try {
			ChatContext.println("[★  "+id+"  ★]"+"ChatThread == sendAll(JSONObject)", packet.toString());
			Enumeration<String> enumer = logonVector.elements();
			while(enumer.hasMoreElements()){
				String userId = enumer.nextElement();
				ChatThread userThread = logonHash.get(userId);
				userThread.send(packet);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ChatContext.println("[★  "+id+"  ★]"+"ChatThread.class ==sendAll(JSONObject)", "데이터를 broadCast 하던도중 에러가 발생하였습니다.");
			e.printStackTrace();
		}
	}
	
	//복수 채팅방 리스트  삭제
	public synchronized void exitAllChattingRoom(){
		//arrayList는 사용자가 채팅방 말고 그냥 모두 windowClosing 을 하였을때. 
		//사용자가 접속해있던 방 리스트들을 기반으로 스레드에서 가지고 있는 아이디를 이용해 List를 수정후 다시 저장해준다.
		if(!roomVector.isEmpty()){
			Enumeration<String> enumer= roomVector.elements();
			while(enumer.hasMoreElements()){
				String chattingRoom = enumer.nextElement();
				ArrayList<String> array = roomHash.get(chattingRoom);
				for(int i=0;i<array.size();i++){
					if(array.get(i).equals(id)){
						array.remove(i);
						roomHash.remove(chattingRoom);
						if(array.size()!=0){
							roomHash.put(chattingRoom, array);
						}else{
							roomVector.remove(chattingRoom);
						}
					}
				}
			}
		}
	}
	
	//단일 채팅방 리스트 삭제
	public synchronized void exitChattingRoom(String chattingRoom){
		//arrayList는 사용자가 채팅방만 나왔을 때  
		ArrayList<String> userList = roomHash.get(chattingRoom);
		for(int j=0;j<userList.size();j++){
			if(id.equals(userList.get(j))){
				userList.remove(j);
			}
		}
		
		roomHash.remove(chattingRoom);
		if(userList.size()==0){
			roomVector.remove(chattingRoom);			
		}else{
			roomHash.put(chattingRoom,userList);
		}
	}
	//채팅방을 들어갑니다.
	public synchronized void enterChattingRoom(String chattingRoom){
		if(roomHash.containsKey(chattingRoom)){
			ArrayList<String> userList = roomHash.get(chattingRoom);
			userList.add(id);
			userRoomList.add(chattingRoom);
			roomHash.remove(chattingRoom);
			roomHash.put(chattingRoom,userList);
		}else{
			ArrayList<String> ary = new ArrayList<String>();
			ary.add(id);
			userRoomList.add(chattingRoom);
			roomVector.add(chattingRoom);
			roomHash.put(chattingRoom, ary);
		}
	}
	
	public synchronized void responseRoomListInfo(){
		// 방에 대한 정보를 전송합니다.
		if(!id.equals("Anonymous")){
			if(!roomVector.isEmpty()){
				JSONObject packet = new JSONObject();
				packet.put("FUNC", ChatContext.FUNC_INFO_ROOM_LIST);
				JSONArray array = new JSONArray();
				for(String roomName : roomVector){
					JSONObject obj = new JSONObject();
					ArrayList<String> users = roomHash.get(roomName);
					obj.put("CHATTINGROOM",roomName);
					obj.put("COUNT", users.size());
					array.add(obj);
				}
				packet.put("DATA", array);
				this.sendAll(packet);
				
			}else{
				JSONObject packet = new JSONObject();
				packet.put("FUNC", ChatContext.FUNC_INFO_ROOM_LIST);
				JSONArray array = new JSONArray();
				packet.put("DATA", array);
				this.sendAll(packet);
			}
		}
	}
	public synchronized void responseChattingUserListInfo(){
		//해당 사용자가 들어가있는 방을 찾는다.
		try{
			JSONObject packet = new JSONObject();
			JSONObject data = new JSONObject();
			packet.put("FUNC", ChatContext.FUNC_INFO_ROOM_USER_LIST);
			for(int i=0;i<userRoomList.size();i++){
				String chattingRoom = userRoomList.getString(i);
				ArrayList<String> userList = roomHash.get(chattingRoom);
				JSONArray userListPacket = new JSONArray();
				for(int j=0;j<userList.size();j++){
					userListPacket.add(userList.get(j));
				}
				data.put(chattingRoom, userListPacket);
			}
			packet.put("DATA", data);
			
			for(int i=0;i<userRoomList.size();i++){
				String chattingRoom = userRoomList.getString(i);
				ArrayList<String> userList = roomHash.get(chattingRoom);
				for(int j=0;j<userList.size();j++){
					ChatThread userThread = logonHash.get(userList.get(j));
					userThread.send(packet);
				}
			}
			
		}catch(Exception e){
			ChatContext.println("[★  "+id+"  ★]"+"ChatThread.class ==responseChattingUserListInfo", e.toString());
			e.printStackTrace();
		}
	}
}
