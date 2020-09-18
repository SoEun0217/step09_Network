package ex0820.chat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client or Server�� ��������� �����͸� ������(����)
 */
public class SendThread extends Thread {
	Socket socket;
	String name;
	public SendThread(Socket socket, String name) {
		super(name);// Thread�� �̸� ����
		this.socket = socket;
		this.name = name;
	}

	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {
			// Ű�����Է¹ޱ�
			Scanner sc = new Scanner(System.in);
			String data = null;
			while ((data = sc.nextLine()) != null) {
				if(data.equals("exit")) {
					pw.println(data);
				}
				pw.println(name+"�� ���� ���� : "+data);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
