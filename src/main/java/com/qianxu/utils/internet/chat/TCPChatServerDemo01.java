package com.qianxu.utils.internet.chat;

import javax.swing.*;
import java.awt.*;

// ��һ��: �����
/*
 * 1. ��Ҫһ������, TCPChatServerDemo01 ���Ϊһ�� `������`. �ȼ̳� JFrame, ��ʱ��������һ��������.
 * 2. ���崰����Ҫ�����.
 * 3. �ڹ��췽���г�ʼ����������
 * 4. �������ӵ� `����` ��.			this ���ǵ�ǰ������Ķ���. ����, �������ӵ� this ��.
 * 5. ���õ�ǰ���������.  (����, ��С, λ��, �ر�, ��ʾ...)
 */

public class TCPChatServerDemo01 extends JFrame {
	public static void main(String[] args) {
		TCPChatServerDemo01 chat = new TCPChatServerDemo01();
	}

	// ����
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;
	private JButton jb;
	private JPanel jp;

	public TCPChatServerDemo01() {
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
	}

	// ��Ϊ
}
