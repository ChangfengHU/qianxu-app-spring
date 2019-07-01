package com.qianxu.utils.internet.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// 第三步: 按下回车实现发送按钮的功能

public class TCPChatServerDemo3 extends JFrame implements ActionListener, KeyListener {

	public static void main(String[] args) {
		TCPChatServerDemo3 chat = new TCPChatServerDemo3();
	}

	// 属性
	/**
	 * 自动生成的序列版本号
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;
	private JButton jb;
	private JPanel jp;

	// 定义一个缓冲字符输出流属性 (套接字通道) 为了给发送按钮使用
	private BufferedWriter bw = null;

	public TCPChatServerDemo3() {
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

		// 为发送按钮绑定一个事件监听
		// 如果当前类作为按钮被点击之后的处理事件, 那么当前类必须要满足一个条件. 实现 ActionListener
		jb.addActionListener(this); // this 代表类对象. 意味着如果发送被点击, this来处理.
									// 当前类来处理按钮被点击的事件.
		// 为文本框绑定监听点击事件
		// 表示一旦键盘被点击了, 就会来到当前类中寻找相当于的键盘处理方法
		jtf.addKeyListener(this);

		/*************** 网络通信代码 ******************/
		try {
			// 1. 创建服务端的套接字
			ServerSocket serverSocket = new ServerSocket(8888);
			// 2. 等待连接
			Socket socket = serverSocket.accept();
			// 3. 获取套接字通道的输入流, 为了提高程序的执行效果, 包装为 BufferedReader (字符)
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 说明 : 以下这句代码目的是为了在发送按钮被点击之后, 可以向套接字通道中传输数据
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 4. 读取数据
			String line = null;
			while ((line = br.readLine()) != null) {
				// line 中就是我们需要的数据, 需要显示到 `文本域` 中
				jta.append(line + System.lineSeparator()); // append 拼接
			}
			// 5. 关闭资源
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 该方法何时被执行? 当发送按钮被点击之后, 系统会自动调用该方法.
	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("按钮被点击了...");
		// 调用发送数据的私有方法
		sendData();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("keyPressed ... " + e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_ENTER) { // 10 阅读性太差
			// System.out.println("发送数据 ...");
			// 调用发送数据的私有方法
			sendData();
		}
	}

	// 发送数据
	private void sendData() {
		// 需求 : 将数据发送到套接字通道中.
		// 1. 获取文本框中的数据
		String text = this.jtf.getText(); // hello
		// 1.2 拼接字符内容
		text = "服务端 对 客户端说:" + text + System.lineSeparator();
		try {
			// 2. 发送 (向通道发送, 通道) // 服务端 对 客户端说: hello \r\n
			bw.write(text);
			// 刷新
			bw.flush();
			// 3. 将内容显示到服务端的文本域中
			this.jta.append(text);
			// 4. 清空文本框中的内容
			this.jtf.setText("");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	// 行为
}
