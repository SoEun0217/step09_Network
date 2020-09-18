package ex0820;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientExam {

	public ClientExam() {
		// 서버에 접속
		try (Socket sk = new Socket("localhost", 8000)) {// 127.0.0.1이 내 ip이다
			// 서버에게 데이터 보내기
			PrintWriter pw=new PrintWriter(sk.getOutputStream(),true);
			pw.println("헬로우");
			
			//서버가 보내온 데이터 읽기
			BufferedReader br=new BufferedReader(new InputStreamReader(sk.getInputStream()));
			String message=br.readLine();
			System.out.println("서버가 보내온 내용 : "+message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new ClientExam();
	}

}
