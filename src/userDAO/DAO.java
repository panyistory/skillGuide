package userDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

public class DAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userId = "scott";
	String userPw = "tiger";

	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;

	String query = null;

	public DAO() {

	}

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

	public int userJoin(DTO userInputDto) {
		int status = 0;
		if (startStmt() == 1) {
			query = "SELECT ID FROM SKILLGUIDE_USERDB WHERE ID='" + userInputDto.id + "'";
			try {
				rset = stmt.executeQuery(query);
				if (!rset.next()) {
					query = "INSERT INTO SKILLGUIDE_USERDB VALUES('" + userInputDto.id + "', '" + userInputDto.pw
							+ "', '" + userInputDto.eMail + "')";
					stmt.executeUpdate(query);
				}
				
				status = 1;
			} catch (Exception e) {
				e.printStackTrace();
				status = 0;
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
		}
		return status;
	}

	public int userLogin(DTO userInputDto) {
		int status = 0;
		if (startStmt() == 1) {
			query = "SELECT ID, PW FROM SKILLGUIDE_USERDB WHERE ID='" + userInputDto.id + "' and PW='" + userInputDto.pw +"'";
			try {
				rset = stmt.executeQuery(query);
				
				if(rset.next())
					status = 1;
				else
					status = 0;
				
			} catch (Exception e) {
				e.printStackTrace();
				status = 0;
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
		}
		return status;
	}
}
