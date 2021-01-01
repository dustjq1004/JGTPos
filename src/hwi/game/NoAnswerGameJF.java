package hwi.game;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class NoAnswerGameJF extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoAnswerGameJF frame = new NoAnswerGameJF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NoAnswerGameJF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(520, 265, 400, 214);
		setTitle("���� ���� â");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel noAnswerGameLab = new JLabel("������ ������ �����Ͽ����ϴ�.");
		noAnswerGameLab.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(noAnswerGameLab, BorderLayout.CENTER);
		
		JButton OkeyBtn = new JButton("Ȯ��");
		contentPane.add(OkeyBtn, BorderLayout.SOUTH);
		
		 OkeyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	}

}
