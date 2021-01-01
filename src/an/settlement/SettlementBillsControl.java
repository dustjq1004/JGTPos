package an.settlement;

import an.tableBoard.PaymentView;

public class SettlementBillsControl {
	
	SettlementBillsView billsview;
	PaymentView payView;
	public SettlementBillsControl(PaymentView payView) {
		this.payView = payView;
		billsview = new SettlementBillsView(this);
		billsview.main();
		
	}
	public void resetGender(String tt, String tt2) {
		switch (payView.payControl.tableNum) {
		case "���̺�_1":
			
			payView.payControl.tableView.manCnt1.setText(tt);
			payView.payControl.tableView.womanCnt1.setText(tt2);
			break;
		
		case "���̺�_2":
//			System.out.println(boardView.lblNewLabel_4);
			payView.payControl.tableView.manCnt2.setText(tt);
			payView.payControl.tableView.womanCnt2.setText(tt2);

			break;
			
		case "���̺�_3":
			
			payView.payControl.tableView.manCnt3.setText(tt);
			payView.payControl.tableView.womanCnt3.setText(tt2);
			
			
			break;

		case "���̺�_4":
			payView.payControl.tableView.manCnt4.setText(tt);
			payView.payControl.tableView.womanCnt4.setText(tt2);
			
			break;
		
		case "���̺�_5":
			payView.payControl.tableView.manCnt5.setText(tt);
			payView.payControl.tableView.womanCnt5.setText(tt2);
			
			break;
			
		case "���̺�_6":
			payView.payControl.tableView.manCnt6.setText(tt);
			payView.payControl.tableView.womanCnt6.setText(tt2);
		
			break;
			
		case "���̺�_7":
			payView.payControl.tableView.manCnt7.setText(tt);
			payView.payControl.tableView.womanCnt7.setText(tt2);
			break;
			
		case "���̺�_8":
			payView.payControl.tableView.manCnt8.setText(tt);
			payView.payControl.tableView.womanCnt8.setText(tt2);
			break;
		}
	}
}
