package ex0820.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChatExam {
	Socket sk;
	ServerSocket server;
	public ServerChatExam() {
		try  {
			server = new ServerSocket(8888);
			System.out.println("Client ���� ����� �Դϴ�...");
			sk = server.accept();// ���� ���
			System.out.println(sk.getInetAddress()+"�԰� ä���� �����մϴ�");

			// �б� Thread(�ޱ�)-inner class�� �����
			new ReceiveThread().start();
			// ���� Thread(������)
			new SendThread(sk, "SERVER").start();

		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {
		new ServerChatExam();
	}

	/**
	 * Ŭ���̾�Ʈ�� �������� �����͸� �о ȭ�鿡 ����ϴ� ������
	 */
	class ReceiveThread extends Thread {
		@Override
		public void run() {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(sk.getInputStream()))) {
				String message;
				while((message=br.readLine())!=null) {
					if(message.equals("exit")) {
						break;
					}
					System.out.println(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				System.exit(0);
			}
		}

	}
}// Ŭ������
