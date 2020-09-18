package ex0821.multiChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUIChatExam extends JFrame {
	JTextArea textArea = new JTextArea();
	JScrollPane jsp = new JScrollPane(textArea);
	JTextField text = new JTextField();

	Socket sk;
	BufferedReader br;
	PrintWriter pw;

	public ClientGUIChatExam() {

		// Container Layout�����ϱ�
		Container con = super.getContentPane();

		// Component���� �߰��ϱ�
		con.add(jsp, BorderLayout.CENTER);
		con.add(text, BorderLayout.SOUTH);

		// �ɼǼ���
		textArea.setFocusable(false);// Ŀ���� ������ ����(Ŀ�� ��Ȱ��ȭ)
		textArea.setBackground(Color.GRAY);// ���� ����ֱ�� new Color(rgb�÷�)
		text.requestFocus();// �� �ڸ��� Ŀ�� ����
		text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		textArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));


		// âũ��
		super.setSize(500, 500);
		// â��ġ
		super.setLocationRelativeTo(null);
		// ���̱�
		super.setVisible(true);
		// xŬ��������
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.connection();
		
		text.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//�Էµ� text���� �о �������� �����Ѵ�.
				pw.println(text.getText());
				//text���� ����
				text.setText("");
			}
		});
		
	}

	/**
	 * ������ �����ϴ� �޼ҵ�
	 */
	public void connection() {
		try {
			sk = new Socket("localhost", 8000);
			br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
			pw = new PrintWriter(sk.getOutputStream(), true);
			String name = JOptionPane.showInputDialog("��ȭ���� �Է����ּ���");
			pw.println(name);

			super.setTitle("[" + name + "]");

			// �޴� ������
			new Thread() {
				public void run() {
					try {
						String data=null;
						while ((data = br.readLine()) != null) {
							textArea.append(data+"\n");//setText�� �����ǹ���,append�� �̾��
							//�ɼ�
							textArea.setCaretPosition(textArea.getText().length());//��ũ���� ��ġ�� �̵�
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				};
			}.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ClientGUIChatExam();
	}

}
