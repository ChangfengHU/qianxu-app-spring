package com.qianxu.utils.internet.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class TCPChatClientDemo2 extends JFrame implements ActionListener {
	public static void main(String[] args) {
		TCPChatClientDemo2 chat = new TCPChatClientDemo2();
	}

	// 属性
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;
	private JButton jb;
	private JPanel jp;

	// 字符缓冲输出流 (套接字通道)
	BufferedWriter bw = null;

	public TCPChatClientDemo2() {
		// 初始化组件
		jta = new JTextArea();
		// 设置文本域不可编辑
		jta.setEditable(false);
		jsp = new JScrollPane(jta); // 将文本域作为参数传入到滚动框中, 说明: 文本域具备了滚动功能

		jtf = new JTextField(10);
		jb = new JButton("发送");
		jp = new JPanel(); // 面板
		// 将文本框和按钮加入到面板中, 作为一个整体
		jp.add(jtf);
		jp.add(jb);

		// 将组件添加到 `窗体` 中
		this.add(jsp, BorderLayout.CENTER); // 将jsp滚动面板添加到当前窗体中, 并同时进行边界布局.
											// 布局在窗体的中心位置
		this.add(jp, BorderLayout.SOUTH);

		// 设置当前窗体的属性. (标题, 大小, 位置, 关闭, 显示...)
		this.setTitle("QQ聊天程序 客户端");
		this.setSize(300, 200);
		this.setLocation(300, 300);
		// 窗体的关闭设置 一旦窗体关闭, 则退出程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		// 需要给发送按钮绑定一个监听点击事件
		jb.addActionListener(this);

		/**************** 网络通信代码 ********************/
		try {
			// 1. 创建一个客户端的套接字
			Socket socket = new Socket("localHost", 8888);
			// 2. 客户端需要获取通道输入流, 读取数据, 显示到 `文本域` 中
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 为发送按钮准备高效的缓冲字符输出流. (目的地: 套接字通道中)
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 3. 读取
			String line = null;
			while ((line = br.readLine()) != null) {
				// 显示到文本域中
				jta.append(line + System.lineSeparator());
			}
			// 4. 关闭
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 该方法何时被执行? 当发送按钮被点击之后, 系统会自动调用该方法.
	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("按钮被点击了...");
		// 需求 : 将数据发送到套接字通道中.
		// 1. 获取文本框中的数据
		String text = this.jtf.getText(); // hello
		// 1.2 拼接字符内容
		text = "客户端 对 服务端说:" + text + System.lineSeparator();
		try {
			// 2. 发送 (向通道发送, 通道)
			bw.write(text);
			// 刷新
			bw.flush(); // 不能忘, 否则数据无法传输成功!
			// 3. 将内容显示到服务端的文本域中
			this.jta.append(text);
			// 4. 清空文本框中的内容
			this.jtf.setText("");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// 行为
}
