package com.qianxu.utils.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// UDP 发送端

public class UDPSendDemo03 {
	public static void main(String[] args) throws IOException {
		// 1. 创建一个发送端的码头
		DatagramSocket datagramSocket = new DatagramSocket();

		// 2. 创建一个发送端的集装箱
		byte[] buf = "Hello UDP, I am coming to you again!".getBytes();
		InetAddress address = InetAddress.getByName("127.0.0.1");
		int port = 8888;
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, address, port);

		// 3. 发送数据
		datagramSocket.send(datagramPacket);

		// 4. 接收回送信息
		// 接收需要一个接收的集装箱
		byte[] buf2 = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf2, buf2.length);
		// 接收 (码头来接收集装箱)
		datagramSocket.receive(dp);

		// 5. 解析数据
		byte[] data = dp.getData();
		int len = dp.getLength();
		String str = new String(data, 0, len);
		System.out.println("接收端返回的数据: " + str);

		// 6. 关闭码头
		datagramSocket.close();
	}
}
