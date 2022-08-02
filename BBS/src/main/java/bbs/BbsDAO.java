package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {

	private Connection conn;

	private ResultSet rs;

	public BbsDAO() {
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbID = "c##bbs";
			String dbPassword = "1234";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

//mysql
	/*
	 * public String getDate() { String SQL = "SELECT NOW()"; try {
	 * PreparedStatement pstmt = conn.prepareStatement(SQL); rs=
	 * pstmt.executeQuery(); if(rs.next()) { return rs.getString(1); } } catch
	 * (Exception e) { // TODO: handle exception e.printStackTrace(); } return "";
	 * //데이터베이스 오류 }
	 */
	public int getNext() {
		String SQL = "SELECT bbsID FROM bbs ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	public int write(String bbsTitle, String userID, String bbsContent, String strDate) {
		String SQL = "INSERT INTO bbs VALUES(?,?,?,TO_DATE(?,'yyyy/mm/dd hh24:mi:ss'),?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, strDate);
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	public ArrayList<Bbs> getList(int pageNumber){   //게시판 리스트 출력
		String SQL = "SELECT * FROM(SELECT * FROM bbs WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC) WHERE ROWNUM <= 10";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber - 1)*10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list; 
	}
	
	public boolean nextPage(int pageNumber) {  //페이징 처리
		String SQL = "SELECT * FROM bbs WHERE bbsID < ? AND bbsAvailable = 1 ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber - 1)*10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false; 
	}
	
	public Bbs getBbs(int bbsID) {  
		String SQL = "SELECT * FROM bbs WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				return bbs;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null; 
	}
	public int delete(int bbsID, String userID) {
		String SQL = "DELETE TABLE bbs WHERE bbsID=? AND userID=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, userID);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	
}
