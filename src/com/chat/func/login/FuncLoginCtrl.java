package com.chat.func.login;

import com.chat.context.ChatContext;

import net.sf.json.JSONObject;

public class FuncLoginCtrl {
	private FuncLoginService service =null;
	
	public FuncLoginCtrl(){
		service = new FuncLoginService();
		
	}
	
	public JSONObject login(String id,String password){
		int result = service.login(id,password);
		JSONObject packet = new JSONObject();
		switch(result){
			case 0:{
				packet.put("FUNC",ChatContext.FUNC_LOGIN_YES);
				
				break;
			}
			case 1:{

				packet.put("FUNC",ChatContext.FUNC_LOGIN_NO);
				
				break;
			}
			case 2:{

				packet.put("FUNC",ChatContext.FUNC_LOGIN_NETWORK);
				
				break;
			}
			case 3:{
				packet.put("FUNC",ChatContext.FUNC_LOGIN_CODE);
				
				break;
			}
		}
		return packet;
		
	}
			
			
			
}
