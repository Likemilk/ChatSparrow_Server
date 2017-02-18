package com.chat.func.session;

import net.sf.json.JSONObject;

import com.chat.func.money.FuncMoneyCtrl;

public class FuncSessionCtrl {
	FuncMoneyCtrl moneyCtrl =null;
	private FuncSessionService service=null;
	
	public FuncSessionCtrl(){
		service = new FuncSessionService();
		moneyCtrl = new FuncMoneyCtrl();
	}
	
	public void sessionOut(String id){
		moneyCtrl.logout(id);
		service.sessionOut(id);
	}
	public JSONObject sessionIn(String id){
		return service.sessionIn(id);
	}
}
