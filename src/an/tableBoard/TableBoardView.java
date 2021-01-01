package an.tableBoard;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import aaaaaaaaaaaaaaa.InitData;
import an.adminLogin.AdminLoginControl;
import an.adminLogin.AdminLoginView;
import an.adminMain.AdminMainView;
import ji.lock_mw.Lock_MW_Controller;
import sup.bills.BillsControl;
import sup.menu.OrderListDBControl;
import sup.menu.OrderMenu;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;




class OrderListFrame extends JFrame {

	String tableName;
	public JPanel orderPane;
	public TableBoardControl control;
	public JPanel orderListPane;
	public JScrollPane scrollPane;
	public ArrayList<OrderMenu> orders;
	public ArrayList<JPanel> ordersPane;
	public JButton btn;
	public JLabel orderState;

	public OrderListFrame(TableBoardControl control, String tableName, JButton btn) {
		this.btn = btn;
		this.control = control;
		this.tableName = tableName;
		JLabel tableNameLabel = new JLabel(tableName);
		JPanel backPane = new JPanel();
		JButton backButton = new JButton("�� ���ư���");

		tableNameLabel.setHorizontalAlignment(JLabel.CENTER);

		backPane.setLayout(new BorderLayout());

		backPane.add(backButton, "West");
		backPane.add(tableNameLabel, "Center");
		backButton.setBackground(Color.pink);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		setBounds(510, 50, 400, 600);
		setTitle("�Ⱦ� â");
		setResizable(false);
		setLayout(new BorderLayout());

		orderListPane = new JPanel();
		scrollPane = new JScrollPane(orderListPane);
		orderListPane.setLayout(new GridLayout(50, 1));

		add(backPane, "North");
		add(scrollPane, "Center");
		setBackground(new Color(206, 251, 201));
		orders = new OrderListDBControl(InitData.ip).pickuplist(tableName);
		System.out.println("tableNum :" + orders.size());
		if(orders.size()==0) {
			btn.setBackground(new Color(206,251,201));
		}else {
			btn.setBackground(Color.pink);
		}
		
		addOrderList();

	}

	public void addOrderList() {

		for (OrderMenu orderMenu : orders) {
			if (orderMenu.getPickup() == 1) {
				addOrder(orderMenu);
			}
		}

	}

	public void refreshOrderList() {

		orders = new OrderListDBControl(InitData.ip).pickuplist(tableName);
		if (orderPane != null) {
			orderPane.remove(orderState);
			scrollPane.remove(orderListPane);
			remove(scrollPane);
			orderListPane = new JPanel();
			orderListPane.setLayout(new GridLayout(50, 1));
			scrollPane = new JScrollPane(orderListPane);
		}
		if (orders.size() != 0) {
			btn.setBackground(Color.pink);
			for (OrderMenu orderMenu : orders) {
				if (orderMenu.getPickup() == 1) {
					addOrder(orderMenu);
				}
			}
		} else {
			btn.setBackground(new Color(206, 251, 201));
			for (OrderMenu orderMenu : orders) {
				if (orderMenu.getPickup() == 1) {
					addOrder(orderMenu);
				}
			}
		}
		add(scrollPane, "Center");
		setVisible(false);
		setVisible(true);
	}

	public void addOrder(OrderMenu orderMenu) {

		orderPane = new JPanel();
		JLabel orderName = new JLabel(orderMenu.getMenuName());
		orderState = new JLabel(orderMenu.getState());
		JLabel orderCnt = new JLabel(orderMenu.getCnt() + "");
		JButton checkBtn = new JButton("�Ⱦ�");
		orderPane.setLayout(new GridLayout(1, 4));

		if (orderMenu.getState().equals("�����Ϸ�") && orderMenu.getType().equals("����")) {
			checkBtn.setEnabled(true);
		} else if (orderMenu.getType().equals("����")) {
			checkBtn.setEnabled(false);
		}
		orderPane.add(orderState);
		orderPane.add(orderName);
		orderPane.add(orderCnt);
		orderPane.add(checkBtn);

		orderListPane.add(orderPane);
		checkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkBtn.setEnabled(false);
				orderMenu.setCancel(0);
				orderMenu.setPickup(0);

				new OrderListDBControl(InitData.ip).updatePickup(orderMenu, tableName);
				control.client.sendMessage("�Ⱦ��Ϸ�", "�ֹ�", tableName);

				refreshOrderList();
			}
		});

	}
}

public class TableBoardView extends JFrame implements WindowListener {

	AdminLoginView adminLogin;
	AdminLoginControl adminLoginControl;
	private JPanel contentPane;
	JPanel order_p;
	AdminMainView mainview;
	public TableBoardControl boardControl;
	JButton table_1;
	PaymentControl payControl;

	public JButton call1,call2,call3,call4,call5,call6,call7,call8;

	public JLabel manCnt1,womanCnt1,manCnt2,womanCnt2,manCnt3,womanCnt3,
	manCnt4,womanCnt4,manCnt5,womanCnt5,manCnt6,womanCnt6,manCnt7,womanCnt7,
	manCnt8,womanCnt8;

	public void main() {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TableBoardView(TableBoardControl boardControl) {
		this.boardControl = boardControl;

		JMenuBar mb = new JMenuBar(); // �޴��� ����
		setJMenuBar(mb);

		JMenu adminMenu = new JMenu("������");

		mb.add(adminMenu);
		JMenuItem adminItem = new JMenuItem("������ ����");

		adminMenu.add(adminItem);

		adminItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 adminLoginControl = new AdminLoginControl(TableBoardView.this);
			}
		});

		order_p = new JPanel();
		addWindowListener(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(200, 40, 1000, 700);
		setTitle("ī����");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 4, 10, 10));

		////////////////////// 1�� ���̺� ����/////////////////////////
		JPanel tablePan1 = new JPanel();
		tablePan1.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePan1.setOpaque(false);
		tablePan1.setBackground(Color.WHITE);
		contentPane.add(tablePan1);
		tablePan1.setLayout(new BoxLayout(tablePan1, BoxLayout.Y_AXIS));

		// 1�� ���̺� ��ȣ
		JLabel tableNum1 = new JLabel("1");
		tableNum1.setAlignmentX(Component.CENTER_ALIGNMENT);
		tableNum1.setFont(new Font("�������", Font.BOLD, 50));
		tablePan1.add(tableNum1);

		// ���̺� ������ ���� ��
		JPanel tablePan1_1 = new JPanel();
		tablePan1_1.setOpaque(false);
		tablePan1.add(tablePan1_1);
		tablePan1_1.setLayout(new BorderLayout(0, 0));

		// ���༺�� ��
		JPanel GPan1 = new JPanel();
		GPan1.setBorder(new LineBorder(new Color(0, 0, 0)));
		GPan1.setOpaque(false);
		tablePan1_1.add(GPan1, BorderLayout.NORTH);
		GPan1.setLayout(new GridLayout(1, 4, 5, 5));

		// ����
		JLabel man1 = new JLabel("�� : ");
		man1.setHorizontalAlignment(SwingConstants.RIGHT);
		man1.setFont(new Font("�������", Font.BOLD, 20));
		GPan1.add(man1);

		// ���� �ο�
		manCnt1 = new JLabel("");
		manCnt1.setFont(new Font("�������", Font.BOLD, 20));
		GPan1.add(manCnt1);

		// ����
		JLabel woman1 = new JLabel("�� : ");
		woman1.setHorizontalAlignment(SwingConstants.RIGHT);
		woman1.setFont(new Font("�������", Font.BOLD, 20));
		GPan1.add(woman1);

		// �����ο�
		womanCnt1 = new JLabel("");
		womanCnt1.setFont(new Font("�������", Font.BOLD, 20));
		GPan1.add(womanCnt1);

		// ��ư ��
		JPanel tablePan1_2 = new JPanel();
		tablePan1_2.setOpaque(false);
		tablePan1_1.add(tablePan1_2, BorderLayout.CENTER);
		tablePan1_2.setLayout(new GridLayout(2, 2, 4, 4));

		call1 = new JButton("ȣ��");
		call1.setBackground(Color.gray);

		call1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 1�� ���̺� - ȣ���ư
				call1.setBackground(Color.gray);

			}
		});
		call1.setFont(new Font("�������", Font.BOLD, 35));
		tablePan1_2.add(call1);
		

		JButton pick1 = new JButton("�Ⱦ�");
		pick1.setFont(new Font("�������", Font.BOLD, 35));
		tablePan1_2.add(pick1);
		boardControl.orderBtn.add(pick1);
		OrderListFrame pickupFrame1 = new OrderListFrame(boardControl,"���̺�_1", pick1);
		boardControl.orderListFrame.add(pickupFrame1);
		pick1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!pickupFrame1.isVisible()) {
					pickupFrame1.setVisible(true);
				}else {
					pickupFrame1.setVisible(false);
				}
				
			}
		});

		JButton pay1 = new JButton("����");
		pay1.setFont(new Font("�������", Font.BOLD, 35));
		tablePan1_2.add(pay1);
		pay1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payControl = new PaymentControl(TableBoardView.this,"���̺�_1");
			}
		});

		JButton gender1 = new JButton("����");
		gender1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1�� ���̺� ���� ��ư

				new Lock_MW_Controller(boardControl,"���̺�_1");
			}
		});
		gender1.setFont(new Font("�������", Font.BOLD, 35));
		tablePan1_2.add(gender1);
		////////////////////// 1�� ���̺� ��/////////////////////////

		////////////////////// 2�� ���̺� ����/////////////////////////
		JPanel tablePan2 = new JPanel();
		tablePan2.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePan2.setOpaque(false);
		tablePan2.setBackground(Color.WHITE);
		contentPane.add(tablePan2);
		tablePan2.setLayout(new BoxLayout(tablePan2, BoxLayout.Y_AXIS));

		// 1�� ���̺� ��ȣ
		JLabel tableNum2 = new JLabel("2");
		tableNum2.setAlignmentX(Component.CENTER_ALIGNMENT);
		tableNum2.setFont(new Font("�������", Font.BOLD, 50));
		tablePan2.add(tableNum2);

		// ���̺� ������ ���� ��
		JPanel tablePan2_1 = new JPanel();
		tablePan2_1.setOpaque(false);
		tablePan2.add(tablePan2_1);
		tablePan2_1.setLayout(new BorderLayout(0, 0));

		// ���༺�� ��
		JPanel GPan2 = new JPanel();
		GPan2.setBorder(new LineBorder(new Color(0, 0, 0)));
		GPan2.setOpaque(false);
		tablePan2_1.add(GPan2, BorderLayout.NORTH);
		GPan2.setLayout(new GridLayout(1, 4, 5, 5));

		// ����
		JLabel man2 = new JLabel("�� : ");
		man2.setHorizontalAlignment(SwingConstants.RIGHT);
		man2.setFont(new Font("�������", Font.BOLD, 20));
		GPan2.add(man2);

		// ���� �ο�
		manCnt2 = new JLabel("");
		manCnt2.setFont(new Font("�������", Font.BOLD, 20));
		GPan2.add(manCnt2);

		// ����
		JLabel woman2 = new JLabel("�� : ");
		woman2.setHorizontalAlignment(SwingConstants.RIGHT);
		woman2.setFont(new Font("�������", Font.BOLD, 20));
		GPan2.add(woman2);

		// �����ο�
		womanCnt2 = new JLabel("");
		womanCnt2.setFont(new Font("�������", Font.BOLD, 20));
		GPan2.add(womanCnt2);

		// ��ư ��
		JPanel tablePan2_2 = new JPanel();
		tablePan2_2.setOpaque(false);
		tablePan2_1.add(tablePan2_2, BorderLayout.CENTER);
		tablePan2_2.setLayout(new GridLayout(2, 2, 4, 4));

		call2 = new JButton("ȣ��");
		call2.setBackground(Color.gray);
		

		call2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 2�� ���̺� ȣ�� ��ư
				call2.setBackground(Color.gray);
			}
		});

		call2.setFont(new Font("�������", Font.BOLD, 35));
		tablePan2_2.add(call2);

		JButton pick2 = new JButton("�Ⱦ�");
		pick2.setFont(new Font("�������", Font.BOLD, 35));
		tablePan2_2.add(pick2);
		boardControl.orderBtn.add(pick2);
		OrderListFrame pickupFrame2 = new OrderListFrame(boardControl,"���̺�_2", pick2);
		boardControl.orderListFrame.add(pickupFrame2);
		pick2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!pickupFrame2.isVisible()) {
					pickupFrame2.setVisible(true);
				}else {
					pickupFrame2.setVisible(false);
				}
				
			}
		});

		JButton pay2 = new JButton("����");
		pay2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1�� ���̺� ���� ��ư
				payControl = new PaymentControl(TableBoardView.this,"���̺�_2");
			}
		});
		pay2.setFont(new Font("�������", Font.BOLD, 35));
		tablePan2_2.add(pay2);

		JButton gender2 = new JButton("����");
		gender2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 2�� ���̺� ���� ��ư
				new Lock_MW_Controller(boardControl,"���̺�_2");
			}
		});
		gender2.setFont(new Font("�������", Font.BOLD, 35));
		tablePan2_2.add(gender2);
		////////////////////// 2�� ���̺� ��/////////////////////////

		////////////////////// 3�� ���̺� ����/////////////////////////
		JPanel tablePan3 = new JPanel();
		tablePan3.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePan3.setOpaque(false);
		tablePan3.setBackground(Color.WHITE);
		contentPane.add(tablePan3);
		tablePan3.setLayout(new BoxLayout(tablePan3, BoxLayout.Y_AXIS));

		// 3�� ���̺� ��ȣ
		JLabel tableNum3 = new JLabel("3");
		tableNum3.setAlignmentX(Component.CENTER_ALIGNMENT);
		tableNum3.setFont(new Font("�������", Font.BOLD, 50));
		tablePan3.add(tableNum3);

		// ���̺� ������ ���� ��
		JPanel tablePan3_1 = new JPanel();
		tablePan3_1.setOpaque(false);
		tablePan3.add(tablePan3_1);/////////////////////////////////////
		tablePan3_1.setLayout(new BorderLayout(0, 0));

		// ���༺�� ��
		JPanel GPan3 = new JPanel();
		GPan3.setBorder(new LineBorder(new Color(0, 0, 0)));
		GPan3.setOpaque(false);
		tablePan3_1.add(GPan3, BorderLayout.NORTH);
		GPan3.setLayout(new GridLayout(1, 4, 5, 5));

		// ����
		JLabel man3 = new JLabel("�� : ");
		man3.setHorizontalAlignment(SwingConstants.RIGHT);
		man3.setFont(new Font("�������", Font.BOLD, 20));
		GPan3.add(man3);

		// ���� �ο�
		manCnt3 = new JLabel("");
		manCnt3.setFont(new Font("�������", Font.BOLD, 20));
		GPan3.add(manCnt3);

		// ����
		JLabel woman3 = new JLabel("�� : ");
		woman3.setHorizontalAlignment(SwingConstants.RIGHT);
		woman3.setFont(new Font("�������", Font.BOLD, 20));
		GPan3.add(woman3);

		// �����ο�
		womanCnt3 = new JLabel("");
		womanCnt3.setFont(new Font("�������", Font.BOLD, 20));
		GPan3.add(womanCnt3);

		// ��ư ��
		JPanel tablePan3_2 = new JPanel();
		tablePan3_2.setOpaque(false);
		tablePan3_1.add(tablePan3_2, BorderLayout.CENTER);
		tablePan3_2.setLayout(new GridLayout(2, 2, 4, 4));

		call3 = new JButton("ȣ��");
		call3.setBackground(Color.gray);
		

		call3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 3�� ���̺� ȣ�� ��ư
				call3.setBackground(Color.gray);
			}
		});
		call3.setFont(new Font("�������", Font.BOLD, 35));
		tablePan3_2.add(call3);
		
		JButton pick3 = new JButton("�Ⱦ�");
		pick3.setFont(new Font("�������", Font.BOLD, 35));
		tablePan3_2.add(pick3);
		boardControl.orderBtn.add(pick3);
		OrderListFrame pickupFrame3 = new OrderListFrame(boardControl,"���̺�_3", pick3);
		boardControl.orderListFrame.add(pickupFrame3);
		pick3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!pickupFrame3.isVisible()) {
					pickupFrame3.setVisible(true);
				}else {
					pickupFrame3.setVisible(false);
				}
				
			}
		});
		JButton pay3 = new JButton("����");
		pay3.setFont(new Font("�������", Font.BOLD, 35));
		tablePan3_2.add(pay3);
		pay3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				payControl = new PaymentControl(TableBoardView.this,"���̺�_3");
			}
		});
		JButton gender3 = new JButton("����");
		gender3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 3�� ���̺� ����
				new Lock_MW_Controller(boardControl,"���̺�_3");
			}
		});
		gender3.setFont(new Font("�������", Font.BOLD, 35));
		tablePan3_2.add(gender3);
		////////////////////// 3�� ���̺� ��/////////////////////////

		////////////////////// 4�� ���̺� ����/////////////////////////
		JPanel tablePan4 = new JPanel();
		tablePan4.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePan4.setOpaque(false);
		tablePan4.setBackground(Color.WHITE);
		contentPane.add(tablePan4);
		tablePan4.setLayout(new BoxLayout(tablePan4, BoxLayout.Y_AXIS));

		// 3�� ���̺� ��ȣ
		JLabel tableNum4 = new JLabel("4");
		tableNum4.setAlignmentX(Component.CENTER_ALIGNMENT);
		tableNum4.setFont(new Font("�������", Font.BOLD, 50));
		tablePan4.add(tableNum4);

		// ���̺� ������ ���� ��
		JPanel tablePan4_1 = new JPanel();
		tablePan4_1.setOpaque(false);
		tablePan4.add(tablePan4_1);/////////////////////////////////////
		tablePan4_1.setLayout(new BorderLayout(0, 0));

		// ���༺�� ��
		JPanel GPan4 = new JPanel();
		GPan4.setBorder(new LineBorder(new Color(0, 0, 0)));
		GPan4.setOpaque(false);
		tablePan4_1.add(GPan4, BorderLayout.NORTH);
		GPan4.setLayout(new GridLayout(1, 4, 5, 5));

		// ����
		JLabel man4 = new JLabel("�� : ");
		man4.setHorizontalAlignment(SwingConstants.RIGHT);
		man4.setFont(new Font("�������", Font.BOLD, 20));
		GPan4.add(man4);

		// ���� �ο�
		manCnt4 = new JLabel("");
		manCnt4.setFont(new Font("�������", Font.BOLD, 20));
		GPan4.add(manCnt4);

		// ����
		JLabel woman4 = new JLabel("�� : ");
		woman4.setHorizontalAlignment(SwingConstants.RIGHT);
		woman4.setFont(new Font("�������", Font.BOLD, 20));
		GPan4.add(woman4);

		// �����ο�
		womanCnt4 = new JLabel("");
		womanCnt4.setFont(new Font("�������", Font.BOLD, 20));
		GPan4.add(womanCnt4);

		// ��ư ��
		JPanel tablePan4_2 = new JPanel();
		tablePan4_2.setOpaque(false);
		tablePan4_1.add(tablePan4_2, BorderLayout.CENTER);
		tablePan4_2.setLayout(new GridLayout(2, 2, 4, 4));

		call4 = new JButton("ȣ��");
		call4.setBackground(Color.gray);
		

		call4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 4�� ���̺� ȣ�� ��ư
				call4.setBackground(Color.gray);
			}
		});
		call4.setFont(new Font("�������", Font.BOLD, 35));
		tablePan4_2.add(call4);

		JButton pick4 = new JButton("�Ⱦ�");
		pick4.setFont(new Font("�������", Font.BOLD, 35));
		tablePan4_2.add(pick4);
		boardControl.orderBtn.add(pick4);
		OrderListFrame pickupFrame4 = new OrderListFrame(boardControl,"���̺�_4", pick4);
		boardControl.orderListFrame.add(pickupFrame4);
		pick4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!pickupFrame4.isVisible()) {
					pickupFrame4.setVisible(true);
				}else {
					pickupFrame4.setVisible(false);
				}
				
			}
		});
		JButton pay4 = new JButton("����");
		pay4.setFont(new Font("�������", Font.BOLD, 35));
		tablePan4_2.add(pay4);
		pay4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				payControl = new PaymentControl(TableBoardView.this,"���̺�_4");
			}
		});

		JButton gender4 = new JButton("����");
		gender4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Lock_MW_Controller(boardControl,"���̺�_4");
			}
		});
		gender4.setFont(new Font("�������", Font.BOLD, 35));
		tablePan4_2.add(gender4);
		////////////////////// 4�� ���̺� ��/////////////////////////

		//////////////////////5�� ���̺� ����/////////////////////////
		JPanel tablePan5 = new JPanel();
		tablePan5.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePan5.setOpaque(false);
		tablePan5.setBackground(Color.WHITE);
		contentPane.add(tablePan5);
		tablePan5.setLayout(new BoxLayout(tablePan5, BoxLayout.Y_AXIS));

		//3�� ���̺� ��ȣ
		JLabel tableNum5 = new JLabel("5");
		tableNum5.setAlignmentX(Component.CENTER_ALIGNMENT);
		tableNum5.setFont(new Font("�������", Font.BOLD, 50));
		tablePan5.add(tableNum5);

		//���̺� ������ ���� ��
		JPanel tablePan5_1 = new JPanel();
		tablePan5_1.setOpaque(false);
		tablePan5.add(tablePan5_1);/////////////////////////////////////
		tablePan5_1.setLayout(new BorderLayout(0, 0));

		//���༺�� ��
		JPanel GPan5 = new JPanel();
		GPan5.setBorder(new LineBorder(new Color(0, 0, 0)));
		GPan5.setOpaque(false);
		tablePan5_1.add(GPan5, BorderLayout.NORTH);
		GPan5.setLayout(new GridLayout(1, 4, 5, 5));

		//����
		JLabel man5 = new JLabel("�� : ");
		man5.setHorizontalAlignment(SwingConstants.RIGHT);
		man5.setFont(new Font("�������", Font.BOLD, 20));
		GPan5.add(man5);

		//���� �ο�
		manCnt5 = new JLabel("");
		manCnt5.setFont(new Font("�������", Font.BOLD, 20));
		GPan5.add(manCnt5);

		//����
		JLabel woman5 = new JLabel("�� : ");
		woman5.setHorizontalAlignment(SwingConstants.RIGHT);
		woman5.setFont(new Font("�������", Font.BOLD, 20));
		GPan5.add(woman5);

		//�����ο�
		womanCnt5 = new JLabel("");
		womanCnt5.setFont(new Font("�������", Font.BOLD, 20));
		GPan5.add(womanCnt5);

		//��ư ��
		JPanel tablePan5_2 = new JPanel();
		tablePan5_2.setOpaque(false);
		tablePan5_1.add(tablePan5_2, BorderLayout.CENTER);
		tablePan5_2.setLayout(new GridLayout(2, 2, 4, 4));

		call5 = new JButton("ȣ��");
		call5.setBackground(Color.gray);
		

		call5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 5�� ���̺� ȣ�� ��ư
				call5.setBackground(Color.gray);
			}
		});
		call5.setFont(new Font("�������", Font.BOLD, 35));
		tablePan5_2.add(call5);

		JButton pick5 = new JButton("�Ⱦ�");
		pick5.setFont(new Font("�������", Font.BOLD, 35));
		tablePan5_2.add(pick5);
		boardControl.orderBtn.add(pick5);
		OrderListFrame pickupFrame5 = new OrderListFrame(boardControl,"���̺�_5", pick5);
		boardControl.orderListFrame.add(pickupFrame5);
		pick5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!pickupFrame5.isVisible()) {
					pickupFrame5.setVisible(true);
				}else {
					pickupFrame5.setVisible(false);
				}
				
			}
		});
		JButton pay5 = new JButton("����");
		pay5.setFont(new Font("�������", Font.BOLD, 35));
		tablePan5_2.add(pay5);
		pay5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				payControl = new PaymentControl(TableBoardView.this,"���̺�_5");
			}
		});
		
		JButton gender5 = new JButton("����");
		gender5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Lock_MW_Controller(boardControl,"���̺�_5");
			}
		});
		gender5.setFont(new Font("�������", Font.BOLD, 35));
		tablePan5_2.add(gender5);
		//////////////////////5�� ���̺� ��/////////////////////////   

		//////////////////////6�� ���̺� ����/////////////////////////
		JPanel tablePan6 = new JPanel();
		tablePan6.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePan6.setOpaque(false);
		tablePan6.setBackground(Color.WHITE);
		contentPane.add(tablePan6);
		tablePan6.setLayout(new BoxLayout(tablePan6, BoxLayout.Y_AXIS));

		//3�� ���̺� ��ȣ
		JLabel tableNum6 = new JLabel("6");
		tableNum6.setAlignmentX(Component.CENTER_ALIGNMENT);
		tableNum6.setFont(new Font("�������", Font.BOLD, 50));
		tablePan6.add(tableNum6);

		//���̺� ������ ���� ��
		JPanel tablePan6_1 = new JPanel();
		tablePan6_1.setOpaque(false);
		tablePan6.add(tablePan6_1);/////////////////////////////////////
		tablePan6_1.setLayout(new BorderLayout(0, 0));

		//���༺�� ��
		JPanel GPan6 = new JPanel();
		GPan6.setBorder(new LineBorder(new Color(0, 0, 0)));
		GPan6.setOpaque(false);
		tablePan6_1.add(GPan6, BorderLayout.NORTH);
		GPan6.setLayout(new GridLayout(1, 4, 5, 5));

		//����
		JLabel man6 = new JLabel("�� : ");
		man6.setHorizontalAlignment(SwingConstants.RIGHT);
		man6.setFont(new Font("�������", Font.BOLD, 20));
		GPan6.add(man6);

		//���� �ο�
		manCnt6 = new JLabel("");
		manCnt6.setFont(new Font("�������", Font.BOLD, 20));
		GPan6.add(manCnt6);

		//����
		JLabel woman6 = new JLabel("�� : ");
		woman6.setHorizontalAlignment(SwingConstants.RIGHT);
		woman6.setFont(new Font("�������", Font.BOLD, 20));
		GPan6.add(woman6);

		//�����ο�
		womanCnt6 = new JLabel("");
		womanCnt6.setFont(new Font("�������", Font.BOLD, 20));
		GPan6.add(womanCnt6);

		//��ư ��
		JPanel tablePan6_2 = new JPanel();
		tablePan6_2.setOpaque(false);
		tablePan6_1.add(tablePan6_2, BorderLayout.CENTER);
		tablePan6_2.setLayout(new GridLayout(2, 2, 4, 4));

		call6 = new JButton("ȣ��");
		call6.setBackground(Color.gray);

		call6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 6�� ���̺� ȣ�� ��ư
				call6.setBackground(Color.gray);
			}
		});
		call6.setFont(new Font("�������", Font.BOLD, 35));
		tablePan6_2.add(call6);

		JButton pick6 = new JButton("�Ⱦ�");
		pick6.setFont(new Font("�������", Font.BOLD, 35));
		boardControl.orderBtn.add(pick6);
		tablePan6_2.add(pick6);
		OrderListFrame pickupFrame6 = new OrderListFrame(boardControl,"���̺�_6", pick6);
		boardControl.orderListFrame.add(pickupFrame6);
		pick6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!pickupFrame6.isVisible()) {
					pickupFrame6.setVisible(true);
				}else {
					pickupFrame6.setVisible(false);
				}
				
			}
		});
		JButton pay6 = new JButton("����");
		pay6.setFont(new Font("�������", Font.BOLD, 35));
		tablePan6_2.add(pay6);
		pay6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				payControl = new PaymentControl(TableBoardView.this,"���̺�_6");
			}
		});

		JButton gender6 = new JButton("����");
		gender6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Lock_MW_Controller(boardControl,"���̺�_6");
			}
		});
		gender6.setFont(new Font("�������", Font.BOLD, 35));
		tablePan6_2.add(gender6);
		//////////////////////6�� ���̺� ��/////////////////////////   

		//////////////////////6�� ���̺� ����/////////////////////////
		JPanel tablePan7 = new JPanel();
		tablePan7.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePan7.setOpaque(false);
		tablePan7.setBackground(Color.WHITE);
		contentPane.add(tablePan7);
		tablePan7.setLayout(new BoxLayout(tablePan7, BoxLayout.Y_AXIS));

		//3�� ���̺� ��ȣ
		JLabel tableNum7 = new JLabel("7");
		tableNum7.setAlignmentX(Component.CENTER_ALIGNMENT);
		tableNum7.setFont(new Font("�������", Font.BOLD, 50));
		tablePan7.add(tableNum7);

		//���̺� ������ ���� ��
		JPanel tablePan7_1 = new JPanel();
		tablePan7_1.setOpaque(false);
		tablePan7.add(tablePan7_1);/////////////////////////////////////
		tablePan7_1.setLayout(new BorderLayout(0, 0));

		//���༺�� ��
		JPanel GPan7 = new JPanel();
		GPan7.setBorder(new LineBorder(new Color(0, 0, 0)));
		GPan7.setOpaque(false);
		tablePan7_1.add(GPan7, BorderLayout.NORTH);
		GPan7.setLayout(new GridLayout(1, 4, 5, 5));

		//����
		JLabel man7 = new JLabel("�� : ");
		man7.setHorizontalAlignment(SwingConstants.RIGHT);
		man7.setFont(new Font("�������", Font.BOLD, 20));
		GPan7.add(man7);

		//���� �ο�
		manCnt7 = new JLabel("");
		manCnt7.setFont(new Font("�������", Font.BOLD, 20));
		GPan7.add(manCnt7);

		//����
		JLabel woman7 = new JLabel("�� : ");
		woman7.setHorizontalAlignment(SwingConstants.RIGHT);
		woman7.setFont(new Font("�������", Font.BOLD, 20));
		GPan7.add(woman7);

		//�����ο�
		womanCnt7 = new JLabel("");
		womanCnt7.setFont(new Font("�������", Font.BOLD, 20));
		GPan7.add(womanCnt7);

		//��ư ��
		JPanel tablePan7_2 = new JPanel();
		tablePan7_2.setOpaque(false);
		tablePan7_1.add(tablePan7_2, BorderLayout.CENTER);
		tablePan7_2.setLayout(new GridLayout(2, 2, 4, 4));

		call7 = new JButton("ȣ��");
		call7.setBackground(Color.gray);

		call7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 7�� ���̺� ȣ�� ��ư
				call7.setBackground(Color.gray);
			}
		});
		call7.setFont(new Font("�������", Font.BOLD, 35));
		tablePan7_2.add(call7);

		JButton pick7 = new JButton("�Ⱦ�");
		pick7.setFont(new Font("�������", Font.BOLD, 35));
		boardControl.orderBtn.add(pick7);
		tablePan7_2.add(pick7);
		OrderListFrame pickupFrame7 = new OrderListFrame(boardControl,"���̺�_7", pick7);
		boardControl.orderListFrame.add(pickupFrame7);
		pick7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!pickupFrame7.isVisible()) {
					pickupFrame7.setVisible(true);
				}else {
					pickupFrame7.setVisible(false);
				}
				
			}
		});
		JButton pay7 = new JButton("����");
		pay7.setFont(new Font("�������", Font.BOLD, 35));
		tablePan7_2.add(pay7);
		pay7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				payControl = new PaymentControl(TableBoardView.this,"���̺�_7");
			}
		});

		JButton gender7 = new JButton("����");
		gender7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Lock_MW_Controller(boardControl,"���̺�_7");
			}
		});
		gender7.setFont(new Font("�������", Font.BOLD, 35));
		tablePan7_2.add(gender7);
		//////////////////////7�� ���̺� ��/////////////////////////   

		//////////////////////8�� ���̺� ����/////////////////////////
		JPanel tablePan8 = new JPanel();
		tablePan8.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePan8.setOpaque(false);
		tablePan8.setBackground(Color.WHITE);
		contentPane.add(tablePan8);
		tablePan8.setLayout(new BoxLayout(tablePan8, BoxLayout.Y_AXIS));

		//3�� ���̺� ��ȣ
		JLabel tableNum8 = new JLabel("8");
		tableNum8.setAlignmentX(Component.CENTER_ALIGNMENT);
		tableNum8.setFont(new Font("�������", Font.BOLD, 50));
		tablePan8.add(tableNum8);

		//���̺� ������ ���� ��
		JPanel tablePan8_1 = new JPanel();
		tablePan8_1.setOpaque(false);
		tablePan8.add(tablePan8_1);/////////////////////////////////////
		tablePan8_1.setLayout(new BorderLayout(0, 0));

		//���༺�� ��
		JPanel GPan8 = new JPanel();
		GPan8.setBorder(new LineBorder(new Color(0, 0, 0)));
		GPan8.setOpaque(false);
		tablePan8_1.add(GPan8, BorderLayout.NORTH);
		GPan8.setLayout(new GridLayout(1, 4, 5, 5));

		//����
		JLabel man8 = new JLabel("�� : ");
		man8.setHorizontalAlignment(SwingConstants.RIGHT);
		man8.setFont(new Font("�������", Font.BOLD, 20));
		GPan8.add(man8);

		//���� �ο�
		manCnt8 = new JLabel("");
		manCnt8.setFont(new Font("�������", Font.BOLD, 20));
		GPan8.add(manCnt8);

		//����
		JLabel woman8 = new JLabel("�� : ");
		woman8.setHorizontalAlignment(SwingConstants.RIGHT);
		woman8.setFont(new Font("�������", Font.BOLD, 20));
		GPan8.add(woman8);

		//�����ο�
		womanCnt8 = new JLabel("");
		womanCnt8.setFont(new Font("�������", Font.BOLD, 20));
		GPan8.add(womanCnt8);

		//��ư ��
		JPanel tablePan8_2 = new JPanel();
		tablePan8_2.setOpaque(false);
		tablePan8_1.add(tablePan8_2, BorderLayout.CENTER);
		tablePan8_2.setLayout(new GridLayout(2, 2, 4, 4));

		call8 = new JButton("ȣ��");
		call8.setBackground(Color.gray);

		call8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 8�� ���̺� ȣ�� ��ư
				call8.setBackground(Color.gray);
			}
		});
		call8.setFont(new Font("�������", Font.BOLD, 35));
		tablePan8_2.add(call8);

		JButton pick8 = new JButton("�Ⱦ�");
		boardControl.orderBtn.add(pick8);
		pick8.setFont(new Font("�������", Font.BOLD, 35));
		tablePan8_2.add(pick8);
		OrderListFrame pickupFram8 = new OrderListFrame(boardControl,"���̺�_8", pick8);
		boardControl.orderListFrame.add(pickupFram8);
		pick8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!pickupFram8.isVisible()) {
					pickupFram8.setVisible(true);
				}else {
					pickupFram8.setVisible(false);
				}
				
			}
		});
		JButton pay8 = new JButton("����");
		pay8.setFont(new Font("�������", Font.BOLD, 35));
		tablePan8_2.add(pay8);
		pay8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				payControl = new PaymentControl(TableBoardView.this,"���̺�_8");
			}
		});
		JButton gender8 = new JButton("����");
		gender8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Lock_MW_Controller(boardControl,"���̺�_8");
			}
		});
		gender8.setFont(new Font("�������", Font.BOLD, 35));
		tablePan8_2.add(gender8);
		setVisible(true);
		//////////////////////5�� ���̺� ��/////////////////////////   
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}

