package com.chat.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.chat.context.ChatContext;
import com.chat.thread.ChatThread;

public class ChatMain {
	public static void main(String[] args) {
		
		try{
			ServerSocket server =new ServerSocket(ChatContext.PORT);
			
			
			while(true){
				Socket socket = null;
				ChatThread client = null;
				
				try{
					
					socket=server.accept();
					client = new ChatThread(socket);
					client.start();
					/*
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println("===============================================");

					System.out.println(socket);		//Socket[addr=/127.0.0.1,port=12197,localport=55555]
					System.out.println(socket.getPort());//12197 접속자의 포트
					System.out.println(socket.getLocalPort()); //서버의 개방 포트
					System.out.println(socket.getReceiveBufferSize()); // 받는 버퍼 사이즈 65536
					System.out.println(socket.getSendBufferSize());  // 보내는 버퍼사이즈 65536
					System.out.println(socket.getInetAddress()); // 접속한 아이피 주소 127.0.0.1
					System.out.println(socket.getChannel());  // 채널없음 null
					System.out.println(socket.getLocalAddress()); // 서버의 주소 127.0.0.1
					System.out.println(socket.getLocalSocketAddress()); // 서버의 주소 및 포트 127.0.0.1:55555
					System.out.println(socket.getRemoteSocketAddress()); // 접속자의 주소 및 포트 127.0.0.1:12197
					
					System.out.println("===============================================");
					System.out.println();
					System.out.println();
					System.out.println();
					*/
				}catch(IOException e){
					
					System.out.println(e);
					
					try{
						if(socket != null){
							socket.close();
						}
					}catch(IOException ioe){
						System.out.println(ioe);
						
					}finally{
						socket=null;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
}
