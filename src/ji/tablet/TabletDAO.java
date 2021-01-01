package ji.tablet;

import java.sql.*;

import java.util.*;

import aaaaaaaaaaaaaaa.InitData;


public class TabletDAO {// ���������� DB�� �����ϴ� ��ü

	Connection con; // DB�� �������ִ� ����
	Statement stmt; // ������ ���ε�, SQL�� �ؼ����ִ� ��ü
	ResultSet rs; // DB���� select�� ����� ��´�
	String sql; // ���������� �� String

	// DB����� �ʿ��� url�� ID, PW
	String url = "jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = "hr";
	String pw = "hr";

	public TabletDAO() {
		try {

			// DB����
			con = DriverManager.getConnection(url, id, pw);

			stmt = con.createStatement(); // ���� ���� �غ�

		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch -> DB�����غ�
	}
	
//	public String create_TableName(String ) {
//		//�� ���� �ɶ� ���̺� �̸� ����
//	}

	// �����͸� ����ֱ�
	public ArrayList<TabletDTO> list() {

		ArrayList<TabletDTO> res = new ArrayList<TabletDTO>();
		// *�����͸� ����ִ� ����Ʈ
		sql = "select * from TABLETABLET";
		// ���� ���๮.DB���̺� �� TABLETABLET�� ���

		try {

			rs = stmt.executeQuery(sql);
			// ���� �������� �����´�. select ���� �϶��� ���

			while (rs.next()) {

				TabletDTO dto = new TabletDTO(); // �����͸� Object�� ��ȯ�ؼ� �������� DTO����

				dto.TT_name = rs.getString("TT_name");
				dto.TT_nickname = rs.getString("TT_nickname");
				dto.TT_concept = rs.getString("TT_concept");
				dto.TT_do = rs.getString("TT_do");
				dto.TT_man = rs.getInt("TT_man");
				dto.TT_woman = rs.getInt("TT_woman");

				res.add(dto);// ������ ����� �� *�����͸� ����ִ� arraylist�� �����͸� �־��ش�.
			} // while�� - �����Ͱ������� ��

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		} // = try-catch ������ ���� �� =
		return res;
	}
	public TabletDTO detail(String tT_name) {

		TabletDTO res = new TabletDTO();
		// *�����͸� ����ִ� ����Ʈ
		sql = "select * from TABLETABLET where TT_name = '"+tT_name+"'";
		// ���� ���๮.DB���̺� �� TABLETABLET�� ���

		try {

			rs = stmt.executeQuery(sql);
			// ���� �������� �����´�. select ���� �϶��� ���

			while (rs.next()) {

				TabletDTO dto = new TabletDTO(); // �����͸� Object�� ��ȯ�ؼ� �������� DTO����

				res.TT_name = rs.getString("TT_name");
				res.TT_nickname = rs.getString("TT_nickname");
				res.TT_concept = rs.getString("TT_concept");
				res.TT_do = rs.getString("TT_do");
				res.TT_man = rs.getInt("TT_man");
				res.TT_woman = rs.getInt("TT_woman");

				// ������ ����� �� *�����͸� ����ִ� arraylist�� �����͸� �־��ش�.
			} // while�� - �����Ͱ������� ��

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		} // = try-catch ������ ���� �� =
		return res;
	}

	public void close() {
		if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
		if(stmt!=null)try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
		if(con!=null)try {con.close();} catch (SQLException e) {e.printStackTrace();}
	}

	public int insert(TabletDTO dto) {

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ���� ������ �������ִ� ������
		sql = "insert into TABLETABLET " + "(TT_nickname, TT_concept, TT_do, TT_name, TT_man, TT_woman) " +
		"values " + "('" + dto.TT_nickname + "','" + dto.TT_concept + "','" + dto.TT_do + "'," + dto.TT_name + "," + dto.TT_man + ","
				+ dto.TT_woman + ")";
		// �� MemberMain���� �߰����� ������ �޾ƿͼ� DB�־��� �������� �������

		System.out.println(sql); // ������ Ȯ���� ���� ���

		try {

			res = stmt.executeUpdate(sql); // ���� ������Ʈ

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// �߰��� �������� ������Ʈ�� ���������� �Ǹ� ������� 1�� ��ȯ���ش�
	} // == public int insert �� =======
		// --------------------------------------------------------

	// --------------------------------------------------------
	// == public int modify : java���� ������ ������ DB�� ���� =======
	public int modify(TabletDTO dto) {

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update TABLETABLET set TT_nickname = '" + dto.TT_nickname + "', TT_concept = '" + dto.TT_concept
				+"'  where TT_name = '" + dto.TT_name+"'" ;
		// �� MemberMain������������ ������ �޾ƿͼ� DB�־��� �������� �������

		
		
		System.out.println(sql); // ������ Ȯ���� ���� ���

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	} // == public int modify �� =======
		// --------------------------------------------------------
	public int modify(TabletDTO dto,String tableNum) {

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update TABLETABLET set TT_nickname = '" + dto.TT_nickname + "', TT_concept = '" + dto.TT_concept
				+"', TT_MAN = "+dto.getTT_man()+", TT_Woman = "+dto.getTT_woman()+", TT_do='���Ӵ��' where TT_name = '" + tableNum+"'" ;
		// �� MemberMain������������ ������ �޾ƿͼ� DB�־��� �������� �������

		
		
		System.out.println(sql); // ������ Ȯ���� ���� ���

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	}
	public int modifystartGame(String tableNum, Object you) {

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update TABLETABLET set TT_do = '������' where (TT_name = '"+tableNum+"' or TT_name = '"+you+"')"; 
		// �� MemberMain������������ ������ �޾ƿͼ� DB�־��� �������� �������

		
		
		System.out.println(sql); // ������ Ȯ���� ���� ���

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	}
	public int modifyEnd(String tableNum, Object you) {

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update TABLETABLET set TT_do = '���Ӵ��' where (TT_name = '"+tableNum+"' or TT_name = '"+you+"')"; 
		// �� MemberMain������������ ������ �޾ƿͼ� DB�־��� �������� �������

		
		
		System.out.println(sql); // ������ Ȯ���� ���� ���

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	}
	// --------------------------------------------------------
	// == public int delete : java���� ������ ������ DB���� ���� =======
	public int delete(String TT_name) { // ������ ���̵𰪸� �޾ƿͼ� Ȯ�� �� �����Ѵ�

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "delete from TABLETABLET where TT_name = '" + TT_name + "'";
		// �� MemberMain���� �����ϱ� ���� �޾ƿ� ���̵� ���� DB���� ã�Ƽ� �����Ѵ�

		System.out.println(sql); // ������ Ȯ���� ���� ���

		try {

			res = stmt.executeUpdate(sql); // ������ ������ ������Ʈ

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	} // == public int delete �� =======

}
