package ji.tablet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TableSetUpFrame extends JFrame  {

   TableSetController tableSetController;
   ButtonGroup bg1;
   public JButton pass;
   public JButton table_SetUp_Bnt;
   private JPanel contentPane;
   private JTextField nickNameTextField ;
   JLabel result;
   
   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               TableSetUpFrame frame = new TableSetUpFrame();
//               frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the frame.
    */
   
   public TableSetUpFrame() {
      init();
   }
   public TableSetUpFrame(TableSetController tableSetController) {
      this.tableSetController=tableSetController;
      init();
      setVisible(true);
   }
   void init() {
      setBounds(400, 200, 612, 210);
      setTitle("�г���/����");
      setResizable(false);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      JPanel nickNameSetUpPanel = new JPanel();
      contentPane.add(nickNameSetUpPanel, BorderLayout.NORTH);
      nickNameSetUpPanel.setLayout(new BorderLayout(0, 0));
      
      JLabel youLab = new JLabel("����� ");
      nickNameSetUpPanel.add(youLab, BorderLayout.WEST);
      
      JLabel areLab = new JLabel(" �Դϴ�.");
      nickNameSetUpPanel.add(areLab, BorderLayout.EAST);
      
      JPanel panel_5 = new JPanel();
      nickNameSetUpPanel.add(panel_5, BorderLayout.CENTER);
      panel_5.setLayout(new BorderLayout(0, 0));
      
      JLabel nickNamelab = new JLabel("�г����� ");
      panel_5.add(nickNamelab, BorderLayout.WEST);
      
      nickNameTextField = new JTextField("GUEST");
      panel_5.add(nickNameTextField, BorderLayout.CENTER);
      nickNameTextField.setColumns(10);
      
       JPanel panel = new JPanel();
         nickNameSetUpPanel.add(panel, BorderLayout.SOUTH);
         panel.setLayout(new GridLayout(0, 2, 1, 0));

         JLabel nickN_regex = new JLabel("           * �ѱ۷� 5���� ���Ϸ� �Է����ּ���.");
         nickN_regex.setHorizontalAlignment(SwingConstants.LEFT);
         panel.add(nickN_regex);
      
      JPanel cenceptSepUpPanel = new JPanel();
      contentPane.add(cenceptSepUpPanel, BorderLayout.CENTER);
      cenceptSepUpPanel.setLayout(new BorderLayout(0, 0));
      
      JPanel panel_3 = new JPanel();
      cenceptSepUpPanel.add(panel_3, BorderLayout.NORTH);
      
      JLabel concept = new JLabel("������ ���� ������ �������ּ��� !");
      panel_3.add(concept);
      
      JPanel conceptBntPanel = new JPanel();
      cenceptSepUpPanel.add(conceptBntPanel, BorderLayout.CENTER);
      
      JToggleButton conceptBnt1 = new JToggleButton("���عޱ� ����"); //�������ִ� ��ư �����ϱ�//true�� �����ִ�.
      conceptBnt1.setBounds(20, 210, 70, 30);
      conceptBntPanel.add(conceptBnt1);
      JToggleButton conceptBnt2 = new JToggleButton("������ ���ܸ�");
      conceptBnt2.setBounds(90, 210, 70, 30);
      conceptBntPanel.add(conceptBnt2);
      JToggleButton conceptBnt3 = new JToggleButton("�޸������ ��");
      conceptBnt3.setBounds(160, 210, 70, 30);
      conceptBntPanel.add(conceptBnt3);
      bg1 = new ButtonGroup(); //��ư�� �����ִ� �� - ���1�� �������� ���2�� Ǯ������ ���ھ
      bg1.add(conceptBnt1);
      bg1.add(conceptBnt2);
      bg1.add(conceptBnt3);
      
      
      JPanel panel_2 = new JPanel();
      contentPane.add(panel_2, BorderLayout.SOUTH);
      panel_2.setLayout(new BorderLayout(0, 0));
      
      //Ȯ�ι�ư ������ �г��Ӱ� ������ DB�� �ڷ� �ְ� ���̺��º��� �߰� �ϱ�
      table_SetUp_Bnt = new JButton("Ȯ��");
      table_SetUp_Bnt.setEnabled(false);
      table_SetUp_Bnt.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
             String strNN = nickNameTextField.getText();

                  if (Pattern.matches("[��-�R]{1,5}", strNN)) {
                  String tT_concept= "";
            tableSetController.tableMaincontroller.openBtn();
      
            if(conceptBnt1.isSelected())tT_concept+=conceptBnt1.getText();
            else if(conceptBnt2.isSelected())tT_concept+=conceptBnt2.getText();
            else if(conceptBnt3.isSelected())tT_concept+=conceptBnt3.getText();
            tableSetController.setNickNameNConcept(strNN, tT_concept ,tableSetController.clientName);
            tableSetController.refreshTable(strNN, tT_concept);
            dispose();//Ȯ�� ��ư ������ â �ݱ�
                  }else if(!Pattern.matches("[��-�R]{1,5}", strNN)){
                      result.setText("���Ŀ� �����ʽ��ϴ�.");
                      result.setForeground(Color.RED);
                   }
            }
         });
      panel_2.add(table_SetUp_Bnt);
      
      result = new JLabel(" ");
         result.setHorizontalAlignment(SwingConstants.LEFT);
         panel.add(result);
      
      //�ǳʶٱ� ��ư ������ â�� ������ �����
      pass = new JButton("�ǳʶٱ�");
      pass.setEnabled(false);      
      panel_2.add(pass, BorderLayout.EAST);
      
      pass.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) 
         
         {
            tableSetController.tableMaincontroller.openBtn();

               tableSetController.setNickNameNConcept("GUEST", "   ", tableSetController.clientName);
               tableSetController.refreshTable("GUEST", "   ");
               dispose();// �ǳʶٱ� ��ư ������ â �ݱ�
         }
      });
      
      
   }

}