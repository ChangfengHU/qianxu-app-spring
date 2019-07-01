package com.qianxu.utils.internet.upload;

import java.io.*;
import java.net.Socket;

// TCP 客户端 : 图片上传

/*
 * 数据源 :	硬盘文件
 * 目的地 :	套接字通道
 */

public class TCPUploadPictureClientDemo01 {
	public static void main(String[] args) throws IOException {
		// 1. 创建客户端的套接字
		Socket socket = new Socket("localHost", 8888);

		// 2. 创建缓冲字节输入流 (硬盘文件)
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream("H:\\D盘经典\\基础java教程\\javaee\\day266\\柳岩.jpg"));
		// 3. 创建缓冲字节输出流 (套接字)
		BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

		// 4. 读写操作
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
			bos.flush();
		}
		// 核心代码...
		socket.shutdownOutput();

		// 5. 读取反馈信息
		// 需要套接字输入流
		InputStream in = socket.getInputStream();
		byte[] readBuf = new byte[1024];
		int readLen = -1;
		while ((readLen = in.read(readBuf)) != -1) {
			String str = new String(readBuf, 0, readLen);
			System.out.println(str);
		}

		// 6. 关闭
		bis.close();
		socket.close();
	}
}
