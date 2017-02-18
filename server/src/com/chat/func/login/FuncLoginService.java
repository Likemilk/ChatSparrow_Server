package com.chat.func.login;

public class FuncLoginService {
	private FuncLoginDAO dao = null;
	
	public FuncLoginService(){
		dao = new FuncLoginDAO();
	}
	
	public int login(String id,String password){
		
		return dao.login(id,password);
	}
	
}
