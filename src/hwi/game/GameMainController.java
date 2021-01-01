package hwi.game;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import hong.client.DefaultClient;
import hong.server.MessageObject;
import hong.table.TableMainController;


public class GameMainController {
	int bombNum;
	AskGameJF askView;
	DefaultClient dc;
	MessageObject msgObject;
	String me;
	
	//��û��
	public GameMainController(DefaultClient dc,String me,String you) {
		rasj=new ReadyAnswerJrame("����� ������Դϴ�...");
		this.dc=dc;
	}
	//�ǽ�û��
	public GameMainController(DefaultClient dc,String me, MessageObject you) {
		askView=new AskGameJF(this, dc, me , you);
		askView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.dc=dc;
		this.me=me;
		this.msgObject=you;
	}

	//////////////////////////////////////////////////////////////////////////////
	//��û�ο� �Լ�
	ReadyAnswerJrame rasj;

	public void doAfterReply(boolean answer,MessageObject msgObject) {
		rasj.dispose();
		if(answer) { //���� �޼��� ����, ���� ����
			System.out.println("me>>>>>>>>>>>>  222"+me);
			new GameJF_2((int)msgObject.getMessageMain(), dc, me, msgObject);	
		}
		else {//���� �޼��� ������, ����
			dc.sendMessage("","���̺���º���",me);
			NoAnswerGameJF temp=new NoAnswerGameJF();
			temp.setVisible(true);
		}
	}
	//////////////////////////////////////////////////////////////////////////////
	//�ǽ�û�ο� �Լ�
	public void doAfterAnswer(boolean answer) {
		if(answer) { //���� �����ϸ� ���� �޼��� ������, ���� ����
			System.out.println();
			bombNum=(int)(Math.random()*20);
			dc.sendMessage(bombNum, "���Ӽ���", msgObject.getSender());
			askView.dispose();
			System.out.println("doAfterAnswer >>>"+me);
			System.out.println("doAfterAnswer >>>"+msgObject.getSender());
			new GameJF_2(bombNum, dc, me, msgObject);
		}
		else {//���� �޼��� ������
			dc.sendMessage("", "���Ӱ���", msgObject.getSender());

			askView.dispose();
		}
		System.out.println("���� / ���� ��ư :"+answer+","+msgObject.getSender());
	}
	
	//////////////////////////////////////////////////////////////////////////////
	//���� ���â
	class ReadyAnswerJrame extends JFrame{
		ReadyAnswerJrame(String msg){

			setBounds(520,265,400,214);
			setTitle("���� ��� â");
			setResizable(false);
			JLabel label=new JLabel( msg);
			add(label);
			setVisible(true);
		}


	}
}
