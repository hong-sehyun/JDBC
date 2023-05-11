package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Miss1 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse1", "scott", "tiger");

			st = con.createStatement();
			rs = st.executeQuery("select sname from s,spj where spj.sno = s.sno and jno='j1'");
			while (rs.next()) {
				System.out.println(String.format("%s", rs.getString("sname")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { //리소스 닫아줌. 가비지 컬랙터의 일을 줄여주기 위해 (그냥 외우기!!!!)
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
