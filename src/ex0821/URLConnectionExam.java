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
			byte b []=new byte[100];//bis.available()�� �ص� �뷮�� �ʹ� Ŀ�� �� ���� ����
			//�ݺ����� ������ ������ ���������� ������ش�.100�� �����Ʈ�� ������ ���ϴ� ��(����)
			while(bis.read(b)!=-1) {
			
			bw.write(b);
			bw.flush();
			}
			System.out.println("�ٿ�ε� �Ϸ�...");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		new URLConnectionExam();
	}
}
