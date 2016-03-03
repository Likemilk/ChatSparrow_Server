package com.chat.func.session;

import net.sf.json.JSONObject;

public class FuncSessionService {
	
	private FuncSessionDAO dao=null;
	
	public FuncSessionService(){
		dao = new FuncSessionDAO();
	}
	
	public void sessionOut(String id){
		dao.sessionOut(id);
	}
	
	public JSONObject sessionIn(String id){
		return dao.sessionIn(id);
	}
}
