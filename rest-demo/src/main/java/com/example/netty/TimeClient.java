package com.example.netty;

import java.io.*;
import java.net.Socket;

/**
 * Created by bloodkilory on 15/4/19.
 */
public class TimeClient {
	public static void main(String[] args) {
		int port = 9090;
		try(Socket socket = new Socket("127.0.0.1", port);
		    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
			out.println("QUERY TIME ORDER");
			System.out.println("Send order to server succeed.");
			String resp = in.readLine();
			System.out.println("Now is: " + resp);

		} catch(Exception e) {
			//
		}
	}
}
