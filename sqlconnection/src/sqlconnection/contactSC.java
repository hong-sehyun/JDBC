package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class contactSC {
	
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
	
	
	private void searchDB() {
		Statement st = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);

		try {
			st = con.createStatement();	
			String sql = "select * from contact where name = " + sc.next();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {        //↓포매팅 된 데이터를 가져옴  자바 홈페이지에서 string.format 검색해서 %~보기 (만약 %5s로 입력하면 칸을 지정해서 출력함!!
				System.out.println(String.format("%s", rs.getString("name")));
			}

		
		} 
		    catch (Exception e) {
			e.printStackTrace();
		}
		sc.close();

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
		contactSC cont = new contactSC();
		if(cont.connectDB() == true) {
		cont.searchDB();
		}
		cont.closeDB();
	}

}

