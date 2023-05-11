package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Hsqlconnection {
	Connection con = null;
	Statement st = null;
	
	private boolean connectDB() {
		try {
			String driver = "org.h2.Driver";
			String url ="jdbc:h2:tcp://localhost/~/telephone"; 
			
			String username ="scott";
			String password ="tiger"; 
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Success");
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
		} return false;

	}
	
	
	private void insertDB(String dno, String dname, int budget) {
		try {
			
			String sql = "insert into dept (dno, dname, budget) values (?, ?, ?) ";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dno);
			ps.setString(2, dname);
			ps.setInt(3, budget);
			ps.executeUpdate();
		
		} 
		    catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("데이터 베이스가 입력되었습니다");

	}

	
	private void closeDB() {
		try {
			con.close();
			System.out.println("데이터 베이스가 닫혔습니다");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Hsqlconnection hsql = new Hsqlconnection();
		
		if(hsql.connectDB()) {
//			hsql.insertDB("d100", "dna100", 1123);
			for (int i = 0; i < 100; i++) {
				hsql.insertDB("d" + i, "dname" + i, 1123 +i*10 );
			}
			hsql.closeDB();
		}
	}


}
