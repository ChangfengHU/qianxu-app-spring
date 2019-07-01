package com.qianxu.utils.internet.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class TCPChatClientDemo3 extends JFrame implements ActionListener, KeyListener {
	public static void main(String[] args) {
		TCPChatClientDemo3 chat = new TCPChatClientDemo3();
	}

	// ����
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;
	private JButton jb;
	private JPanel jp;

	// �ַ���������� (�׽���ͨ��)
	private BufferedWriter bw = null;

	public TCPChatClientDemo3() {
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

		// ��Ҫ�����Ͱ�ť��һ����������¼�
		jb.addActionListener(this);
		// ��Ҫ���ı����һ�����̵���¼�
		jtf.addKeyListener(this);

		/**************** ����ͨ�Ŵ��� ********************/
		try {
			// 1. ����һ���ͻ��˵��׽���
			Socket socket = new Socket("localHost", 8888);
			// 2. �ͻ�����Ҫ��ȡͨ��������, ��ȡ����, ��ʾ�� `�ı���` ��
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Ϊ���Ͱ�ť׼����Ч�Ļ����ַ������. (Ŀ�ĵ�: �׽���ͨ����)
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 3. ��ȡ
			String line = null;
			while ((line = br.readLine()) != null) {
				// ��ʾ���ı�����
				jta.append(line + System.lineSeparator());
			}
			// 4. �ر�
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �÷�����ʱ��ִ��? �����Ͱ�ť�����֮��, ϵͳ���Զ����ø÷���.
	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("��ť�������...");
		sendData();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// ��������
			sendData();
		}
	}

	// ��������
	private void sendData() {
		// ���� : �����ݷ��͵��׽���ͨ����.
		// 1. ��ȡ�ı����е�����
		String text = this.jtf.getText(); // hello
		// 1.2 ƴ���ַ�����
		text = "�ͻ��� �� �����˵:" + text + System.lineSeparator();
		try {
			// 2. ���� (��ͨ������, ͨ��)
			bw.write(text);
			// ˢ��
			bw.flush(); // ������, ���������޷�����ɹ�!
			// 3. ��������ʾ������˵��ı�����
			this.jta.append(text);
			// 4. ����ı����е�����
			this.jtf.setText("");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// ��Ϊ
}
