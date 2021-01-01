package an.adminLogin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import an.tableBoard.TableBoardView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLoginView extends JFrame {

	private JPanel contentPane;
	private JPasswordField password;
	private JLabel result;
	public TableBoardView tableBoardView;
	AdminLoginControl control; // �α��� ��Ʈ�� Ŭ����.

	
	/**
	 * @wbp.parser.constructor
	 */
	public AdminLoginView(TableBoardView tableBoardView) {
		this.tableBoardView = tableBoardView;
		init();
		
	}

	public AdminLoginView(AdminLoginControl control) {
		this.control = control;
		init();

	}

	void init() {
		setVisible(true);
		this.tableBoardView = tableBoardView;
		
		setBounds(410, 40, 600, 600);
		setTitle("�α��� â");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("User :");
		panel_2.add(lblNewLabel);

	
		password = new JPasswordField(10);
		panel_2.add(password);

		
		JButton btnNewButton = new JButton("�α���");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String strPw = String.valueOf(password.getPassword());
				

				AdminLogDTO dto = new AdminLogDAO().detail(strPw);
				if (dto!=null) {
					result.setText("�α����� �Ǿ����ϴ�");
					result.setForeground(Color.blue);

					control.loginViewClose();// �α��� �����Ǹ�, an_adminLoginControl Ŭ������ loginViewClose() �޼ҵ� ����

				} else {
					result.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					result.setForeground(Color.RED);
				}

			}
		});
		panel_2.add(btnNewButton);
		result = new JLabel(" ");
		panel_2.add(result);

	}

}
