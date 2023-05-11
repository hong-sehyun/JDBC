package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlC {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse1", "scott", "tiger");

			st = con.createStatement();
			rs = st.executeQuery("Select sno, pno, jno, qty from spj order by sno");

			while (rs.next()) {        //↓포매팅 된 데이터를 가져옴  자바 홈페이지에서 string.format 검색해서 %~보기 (만약 %5s로 입력하면 칸을 지정해서 출력함!!
				System.out.println(String.format("%s, %s, %s, %d", rs.getString("sno"), rs.getString("pno"),
						rs.getString("jno"), rs.getInt("qty")));
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
