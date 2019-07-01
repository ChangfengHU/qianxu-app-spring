package com.qianxu.utils.internet.upload;

import java.io.*;
import java.net.Socket;

/*
 * 面向过程的语言 : 面向对象的语言
 * 
 * 函数 : function
 * 
 * 方法 :	method
 * 构造方法 Constructor
 * 实例方法 Instance method
 * 
 */

// TCP 文本文件上传  客户端
/*
 * 数据源 : 硬盘文件
 * 目的地 : 套接字通道
 */

public class TCPUploadTextClientDemo01 {
	public static void main(String[] args) throws IOException {
		// 1. 创建一个客户端的套接字
		Socket socket = new Socket("127.0.0.1", 8888);

		// 2. 创建一个缓冲字符输入流 (硬盘文件)
		BufferedReader br = new BufferedReader(new FileReader("java.txt"));
		// 3. 创建一个缓冲字符输出流 (套接字通道)
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		// 4. 读写操作
		String line = null;
		while ((line = br.readLine()) != null) {
			// 三步曲
			System.out.println(line);
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		// 思考 : 你有往通道中写入数据吗 ? 有
		socket.shutdownOutput();

		// 5. 读取服务端返回的信息
		// 需要通道的输入流
		InputStream in = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = in.read(buf)) != -1) {
			String str = new String(buf, 0, len);
			System.out.println(str);
		}

		// 6. 关闭
		br.close();
		socket.close();
	}
}
