package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Test02 {

	Connection con = null;
	Statement st = null;
	

//	private void insertDeptStatement (String dno, String dname, int budget) {
//		String sql = String.format("insert into dept (dno, dname, budget) values ('%s', '%s', %d)", dno, dname, budget);
//		try {
//			st = con.createStatement();
//			st.executeUpdate(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	
//	}
	
	

	private void insertDeptStatement (String dno, String dname, int budget) {
		//int cnt = st.executeUpdate(String.format("insert into dept (dno, dname, budget) values ('%s', '%s', %d)", dno, dname, budget));
		try {
			
			int cnt = con.createStatement().executeUpdate(String.format("insert into dept (dno, dname, budget) values ('%s', '%s', %d)", dno, dname, budget));

			System.out.println("데이터가" + cnt +"개 입력되었습니다");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	private void insertDept (String dno, String dname, int budget) {
		String sql = "insert into dept (dno, dname, budget) value (?, ?, ?) ";
		
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					
					ps.setString(1, dno);
					ps.setString(2, dname);
					ps.setInt(3, budget);
					ps.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		System.out.println("데이터 베이스가 입력되었습니다");
		
	}
	
	
	private boolean connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse1", "scott", "tiger");
			
			System.out.println("데이터 베이스가 연결되었습니다");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void closeDB() {
		try {
			con.close();
			System.out.println("데이터 베이스가 닫혔습니다");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteDeptTriggerStatement(int from, int to) {
		try{
		Statement st = con.createStatement();
		
		int cnt = st.executeUpdate(String.format("delete from depttrigger where %d <= id and id <= %d", from, to));
		System.out.println("데이터가" + cnt +"개 삭제되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void updateDeptTriggerPrepared(String dno, String dname, int budget) {
		
		try {
			PreparedStatement ps = con.prepareStatement("update dept set dname=?, budget=? where dno=?");
			
			ps.setString(1, dname);
			ps.setInt(2, budget);
			ps.setString(3, dno);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("데이터가 업데이트 되었습니다");
		
	}
	
	private void updateDeptTriggerStatement(String dno, String dname, int budget) {
		try{
			Statement st = con.createStatement();			
			st.executeUpdate(String.format("update dept set dname '%s', budget %d where dno = '%s'", dname, budget , dno));
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}


	public static void main(String[] args) {
		Test02 tt = new Test02();
		if(tt.connectDB()) {
			//tt.insertDept("d3", "dname12", 1123);
			//tt.insertDeptStatement("d30", "dname12", 1123);
			//tt.deleteDeptTriggerStatement(1,3);
			//tt.updateDeptTriggerPrepared("d7", "dname7", 100);
			tt.updateDeptTriggerStatement("d7", "dname7" , 100);
			tt.closeDB();
			
		}
	}

}
