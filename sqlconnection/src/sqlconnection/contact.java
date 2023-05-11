package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class contact {
	
	Connection con = null;

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
	
	
	private void insertDB() {
		try {
			
			String sql = "insert into contact (cid, name, category, home, office,birthday) "
					//+ "values (1, 'kim' , 'friends', 'busan', 'b', '1999-01-01'); ";
					+ "values (? , ?, ? , ?, ?, ?)";

			PreparedStatement ps = con.prepareStatement(sql);
			
			
			ps.setInt(1, 2 );
			ps.setString(2, "lee");
			ps.setString(3,"etc" );
			ps.setString(4, "daegu");
			ps.setString(5, "seoul");
			ps.setDate(6, new java.sql.Date(1999-01-02));
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
		contact cont = new contact();
		if(cont.connectDB() == true) {
		cont.insertDB();}
		cont.closeDB();
	}

}
