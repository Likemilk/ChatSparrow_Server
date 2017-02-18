package com.chat.func.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;

import com.chat.context.ChatContext;

public class FuncJoinDAO {
	public Connection conn=null;
	public PreparedStatement pstmt=null; 

	// 연결과 해제
	public synchronized void connect(){
		try{
			Class.forName(ChatContext.JDBC_DRIVER);
			conn = DriverManager.getConnection(ChatContext.JDBC_URL,ChatContext.JDBC_ID,ChatContext.JDBC_PASSWORD);
		
		}catch(Exception e){
			e.printStackTrace();
			ChatContext.println("FuncJoinDAO",e.toString());
			ChatContext.println("FuncJoinDAO","connect 를 생성하지 못함");
		}finally{
			ChatContext.println("FuncJoinDAO","connect 메서드가 종료됨 ");
		}
	}

	public synchronized void disconnect(){
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(Exception e){
				ChatContext.println("FuncJoinDAO","pstmt 닫는데 에러남");
			}finally{
				ChatContext.println("FuncJoinDAO","pstmt 종료 시도 끝 ");
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				ChatContext.println("FuncJoinDAO","conn 닫는데 에러남");
			}finally{
				ChatContext.println("FuncJoinDAO","conn 종료 시도 끝");
			}
		}
	}
	public synchronized int join(JSONObject obj) throws SQLException{
		String id = obj.getString("ID");
		String password = obj.getString("PASSWORD");
		String pirate = obj.getString("PIRATE");
		ChatContext.println("FuncJoinDAO.class", "login == id = ["+id+"], password ="+password+"], pirate =["+pirate+"]");
		connect();
		String sql1 = "select count(*) as COUNT from user where user.`id` like ?";
		String sql2 = "insert into user values (?,?,?,100,0,current_timestamp())";
		String sql3 = "select count(*) as COUNT from pirate where pirate.`group` like ?";
		String sql4 = "insert into pirate values (?,100,1,current_timestamp())";
		String sql5 = "update pirate set pirate.`money`=pirate.`money`+100, pirate.`member`=pirate.`member`+1 where pirate.`group`=?";
		
		try{
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			ResultSet rs1 = pstmt.executeQuery();
			while(rs1.next()){
				if(rs1.getInt("COUNT")<1){
					pstmt=conn.prepareStatement(sql2);
					pstmt.setString(1, id);
					pstmt.setString(2, password);
					pstmt.setString(3, pirate);
					if(pstmt.executeUpdate()==1){
						pstmt=conn.prepareStatement(sql3);
						pstmt.setString(1, pirate);
						ResultSet rs2 = pstmt.executeQuery();
						while(rs2.next()){
							ChatContext.println("FuncJoinDAO.class","[ "+rs2.getInt("COUNT")+ "]");
							if(rs2.getInt("COUNT")<1){
								pstmt=conn.prepareStatement(sql4);
								pstmt.setString(1, pirate);
								if(pstmt.executeUpdate()==1){
									conn.commit();
									return 0;
								}else{
									conn.rollback();
									return 3;	//pirate - group insert 에러 
								}
							}else{
								pstmt=conn.prepareStatement(sql5);
								pstmt.setString(1, pirate);
								if(pstmt.executeUpdate()==1){
									conn.commit();
									return 0;
								}else{
									conn.rollback();
									return 4;	//pirate - group update 에러 
								}
							}
						}
					}else{
						conn.rollback();
						return 2; //user - id inert  에러 
					}
				}else{
					conn.rollback();
					return 1; // 아이디 중복
				}
			}
			
		
		}catch(SQLException e){
			ChatContext.println("FuncJoinDAO", e.toString());
			conn.rollback();
			return 5;  // SQL 예외 에러
		}finally{
			conn.setAutoCommit(true);
			disconnect();
		}
		return 6;	//코드 에러
	}
	
}
