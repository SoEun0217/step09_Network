package ex0820.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientChatExam {
	public ClientChatExam() {
		try  {//이렇게 되면 try블럭이 빠져나가면서 socket이 닫힘!
			Socket sk = new Socket("localhost", 8888);
			System.out.println("서버와 채팅 시작하자!!");

			// 읽기 스레드
			new Thread(new Runnable() {
				

				@Override
				public void run() {
					try (BufferedReader br = new BufferedReader(new InputStreamReader(sk.getInputStream()))) {
						String message=null;
						while ((message = br.readLine()) != null) {
							if(message.equals("exit"))break;
							System.out.println(message);
						}

					} catch (Exception e){
						e.printStackTrace();
					}finally {
						System.exit(0);
					}
				}
			}).start();
			
			// 보내기 스레드
			new SendThread(sk, "Client").start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new ClientChatExam();
	}

}
