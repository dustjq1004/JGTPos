package hong.table;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import aaaaaaaaaaaaaaa.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import hong.table.TableFrame.TablePane;
import hwi.game.GameMainController;
import ji.tablet.TableSetController;
import ji.tablet.TableSetController2;
import ji.tablet.TabletDAO;
import ji.tablet.TabletDTO;
import sup.bills.BillsControl;
import sup.menu.MenuMainController;
import sup.menuManagement.MenuManagementDBControl;

public class TableMainController {
	public TableFrame tableFrame; // ��
	MenuMainController menuPan;
	TableMainInform tableMainInform;
	TableSetController tableSetController;
	TableSetController2 tableSetController2;
	public MessageReceiver messageReceiver;
	public String clientName; // ���̺� �̸� �Է�
	public DefaultClient defaultClient;
	public BillsControl billsControl;


	boolean isLock;
	GameMainController gm_1;//��û �Ҷ�

	public TableFrame getTableFrame() {
		return tableFrame;
	}

	public void setTableFrame(TableFrame tableFrame) {
		this.tableFrame = tableFrame;
	}

	public TableMainInform getTableMainInform() {
		return tableMainInform;
	}


	public void setTableMainInform(TableMainInform tableMainInform) {
		this.tableMainInform = tableMainInform;
	}

	public MessageReceiver getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(MessageReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public DefaultClient getDefaultClient() {
		return defaultClient;
	}


	public TableMainController(String clientName) {
		//�����ΰ� frame���� �ؾ��ϴ°�
		new TabletDAO().modify(new TabletDTO(), clientName);
		this.clientName = clientName;
		messageReceiver = new MessageReceiver(); // ���α׷��� ������ ������ �ڵ� ����
		tableFrame = new TableFrame(this);
		tableFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tableSetController = new TableSetController(this, clientName); // ���̺� ��
		billsControl = new BillsControl(clientName, InitData.ip, this);
	}

	// ���̺� ����
	void tableSetting() {
		tableSetController2 = new TableSetController2(this, clientName);
	}

	// ���� ȣ��
	void callWaiter() {
		defaultClient.sendMessage(clientName, "����ȣ��", "ī����");
	}

	// �޴���
	void orderMenu() {
		// new MenuMainController(tableMainInform.getTableName(), defaultClient);
		menuPan = new MenuMainController(clientName, defaultClient, this);
	}

	// ��꼭
	void openBill() {
		if (billsControl == null) {
			billsControl.billsMain.setVisible(true);
		} else {
			billsControl.billsMain.setVisible(true);
		}
	}
	void letsGame(String you) {
		/// ���濡�� ��û
		defaultClient.sendMessage(tableFrame.nickNameLab.getText(), "���ӽ�û", you);

		btnEnable();

		///
		gm_1 = new GameMainController(defaultClient, clientName,you);

	}
	void btnEnable() {
		for (TablePane tp : tableFrame.tablePanes) {
			System.out.println("��ư ��Ȱ��ȭ>>>>>>>>>>");
			tp.urGameBnt.setEnabled(false);
		}
	}

	public void openBtn() {
		tableFrame.setUpBtn.setEnabled(true);
		tableFrame.billBtn.setEnabled(true);
		tableFrame.menuPanBtn.setEnabled(true);
		tableFrame.staffCallBtn.setEnabled(true);
		System.out.println("tableFrame.tablePanes : " + tableFrame.tablePanes);

	}

	public void openTable() {
		System.out.println("tableSetController:" + tableSetController);
		tableSetController.tableSetUpFrame.pass.setEnabled(true);
		tableSetController.tableSetUpFrame.table_SetUp_Bnt.setEnabled(true);
	}

	public void refreshAllTable() {
		System.out.println(clientName);
		ArrayList<TabletDTO> tabletInfo = new TabletDAO().list();
		//////////////////////////////

		tableFrame.tablePanes.removeAll(tableFrame.tablePanes);
		tableFrame.tablePanes = new ArrayList<TablePane>();
		del();
		for (TabletDTO tabletDTO : tabletInfo) {
			if (!tabletDTO.getTT_name().equals(clientName)) {
				tableFrame.mkTablePane(tabletDTO.getTT_name(), tabletDTO.getTT_nickname(), tabletDTO.getTT_concept(),
						tabletDTO.getTT_man(), tabletDTO.getTT_woman(),tabletDTO.getTT_do());
			}else {
				tableFrame.manCntLab.setText(tabletDTO.getTT_man()+"");
				tableFrame.womanCntLab.setText(tabletDTO.getTT_woman()+"");
			}
		}

		tableFrame.panel_3.setVisible(false);
		tableFrame.panel_3.setVisible(true);

	}

	void del() {
		tableFrame.scrollPane.remove(tableFrame.tableListPanel);
		tableFrame.panel_3.remove(tableFrame.scrollPane);
		tableFrame.scrollPane = new JScrollPane();
		tableFrame.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tableFrame.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableFrame.panel_3.add(tableFrame.scrollPane, BorderLayout.CENTER);
		tableFrame.tableListPanel = new JPanel();
		tableFrame.tableListPanel.setLayout(new GridLayout(8, 1));
		tableFrame.scrollPane.setViewportView(tableFrame.tableListPanel);
	}

	void tableReSet() {

		tableFrame.dispose();
		tableFrame = new TableFrame(this);
		tableSetController = new TableSetController(this, clientName);
	}

	class MessageReceiver implements ReceiverObjFromClient {
		// DefaultClient defaultClient;

		public MessageReceiver() {
			defaultClient = new DefaultClient(clientName, this, InitData.ip, 7777);
		}

		void sendMessage(Object msgMain, String type, String... receivers) {
			defaultClient.sendMessage(msgMain, type, receivers);
		}

		@Override
		public void getMsgObjectFromClient(MessageObject msgObject) {
			// ���� �޼��� type ���� ������ �Լ� ���� �Ұ�.
			if(msgObject.getType().equals("������")) {
				isLock=true;
				openTable();
			}
			else if(isLock){
				switch (msgObject.getType()) {
				case "�޴�����":
					System.out.println("�޴� �����̴٤�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					menuPan.menuChange();
					break;
				case "���ӽ�û"://���� ��û ���� ��,
					/// ���� / ���� â�� �־�� ��
					// ���� ������ ���濡�Ե� ȸ���ؾ� ��
					// ~ �����ߴ� ġ�� �ϴ� �ٷ� ����â ����
					new GameMainController(defaultClient, clientName, msgObject);
					break;
				case "���Ӽ���":
					gm_1.doAfterReply(true,msgObject);
					break;
				case "���Ӱ���" ://���� ��û�ߴµ� ���� �޾��� ��,
					gm_1.doAfterReply(false,msgObject);
					gm_1=null;
					break;
				case "��������":
				case "���̺���º���":
					refreshAllTable();
					break;
				case "�����Ϸ�":
					tableReSet();
					isLock=false;
					break;
				case "�ֹ�":
					billsControl.receiveMessage(msgObject);
					break;

				case "����":
					if(!msgObject.equals(clientName)) {
						refreshAllTable();					
					}
					break;
				case "���ӹ�ư��Ȱ��ȭ":
					btnEnable();
					break;
				}
			}

		}
	}
}
