package ex0821;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLConnectionExam {
	public URLConnectionExam(){
		try {
			/*
			 * URL url=new URL("https://www.naver.com/"); InputStream is=url.openStream();
			 * BufferedReader br=new BufferedReader(new InputStreamReader(is)); String
			 * data=null; BufferedWriter bw=new BufferedWriter(new
			 * FileWriter("src/ex0821/daum.txt")); while((data=br.readLine())!=null){
			 * System.out.println(data); bw.write(data); bw.newLine(); }
			 */
			URL url=new URL("http://192.168.0.136/Edu/SW/epp351.exe");
			BufferedOutputStream bw=new BufferedOutputStream(new FileOutputStream("src/ex0821/epp351.exe"));
			BufferedInputStream bis=new BufferedInputStream(url.openStream());
			byte b []=new byte[100];//bis.available()을 해도 용량이 너무 커서 다 읽지 못함
			//반복문을 돌려서 읽을게 없을때까지 만들어준다.100은 몇바이트씩 읽을지 정하는 것(임의)
			while(bis.read(b)!=-1) {
			
			bw.write(b);
			bw.flush();
			}
			System.out.println("다운로드 완료...");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		new URLConnectionExam();
	}
}
