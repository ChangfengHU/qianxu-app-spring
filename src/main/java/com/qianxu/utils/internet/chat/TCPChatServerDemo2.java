package com.qianxu.utils.internet.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// �ڶ���: ʵ�ֻ�������

public class TCPChatServerDemo2 extends JFrame implements ActionListener {

	// The serializable class TCPChatServerDemo2 does not declare a static final
	// serialVersionUID field of type long
	// The type TCPChatServerDemo2 must implement the inherited abstract method
	// ��ǰ�����ʵ�ּ̳еĳ��󷽷�
	public static void main(String[] args) {
		TCPChatServerDemo2 chat = new TCPChatServerDemo2();
	}

	// ����
	/**
	 * �Զ����ɵ����а汾��
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;
	private JButton jb;
	private JPanel jp;

	// ����һ�������ַ���������� (�׽���ͨ��) Ϊ�˸����Ͱ�ťʹ��
	BufferedWriter bw = null;

	public TCPChatServerDemo2() {
		// ��ʼ�����
		jta = new JTextArea();
		// �����ı��򲻿ɱ༭
		jta.setEditable(false);
		jsp = new JScrollPane(jta); // ���ı�����Ϊ�������뵽��������, ˵��: �ı���߱��˹�������

		jtf = new JTextField(10);
		jb = new JButton("����");
		jp = new JPanel(); // ���
		// ���ı���Ͱ�ť���뵽�����, ��Ϊһ������
		jp.add(jtf);
		jp.add(jb);

		// �������ӵ� `����` ��
		this.add(jsp, BorderLayout.CENTER); // ��jsp���������ӵ���ǰ������, ��ͬʱ���б߽粼��.
											// �����ڴ��������λ��
		this.add(jp, BorderLayout.SOUTH);

		// ���õ�ǰ���������. (����, ��С, λ��, �ر�, ��ʾ...)
		this.setTitle("QQ������� �����");
		this.setSize(300, 200);
		this.setLocation(300, 300);
		// ����Ĺر����� һ������ر�, ���˳�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		// Ϊ���Ͱ�ť��һ���¼�����
		// �����ǰ����Ϊ��ť�����֮��Ĵ����¼�, ��ô��ǰ�����Ҫ����һ������. ʵ�� ActionListener
		jb.addActionListener(this); // this ���������. ��ζ��������ͱ����, this������.
									// ��ǰ��������ť��������¼�.

		/*************** ����ͨ�Ŵ��� ******************/
		try {
			// 1. ��������˵��׽���
			ServerSocket serverSocket = new ServerSocket(8888);
			// 2. �ȴ�����
			Socket socket = serverSocket.accept();
			// 3. ��ȡ�׽���ͨ����������, Ϊ����߳����ִ��Ч��, ��װΪ BufferedReader (�ַ�)
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// ˵�� : ����������Ŀ����Ϊ���ڷ��Ͱ�ť�����֮��, �������׽���ͨ���д�������
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 4. ��ȡ����
			String line = null;
			while ((line = br.readLine()) != null) {
				// line �о���������Ҫ������, ��Ҫ��ʾ�� `�ı���` ��
				jta.append(line + System.lineSeparator()); // append ƴ��
			}
			// 5. �ر���Դ
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// �÷�����ʱ��ִ��? �����Ͱ�ť�����֮��, ϵͳ���Զ����ø÷���.
	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("��ť�������...");
		// ���� : �����ݷ��͵��׽���ͨ����.
		// 1. ��ȡ�ı����е�����
		String text = this.jtf.getText(); // hello
		// 1.2 ƴ���ַ�����
		text = "����� �� �ͻ���˵:" + text + System.lineSeparator();
		try {
			// 2. ���� (��ͨ������, ͨ��) // ����� �� �ͻ���˵: hello \r\n
			bw.write(text);
			// ˢ��
			bw.flush();
			// 3. ��������ʾ������˵��ı�����
			this.jta.append(text);
			// 4. ����ı����е�����
			this.jtf.setText("");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// ��Ϊ
}
