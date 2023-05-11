package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Hsqlconnection_교수님ver {
	private Connection con = null;
	
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
	
	
	private boolean insertDB(String dno, String dname, int budget) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate(String.format("insert into dept (dno, dname, budget) values ('%s, '%s', '%d') ", dno, dname, budget));
			return true;
		
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
		
		Hsqlconnection_교수님ver hsql = new Hsqlconnection_교수님ver();		
		if(hsql.connectDB() == true) {

			for (int i = 0; i < 100; i++) {
				if(hsql.insertDB("d" + i, "dname" + i, 1123 + i*10 ) == false) {
					System.out.println("데이터 입력에 실패하였습니다.");
					break;
				}
			}
			hsql.closeDB();
		}
	}


}
