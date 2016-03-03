package com.chat.func.money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chat.context.ChatContext;

public class FuncMoneyDAO {
	Connection conn=null;
	PreparedStatement pstmt=null; 

	// 연결과 해제
	public synchronized void connect(){
		try{
			Class.forName(ChatContext.JDBC_DRIVER);
			conn = DriverManager.getConnection(ChatContext.JDBC_URL,ChatContext.JDBC_ID,ChatContext.JDBC_PASSWORD);
		
		}catch(Exception e){
			e.printStackTrace();
			ChatContext.println("FuncMoneyDAO",e.toString());
			ChatContext.println("FuncMoneyDAO","connect 를 생성하지 못함");
		}finally{
			ChatContext.println("FuncMoneyDAO","connect 메서드가 종료됨 ");
		}
	}

	public synchronized void disconnect(){
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(Exception e){
				ChatContext.println("FuncMoneyDAO","pstmt 닫는데 에러남");
			}finally{
				ChatContext.println("FuncMoneyDAO","pstmt 종료 시도 끝 ");
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				ChatContext.println("FuncMoneyDAO","conn 닫는데 에러남");
			}finally{
				ChatContext.println("FuncMoneyDAO","conn 종료 시도 끝");
			}
		}
	}
	
	public void chatting(String id){
		//  + 5원
		connect();
		ChatContext.println("[ "+id+" ] FuncMoneyDAO", "login == id = ["+id+"]");
		String sql = "update user set money = money+5 where id like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			ChatContext.println("[ "+id+" ] FuncMoneyDAO", "chatting 메서드 예외 에러 = ["+e+"]");
		} finally {
			disconnect();
		}
	}
	public void makeRoom(String id){
		//  + 100원
		connect();
		ChatContext.println("[ "+id+" ] FuncMoneyDAO", "login == id = ["+id+"]");
		String sql = "update user set money = money+100 where id like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			ChatContext.println("[ "+id+" ] FuncMoneyDAO", "chatting 메서드 예외 에러 = ["+e+"]");
		} finally {
			disconnect();
		}
	}
	public void enterRoom(String id){
		//  + 50
		connect();
		ChatContext.println("[ "+id+" ] FuncMoneyDAO", "login == id = ["+id+"]");
		String sql = "update user set money = money+50 where id like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			ChatContext.println("[ "+id+" ] FuncMoneyDAO", "chatting 메서드 예외 에러 = ["+e+"]");
		} finally {
			disconnect();
		}
	}
	public void chattingExit(String id){
		//  - 50
		connect();
		ChatContext.println("[ "+id+" ] FuncMoneyDAO", "login == id = ["+id+"]");
		String sql = "update user set money = money-50 where id like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			ChatContext.println("[ "+id+" ] FuncMoneyDAO", "chatting 메서드 예외 에러 = ["+e+"]");
		} finally {
			disconnect();
		}
	}
	public void logout(String id){
		//  - 300원
		connect();
		ChatContext.println("[ "+id+" ] FuncMoneyDAO", "login == id = ["+id+"]");
		String sql = "update user set money = money-300 where id like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			ChatContext.println("[ "+id+" ] FuncMoneyDAO", "chatting 메서드 예외 에러 = ["+e+"]");
		} finally {
			disconnect();
		}
	}
}
