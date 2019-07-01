package com.qianxu.utils.internet.upload;

import java.io.*;
import java.net.Socket;

/*
 * ������̵����� : ������������
 * 
 * ���� : function
 * 
 * ���� :	method
 * ���췽�� Constructor
 * ʵ������ Instance method
 * 
 */

// TCP �ı��ļ��ϴ�  �ͻ���
/*
 * ����Դ : Ӳ���ļ�
 * Ŀ�ĵ� : �׽���ͨ��
 */

public class TCPUploadTextClientDemo01 {
	public static void main(String[] args) throws IOException {
		// 1. ����һ���ͻ��˵��׽���
		Socket socket = new Socket("127.0.0.1", 8888);

		// 2. ����һ�������ַ������� (Ӳ���ļ�)
		BufferedReader br = new BufferedReader(new FileReader("java.txt"));
		// 3. ����һ�������ַ������ (�׽���ͨ��)
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		// 4. ��д����
		String line = null;
		while ((line = br.readLine()) != null) {
			// ������
			System.out.println(line);
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		// ˼�� : ������ͨ����д�������� ? ��
		socket.shutdownOutput();

		// 5. ��ȡ����˷��ص���Ϣ
		// ��Ҫͨ����������
		InputStream in = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = in.read(buf)) != -1) {
			String str = new String(buf, 0, len);
			System.out.println(str);
		}

		// 6. �ر�
		br.close();
		socket.close();
	}
}
