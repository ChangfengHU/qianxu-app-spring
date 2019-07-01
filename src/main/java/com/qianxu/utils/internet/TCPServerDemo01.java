package com.qianxu.utils.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// TCP ����˳���

public class TCPServerDemo01 {
	public static void main(String[] args) throws IOException {
		// 1. ����������׽���
		ServerSocket serverSocket = new ServerSocket(80);

		// 2. ������׽��ֵȴ� / ���� ���� (��������)
       while (true){
		   Socket socket = serverSocket.accept();
		   // 3. ��ȡ�׽���ͨ����������, ��ȡ����
		   InputStream in = socket.getInputStream();
		   byte[] buf = new byte[1024];
		   int len = -1;
		   // ��ȡ�ͻ��˵� IP ��ַ
		   String ip = socket.getInetAddress().getHostAddress();
		   // while ((len = in.read(buf)) != -1) IOѧϰ�б�д�Ĵ���. IOѧϰʱ���ļ��ж�ȡ����.
		   // �ļ��п��Զ�ȡ�� -1.
		   // ���ڲ�һ��, �� `�׽���` ͨ���ж�ȡ����. ��ȡ���� -1.
		   while ((len = in.read(buf)) != -1) {
			   String str = new String(buf, 0, len);
			   System.out.println(ip + " : " + str);
		   }

		   // 4. ���ͻ��˻���һ��������Ϣ (��Ҫ�õ��׽���ͨ���������)
		   OutputStream out = socket.getOutputStream();
		   out.write("�ϴ��ɹ�!".getBytes());
		   // shutdown �ر������. ���� : ֻҪ���׽���ͨ������������, ���һ��Ҫ�ر��׽��������. �����ͻ�����ѭ����ȡʱ,���ܹ���ȡ��
		   // -1.
		   socket.shutdownOutput();
	   }
//		serverSocket.close();
		// 5. �رշ�����׽���
	}
}
