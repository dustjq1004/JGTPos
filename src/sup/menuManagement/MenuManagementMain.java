package sup.menuManagement;

import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sup.menu.Menu;
import sup.menu.MenuMainView;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class MenuManagementMain extends JDialog {
   
   DefaultTableModel model;
   JPanel contentPane;
   JTable menuTable;
   MMcontroller mmCon;
   int selectedIndex;
   /**
    * Launch the application.
    */
   

   /**
    * Create the frame.
    */
   public MenuManagementMain(MMcontroller mmCon,JFrame par) {
	  super(par,"�޴�����",true);
      this.mmCon = mmCon;
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(410, 40, 600, 350);
      //setTitle("�޴� ����");
      setResizable(false);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(new GridLayout(0, 2, 0, 0));
      menuTable = new JTable();
      JPanel panel_1 = new JPanel();
      contentPane.add(panel_1);
      panel_1.setLayout(new BorderLayout(0, 0));
      
      JScrollPane scrollPane = new JScrollPane(menuTable);
      
      panel_1.add(scrollPane);
      
      JPanel panel = new JPanel();
      contentPane.add(panel);
      
      JButton btnNewButton = new JButton("\uBA54\uB274 \uCD94\uAC00");
      
      btnNewButton.addActionListener(new btnAction());
      panel.setLayout(new GridLayout(0, 1, 0, 0));
      panel.add(btnNewButton);
      

      //�޴� ���� ��ư
      JButton deleteMenuBtn = new JButton("\uBA54\uB274 \uC0AD\uC81C");
      deleteMenuBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String value = (String) menuTable.getValueAt(selectedIndex, 0);
            mmCon.del_menu(value);
            menuTable = mmCon.refreshMeneegementList(model, menuTable);
            mmCon.adminView.adminControl.adminloginControl.boardView.boardControl.client.sendMessage("", "�޴�����", "���̺�_1","���̺�_2","���̺�_3","���̺�_4","���̺�_5",
                  "���̺�_6","���̺�_7","���̺�_8");
         }
      });
      panel.add(deleteMenuBtn);
      
      JButton modifyMenuBtn = new JButton("\uBA54\uB274 \uC218\uC815");
      
      //�޴� ���� ��ư
      modifyMenuBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Menu menu = new Menu(
                  (String)menuTable.getValueAt(selectedIndex, 0)
                  ,(String) menuTable.getValueAt(selectedIndex, 2)
                  ,Integer.parseInt((String) menuTable.getValueAt(selectedIndex, 1) ),
                  mmCon.MenuList.get(selectedIndex).getImageF());
            mmCon.open_menuModifyFream(MenuManagementMain.this, menu);
//            mmCon.update_menu(new Menu((String)menuTable.getValueAt(selectedIndex, 0), (String)menuTable.getValueAt(selectedIndex, 1)
//                                          ,(String) menuTable.getValueAt(selectedIndex, 3)
//                                          ,Integer.parseInt((String) menuTable.getValueAt(selectedIndex, 2))));
            menuTable = mmCon.refreshMeneegementList(model, menuTable);
         }
      });
      panel.add(modifyMenuBtn);
      
      menuTable = mmCon.insertMenu(model,menuTable);
      
      
      menuTable.addMouseListener(new mouseAction());
      setVisible(true);
   }
   
   
   //�޴� �߰�
   class btnAction implements ActionListener{
      
      @Override
      public void actionPerformed(ActionEvent e) {
         mmCon.open_menuInsertFrame(MenuManagementMain.this);
         
      }
      
   }
   class mouseAction implements MouseListener{

      @Override
      public void mouseClicked(MouseEvent e) {

         selectedIndex = menuTable.getSelectedRow();
         System.out.println(selectedIndex);
      }

      @Override
      public void mousePressed(MouseEvent e) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mouseReleased(MouseEvent e) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mouseEntered(MouseEvent e) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mouseExited(MouseEvent e) {
         // TODO Auto-generated method stub
         
      }
      
   }
}