package com.qianxu.utils.internet.chat;

import javax.swing.*;
import java.awt.*;

public class TCPChatClientDemo01 extends JFrame {
	public static void main(String[] args) {
		TCPChatClientDemo01 chat = new TCPChatClientDemo01();
	}

	// ����
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;
	private JButton jb;
	private JPanel jp;

	public TCPChatClientDemo01() {
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
		this.setTitle("QQ������� �ͻ���");
		this.setSize(300, 200);
		this.setLocation(300, 300);
		// ����Ĺر����� һ������ر�, ���˳�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// ��Ϊ
}
