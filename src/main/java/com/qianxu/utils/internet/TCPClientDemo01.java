package com.qianxu.utils.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// TCP 客户端的程序

public class TCPClientDemo01 {
	public static void main(String[] args) throws IOException {
		// 客户端不能在服务端没有开启服务之前建立连接, 否则抛出 : Connection refused: connect 连接被决绝.

		// 1. 创建客户端的套接字 (尝试连接)
		Socket socket = new Socket("localHost", 10010);

		// 2. 获取套接字的输出流, 向通道中写数据
		OutputStream out = socket.getOutputStream();
		byte[] data = "Hello TCP, I am coming to you. I love you.".getBytes();
		out.write(data);
		// 关闭套接字的输出流
		socket.shutdownOutput();

		// 3. 获取套接字的输入流, 从通道中读取数据
		InputStream in = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = in.read(buf)) != -1) {
			String str = new String(buf, 0, len);
			System.out.println("服务端回送的数据为 : " + str);
		}

		// 4. 关闭客户端套接字
		socket.close();
	}
}
