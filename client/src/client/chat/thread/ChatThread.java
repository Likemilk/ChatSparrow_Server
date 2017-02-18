package client.chat.thread;



import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JTable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import client.chat.alert.ChatAlertUI;
import client.chat.chatting.ChatChattingUI;
import client.chat.context.ChatContext;
import client.chat.join.ChatJoinUI;
import client.chat.login.ChatLoginUI;
import client.chat.room.ChatRoomUI;
import client.chat.room.CustomTableModel;



public class ChatThread extends Thread{
	private ChatLoginUI uiLogin =null;
	private ChatJoinUI uiJoin =null;
	private ChatRoomUI uiRoom =null;
	
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private JSONObject res;
	private Thread thisThread;
	
	public ChatThread thread;
	public static String id = "";
	public static Hashtable<String,ChatChattingUI> roomHash;
	
	static{
		roomHash = new Hashtable<String,ChatChattingUI>();
		
	}
	
	//회원가입을 눌렀을 경우에 시작되는 생성자.
	public ChatThread(ChatLoginUI login){
		try{
			this.uiLogin=login;
			this.thread = this;
			socket = new Socket(ChatContext.IP,ChatContext.PORT);
			System.out.println("소켓이 생성되었습니다.");
			
			dis = new DataInputStream(this.socket.getInputStream());
			dos = new DataOutputStream(this.socket.getOutputStream());
			this.res = new JSONObject();
			this.thisThread = this;
			
		}catch(UnknownHostException uhe){
			ChatAlertUI err = new ChatAlertUI(login,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_CODE_UNKNOWN_HOST);
			err.setVisible(true);
			
			System.out.println("uhe");
		}catch(IOException ioe){
			ChatAlertUI err = new ChatAlertUI(login,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_CODE_IO);
			err.setVisible(true);
			
			System.out.println("ioe");
		}catch(Exception e){
			ChatAlertUI err = new ChatAlertUI(login,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_CODE_E);
			err.setVisible(true);
			
			System.out.println("e");
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
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
						uiLogin.dispose();
						uiRoom = new ChatRoomUI(this);
						JSONObject obj = new JSONObject();
						obj.accumulate("FUNC",ChatContext.FUNC_LOGIN_SUCCESS);
						send(obj);
						break;
					} 
					case ChatContext.FUNC_LOGIN_NO:{
						ChatAlertUI err = new ChatAlertUI(uiLogin,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_LOGIN);
						err.setVisible(true);
						break;
					}
					case ChatContext.FUNC_LOGIN_NETWORK:{
						ChatAlertUI err = new ChatAlertUI(uiLogin,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_LOGIN_NETWORK);
						err.setVisible(true);
						break;
					}
					case ChatContext.FUNC_LOGIN_CODE:{
						ChatAlertUI err = new ChatAlertUI(uiLogin,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_LOGIN_CODE);
						err.setVisible(true);
						break;
					}
					case ChatContext.FUNC_LOGIN_SESSION:{
						JSONObject resData = res.getJSONObject("DATA");
						uiRoom.idText.setText("아이디 : "+resData.getString("ID"));
						uiRoom.dateText.setText("가입일 : "+resData.getString("DATE"));
						uiRoom.pirateText.setText("해적단 : "+resData.getString("PIRATE"));
						uiRoom.moneyText.setText("가진돈 : "+resData.getString("MONEY"));
						
						break;
					}
					
					case ChatContext.FUNC_JOIN_YES:{
						uiJoin.dispose();
						uiLogin.dispose();
						uiRoom = new ChatRoomUI(this);
						JSONObject obj = new JSONObject();
						obj.accumulate("FUNC",ChatContext.FUNC_LOGIN_SUCCESS);
						send(obj);
						ChatAlertUI err = new ChatAlertUI(uiRoom,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_JOIN_YES);
						err.setVisible(true);
						
						break;
					}
					case ChatContext.FUNC_JOIN_ID:{
						ChatAlertUI err = new ChatAlertUI(uiJoin,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_JOIN_ID);
						err.setVisible(true);
						break;
					}
					case ChatContext.FUNC_JOIN_SQL_CODE:{
						ChatAlertUI err = new ChatAlertUI(uiJoin,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_JOIN_SQL_CODE);
						err.setVisible(true);
						break;
					}
					case ChatContext.FUNC_JOIN_SQL_ERR:{
						ChatAlertUI err = new ChatAlertUI(uiJoin,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_JOIN_SQL_ERR);
						err.setVisible(true);
						break;
					}
					case ChatContext.FUNC_JOIN_CODE :{
						ChatAlertUI err = new ChatAlertUI(uiJoin,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_JOIN_CODE);
						err.setVisible(true);
						break;
					}
					case ChatContext.FUNC_INFO_ROOM_LIST:{
						JSONArray list = res.getJSONArray("DATA");
						String cols[]={"해적단","인원"};
						String rows[][]=new String[list.size()][2];
						for(int i=0;i<list.size();i++){
							JSONObject obj = list.getJSONObject(i);
							rows[i][0]=obj.getString("CHATTINGROOM");
							rows[i][1]=obj.getString("COUNT");
						}
						CustomTableModel roomListModel = new CustomTableModel(rows, cols);
						
						ChatRoomUI.table.setModel(roomListModel);
						ChatRoomUI.table.setRowHeight(30);
						ChatRoomUI.table.getColumnModel().getColumn(0).setPreferredWidth(300);
						ChatRoomUI.table.getColumnModel().getColumn(1).setPreferredWidth(90);
						
						ChatRoomUI.table.addMouseListener(new MouseAdapter() {
						    public void mousePressed(MouseEvent me) {
						        JTable table =(JTable) me.getSource();
						        Point p = me.getPoint();
						        int row = table.rowAtPoint(p);
						        if (me.getClickCount() == 2) {
						        	if(!roomHash.containsKey(table.getModel().getValueAt(row,0).toString())){
						        		String temp =table.getModel().getValueAt(row,0).toString();
						        		ChatContext.println("ChatThread.class--TABLE_CLICK_ROW", "[["+temp+"]]");
										ChatChattingUI uiChatting = new ChatChattingUI(thread,temp);
										roomHash.put(temp, uiChatting);
										JSONObject packet = new JSONObject();
										packet.put("FUNC", ChatContext.FUNC_ENTER_ROOM);
										packet.put("DATA", temp);
										send(packet);
									}
						        }
						    }
						});
						
						break;
					}
					case ChatContext.FUNC_INFO_ROOM_USER_LIST:{
						JSONObject data = res.getJSONObject("DATA");
						//채팅방 구하기
						Iterator<String> rooms = data.keys();
						while(rooms.hasNext()){
							String room =rooms.next();
							JSONArray userList = data.getJSONArray(room);
							if(roomHash.containsKey(room)){
								ChatChattingUI chat =  roomHash.get(room);
								chat.userList.removeAll();
								for(int i=0; i<userList.size();i++){
									chat.userList.add(userList.getString(i));
								}
							}
						}
						break;
					}
					case ChatContext.FUNC_MAKE_ROOM_YES :{
						ChatChattingUI uiChatting = new ChatChattingUI(this,res.getString("DATA"));
						roomHash.put(res.getString("DATA"), uiChatting);
						ChatAlertUI err = new ChatAlertUI(uiChatting,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_MAKE_ROOM_YES);
						err.setVisible(true);
						
						break;
					} 
					case ChatContext.FUNC_MAKE_ROOM_NO :{
						ChatAlertUI err = new ChatAlertUI(uiRoom,ChatContext.ALERT_ERR_TITLE,ChatContext.ERR_MAKE_ROOM_NO);
						err.setVisible(true);
						
						break;
					}
					case ChatContext.FUNC_MSG_RESPONSE:{
						JSONObject resData = res.getJSONObject("DATA");
						
						switch (resData.getString("TYPE")) {
							case ChatContext.MSG_TYPE_WHISPER: {
								
								Enumeration<String> enumer = roomHash.keys();
								while(enumer.hasMoreElements()){
									ChatChattingUI ui= roomHash.get(enumer.nextElement());
									ui.messageDialog.append(""
											+ "\n귓속말 수신:[ "+resData.getString("ID")+" ] : "+ resData.getString("MSG"));
									ui.messageDialog.setCaretPosition(ui.messageDialog.getDocument().getLength());
								}
								
								
								break;
							}
							case ChatContext.MSG_TYPE_WHISPER_SELF:{
								String chattingRoom = resData.getString("CHATTINGROOM");
								if(roomHash.containsKey(chattingRoom)){	
									ChatChattingUI ui= roomHash.get(chattingRoom);
									ui.messageDialog.append(""
											+ "\n귓속말 전송:[ "+resData.getString("WHISPER")+" ] : "+ resData.getString("MSG"));
									ui.messageDialog.setCaretPosition(ui.messageDialog.getDocument().getLength());
								}
								break;
							}
							case ChatContext.MSG_TYPE_WHISPER_FAIL: {
								String chattingRoom = resData.getString("CHATTINGROOM");
								if(roomHash.containsKey(chattingRoom)){
									ChatChattingUI ui= roomHash.get(chattingRoom);
									ui.messageDialog.append(""
											+ "\n[귓속말Error:[ "+res.getString("WHISPER")+" ] 님이 접속중이지 않거나 존재하지 않습니다.");
									ui.messageDialog.setCaretPosition(ui.messageDialog.getDocument().getLength());
									
								}
								break;
							}
							case ChatContext.MSG_TYPE_ALL: {
								Enumeration<ChatChattingUI> iterator = roomHash.elements();
								while(iterator.hasMoreElements()){
									ChatChattingUI chattingRoom = iterator.nextElement();
									chattingRoom.messageDialog.append("\n["+resData.getString("ID")+"]'s 사자후 : "+resData.getString("MSG"));
									chattingRoom.messageDialog.setCaretPosition(chattingRoom.messageDialog.getDocument().getLength());
								}
								break;
							}
							case ChatContext.MSG_TYPE_NORMAL: {
								if(roomHash.containsKey(resData.getString("CHATTINGROOM"))){
									ChatChattingUI chattingRoom = roomHash.get(resData.getString("CHATTINGROOM"));
									chattingRoom.messageDialog.append("\n"
										+ "[ "+resData.getString("ID")+" ]: "+resData.getString("MSG"));
									chattingRoom.messageDialog.setCaretPosition(chattingRoom.messageDialog.getDocument().getLength());
									
								}else{
									//자신이 속해있는 방에 온 메세지가 아니면 버린다.
								}
								break;
							}
						}
					}				
				}//Switch 종료
				Thread.sleep(100);
			}//while 종료
		}catch(NullPointerException e){
			System.out.println(id);
			e.printStackTrace();
			release();
		}catch(InterruptedException e){
			System.out.println(id);
			e.printStackTrace();
			release();
		}catch(Exception e){
			System.out.println(id);
			e.printStackTrace();
			release();
		}		
	}

	
	public synchronized void send(JSONObject packet){
		ChatContext.println("ChatThread.class", "send(JSONObject) ==="+packet.toString());
		try {
			if(dos!=null){
				dos.writeUTF(packet.toString());
				dos.flush();
			}else{
				ChatContext.println("ChatThread.class", "dos 의 값이 null 상태이므로 메세지를 보낼 수 없습니다.");
			}
		} catch (IOException e) {
			ChatContext.println("ChatThread.class", "dos 의 값이 null 상태이므로 메세지를 보낼 수 없습니다.");
		}
	}
	public synchronized void send(JSONObject packet,ChatLoginUI uiLogin){
		this.uiLogin = uiLogin;
		ChatContext.println("ChatThread.class", "send(JSONObject,ChatLoginUI) ==="+packet.toString());
		try {
			if(dos!=null){
				dos.writeUTF(packet.toString());
				dos.flush();
			}else{
				ChatContext.println("ChatThread.class", "dos 의 값이 null 상태이므로 메세지를 보낼 수 없습니다.");
			}
		} catch (IOException e) {
			ChatContext.println("ChatThread.class", "dos 의 값이 null 상태이므로 메세지를 보낼 수 없습니다.");
		}
	}
	public synchronized void send(JSONObject packet,ChatJoinUI uiJoin){
		this.uiJoin=uiJoin;
		ChatContext.println("ChatThread.class", "send(JSONObject,ChatJoinUI) ==="+packet.toString());
		
		try {
			if(dos!=null){
				dos.writeUTF(packet.toString());
				dos.flush();
			}else{
				ChatContext.println("ChatThread.class", "dos 의 값이 null 상태이므로 메세지를 보낼 수 없습니다.");
			}
		} catch (IOException e) {
			ChatContext.println("ChatThread.class", "dos 의 값이 null 상태이므로 메세지를 보낼 수 없습니다.");
		}
	}
	public synchronized void send(JSONObject packet, ChatRoomUI uiRoom){
		this.uiRoom = uiRoom;
		ChatContext.println("ChatThread.class", "send(JSONObject,ChatRoomUI) ==="+packet.toString());
		try {
			if(dos!=null){
				dos.writeUTF(packet.toString());
				dos.flush();
			}else{
				ChatContext.println("ChatThread.class", "dos 의 값이 null 상태이므로 메세지를 보낼 수 없습니다.");
			}
		} catch (IOException e) {
			ChatContext.println("ChatThread.class", "dos 의 값이 null 상태이므로 메세지를 보낼 수 없습니다.");
		}
	}

	
	public void requestSessionOut(){
		try{
			JSONObject data= new JSONObject(); 
			data.accumulate("FUNC",ChatContext.FUNC_SESSION_OUT);
			Enumeration<String> chat = roomHash.keys();
			JSONArray ary = new JSONArray();
			while(chat.hasMoreElements()){
				ary.add(chat.nextElement());
			}
			data.put("CHAT_USER_LIST", ary);
			send(data);
			release();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public synchronized void requestExitChat(String chatName){
		ChatChattingUI window = roomHash.get(chatName);
		window.dispose();
		roomHash.remove(chatName);
		JSONObject packet = new JSONObject();
		packet.put("FUNC", ChatContext.FUNC_EXIT_ROOM);
		packet.put("DATA", chatName);
		this.send(packet);
	}
	// 자원을 해제 한다.
	public void release(){
		if(thisThread != null){
			thisThread = null;
		}
		try{
			if(dos!=null){
				dos.close();
			}
		}catch(Exception e){
		}finally{
			dos = null;
			System.exit(0);
		}
		
		try{
			send(res); //SendWords 패킷을 전송한다.
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public synchronized void requestMessage(String msg,String titleName){
		try{
			String split[] = msg.split(" ");
			
			JSONObject packet = new JSONObject();
			packet.put("FUNC",ChatContext.FUNC_MSG_REQUEST);
			JSONObject object = new JSONObject();
			
			switch(split[0]){
				case ChatContext.MSG_TYPE_TOKEN_WHISPER:{
					object.put("TYPE", ChatContext.MSG_TYPE_WHISPER);
					object.put("WHISPER", split[1]);
					StringBuffer message = new StringBuffer();
					for(int i=2; i<split.length;i++){
						message.append(split[i]+" ");
					}
					object.put("MSG",message.toString());
					object.put("CHATTINGROOM",titleName);
					break;
				}
				case ChatContext.MSG_TYPE_TOKEN_ALL:{
					object.put("TYPE", ChatContext.MSG_TYPE_ALL);
					StringBuffer message = new StringBuffer();
					for(int i=1; i<split.length;i++){
						message.append(split[i]+" ");
					}
					object.put("MSG",message.toString());
					break;
				}
				default:{
					object.put("TYPE", ChatContext.MSG_TYPE_NORMAL);
					object.put("CHATTINGROOM",titleName);
					object.put("MSG", msg);
					break;
				}
			}
			packet.put("DATA", object);
			send(packet);
			
		}catch(Exception e){
			ChatContext.println("ChatThread-requestMessage", e.toString());
		}
	}
	
	
	
}
