package com.qianxu.utils.internet.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// TCP ����� : ͼƬ�ϴ�   

/*
 * ����Դ :	�׽���ͨ��
 * Ŀ�ĵ� :	Ӳ���ļ�
 * 
 * ͼƬ��Դ���ֽ�����.		���Ч�ķ�ʽ : BufferedInputStream / BufferedOutputStream
 * 
 * IO �� :
 * �ֽ� : BufferedInputStream / BufferedOutputStream
 * �ַ� : BufferedReader / BufferedWriter
 * 
 */

public class TCPUploadPictureServerDemo01 {
	public static void main(String[] args) throws IOException {
		// 1. ��������˵��׽���
		ServerSocket serverSocket = new ServerSocket(8888);

		// 2. �ȴ�����
		Socket socket = serverSocket.accept();

		// 3. ��װһ�������ֽ������� (�׽���)
		BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
		// 4. ��װһ�������ֽ������ (Ӳ���ļ�)
		File parentFile = new File("H:\\D�̾���\\����java�̳�\\javaee");
		// �жϸ�Ŀ¼�Ƿ����
		if (!parentFile.exists()) {
			// ������, �ʹ���
			parentFile.mkdirs();
		}
		// ʹ�ÿͻ��˵� IP ��ΪͼƬ������
		String ip = socket.getInetAddress().getHostAddress();
		// ���� ip �͸�Ŀ¼����һ�� File ����
		File file = new File(parentFile, ip + ".jpg");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

		// 5. ��д����
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
			bos.flush();
		}

		// 6. ����һ��������Ϣ
		OutputStream out = socket.getOutputStream();
		out.write("�ϴ��ɹ�!".getBytes());
		socket.shutdownOutput();

		// 7. �ر�
		bos.close();
		serverSocket.close();
	}

}
