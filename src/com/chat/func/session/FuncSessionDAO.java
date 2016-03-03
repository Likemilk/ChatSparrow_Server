package com.chat.func.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;

import com.chat.context.ChatContext;

public class FuncSessionDAO {
	Connection conn=null;
	PreparedStatement pstmt=null; 

	// 연결과 해제
	public synchronized void connect(){
		try{
			Class.forName(ChatContext.JDBC_DRIVER);
			conn = DriverManager.getConnection(ChatContext.JDBC_URL,ChatContext.JDBC_ID,ChatContext.JDBC_PASSWORD);
		
		}catch(Exception e){
			e.printStackTrace();
			ChatContext.println("FuncSessionDAO",e.toString());
			ChatContext.println("FuncSessionDAO","connect 를 생성하지 못함");
		}finally{
			ChatContext.println("FuncSessionDAO","connect 메서드가 종료됨 ");
		}
	}

	public synchronized void disconnect(){
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(Exception e){
				ChatContext.println("FuncSessionDAO","pstmt 닫는데 에러남");
			}finally{
				ChatContext.println("FuncSessionDAO","pstmt 종료 시도 끝 ");
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				ChatContext.println("FuncSessionDAO","conn 닫는데 에러남");
			}finally{
				ChatContext.println("FuncSessionDAO","conn 종료 시도 끝");
			}
		}
	}
	
	
	
	public synchronized void sessionOut(String id){
		connect();
		ChatContext.println("FuncSessionDAO", "sessionOut == id = ["+id+"]");
		String sql1 = "update user set user.`login`=0 where user.`id` like ?;";
		String sql2 = "select money as MONEY from user where `id` like ?";
		String sql3 = "delete from user where `id` like ?";
		
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1,id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int money = rs.getInt("MONEY");
				if(money < -2300){
					pstmt = conn.prepareStatement(sql3);
					pstmt.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			ChatContext.println("FuncSessionDAO", "sessionOut 메서드 예외 에러 = ["+e+"]");
		} finally {
			disconnect();
		}
	}
	
	public synchronized JSONObject sessionIn(String id){
		connect();
		ChatContext.println("FuncSessionDAO", "sessionIn == id = ["+id+"]");
		JSONObject data = null;
		String sql = "update user set user.`login`=1 where user.`id` like ?;";
		String sql2 = "select "
				+ "user.`id` as ID, "
				+ "user.`group` as PIRATE, "
				+ "user.`money` as MONEY, "
				+ "DATE_FORMAT(user.`date`,'%Y-%m-%d') as DATE from user where user.`id` like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			data = new JSONObject();
			while(rs.next()){
				data.put("ID",rs.getString("ID"));
				data.put("PIRATE",rs.getString("PIRATE"));
				data.put("MONEY",rs.getString("MONEY"));
				data.put("DATE",rs.getString("DATE"));
			}
			
		} catch (SQLException e) {
			ChatContext.println("FuncSessionDAO", "sessionIn 메서드 예외 에러 = ["+e+"]");
		} finally {
			disconnect();
		}
		return data;
	}
	
}
