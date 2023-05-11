package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Category {

		private Connection connectDB() {
			try {
				Class.forName("org.h2.Driver");
				Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/telephone", "scott", "tiger");
				System.out.println("Connection Success");
				return con;
				
			}catch(Exception e) {
				e.printStackTrace();
			} return null;

		}

	private void insertContactWithStatement(Connection con) {
		Statement st = null;
		String [] cates = {"friends", "coworkers", "family", "etc"};
		Random rd = new Random();
		int totcnt = 10000;
		
		try {
			st = con.createStatement();
			
			for (int i = 1; i <= totcnt; i++) {
				String name = "name" + i;
				String category = cates[rd.nextInt(4)]; //0~3
				String home = "home" + i;
				String office = "office" + i;
				String birthday = String.format("%4d-%02d-%02d", rd.nextInt(1950, 2022), rd.nextInt(1, 13), rd.nextInt(1, 29));
				
				String sql = String.format("insert into contact (cid, name, category, home, office, birthday) "
						+ "values (%d, '%s', '%s', '%s', '%s', '%s')", i, name, category, home, office, birthday);
				
				System.out.println(String.format("%.2f:%d/%d", i*100/(double)totcnt, i, totcnt));
				
				System.out.println(sql);
				st.execute(sql);
				
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void closeDB(Connection con) {
		try {
			con.close();
			System.out.println("데이터 베이스가 닫혔습니다");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Category ct = new Category();
		
		Connection con = ct.connectDB();
		
		ct.insertContactWithStatement(con);
		
		ct.closeDB(con);
	}

	
}
