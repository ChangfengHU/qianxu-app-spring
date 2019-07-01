package com.qianxu.utils.internet.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// �ı��ļ��ϴ�  �����
/*
 * ���� : TCP ����� .
 * 
 * ����Դ : �׽���ͨ��
 * Ŀ�ĵ� : Ӳ���ļ�
 * 
 * �ı��ļ��ϴ� - ���Ч��ʽ : �����ַ������� BufferedReader / �����ַ������ BufferedWriter 
 */

public class TCPUploadTextServerDemo01 {
	public static void main(String[] args) throws IOException {
		// 1. ��������˵��׽���
		ServerSocket serverSocket = new ServerSocket(8888);

		// 2. �ȴ�����
		Socket socket = serverSocket.accept();

		// 3. ���׽���ͨ���л�ȡ������ (�׽���ͨ��)
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// 4. ����һ������������� (Ӳ���ļ� ��Ŀ��Ŀ¼)
		BufferedWriter bw = new BufferedWriter(new FileWriter("copy.txt"));

		// 5. ��д����
		String line = null;
		while ((line = br.readLine()) != null) {
			// ������
			System.out.println(line);
			bw.write(line);
			bw.newLine();
			bw.flush();
		}

		// 6. ����һ��������Ϣ
		// ��Ҫ�׽��ֵ������
		OutputStream out = socket.getOutputStream();
		out.write("�ϴ��ɹ�!".getBytes());
		// ˼��: ���������׽���ͨ����д������ ? ��.
		socket.shutdownOutput();

		// 7. �ر�
		bw.close();
		serverSocket.close();
	}
}
