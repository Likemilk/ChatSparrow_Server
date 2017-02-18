package com.chat.func.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chat.context.ChatContext;

public class FuncLoginDAO {
	
	Connection conn=null;
	PreparedStatement pstmt=null; 

	// 연결과 해제
	public synchronized void connect(){
		try{
			Class.forName(ChatContext.JDBC_DRIVER);
			conn = DriverManager.getConnection(ChatContext.JDBC_URL,ChatContext.JDBC_ID,ChatContext.JDBC_PASSWORD);
		
		}catch(Exception e){
			e.printStackTrace();
			ChatContext.println("FuncLoginDAO",e.toString());
			ChatContext.println("FuncLoginDAO","connect 를 생성하지 못함");
		}finally{
			ChatContext.println("FuncLoginDAO","connect 메서드가 종료됨 ");
		}
	}

	public synchronized void disconnect(){
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(Exception e){
				ChatContext.println("FuncLoginDAO","pstmt 닫는데 에러남");
			}finally{
				ChatContext.println("FuncLoginDAO","pstmt 종료 시도 끝 ");
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				ChatContext.println("FuncLoginDAO","conn 닫는데 에러남");
			}finally{
				ChatContext.println("FuncLoginDAO","conn 종료 시도 끝");
			}
		}
	}
	
	public int login(String id,String password){
		connect();

		ChatContext.println("FuncLoginDAO", "login == id = ["+id+"], password ="+password+"]");
		
		String sql = "select count(*) as COUNT from user where user.`id` like ? and user.`password` like ? and user.`login` = 0";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getInt("COUNT")==1){
					return 0;
				}else{
					return 1;
				}
				
			}
		} catch (SQLException e) {
			ChatContext.println("FuncLoginDAO", "login 메서드 예외 에러 = ["+e+"]");
			return 2;
		} finally {
			disconnect();
		}
		return 3;
		
	}
	
}
