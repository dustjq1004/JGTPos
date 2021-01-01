package ji.lock_mw;

import java.sql.*;

import java.util.*;

import aaaaaaaaaaaaaaa.InitData;


public class Lock_MW_DAO {// ���������� DB�� �����ϴ� ��ü

	Connection con; // DB�� �������ִ� ����
	Statement stmt; // ������ ���ε�, SQL�� �ؼ����ִ� ��ü
	ResultSet rs; // DB���� select�� ����� ��´�
	String sql; // ���������� �� String

	// DB����� �ʿ��� url�� ID, PW
	String url = "jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = "hr";
	String pw = "hr";

	public Lock_MW_DAO() {// DB����
		try {con = DriverManager.getConnection(url, id, pw);
			stmt = con.createStatement(); // ���� ���� �غ�
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch -> DB�����غ�
	}

	public ArrayList<Lock_MW_DTO> list() {// �����͸� ����ֱ�

		ArrayList<Lock_MW_DTO> res = new ArrayList<Lock_MW_DTO>();// *�����͸� ����ִ� ����Ʈ
		
		sql = "select * from TABLETABLET";// ���� ���๮.DB���̺� �� TABLETABLET�� ���
		
		try {	rs = stmt.executeQuery(sql);// ���� �������� �����´�. select ���� �϶��� ���
			
			while (rs.next()) {
				Lock_MW_DTO dto = new Lock_MW_DTO(); // �����͸� Object�� ��ȯ�ؼ� �������� DTO����

				dto.TT_name = rs.getString("TT_name");
				dto.TT_man = rs.getInt("TT_man");
				dto.TT_woman = rs.getInt("TT_woman");

				res.add(dto);// ������ ����� �� *�����͸� ����ִ� arraylist�� �����͸� �־��ش�.
			} // while�� - �����Ͱ������� ��
		} catch (SQLException e) {e.printStackTrace();} finally {close();} // = try-catch ������ ���� �� =
		return res;
	}

	public void close() {
		if (rs != null) try { rs.close();} catch (SQLException e) {} // resultset
		if (stmt != null) try { stmt.close();} catch (SQLException e) {} // statement
		if (con != null) try { con.close();} catch (SQLException e) {} // connection
		}// ���� �������� �� �� ���� Ȯ���ϸ鼭 �ݾ��ָ鼭 ������
	
	public int insert(Lock_MW_DTO dto) {

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ���� ������ �������ִ� ������
		sql = "insert into TABLETABLET " + "(TT_name, TT_man, TT_woman) " +
		"values " + "('" + dto.TT_name + "'," + dto.TT_man + ","+ dto.TT_woman + ")";

		System.out.println(sql); // ������ Ȯ���� ���� ���

		try { res = stmt.executeUpdate(sql); // ���� ������Ʈ
		} catch (Exception e) {e.printStackTrace();} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}
		return res;
		// �߰��� �������� ������Ʈ�� ���������� �Ǹ� ������� 1�� ��ȯ���ش�
	} // == public int insert �� =======
		
	// --------------------------------------------------------
	// == public int modify : java���� ������ ������ DB�� ���� =======
	public int modify(Lock_MW_DTO dto) {

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update TABLETABLET set TT_woman = " + dto.TT_woman +", TT_man = "+ dto.TT_man + " where TT_name = '" + dto.TT_name +"'";
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
