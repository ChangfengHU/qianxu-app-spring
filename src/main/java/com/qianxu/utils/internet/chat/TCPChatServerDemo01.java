package com.qianxu.utils.internet.chat;

import javax.swing.*;
import java.awt.*;

// 第一步: 搭建界面
/*
 * 1. 需要一个窗口, TCPChatServerDemo01 类成为一个 `窗体类`. 先继承 JFrame, 此时这个类就是一个窗体类.
 * 2. 定义窗体需要的组件.
 * 3. 在构造方法中初始化定义的组件
 * 4. 将组件添加到 `窗体` 中.			this 就是当前窗体类的对象. 所有, 将组件添加到 this 中.
 * 5. 设置当前窗体的属性.  (标题, 大小, 位置, 关闭, 显示...)
 */

public class TCPChatServerDemo01 extends JFrame {
	public static void main(String[] args) {
		TCPChatServerDemo01 chat = new TCPChatServerDemo01();
	}

	// 属性
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;
	private JButton jb;
	private JPanel jp;

	public TCPChatServerDemo01() {
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
		this.setTitle("QQ聊天程序 服务端");
		this.setSize(300, 200);
		this.setLocation(300, 300);
		// 窗体的关闭设置 一旦窗体关闭, 则退出程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// 行为
}
