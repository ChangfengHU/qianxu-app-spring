package com.qianxu.utils.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// UDP 接收端

public class UDPReceiveDemo03 {
	public static void main(String[] args) throws IOException {
		// Address already in use: Cannot bind 地址已经被使用, 不能再次绑定.

		// 1. 创建接收端的码头
		DatagramSocket datagramSocket = new DatagramSocket(8888);

		// 2. 创建接收端的集装箱
		// 准备参数
		byte[] buf = new byte[1024];
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);

		// 3. 使用码头接收集装箱
		datagramSocket.receive(datagramPacket);

		// 4. 解析数据
		byte[] data = datagramPacket.getData();
		int length = datagramPacket.getLength();
		String str = new String(data, 0, length);
		System.out.println(str);

		// 5. 回送反馈信息
		// 回送信息需要一个发送的集装箱
		InetAddress address = datagramPacket.getAddress();
		int port = datagramPacket.getPort();
		byte[] info = "我已稳稳的收到你发来的数据, 请放心".getBytes();
		DatagramPacket dp = new DatagramPacket(info, info.length, address, port);
		// 发送
		datagramSocket.send(dp);

		// 6. 关闭码头
		datagramSocket.close();
	}
}
