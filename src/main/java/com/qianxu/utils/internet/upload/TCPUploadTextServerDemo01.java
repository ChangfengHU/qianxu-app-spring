package com.qianxu.utils.internet.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// 文本文件上传  服务端
/*
 * 请问 : TCP 服务端 .
 * 
 * 数据源 : 套接字通道
 * 目的地 : 硬盘文件
 * 
 * 文本文件上传 - 最高效方式 : 缓冲字符输入流 BufferedReader / 缓冲字符输出流 BufferedWriter 
 */

public class TCPUploadTextServerDemo01 {
	public static void main(String[] args) throws IOException {
		// 1. 创建服务端的套接字
		ServerSocket serverSocket = new ServerSocket(8888);

		// 2. 等待连接
		Socket socket = serverSocket.accept();

		// 3. 从套接字通道中获取输入流 (套接字通道)
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// 4. 创建一个缓冲区输出流 (硬盘文件 项目根目录)
		BufferedWriter bw = new BufferedWriter(new FileWriter("copy.txt"));

		// 5. 读写操作
		String line = null;
		while ((line = br.readLine()) != null) {
			// 三步曲
			System.out.println(line);
			bw.write(line);
			bw.newLine();
			bw.flush();
		}

		// 6. 回送一条反馈信息
		// 需要套接字的输出流
		OutputStream out = socket.getOutputStream();
		out.write("上传成功!".getBytes());
		// 思考: 我们有往套接字通道中写数据吗 ? 有.
		socket.shutdownOutput();

		// 7. 关闭
		bw.close();
		serverSocket.close();
	}
}
