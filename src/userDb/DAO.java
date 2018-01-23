package userDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userId = "scott";
	String userPw = "tiger";

	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;

	public int startConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userId, userPw);
			System.out.println("Connect Object Execute Success !");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int startStmt() {
		try {
			if (startConn() == 1) {
				stmt = conn.createStatement();
				System.out.println("Statement Object Execute Success !");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return -1;
	}

	public int selectDb(String jobName) {
		String query = "select * from job where name = 'worrior_1'";

		try {
			if (startStmt() == 1) {
				rset = stmt.executeQuery(query);

				 while(rset.next()){
					 for(int i=2;i<6;i++)
					 System.out.println(rset.getInt(i));
				 }
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return -1;
	}

}
