package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Mission2 {
	
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
	
	
	

	public static void exeQuery (String sql) {
		Connection con = connectDB();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			System.out.println("\nQuery || " + sql);
			System.out.println("\n실행결과" + "-".repeat(74));
			
			ResultSetMetaData meta = rs.getMetaData();
			int cnt = meta.getColumnCount();
			for (int i = 1 ; i <= cnt ; i++) {
				meta.getColumnName(i);
			}
			
//			while (rs.next()) {
//				for (int i = 1 ; i <= cnt ; i++) {
//					
//					System.out.print(rs.getString(i) + " ");
//				}
//			}
			while (rs.next()) {
				for (int i = 1 ; i <= 1 ; i++) {
					for (int j = 1; j <= cnt; j++) {
						System.out.print(rs.getString(j) + " ");
					} System.out.println("");
				}
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
	
	
	
	
	public String ex01 () {
		return "select jname from j where city='london'";
	}
	
	
	public static void main(String[] args) {
		
		ArrayList<QueryExe> list = new ArrayList<>();
			list.add(new QueryExe(1, "London에 있는 프로젝트 이름을 찾아라.", "select jname from j where city='london'"));
			list.add(new QueryExe(2, "프로젝트 j1에 참여하는 공급자의 이름을 찾아라.", "select sname from s,spj where spj.sno = s.sno and jno='j1'"));
			list.add(new QueryExe(3, "공급 수량이 300 이상 750 이하인 모든 공급의 sno, pno, qty를 찾아라.", "(select sno, pno, qty from spj where qty>300) union (select sno, pno, qty from spj where qty<300)"));
			list.add(new QueryExe(4, "부품의 color와 city의 모든 쌍을 찾아라. 같은 쌍은 한 번만 검색되어야 한다.", "(select color, city from p) union (select color, city from p)"));
			list.add(new QueryExe(5, "같은 도시에 있는 공급자, 부품, 프로 젝트의 모든 sno, pno, jno 쌍을 찾아라. 찾아진 sno,pno, jno의 city들은 모두 같아야 한다.", "select spj.sno, spj.pno, spj.jno from s, p, j, spj where spj.sno = s.sno and spj.pno = p.pno and spj.jno = j.jno and s.city = j.city and j.city = p.city order by s.city"));
			list.add(new QueryExe(6, "같은 도시에 있지 않는 공급자, 부품, 프로젝트의 sno, pno, jno을 찾아라. 찾아진 sno, pno, jno의 city 들은 같은 것이 없어야 한다.", "select spj.sno, spj.pno, spj.jno from s, p, j, spj where spj.sno = s.sno and spj.pno = p.pno and spj.jno = j.jno and s.city != p.city and p.city != j.city and s.city != j.city order by s.city"));
			list.add(new QueryExe(7, "London에 있는 공급자에 의해 공급된 부품의 번호, 이름을 찾아라.", "select distinct spj.pno, pname from s, p, spj where spj.sno = s.sno and spj.pno = p.pno and s.city = 'london'"));
			list.add(new QueryExe(8, "London에 있는 공급자가 London의 프로젝트에 1 공급한 부품의 부품 번호와 이름을 찾아라.", "select distinct pno, pname from p where exists (select * from s where exists (select * from j where exists (select * from spj where s.sno=spj.sno and p.pno = spj.pno and spj.jno = j.jno and s.city = 'london' and j.city = 'london')));"));
			list.add(new QueryExe(9, "한 도시에 있는 공급자가 다른 도시에 있는 프로젝트에 공급할 때 공급자 도시, 프로젝트 도시 쌍을 모두 구하라.", "select distinct s.city, j.city from s left join j on s.city != j.city"));
			list.add(new QueryExe(10, "프로젝트 j1에 참여하는 공급자의 이름을 찾아라.", "select p.pno, pname from s, j, p, spj where spj.sno = s.sno and spj.pno = p.pno and spj.jno = j.jno and s.city = j.city"));
			list.add(new QueryExe(11, "X", ""));
			list.add(new QueryExe(12, "X", ""));
			list.add(new QueryExe(13, "공급자 s1이 공급한 프로젝트 개수를 찾아라.", "select count(jno),sno from spj where sno = 's1'"));
			list.add(new QueryExe(14, "공급자 s1이 공급한 부품 p1의 전체 수량을 찾아라.", "select sum(qty),sno from spj where sno = 's1'"));
			list.add(new QueryExe(15, "프로젝트에 공급된 각 부품에 대하여, 각 부품 번호, 공급된 프로젝트 번호, 각 프로젝트에 공급된 수량을 찾아라.", "select pno, jno, qty from spj order by pno, jno"));
			list.add(new QueryExe(16, "X", ""));
			list.add(new QueryExe(17, "공급자 s1이 공급한 프로젝트 번호와 이름을 찾아라.", "select spj.sno, spj.jno, jname from s, j, spj where spj.sno = s.sno and spj.jno = j.jno and spj.sno='s1'"));
			list.add(new QueryExe(18, "공급자 s1이 공급한 부품의 color를 찾아라.", "select distinct color from s, p, spj where spj.sno = s.sno and spj.pno = p.pno and s.sno = 's1'"));

		
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			for (QueryExe qe : list) {
				System.out.println(String.format("%d, %s", qe.getNum(), qe.getText()));
			}
			
			System.out.println("\n선택<0:exit>:");
			int sel = sc.nextInt();
			if(sel == 0) break;
			
			for(QueryExe qe : list) {
				if (qe.getNum() == sel) {
				
					exeQuery(qe.getSql());
				}
				
			}
			System.out.println("종료합니다.");
		}
		sc.close();
	}
}
