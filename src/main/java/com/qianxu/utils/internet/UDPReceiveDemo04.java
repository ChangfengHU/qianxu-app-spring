package com.qianxu.utils.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

// UDP发送端实现键盘录入,接收端实现循环读取

public class UDPReceiveDemo04 {
	public static void main(String[] args) throws IOException {
		// 1. 创建一个接收端的码头
		DatagramSocket datagramSocket = new DatagramSocket(8888);

		// 2. 创建一个接收端的集装箱
		byte[] buf = new byte[1024]; // 64kb
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);

		while (true) {
			// 3. 码头接收集装箱
			datagramSocket.receive(datagramPacket);

			// 4. 解析数据
			String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.println("接收到数据:"+str);
			// 判断str的内容 exit 退出
			if ("exit".equals(str)) { // str.equals("exit")
				break;
			}

			// 打印输出查看
			String ip = datagramPacket.getAddress().getHostAddress();
			System.out.println(ip + " :  " + str);
		}

		// 5. 关闭码头
		datagramSocket.close();
	}
}
