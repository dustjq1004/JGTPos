package an.tableBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import an.settlement.SettlementBillsControl;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PaymentView extends JFrame implements Runnable{


	public ArrayList<PaymentDTO> payment;
	public static PaymentControl payControl;
	SettlementBillsControl settleControl;

	private Thread thread;
	public JLabel label;
	///
	JPanel contentPane;
	public JTable table;
	JTextField textField_1;
	JTextField textField_4;
	JTextField textField_5;
	JTextField textField_6;
	public JTextField textField_11;
	JTable table_1;


	public static void main() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentView frame = new PaymentView(payControl);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PaymentView(PaymentControl payControl) {
		this.payControl = payControl;

		setBounds(300, 70, 800, 600);
		setTitle("���� â");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		label = new JLabel();
		label.setBounds(93, 5, 200, 20);
		label.setForeground(Color.black);
		label.setFont(new Font("Serif", Font.PLAIN, 18));
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}

		JPanel panel_2 = new JPanel();

		panel_2.setBackground(UIManager.getColor("Button.background"));
		panel_2.setBounds(0, 452, 394, 32);
		panel_2.setLayout(null);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_8.setBounds(689, 309, 95, 252);
		panel_8.setLayout(new GridLayout(0, 1, 0123123, 0));

		JButton btnNewButton_18 = new JButton("���� �ϱ�");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				settleControl = new SettlementBillsControl(PaymentView.this);

			}
		});
		panel_8.add(btnNewButton_18);

		JPanel panel_3 = new JPanel();

		panel_3.setBounds(406, 35, 378, 264);
		panel_3.setLayout(new GridLayout(4, 4, 0, 0));

		JLabel lblNewLabel_9 = new JLabel("���� �ݾ�");
		panel_3.add(lblNewLabel_9);
		//////////////////////////////////////////		
		textField_11 = new JTextField();
		textField_11.setBackground(Color.WHITE);
		panel_3.add(textField_11);
		textField_11.setEditable(false);
		textField_11.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 35, 394, 323);




		table = new JTable();

		Vector<String> index = new Vector<String>();

		index.add("���̺��ȣ");
		index.add("�޴�");
		index.add("����");
		index.add("����");

		DefaultTableModel df = new DefaultTableModel(index,0);
		payment = new PaymentDAO().payment(payControl.tableNum);



		for (PaymentDTO order : payment) {

			Vector<Object> vct = new Vector<Object>();

			vct.add(order.getBills_tablenum());
			vct.add(order.getBills_orderedmenu());
			vct.add(order.getBills_count());
			vct.add(order.getBills_price());

			df.addRow(vct);

		}

		table = new JTable(df);
		scrollPane.setViewportView(table);



		JLabel lblNewLabel_11 = new JLabel("���� ���� :");
		lblNewLabel_11.setPreferredSize(new Dimension(150, 50));
		lblNewLabel_11.setFont(new Font("���� ���", Font.BOLD, 18));
		lblNewLabel_11.setBounds(0, 5, 81, 20);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(283)
							.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}


	public void run() {
		while(true){
			Calendar cal = Calendar.getInstance();

			String now = 
					cal.get(Calendar.YEAR)+"��"+
							(cal.get(Calendar.MONTH)+1)+"��"+
							cal.get(Calendar.DATE)+"��"+
							cal.get(Calendar.HOUR)+"��"+
							cal.get(Calendar.MINUTE)+"��"+
							cal.get(Calendar.SECOND)+"��";
			label.setText(now);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}
}
