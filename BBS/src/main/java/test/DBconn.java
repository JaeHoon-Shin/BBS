package test;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBconn {
	public static void main(String[] args) {
		String d = "oracle.jdbc.driver.OracleDriver";
		String u = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user ="c##scott";
		String pw ="tiger";
		
		try {
			Class.forName(d);
			
			DriverManager.getConnection(u,user,pw);
			System.out.println("성공");
		} catch (ClassNotFoundException e) {
			System.out.println("실패");
			// TODO: handle exception
		}catch (SQLException e) {
			System.out.println("ff");
			// TODO: handle exception
		}
	}
}
