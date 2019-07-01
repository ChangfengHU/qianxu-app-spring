package com.qianxu.utils.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// TCP �ͻ��˵ĳ���

public class TCPClientDemo01 {
	public static void main(String[] args) throws IOException {
		// �ͻ��˲����ڷ����û�п�������֮ǰ��������, �����׳� : Connection refused: connect ���ӱ�����.

		// 1. �����ͻ��˵��׽��� (��������)
		Socket socket = new Socket("localHost", 10010);

		// 2. ��ȡ�׽��ֵ������, ��ͨ����д����
		OutputStream out = socket.getOutputStream();
		byte[] data = "Hello TCP, I am coming to you. I love you.".getBytes();
		out.write(data);
		// �ر��׽��ֵ������
		socket.shutdownOutput();

		// 3. ��ȡ�׽��ֵ�������, ��ͨ���ж�ȡ����
		InputStream in = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = in.read(buf)) != -1) {
			String str = new String(buf, 0, len);
			System.out.println("����˻��͵�����Ϊ : " + str);
		}

		// 4. �رտͻ����׽���
		socket.close();
	}
}
