package an.tableBoard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import aaaaaaaaaaaaaaa.InitData;
import an.adminMain.AdminMainView;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;

public class TableBoardControl {
	public DefaultClient client;
	public TableBoardView boardView;
	ArrayList<JButton> orderBtn;
	public ArrayList<OrderListFrame> orderListFrame;
	public Receiver receiver;
	
	public TableBoardControl() {
		orderListFrame = new ArrayList<OrderListFrame>();
		orderBtn = new ArrayList<JButton>();
		boardView = new TableBoardView(this);
		receiver = new Receiver(this);
		this.client = new DefaultClient("ī����", receiver, InitData.ip, 7777);
	}
	
	
	class Receiver implements ReceiverObjFromClient{
		
		TableBoardControl boardControl;
		public Receiver(TableBoardControl boardControl) {
			super();
			
			this.boardControl = boardControl;
		}
		
		@Override
		public void getMsgObjectFromClient(MessageObject msgObject) {
			System.out.println(msgObject.getType());
			switch (msgObject.getType()) {
			case "�����Ϸ�": 
				System.out.println("��ӷ��Ƴʸ��������Ϥ�");
				for (OrderListFrame olf : orderListFrame) {
					if(olf.tableName.equals(msgObject.getMessageMain())) {
						olf.refreshOrderList();
						boardControl.completeOrder(msgObject);
						break;
					}
				}
				
				break;
			case "����ȣ��" :
				boardControl.callStaff(msgObject);
				break;
			case "�ֹ�":
				System.out.println(">>>>>>>>>>>>>>>>>>"+msgObject.getMessageMain());
				for (OrderListFrame olf : orderListFrame) {
					if(olf.tableName.equals(msgObject.getMessageMain())) {
						olf.refreshOrderList();
						break;
					}
				}
				break;
			}
//			System.out.println(msgObject.getMessageMain());
			
		} //���� �޾Ƽ� �ڹٷ� �����͸� �̾� �� ������,

//		@Override
//		public void getReceiveFileFromClient(Object o) {
//		}


	}

	public void reset(String tableNum) {
		client.sendMessage("���� �Ϸ�", "�����Ϸ�", tableNum);
		client.sendMessage("", "���̺���º���", "���̺�_1","���̺�_2","���̺�_3","���̺�_4","���̺�_5","���̺�6","���̺�_7","���̺�_8");
	}
	public void callStaff (MessageObject msgObject) {
		if(msgObject.getMessageMain().equals("���̺�_1")) {
			boardView.call1.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("���̺�_2")) {
			boardView.call2.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("���̺�_3")) {  
			boardView.call3.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("���̺�_4")) {
			boardView.call4.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("���̺�_5")) {
			boardView.call5.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("���̺�_6")) {
			boardView.call6.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("���̺�_7")) {
			boardView.call7.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("���̺�_8")) {
			boardView.call8.setBackground(Color.orange);
		}
	}
	public void completeOrder(MessageObject msgObject) {
		if(msgObject.getMessageMain().equals("���̺�_1")) {
			orderBtn.get(0).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("���̺�_2")) {
			orderBtn.get(1).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("���̺�_3")) {
			orderBtn.get(2).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("���̺�_4")) {
			orderBtn.get(3).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("���̺�_5")) {
			orderBtn.get(4).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("���̺�_6")) {
			orderBtn.get(5).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("���̺�_7")) {
			orderBtn.get(6).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("���̺�_8")) {
			orderBtn.get(7).setBackground(Color.red);;
		}
		boardView.order_p.setVisible(false);
		boardView.order_p.setVisible(true);
	}
}
