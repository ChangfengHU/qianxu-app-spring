package com.qianxu.utils.internet.upload;

import java.io.*;
import java.net.Socket;

// TCP �ͻ��� : ͼƬ�ϴ�

/*
 * ����Դ :	Ӳ���ļ�
 * Ŀ�ĵ� :	�׽���ͨ��
 */

public class TCPUploadPictureClientDemo01 {
	public static void main(String[] args) throws IOException {
		// 1. �����ͻ��˵��׽���
		Socket socket = new Socket("localHost", 8888);

		// 2. ���������ֽ������� (Ӳ���ļ�)
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream("H:\\D�̾���\\����java�̳�\\javaee\\day266\\����.jpg"));
		// 3. ���������ֽ������ (�׽���)
		BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

		// 4. ��д����
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
			bos.flush();
		}
		// ���Ĵ���...
		socket.shutdownOutput();

		// 5. ��ȡ������Ϣ
		// ��Ҫ�׽���������
		InputStream in = socket.getInputStream();
		byte[] readBuf = new byte[1024];
		int readLen = -1;
		while ((readLen = in.read(readBuf)) != -1) {
			String str = new String(readBuf, 0, readLen);
			System.out.println(str);
		}

		// 6. �ر�
		bis.close();
		socket.close();
	}
}
