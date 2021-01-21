package com.infogames.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.infogames.jsp.ReviewBoardDBQuery;

public class ReviewBoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public ReviewBoardDAO() {
		try {
			Class.forName(ReviewBoardDBQuery.DRIVER);
			conn = DriverManager.getConnection(ReviewBoardDBQuery.URL, ReviewBoardDBQuery.USERID,ReviewBoardDBQuery.USERPW);
			System.out.println("FreeBoardDAO생성, 데이터베이스 연결!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
	}
	
	public ReviewBoardDTO[] createArrayList(ResultSet rs) throws SQLException {
		ArrayList<ReviewBoardDTO> list = new ArrayList<ReviewBoardDTO>();

		while (rs.next()) {
			int accNum = rs.getInt("ACCNUM");
			int boardId = rs.getInt("BOARD_ID");
			int writeId = rs.getInt("WRITE_ID");
			String subject = rs.getString("SUBJECT");
			String content = rs.getString("CONTENT");
			int viewCnt = rs.getInt("VIEWCNT");
			String id = rs.getString("ID");
			String nickName = rs.getString("NICKNAME");
			int rnum = rs.getInt("RNUM");
			int rating = rs.getInt("RATING");
			Date d = rs.getDate("REGDATE");
			Time t = rs.getTime("REGDATE");
			String regdate = "";
			if (d != null) {
				regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}

			ReviewBoardDTO dto = new ReviewBoardDTO(accNum, boardId, writeId, subject, content, viewCnt, id, nickName, rnum, rating);
			dto.setRegDate(regdate);
			list.add(dto);
		}

		int size = list.size();

		ReviewBoardDTO[] arr = new ReviewBoardDTO[size];
		list.toArray(arr);

		return arr;
	}
	
	public ReviewBoardDTO[] createArrayView(ResultSet rs) throws SQLException {
		ArrayList<ReviewBoardDTO> list = new ArrayList<ReviewBoardDTO>();

		while (rs.next()) {
			int accNum = rs.getInt("ACCNUM");
			int boardId = rs.getInt("BOARD_ID");
			int writeId = rs.getInt("WRITE_ID");
			String subject = rs.getString("SUBJECT");
			String content = rs.getString("CONTENT");
			int viewCnt = rs.getInt("VIEWCNT");
			String id = rs.getString("ID");
			String nickName = rs.getString("NICKNAME");
			String originalFileName = rs.getString("ORIGINALNAME");
			String systemFileName = rs.getString("SYSTEMNAME");
			int fileSeq = rs.getInt("FILESEQ");
			int rating = rs.getInt("RATING");
			Date d = rs.getDate("REGDATE");
			Time t = rs.getTime("REGDATE");
			String regdate = "";
			if (d != null) {
				regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}

			ReviewBoardDTO dto = new ReviewBoardDTO(accNum, boardId, writeId, subject, content, viewCnt, id, nickName, originalFileName, systemFileName, fileSeq, rating);
			dto.setRegDate(regdate);
			list.add(dto);
		}

		int size = list.size();

		ReviewBoardDTO[] arr = new ReviewBoardDTO[size];
		list.toArray(arr);

		return arr;
	}
	
	public int insert(String subject, String content, int accNum, String[] originalFileName, String[] systemFileName, int rating) throws SQLException {
		int cnt = 0;
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_INSERT);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();

			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_ID_INSERT);
			pstmt.setInt(1, accNum);
			pstmt.setInt(2, rating);
			cnt = pstmt.executeUpdate();
			
			if(originalFileName != null) {
				pstmt.close();
			
				for(int i = 0; i < originalFileName.length; i++) {
					pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_FILE_INSERT);
					pstmt.setString(1, systemFileName[i]);
					pstmt.setString(2, originalFileName[i]);
					pstmt.setInt(3, i+1);
					cnt = pstmt.executeUpdate();
				}
			}
			
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			close();
		}

		return cnt;
	}
	
	public ReviewBoardDTO[] select(int startNum, int endNum) throws SQLException {
		ReviewBoardDTO[] arr = null;

		try {
			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_SELECT);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			arr = createArrayList(rs);
		} finally {
			close();
		}

		return arr;
	}
	
	public int totalWrite() throws SQLException{
		int totalWrite = 0;
		
		try {
			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_TOTAL_CNT);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalWrite = rs.getInt(1);
			}
		} finally {
			
		}
		return totalWrite;
	}
	
	public ReviewBoardDTO[] selectById(int writeId) throws SQLException{
		ReviewBoardDTO[] arr = null;
		
		try {
			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_SELECT_BY_ID);
			pstmt.setInt(1, writeId);
			rs = pstmt.executeQuery();
			arr = createArrayView(rs);
		} finally {
			close();
		}
		
		return arr;
	}
	
	public ReviewBoardDTO[] readById(int writeId) throws SQLException {
		int cnt = 0;
		ReviewBoardDTO[] arr = null;

		try {
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_INC_VIEWCNT);
		pstmt.setInt(1, writeId);
		cnt = pstmt.executeUpdate();

		pstmt.close();

		pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_SELECT_BY_ID);
		pstmt.setInt(1, writeId);
		rs = pstmt.executeQuery();
		
		arr = createArrayView(rs);
		conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			close();
		}
		return arr;
	}
	
	public int update(String subject, String content, int writeId, int rating) throws SQLException{
		int cnt = 0;
		
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_UPDATE);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, writeId);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_RATING_UPDATE);
			pstmt.setInt(1, rating);
			pstmt.setInt(2, writeId);
			cnt = pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}  finally {
			close();
		}
		
		return cnt;
	}
	
	public int deleteById(int writeId) throws SQLException{
		int cnt = 0;
		
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARDID_DELETE_BY_ID);
			pstmt.setInt(1, writeId);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_FILE_DELETE);
			pstmt.setInt(1, writeId);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(ReviewBoardDBQuery.SQL_REVIEWBOARD_DELETE_BY_ID);
			pstmt.setInt(1, writeId);
			cnt = pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			close();
		}
		
		return cnt;
	}
}
