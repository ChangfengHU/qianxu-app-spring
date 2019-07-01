package com.qianxu.utils.internet.chat;

import javax.swing.*;
import java.awt.*;

public class TCPChatClientDemo01 extends JFrame {
	public static void main(String[] args) {
		TCPChatClientDemo01 chat = new TCPChatClientDemo01();
	}

	// 属性
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;
	private JButton jb;
	private JPanel jp;

	public TCPChatClientDemo01() {
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
	}

	// 行为
}
