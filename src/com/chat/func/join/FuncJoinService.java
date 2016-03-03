package com.chat.func.join;

import java.sql.SQLException;

import com.chat.context.ChatContext;

import net.sf.json.JSONObject;

public class FuncJoinService {
	private FuncJoinDAO dao = null;
	
	public FuncJoinService(){
		dao = new FuncJoinDAO();
	
	}
	
	public int join(JSONObject obj){
		try {
			return dao.join(obj);
		} catch (SQLException e) {
			ChatContext.println("FuncJoinService.class", e.toString());
			return 6;
		}
	}
	
}
