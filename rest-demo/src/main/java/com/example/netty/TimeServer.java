package com.example.netty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by bloodkilory on 15/4/19.
 */
public class TimeServer {
	public static void main(String[] args) throws IOException{
		int port = 9090;
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port: " + port);
			Socket socket;
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
			while(true) {
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		} catch(Exception e) {
			//
		} finally {
			if(server != null) {
				System.out.println("Time server will be close now.");
				server.close();
				server = null;
			}
		}
	}
}