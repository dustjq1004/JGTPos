package hong.table;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import hong.client.DefaultClient;
import hong.server.MessageObject;
import ji.tablet.TableSetController;
import ji.tablet.TabletDAO;
import ji.tablet.TabletDTO;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TableFrame extends JFrame {
   TableFrame frame;
   private JPanel contentPane;
   ArrayList<TablePane> tablePanes;

   TableSetController tableSetController;
   TableMainController tableMainController;
   static DefaultClient defaultClient;
   JPanel tableListPanel;
   JPanel panel_3;

   JButton setUpBtn;
   JButton billBtn;
   JButton menuPanBtn;
   JButton staffCallBtn;

   TablePane tablePane;
   JScrollPane scrollPane;
   //////////////////// myTable ������ �޾ƿ� �ֵ�
   public JLabel nickNameLab;// �г���
   JLabel conceptLab;// ����
   JLabel manCntLab;// ���� ��
   JLabel womanCntLab;// ���� ��
   JLabel tableNumberLab;// ���̺� ��ȣ
   ////////////////////
   // �� �༮�� ����, �ʱ�ȭ ��ų �� �־�� �Ѵ�!
   HashMap<String, TablePane> otherTablePanes = new HashMap<String, TableFrame.TablePane>();

   ////////////////////
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               TableFrame frame = new TableFrame(defaultClient);
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public void refreshMyTableSetting(String nickName, String t_concept) {
      // TableInform my=tableMainController.getTableMainInform().getMyTableInform();
      TabletDTO tabletDTO = new TabletDAO().detail(tableMainController.clientName);
      System.out.println(tabletDTO.getTT_nickname());
      nickNameLab.setText(tabletDTO.getTT_nickname());// �г���
      conceptLab.setText(tabletDTO.getTT_concept());
      ;// ����
      manCntLab.setText("" + tabletDTO.getTT_man());// ���� ��
      womanCntLab.setText("" + tabletDTO.getTT_woman());// ���� ��
      tableNumberLab.setText(tabletDTO.getTT_name());// ���̺� ��ȣ
      tableMainController.defaultClient.sendMessage("����", "���̺���º���", "���̺�_1", "���̺�_2", "���̺�_3", "���̺�_4", "���̺�_5",
            "���̺�_6", "���̺�_7", "���̺�_8");
   }

   public TableFrame(DefaultClient defaultClient) {
      this.defaultClient = defaultClient;
      init();
   }

   TableFrame(TableMainController tableMainController) {
      this.tableMainController = tableMainController;
      this.defaultClient = tableMainController.defaultClient;
      init();
      setVisible(true);
   }

   void init() {
      tablePanes = new ArrayList<TablePane>();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(220, 70, 950, 600);
      setTitle("���̺� ����");
      setResizable(false);
      setBackground(Color.WHITE);
      contentPane = new JPanel();
      contentPane.setForeground(Color.WHITE);
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
      contentPane.setLayout(new BorderLayout(10, 10));
      setContentPane(contentPane);

      JPanel myTableInformPan = new JPanel();
      myTableInformPan.setBackground(Color.WHITE);
      contentPane.add(myTableInformPan, BorderLayout.NORTH);
      myTableInformPan.setLayout(new BorderLayout(0, 0));

      // �� ���̺� ����
      JPanel left = new JPanel();
      left.setBackground(Color.WHITE);
      myTableInformPan.add(left, BorderLayout.CENTER);
      left.setLayout(new BoxLayout(left, BoxLayout.X_AXIS));

      JLabel brandNameLab = new JLabel("������ ����  ");
      brandNameLab.setFont(new Font("�������", Font.BOLD, 30));
      left.add(brandNameLab);

      JLabel n = new JLabel(" N: ");
      n.setFont(new Font("�������", Font.BOLD, 20));
      left.add(n);

      nickNameLab = new JLabel("(�г���)");
      nickNameLab.setFont(new Font("�������", Font.BOLD, 20));
      left.add(nickNameLab);

      JLabel c = new JLabel(" C: ");
      c.setFont(new Font("�������", Font.BOLD, 20));
      left.add(c);

      conceptLab = new JLabel("(����)");
      conceptLab.setFont(new Font("�������", Font.BOLD, 20));
      left.add(conceptLab);

      JLabel manLab = new JLabel("     ��   ");
      manLab.setFont(new Font("�������", Font.BOLD, 20));
      manLab.setHorizontalAlignment(SwingConstants.RIGHT);
      left.add(manLab);

      manCntLab = new JLabel("(���ο�) ");
      manCntLab.setFont(new Font("�������", Font.BOLD, 20));
      left.add(manCntLab);

      JLabel womanLab = new JLabel("     ��   ");
      womanLab.setFont(new Font("�������", Font.BOLD, 20));
      womanLab.setHorizontalAlignment(SwingConstants.RIGHT);
      left.add(womanLab);

      womanCntLab = new JLabel("(���ο�)");
      womanCntLab.setFont(new Font("�������", Font.BOLD, 20));
      left.add(womanCntLab);

      JPanel panel_5 = new JPanel();
      panel_5.setBackground(Color.WHITE);
      myTableInformPan.add(panel_5, BorderLayout.EAST);
      panel_5.setLayout(new BorderLayout(0, 0));

      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel_5.add(panel, BorderLayout.EAST);

      JLabel noLab = new JLabel("no. ");
      noLab.setFont(new Font("�������", Font.BOLD, 27));
      noLab.setHorizontalAlignment(SwingConstants.RIGHT);
      panel.add(noLab);

      tableNumberLab = new JLabel("(���̺� ��ȣ)");
      tableNumberLab.setHorizontalAlignment(SwingConstants.RIGHT);
      tableNumberLab.setFont(new Font("�������", Font.BOLD, 27));
      panel.add(tableNumberLab);

      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.WHITE);
      contentPane.add(panel_1, BorderLayout.CENTER);
      panel_1.setLayout(new BorderLayout(10, 10));

      // ����ȣ����� ��꼭 �������� ��� ��ư�� ��. (�׺��� ������)
      JPanel panel_2 = new JPanel();
      panel_1.add(panel_2, BorderLayout.EAST);
      panel_2.setLayout(new GridLayout(3, 1, 0, 0));

      staffCallBtn = new JButton("���� ȣ��");
      staffCallBtn.setBackground(new Color(255, 69, 0));
      staffCallBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tableMainController.callWaiter();
         }
      });
      staffCallBtn.setFont(new Font("�������", Font.BOLD, 30));
      staffCallBtn.setEnabled(false);
      panel_2.add(staffCallBtn);

      menuPanBtn = new JButton("�޴���");
      menuPanBtn.setFont(new Font("�������", Font.BOLD, 30));
      menuPanBtn.setBackground(new Color(255, 99, 71));
      menuPanBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tableMainController.orderMenu();
         }
      });
      menuPanBtn.setEnabled(false);
      panel_2.add(menuPanBtn);

      JPanel panel_4 = new JPanel();
      panel_2.add(panel_4);
      panel_4.setLayout(new GridLayout(2, 1, 0, 0));

      billBtn = new JButton("��꼭");
      billBtn.setBackground(new Color(255, 160, 122));
      billBtn.setFont(new Font("�������", Font.BOLD, 30));
      billBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tableMainController.openBill();
         }
      });
      panel_4.add(billBtn);
      billBtn.setEnabled(false);

      setUpBtn = new JButton("����");
      setUpBtn.setBackground(new Color(255, 160, 122));
      setUpBtn.setFont(new Font("�������", Font.BOLD, 30));
      setUpBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tableMainController.tableSetting();
         }
      });
      setUpBtn.setEnabled(false);
      panel_4.add(setUpBtn, BorderLayout.EAST);

      // (�׺��� ����)
      panel_3 = new JPanel();
      panel_1.add(panel_3, BorderLayout.CENTER);

      panel_3.setLayout(new BorderLayout(10, 10));

      scrollPane = new JScrollPane();
      scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      panel_3.add(scrollPane, BorderLayout.CENTER);

      tableListPanel = new JPanel();
      tableListPanel.setBackground(Color.WHITE);
      tableListPanel.setForeground(Color.WHITE);
      scrollPane.setViewportView(tableListPanel);
      tableListPanel.setLayout(new GridLayout(7, 1));

   }

   void mkTablePane(String tableName, String nickName, String concept, int manCnt, int womanCnt, String doSomthing) {
      tablePanes.add(new TablePane(tableName, nickName, concept, manCnt, womanCnt, doSomthing));
   }

   // �º� ������ �� ���̺� ��Ȳ���� �������̺� ���� �г� ��ü
   class TablePane extends JPanel {
      public JButton urGameBnt;
      int manCnt;
      int womanCnt;
      String tableName;
      Image change;

      public TablePane(String tableName) {
         this.tableName = tableName;
         init("����", "�޸��� ���� ��", 0, 3, "���Ӵ��");
      }

      public TablePane(String tableName, String nickName, String concept, int manCnt, int womanCnt,
            String doSomthing) {
         this.tableName = tableName;

         init(nickName, concept, manCnt, womanCnt, doSomthing);
      }

      public void setBackColor() {
         int tmp = 3;
         if (manCnt != 0 && womanCnt != 0)
            tmp = 2;
         else if (manCnt != 0)
            tmp = 1;
         else if (womanCnt != 0)
            tmp = 0;
         Color color = new Color[] { Color.PINK, new Color(7, 200, 255), new Color(0, 254, 0), Color.WHITE }[tmp];
         setBackground(color);
      }

      void init(String nickName, String concept, int manCnt, int womanCnt, String doSomthing) {
         this.manCnt = manCnt;
         this.womanCnt = womanCnt;
         setBackColor();
         ////////// ��Ȳ��_1 (��ũ)
         setAlignmentY(Component.CENTER_ALIGNMENT);
         setPreferredSize(new Dimension(350, 100));// ���� ���̺� ���� �� ũ��
         setBorder(new LineBorder(new Color(0, 0, 0), 2));

         // 0
         tableListPanel.add(this);
         setLayout(new BorderLayout(10, 10));// 0

         // 1
         JPanel urNGCPan = new JPanel();
         urNGCPan.setOpaque(false);
         add(urNGCPan, BorderLayout.WEST);
         urNGCPan.setLayout(new BorderLayout(0, 0));

         // 2
         JPanel urNickConG = new JPanel();
         add(urNickConG, BorderLayout.CENTER);
         urNickConG.setOpaque(false);
         urNickConG.setLayout(new GridLayout(2, 0, 0, 0));

         // 2-1 ���༺�� ��
         JPanel urGCPan = new JPanel();
         urGCPan.setOpaque(false);
         urGCPan.setLayout(new BoxLayout(urGCPan, BoxLayout.X_AXIS));
         urNickConG.add(urGCPan);

         // 2-1-1 ��Ȳ�� ���� �ο��� ǥ��
         // JLabel urManLab = new JLabel("�� ");
         // urManLab.setFont(new Font("�������", Font.BOLD, 20));
         // urGCPan.add(urManLab);

         // 2-1-2
         JLabel urManCntLab = new JLabel("��  " + manCnt);
         urManCntLab.setFont(new Font("�������", Font.BOLD, 20));
         urGCPan.add(urManCntLab);

         // 2-1-3 ��Ȳ�� ���� �ο��� ǥ��
         // JLabel urWomanLab = new JLabel(" �� ");
         // urWomanLab.setFont(new Font("�������", Font.BOLD, 20));
         // urGCPan.add(urWomanLab);

         // 2-1-4
         JLabel urWomanCntLab = new JLabel("   ��  " + womanCnt);
         urWomanCntLab.setFont(new Font("�������", Font.BOLD, 20));
         urGCPan.add(urWomanCntLab);

         // 2-2
         JPanel NickCon = new JPanel();
         NickCon.setLayout(new GridLayout(0, 2, 0, 0));
         NickCon.setOpaque(false);
         urNickConG.add(NickCon);

         // 2-2-1 �г��� ǥ��
         JLabel urNickLab = new JLabel("�г��� : " + nickName);
         urNickLab.setFont(new Font("�������", Font.BOLD, 20));
         NickCon.add(urNickLab);

         // 2-2-2 ��Ȳ�� ���� ǥ��
         JLabel urConceptLab = new JLabel("���� : " + concept);
         urConceptLab.setFont(new Font("�������", Font.BOLD, 20));
         NickCon.add(urConceptLab);

         // 3 �Ǿ��ư ��
         JPanel urGamePan = new JPanel();
         urGamePan.setOpaque(false);
         add(urGamePan, BorderLayout.EAST);
         urGamePan.setLayout(new BorderLayout(0, 0));

         // 3-1 ���� ��ư
         ImageIcon croco = new ImageIcon("img/�����̴� �Ǿ�.gif");
         urGameBnt = new JButton(croco);
         urGameBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

               tableMainController.letsGame(tableName);
            }
         });
         if (doSomthing.equals("������")) {
            urGameBnt.setEnabled(false);
         } else {
            urGameBnt.setEnabled(true);
         }
         urGameBnt.setOpaque(false);
         urGameBnt.setBackground(Color.black);
         urGamePan.add(urGameBnt);

         // 1-1 ������ ��
         JPanel urIcon = new JPanel();
         urIcon.setOpaque(false);
         urNGCPan.add(urIcon, BorderLayout.CENTER);
         urIcon.setLayout(new BorderLayout(0, 0));
         urIcon.setBorder(new EmptyBorder(5, 20, 5, 30));

         if (manCnt > 0 && womanCnt > 0) {
            ImageIcon icon = new ImageIcon("img/ȥ��.png");
            Image img = icon.getImage();
            change = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
         } else if (manCnt > 0) {
            ImageIcon icon = new ImageIcon("img/����.png");
            Image img = icon.getImage();
            change = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
         } else if (womanCnt > 0) {
            ImageIcon icon = new ImageIcon("img/����.png");
            Image img = icon.getImage();
            change = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
         } else {
            ImageIcon icon = new ImageIcon("img/����.png");
            Image img = icon.getImage();
            change = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            urGameBnt.setEnabled(false);
         }
         // 1-1-1 ������ ��
         JLabel urIconLab = new JLabel(new ImageIcon(change));
         urIconLab.setOpaque(false);
         urIcon.add(urIconLab);
      }
   }
}