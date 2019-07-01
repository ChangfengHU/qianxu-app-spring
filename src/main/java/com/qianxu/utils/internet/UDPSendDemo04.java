package com.qianxu.utils.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// UDP发送端实现键盘录入,接收端实现循环读取

public class UDPSendDemo04 {
	public static void main(String[] args) throws IOException {
		// 1. 创建一个发送端的码头
		DatagramSocket datagramSocket = new DatagramSocket();

		// 2. 创建一个发送端的集装箱
		// 准备参数 Scanner / 一行一行法. BufferedReader System.in 输出流
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		// IP对象
		InetAddress address = InetAddress.getByName("localHost");
		int port = 8888;
		while ((line = br.readLine()) != null) { // null 空对象 null 字符串
			// 决定一个结束条件
			if ("null".equals(line)) { // exit
				break;
			}
			System.out.println("发送数据:"+line);
			DatagramPacket datagramPacket = new DatagramPacket(line.getBytes(), line.getBytes().length, address, port);
			// 3. 使用码头发送集装箱
			datagramSocket.send(datagramPacket);
		}

		// 4. 关闭码头
		datagramSocket.close();
	}
}
