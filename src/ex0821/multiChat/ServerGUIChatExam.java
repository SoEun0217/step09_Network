package ex0821.multiChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerGUIChatExam {
	ServerSocket server;
	Socket sk;
	List<ClientSocketThread> list = new ArrayList<ClientSocketThread>();

	public ServerGUIChatExam() {
		try {
			server = new ServerSocket(8000);
			while (true) {
				System.out.println("Client ���� �����...");
				sk = server.accept();
				System.out.println(sk.getInetAddress() + "���� ���ӵǾ����ϴ�...");

				ClientSocketThread th = new ClientSocketThread();
				list.add(th);
				th.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// �����ڳ�

	/**
	 * ������ Client�� ���� Thread ���� 
	 * : ���� Thread�� Client �� ������ �����͸� �о ��� Client���� �����͸� �����ؾ��ϴ� ���-run
	 */

	class ClientSocketThread extends Thread {
		PrintWriter pw;// ���ӵ� Ŭ���̾�Ʈ���� ������ ������
		BufferedReader br;// ���ӵ� Ŭ���̾�Ʈ�� ������ ������ �б�
		String name;// ��ȭ��
		String ip;// ������ ip

		public ClientSocketThread() {
			try {
				br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
				pw = new PrintWriter(sk.getOutputStream(), true);
				ip = sk.getInetAddress().toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				name = br.readLine();// ��ȭ��
				sendMessage("[" + name + "]���� �����ϼ̽��ϴ�.");
				String message;
				while ((message = br.readLine()) != null) {
					sendMessage("[" + name + "] " + message);
				}

			} catch (Exception e) {
				// ���� Client�� â�� ������ Socket�� ����
				list.remove(this);
				sendMessage("[" + name + "]���� �����ϼ̽��ϴ�.");
				System.out.println(ip+"�ּ��� [" + name + "]���� �����ϼ̽��ϴ�.\n");
			}

		}

	}// inner class��

	/**
	 * list�ȿ� �ִ� ��� Ŭ���̾�Ʈ���� ������ �����ϱ�(�������)
	 */
	public void sendMessage(String message) {
		for (ClientSocketThread th : list) {
			th.pw.println(message);
		}
	}

	public static void main(String[] args) {
		new ServerGUIChatExam();
	}

}// Ŭ������
