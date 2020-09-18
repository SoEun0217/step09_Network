package ex0820.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientChatExam {
	public ClientChatExam() {
		try  {//�̷��� �Ǹ� try���� ���������鼭 socket�� ����!
			Socket sk = new Socket("localhost", 8888);
			System.out.println("������ ä�� ��������!!");

			// �б� ������
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
			
			// ������ ������
			new SendThread(sk, "Client").start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new ClientChatExam();
	}

}
