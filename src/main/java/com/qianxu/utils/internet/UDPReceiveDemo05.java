package com.qianxu.utils.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

// UDP 实现互动聊天

public class UDPReceiveDemo05 {
	public static void main(String[] args) throws IOException {
		// 1. 创建一个码头
		DatagramSocket datagramSocket = new DatagramSocket(9999);

		// 2. 实现键盘录入不断发送数据 (发送任务线程, 作用: 键盘录入发送数据)
		String ip = "127.0.0.1";
		int port = 9999; // 注意 : 这是对象计算机的 `端口号`
		SendTask sendTask = new SendTask(datagramSocket, ip, port);
		Thread sendThread = new Thread(sendTask, "发送线程");
		sendThread.start();

		// 3. 实现循环接收数据 (接收任务线程, 作用 : 循环接收数据)
		ReceiveTask receiveTask = new ReceiveTask(datagramSocket);
		Thread receiveThread = new Thread(receiveTask, "接收线程");
		receiveThread.start();
	}
}

// 定义一个接收任务
class ReceiveTask implements Runnable {
	// 属性
	private DatagramSocket ds;

	public ReceiveTask(DatagramSocket ds) {
		this.ds = ds;
	}

	// 行为
	@Override
	public void run() {
		// 循环接收
		byte[] buf = new byte[1024];
		while (true) {
			// 接收需要接收的集装箱
			DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
			// 使用码头接收数据
			// Unhandled exception type IOException 未处理异常类型 IOException
			try {
				ds.receive(datagramPacket); // receive 是阻塞方法
				// 解析数据
				String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
				String ip = datagramPacket.getAddress().getHostAddress();
				System.out.println(ip + " : " + str);
				// 约定结束的条件 ....
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

// 定义一个发送任务
class SendTask implements Runnable {
	// 属性
	private DatagramSocket ds; // The value of the field SendTask.ds is not used
								// 该SendTask.ds属性没有被使用
	private String ip;
	private int port;

	// 构造方法重载
	public SendTask(DatagramSocket ds, String ip, int port) {
		this.ds = ds;
		this.ip = ip;
		this.port = port;
	}

	// 行为
	@Override
	public void run() {
		// 键盘录入发送数据
		Scanner sc = new Scanner(System.in);
		String line = null;
		// 定义一个 IP 对象

		while ((line = sc.nextLine()) != null) {
			// 约定一个结束条件
			if ("exit".equals(line)) {
				break;
			}
			try {
				InetAddress address = InetAddress.getByName(ip);
				// line 中就是要发送的数据
				// 定义一个发送的集装箱
				// The local variable address may not have been initialized 本地变量
				// address 可能没有被初始化
				DatagramPacket datagramPacket = new DatagramPacket(line.getBytes(), line.getBytes().length, address,
						port);
				// 使用码头发送集装箱
				ds.send(datagramPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 关闭 Scanner 对象
		sc.close();
	}
}
