package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO(){
		try {
			String dbURL="jdbc:oracle:thin:@localhost:1521:orcl";
			String dbID="c##bbs";
			String dbPassword="1234";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(dbURL,dbID,dbPassword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM users WHERE userID=?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				}
				return 0; //비밀번호 불일치
			}
			return -1; //아이디 없음
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -2;//데이터 베이스 오류
		
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
}
