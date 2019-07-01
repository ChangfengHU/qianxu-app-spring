package com.qianxu.utils.internet.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// TCP 服务端 : 图片上传   

/*
 * 数据源 :	套接字通道
 * 目的地 :	硬盘文件
 * 
 * 图片资源是字节数据.		最高效的方式 : BufferedInputStream / BufferedOutputStream
 * 
 * IO 流 :
 * 字节 : BufferedInputStream / BufferedOutputStream
 * 字符 : BufferedReader / BufferedWriter
 * 
 */

public class TCPUploadPictureServerDemo01 {
	public static void main(String[] args) throws IOException {
		// 1. 创建服务端的套接字
		ServerSocket serverSocket = new ServerSocket(8888);

		// 2. 等待连接
		Socket socket = serverSocket.accept();

		// 3. 包装一个缓冲字节输入流 (套接字)
		BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
		// 4. 包装一个缓冲字节输出流 (硬盘文件)
		File parentFile = new File("H:\\D盘经典\\基础java教程\\javaee");
		// 判断父目录是否存在
		if (!parentFile.exists()) {
			// 不存在, 就创建
			parentFile.mkdirs();
		}
		// 使用客户端的 IP 作为图片的名称
		String ip = socket.getInetAddress().getHostAddress();
		// 根据 ip 和父目录创建一个 File 对象
		File file = new File(parentFile, ip + ".jpg");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

		// 5. 读写操作
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
			bos.flush();
		}

		// 6. 回送一条反馈信息
		OutputStream out = socket.getOutputStream();
		out.write("上传成功!".getBytes());
		socket.shutdownOutput();

		// 7. 关闭
		bos.close();
		serverSocket.close();
	}

}
