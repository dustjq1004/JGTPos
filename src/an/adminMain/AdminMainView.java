package an.adminMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import an.adminLogin.ChangePWFrame;
//import an.adminSettings.AdminSettingsControl;���ο����� ���� �������gui�� ���� ����X
import an.sales.SalesControl;
import an.tableBoard.TableBoardControl;
import an.tableBoard.TableBoardView;
import sup.menuManagement.MMcontroller;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainView extends JFrame {

	Ppt ppt;
	public AdminMainControl adminControl;
	TableBoardView boareView;
//	AdminSettingsControl settingcontrol; ���ο����� ���� �������gui�� ���� ����X
	private JPanel contentPane;
	MMcontroller mmcontrol;
	public JButton btnNewButton_6;
	TableBoardControl tbc;

	public AdminMainView(Ppt ppt) {
	this.ppt = ppt;
	}



	public AdminMainView(AdminMainControl adminControl) {

		this.adminControl = adminControl;
		setBounds(410, 40, 600, 600);
		setTitle("������ ���� â");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(3, 1, 2, 0));

		JButton btnNewButton_4 = new JButton("�޴�����");
		btnNewButton_4.setFont(new Font("�ü�ü",Font.BOLD,50));
		btnNewButton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mmcontrol = new MMcontroller(AdminMainView.this,7777);
			}
		});
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_8 = new JButton("�������");
		btnNewButton_8.setFont(new Font("�ü�ü",Font.BOLD,50));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//������� ������ �����ϱ�.
				new SalesControl(AdminMainView.this);
			}
		});
		contentPane.add(btnNewButton_8);

		
		JButton btnNewButton_5 = new JButton("������ ����");
		btnNewButton_5.setFont(new Font("�ü�ü",Font.ROMAN_BASELINE,50));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				new AdminSettingsControl();
				new ChangePWFrame(AdminMainView.this,adminControl.adminloginControl);
				//������ ���� GUI�� ���� �� �� �ְ� �����.

			}
		});
		contentPane.add(btnNewButton_5);
		setVisible(true);
		setContentPane(contentPane);

	}

}
