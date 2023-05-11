package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

class QueryExe {
	int num ;
	String text;
	String sql;
	
	QueryExe(int num, String text) {
		this.num = num;
		this.text = text;
	}

	QueryExe(int num, String text, String sql) {
		this.num = num;
		this.text = text;
		this.sql = sql;
	}
	
	int getNum() {
		return num;
	}
	String getText() {
		return text;
	}
	String getSql() {
		return sql;
	}
}







public class Mission {
	
	public static Connection connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse1", "scott", "tiger");
			System.out.println("Connection Success");
			return con;
			
		}catch(Exception e) {
			e.printStackTrace();
		} return null;
	}
	
	public static void closeDB(Connection con) {
		try {
			con.close();
			System.out.println("데이터 베이스가 닫혔습니다");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void ex01 () {
		Connection con = connectDB();
		Statement st = null;
		ResultSet rs = null;
		System.out.println("\nQuery || select jname from j where city='london'");
		System.out.println("\n실행결과" + "-".repeat(74));
		try {
			st = con.createStatement();
			rs = st.executeQuery("select jname from j where city='london'");
			while (rs.next()) {
				System.out.println(String.format("%s", rs.getString("jname")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		System.out.println("-".repeat(80));
		closeDB(con);
	}
	
	public static void ex02 () {
		
		Connection con = connectDB();
		Statement st = null;
		ResultSet rs = null;
		System.out.println("\nQuery || select sname from s,spj where spj.sno = s.sno and jno='j1'");
		System.out.println("\n실행결과" + "-".repeat(74));
		try {
			st = con.createStatement();
			rs = st.executeQuery("select sname from s,spj where spj.sno = s.sno and jno='j1'");
			while (rs.next()) {
				System.out.println(String.format("%s", rs.getString("sname")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		System.out.println("-".repeat(80));
		closeDB(con);
	}
	
	public static void ex03 () {
		
		Connection con = connectDB();
		Statement st = null;
		ResultSet rs = null;
		System.out.println("\nQuery || ");
		System.out.println("\n실행결과" + "-".repeat(74));
		try {
			st = con.createStatement();
			rs = st.executeQuery("(select sno, pno, qty from spj where qty>300) union (select sno, pno, qty from spj where qty<300)");
			while (rs.next()) {
				System.out.println(String.format("%s, %s, %d", rs.getString("sno"), rs.getString("pno"), rs.getInt("qty")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		System.out.println("-".repeat(80));
		closeDB(con);
	}

	public static void ex04 () {
		Connection con = connectDB();
		Statement st = null;
		ResultSet rs = null;
		System.out.println("\nQuery || ");
		System.out.println("\n실행결과" + "-".repeat(74));
		try {
			st = con.createStatement();
			rs = st.executeQuery("(select color, city from p) union (select color, city from p)");
			while (rs.next()) {
			System.out.println(String.format("%s, %s", rs.getString("color"), rs.getString("city")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		System.out.println("-".repeat(80));
		closeDB(con);

	}
	
	public static void ex05 () {
		
		Connection con = connectDB();
		Statement st = null;
		ResultSet rs = null;
		System.out.println("\nQuery || ");
		System.out.println("\n실행결과" + "-".repeat(74));
		try {
			st = con.createStatement();
			rs = st.executeQuery("select spj.sno, spj.pno, spj.jno from s, p, j, spj where spj.sno = s.sno and spj.pno = p.pno and spj.jno = j.jno and s.city = j.city and j.city = p.city order by s.city");
			while (rs.next()) {
				System.out.println(String.format("%s, %s, %s", rs.getString("spj.sno"), rs.getString("spj.pno"), rs.getString("spj.jno")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		System.out.println("-".repeat(80));
		closeDB(con);
	}
	
	public static void ex06 () {
		
		Connection con = connectDB();
		Statement st = null;
		ResultSet rs = null;
		System.out.println("\nQuery || ");
		System.out.println("\n실행결과" + "-".repeat(74));
		try {
			st = con.createStatement();
			rs = st.executeQuery("select spj.sno, spj.pno, spj.jno from s, p, j, spj where spj.sno = s.sno and spj.pno = p.pno and spj.jno = j.jno and s.city != p.city and p.city != j.city and s.city != j.city order by s.city");
			while (rs.next()) {
				System.out.println(String.format("%s, %s, %s", rs.getString("spj.sno"), rs.getString("spj.pno"), rs.getString("spj.jno")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		System.out.println("-".repeat(80));
		closeDB(con);
	}
	
	public static void ex07 () {
		
		Connection con = connectDB();
		Statement st = null;
		ResultSet rs = null;
		System.out.println("\nQuery ||" + "\nselect distinct spj.pno, pname, spj.sno, s.city\r\n"
				+ "from s, p, spj\r\n"
				+ "where spj.sno = s.sno\r\n"
				+ "and spj.pno = p.pno\r\n"
				+ "and s.city = 'london';");
		System.out.println("\n실행결과" + "-".repeat(74));
		try {
			st = con.createStatement();
			rs = st.executeQuery("select distinct spj.pno, pname from s, p, spj "
					+"where spj.sno = s.sno and spj.pno = p.pno and s.city = 'london'");
						
			while (rs.next()) {
				System.out.println(String.format("%s, %s", rs.getString("spj.pno"), rs.getString("pname")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		System.out.println("-".repeat(80));
		closeDB(con);
	}
	
	

	public static void main(String[] args) {
		
		
		ArrayList<QueryExe> list = new ArrayList<>();
			list.add(new QueryExe(1, "London에 있는 프로젝트 이름을 찾아라."));
			list.add(new QueryExe(2, "프로젝트 j1에 참여하는 공급자의 이름을 찾아라."));
			list.add(new QueryExe(3, "공급 수량이 300 이상 750 이하인 모든 공급의 sno, pno, qty를 찾아라."));
			list.add(new QueryExe(4, "부품의 color와 city의 모든 쌍을 찾아라. 같은 쌍은 한 번만 검색되어야 한다."));
			list.add(new QueryExe(5, "같은 도시에 있는 공급자, 부품, 프로 젝트의 모든 sno, pno, jno 쌍을 찾아라. 찾아진 sno,pno, jno의 city들은 모두 같아야 한다."));
			list.add(new QueryExe(6, "같은 도시에 있지 않는 공급자, 부품, 프로젝트의 sno, pno, jno을 찾아라. 찾아진 sno, pno, jno의 city 들은 같은 것이 없어야 한다."));
			list.add(new QueryExe(7, "London에 있는 공급자에 의해 공급된 부품의 번호, 이름을 찾아라."));
			list.add(new QueryExe(8, ""));


		
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			for (QueryExe qe : list) {
				System.out.println(String.format("%d, %s", qe.getNum(), qe.getText()));
			}
			
			System.out.println("선택<0:exit>:");
			int sel = sc.nextInt();
			if(sel == 0) break;
			
			for(QueryExe qe : list) {
				if (qe.getNum() == sel) {
					switch(sel) {
						case 1 : ex01();  break;
						case 2 : ex02();  break;
						case 3 : ex03();  break;
						case 4 : ex04();  break;
						case 5 : ex05();  break;
						case 6 : ex06();  break;
						case 7 : ex07();  break;


						default : System.out.println("제대로 선택하도록...");
					}					
				}
				
			}
			System.out.println("종료합니다.");
		}
		sc.close();
	}
}
