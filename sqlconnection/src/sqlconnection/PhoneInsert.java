package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class PhoneInsert {
	
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
	
	private void insertPhoneWithStatement(Connection con) {
		Statement st = null;
		String [] types = {"hp", "tel", "office_tel", "fax", "etc"};
		Random rd = new Random();
		int totcnt = 10000;
		
		try {
			st = con.createStatement();
			
			for (int i = 1; i <= totcnt; i++) {
				int SEQ = rd.nextInt(1, 4);
				String number = "010" + "-" + (int)(Math.random()*10000) + "-" + (int)(Math.random()*10000); 				
				String type = types[rd.nextInt(5)];
				
				String sql = String.format("insert into Phone (cid, SEQ, number, type) "
						+ "values (%d, %d, '%s', '%s')", i, SEQ, number, type);
				
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
		PhoneInsert pi = new PhoneInsert();
		Connection con = pi.connectDB();
		pi.insertPhoneWithStatement(con);
		
		pi.closeDB(con);
	}

}
