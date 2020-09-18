package ex0820.chat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client or Server가 상대측에게 데이터를 보내기(전송)
 */
public class SendThread extends Thread {
	Socket socket;
	String name;
	public SendThread(Socket socket, String name) {
		super(name);// Thread의 이름 설정
		this.socket = socket;
		this.name = name;
	}

	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {
			// 키보드입력받기
			Scanner sc = new Scanner(System.in);
			String data = null;
			while ((data = sc.nextLine()) != null) {
				if(data.equals("exit")) {
					pw.println(data);
				}
				pw.println(name+"가 보낸 내용 : "+data);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
