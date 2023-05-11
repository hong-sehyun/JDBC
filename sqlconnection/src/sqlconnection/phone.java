package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class phone {
	
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
			
			String sql = "insert into phone (cid, seq, number, type) "
					//+ "values (1, 10 , '01012341111', 'hp'); ";
					+ "values (? , ? , ? , ?)";

			PreparedStatement ps = con.prepareStatement(sql);
					
			
			ps.setInt(1, 2 );
			ps.setInt(2, 12);
			ps.setString(3,"01012342222" );
			ps.setString(4, "hp");
			
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
		phone cont = new phone();
		if(cont.connectDB() == true) {
		cont.insertDB();
		}
		cont.closeDB();
	}

}
