package com.example.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 工作线程
 */
class TimeServerHandler implements Runnable {

	private Socket socket;

	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try(BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		    PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
			String currentTime;
			String body;
			while(true) {
				body = in.readLine();
				if(body == null) {
					break;
				}
				System.out.println("The time server received order: " + body);
				currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
						new Date(System.currentTimeMillis()).toString() : "BAD " +
						"ORDER";
				out.println(currentTime);
			}
		} catch(IOException e) {
			//
		} finally {
			if(this.socket != null) {
				try {
					this.socket.close();
				} catch(IOException e1) {
					e1.printStackTrace();
				}
				this.socket = null;
			}
		}
	}
}
