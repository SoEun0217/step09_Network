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
			System.out.println("Client 접속 대기중 입니다...");
			sk = server.accept();// 접속 대기
			System.out.println(sk.getInetAddress()+"님과 채팅을 시작합니다");

			// 읽기 Thread(받기)-inner class로 만들기
			new ReceiveThread().start();
			// 쓰기 Thread(보내기)
			new SendThread(sk, "SERVER").start();

		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {
		new ServerChatExam();
	}

	/**
	 * 클라이언트가 보내오는 데이터를 읽어서 화면에 출력하는 스레드
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
}// 클래스끝
