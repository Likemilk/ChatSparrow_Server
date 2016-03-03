package com.chat.func.money;

public class FuncMoneyCtrl {
	public FuncMoneyDAO dao =null;
	public FuncMoneyCtrl(){
		dao = new FuncMoneyDAO();
	}
	
	
	public void enterRoom(String id){
		dao.enterRoom(id);
	}
	public void chatting(String id){
		dao.chatting(id);
	}
	public void makeRoom(String id){
		dao.makeRoom(id);
	}
	public void chattingExit(String id){
		dao.chattingExit(id);
	}
	public void logout(String id){
		dao.logout(id);
	}
}
