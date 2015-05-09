package com.example.netty;

/**
 * Created by bloodkilory on 15/4/19.
 */
public class AioTimeServer {
	public static void main(String[] args) {
		int port = 9090;
		AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(port);
		new Thread(timeServerHandler, "AIO-AsyncTimeServerHandler-001").start();
	}
}
