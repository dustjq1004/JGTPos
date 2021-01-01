package hwi.game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hong.client.DefaultClient;
import hong.server.MessageObject;
import ji.tablet.TabletDAO;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class AskGameJF extends JFrame {

	boolean answer;
	private JPanel contentPane;


	public AskGameJF(GameMainController gmc, DefaultClient df, String me, MessageObject you) {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(520, 265, 400, 214);
		setTitle("���� ��û â");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 3, 0));
		
		JLabel askTableLab = new JLabel((String)you.getSender());
		askTableLab.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(askTableLab);
		JLabel askTableLab_ = new JLabel("�г��� : "+(String)you.getMessageMain());
		askTableLab_.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(askTableLab_);
		
		JLabel gameMsgLab = new JLabel("���� ���� �ϽǷ���? (����)");
		gameMsgLab.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(gameMsgLab);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton gameYesBtn = new JButton("����");
		panel.add(gameYesBtn);
		gameYesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TabletDAO().modifystartGame(me, you.getSender());
				df.sendMessage("", "���̺���º���", "���̺�_1","���̺�_2","���̺�_3","���̺�_4","���̺�_5",
						"���̺�_6","���̺�_7","���̺�_8");
				gmc.doAfterAnswer(true);
			}
		});
		
		JButton gameNOBtn = new JButton("����");
		panel.add(gameNOBtn);
		gameNOBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gmc.doAfterAnswer(false);			
			}
		});
		setVisible(true);
	}
}
