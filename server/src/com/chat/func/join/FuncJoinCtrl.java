package com.chat.func.join;

import com.chat.context.ChatContext;

import net.sf.json.JSONObject;

public class FuncJoinCtrl {
	private FuncJoinService service = null;
	
	
	public FuncJoinCtrl(){
		service = new FuncJoinService();
		
		
		
	}
	public JSONObject join(JSONObject data){
		JSONObject packet = new JSONObject();
		int result = service.join(data);
		ChatContext.println("FuncJoinCtrl.class", "result = [ "+result+" ] ");
		
		switch(result){
			case 0:{
				packet.put("FUNC",ChatContext.FUNC_JOIN_YES);
				break;
			}
			case 1:{
				packet.put("FUNC",ChatContext.FUNC_JOIN_ID);
				break;
			}
			case 2: case 3:	case 4: {
				packet.put("FUNC",ChatContext.FUNC_JOIN_SQL_CODE);
				break;
			}case 5:{
				packet.put("FUNC",ChatContext.FUNC_JOIN_SQL_ERR);
				break;
			}
			case 6:{
				packet.put("FUNC",ChatContext.FUNC_JOIN_CODE);
				break;
			}
		}
		return packet;
	}
	
	
	
	
}
