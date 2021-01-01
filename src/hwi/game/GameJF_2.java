package hwi.game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import ji.tablet.TabletDAO;

public class GameJF_2 extends JFrame implements ReceiverObjFromClient {

	private JPanel contentPane;

	DefaultClient dc;

	TeethBtn[] btn;


	String me;

	MessageObject you;

	JLabel lblNewLabel;

	boolean turn = true;

	int bombNum = -1;

	//	int bombNum = (int)(Math.random()*jb.size());

	class TeethBtn extends JButton implements ActionListener {

		int i;//��ư ��ȣ

		String [] imgStr= {"img/�Ǿ��̻�3.png","img/�Ǿ��̻�3.png","img/�Ǿ��̻�3.png","img/�Ǿ��̻�3.png","img/�Ǿ��̻�3.png",
				"img/�Ǿ��̻�3.png","img/�Ǿ��̻�3.png","img/�Ǿ��̻�13.png","img/�Ǿ��̻�13.png","img/�Ǿ��̻�13.png",
				"img/�Ǿ��̻�13.png","img/�Ǿ��̻�13.png","img/�Ǿ��̻�13.png","img/�Ǿ��̻�13.png","img/�Ǿ��̻�13.png",
				"img/�Ǿ��̻�13.png","img/�Ǿ��̻�13.png","img/�Ǿ��̻�3.png","img/�Ǿ��̻�3.png","img/�Ǿ��̻�3.png"};
		public TeethBtn(int x, int y, int width, int height) {
			super();

			setBounds(x, y, width, height);
			setIcon(new ImageIcon("img/�̻�.png"));
			setContentAreaFilled(false);//////��ư �̹��� ������ ���� �ּ�ó��
			setVisible(true);
			addActionListener(this);
			setLayout(new BorderLayout(0, 0));

		}

		public void actionPerformed(ActionEvent e) {
			System.out.println(i);

			if (turn) {//������
				String msg = i + "";

				if (bombNum == i) {//
					System.out.println("��~~~~~~~~~~~~~~~~~~~~~`");
					msg = "��";
					// ���� ��
					lblNewLabel.setIcon(new ImageIcon("img/�й�.png"));

					// ���� �ļ� ó��
					for (int i = 0; i < btn.length; i++) {
						btn[i].setEnabled(false);
					}
					//					new TabletDAO().modifyEnd(me, you.getMessageMain());
					//					dc.sendMessage("", "���̺���º���", "���̺�_1","���̺�_2","���̺�_3","���̺�_4","���̺�_5",
					//							"���̺�_6","���̺�_7","���̺�_8");
				} else {
					setIcon(new ImageIcon(imgStr[i]));
					setContentAreaFilled(true);
					setEnabled(false);
				}

				dc.sendMessage(msg, "game", you.getSender());

				turn = false;

			} else {
				System.out.println("��ٷ�!");
			}
		}
	}

	@Override
	public void getMsgObjectFromClient(MessageObject mo) {

		//��밡 ���� ����Ǿ��� ��, ���� ��������
		if(mo.getType().equals("��������")&&mo.getMessageMain().equals(you.getSender())) {
			new TabletDAO().modifyEnd(me, you.getSender());

			dc.sendMessage("", "���̺���º���", "���̺�_1","���̺�_2","���̺�_3","���̺�_4","���̺�_5",
					"���̺�_6","���̺�_7","���̺�_8");
			dispose();
			dc.removeReceiverObjFromClient(this);
		}
		else {
			String ri = (String) mo.getMessageMain();
			System.out.println("���� �޴´� Ȯ��!!! " + ri);
			if (ri.equals("��")) {
				System.out.println("���� �̱�");

				/// �̱� ȭ�� ������
				lblNewLabel.setIcon(new ImageIcon("img/�¸�.jpg"));

				/// �̱�� �ļ� ó��
				for (int i = 0; i < btn.length; i++) {

					btn[i].setEnabled(false);

				}
				try {
					Thread.sleep(5000);
					new TabletDAO().modifyEnd(me, you.getSender());
					dc.sendMessage("�ݾ�", "game", you.getSender());
					dispose();
					dc.removeReceiverObjFromClient(this);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}else if (ri.equals("�ݾ�")) { 
				new TabletDAO().modifyEnd(me, you.getSender());

				dc.sendMessage("", "���̺���º���", "���̺�_1","���̺�_2","���̺�_3","���̺�_4","���̺�_5",
						"���̺�_6","���̺�_7","���̺�_8");
				dispose();
				dc.removeReceiverObjFromClient(this);

			}else {

				int ti = Integer.parseInt(ri);

				btn[ti].setIcon(new ImageIcon("img/�̻�����.png"));
				btn[ti].setContentAreaFilled(true);
				btn[ti].setEnabled(false);

				turn = true;
			}
		}
	}

	public GameJF_2(int bombNum, DefaultClient dc, String me,MessageObject you) {

		//	

		this.bombNum = bombNum;

		//
		//		this.bombNum=1;

		this.dc = dc;
		this.me = me;
		this.you = you;

		setResizable(false);
		setBounds(220, 70, 950, 600);
		setTitle("�Ǿ� ���Һ� ����");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img/�Ǿ��Թ���.png"));
		lblNewLabel.setBounds(0, 0, 944, 571);
		contentPane.add(lblNewLabel);


		btn = new TeethBtn[] { new TeethBtn(215, 87, 90, 51), new TeethBtn(350, 73, 90, 51),
				new TeethBtn(498, 73, 90, 51), new TeethBtn(636, 87, 90, 51), new TeethBtn(687, 129, 90, 51),
				new TeethBtn(687, 184, 90, 51), new TeethBtn(675, 239, 90, 51), new TeethBtn(678, 317, 90, 51),
				new TeethBtn(678, 371, 90, 51),new TeethBtn(687, 424, 90, 51),new TeethBtn(638, 471, 90, 51)   ,
				new TeethBtn(504, 480, 90, 51),new TeethBtn(353, 480, 90, 51),new TeethBtn(223, 471, 90, 51)   
				,new TeethBtn(164, 424, 90, 51),new TeethBtn(170, 371, 90, 51),new TeethBtn(180, 317, 90, 51)
				,new TeethBtn(180, 239, 90, 51),new TeethBtn(170, 184, 90, 51),
				new TeethBtn(164, 129, 90, 51) };
		//�̻� ��ȣ �ο�(�ε��� ������) ��, �гο� �̻� (��ư) �߰�
		for (int i = 0; i < btn.length; i++) {
			contentPane.add(btn[i]);
			btn[i].i = i;
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		System.out.println("���� ������ ���� >>>>>>"+dc);
		dc.addReceiverObjFromClient(this);

	}
}
