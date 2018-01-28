package userDAO;

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

	public int modifyDb(DTO dto) {
		String query = "update job set skill_1='" + dto.getSkill_1() + "', skill_2='" + dto.getSkill_2() + "', skill_3='" + dto.getSkill_3() + "', skill_4='" + dto.getSkill_4() + "' where name='" + dto.getName() + "'";
		try {
			if (startStmt() == 1) {
			return stmt.executeUpdate(query);
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
		return 0;
	}

	public DTO selectDb(String job) {
		String query = "select * from job where name = '" + job + "'";
		int[] skillIdx = new int[4];
		DTO dto = new DTO();
		try {
			if (startStmt() == 1) {
				rset = stmt.executeQuery(query);

				while (rset.next()) {
					dto.setName(rset.getString(1));
					skillIdx[0] = rset.getInt(2);
					skillIdx[1] = rset.getInt(3);
					skillIdx[2] = rset.getInt(4);
					skillIdx[3] = rset.getInt(5);
					dto.setSkillIdx(skillIdx);
				}
			}
			return dto;
			// return dto;
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

		return dto;
	}

}
