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

	int[] skillIdx = null;
	int i = 2;

	public int startConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userId, userPw);
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
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int selectDb(String jobName) {
		String query = "select * from job where name = '" + jobName + "'";

		try {
			if (startStmt() == 1) {
				rset = stmt.executeQuery(query);

				while (rset.next()) {
					while (rset.getString(i) != null)
						i++;

					skillIdx = new int[i - 2];

					for (i = 2; rset.getString(i) != null; i++)
						skillIdx[i - 2] = rset.getInt(i);
				}
			}
		} catch (Exception e) {
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
				e2.printStackTrace();
			}
		}

		return -1;
	}

}
