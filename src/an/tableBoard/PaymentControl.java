package an.tableBoard;

import sup.menu.OrderListDBControl;

public class PaymentControl {
	public int tot;
	public PaymentView payView;
	public TableBoardView tableView;
	public String tableNum;
	public PaymentControl(TableBoardView tableView, String tableNum) {
			this.tableView = tableView;
			this.tableNum = tableNum;
			payView = new PaymentView(this);

			totPrice();
	}

	public void totPrice() {
		System.out.println("?");
	       tot= 0;
	      for (PaymentDTO pay : payView.payment) {
	    	  
	    	  
	         tot += pay.bills_price;
	         System.out.println(tot);
	
	      }
	      
	      payView.textField_11.setText(tot+" "); // �ؽ�Ʈ �ʵ忡 �� �� ��.
	      
	   }
}
