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

import com.infogames.jsp.FreeBoardDBQuery;

public class FreeBoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public FreeBoardDAO() {
		try {
			Class.forName(FreeBoardDBQuery.DRIVER);
			conn = DriverManager.getConnection(FreeBoardDBQuery.URL, FreeBoardDBQuery.USERID, FreeBoardDBQuery.USERPW);
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
	
	public FreeBoardDTO[] createArrayList(ResultSet rs) throws SQLException {
		ArrayList<FreeBoardDTO> list = new ArrayList<FreeBoardDTO>();

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
			Date d = rs.getDate("REGDATE");
			Time t = rs.getTime("REGDATE");
			String regdate = "";
			if (d != null) {
				regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}

			FreeBoardDTO dto = new FreeBoardDTO(accNum, boardId, writeId, subject, content, viewCnt, id, nickName, rnum);
			dto.setRegDate(regdate);
			list.add(dto);
		}

		int size = list.size();

		FreeBoardDTO[] arr = new FreeBoardDTO[size];
		list.toArray(arr);

		return arr;
	}
	
	public FreeBoardDTO[] createArrayView(ResultSet rs) throws SQLException {
		ArrayList<FreeBoardDTO> list = new ArrayList<FreeBoardDTO>();

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
			Date d = rs.getDate("REGDATE");
			Time t = rs.getTime("REGDATE");
			String regdate = "";
			if (d != null) {
				regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}

			FreeBoardDTO dto = new FreeBoardDTO(accNum, boardId, writeId, subject, content, viewCnt, id, nickName, originalFileName, systemFileName, fileSeq);
			dto.setRegDate(regdate);
			list.add(dto);
		}

		int size = list.size();

		FreeBoardDTO[] arr = new FreeBoardDTO[size];
		list.toArray(arr);

		return arr;
	}
	
	public int insert(String subject, String content, int accNum, String[] originalFileName, String[] systemFileName) throws SQLException {
		int cnt = 0;
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_INSERT);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();

			pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_ID_INSERT);
			pstmt.setInt(1, accNum);
			cnt = pstmt.executeUpdate();
			
			if(originalFileName != null) {
				pstmt.close();
			
				for(int i = 0; i < originalFileName.length; i++) {
					pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_FILE_INSERT);
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
	
	public FreeBoardDTO[] select(int startNum, int endNum) throws SQLException {
		FreeBoardDTO[] arr = null;

		try {
			pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_SELECT);
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
			pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_TOTAL_CNT);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalWrite = rs.getInt(1);
			}
		} finally {
			
		}
		return totalWrite;
	}
	
	public FreeBoardDTO[] selectById(int writeId) throws SQLException{
		FreeBoardDTO[] arr = null;
		
		try {
			pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_SELECT_BY_ID);
			pstmt.setInt(1, writeId);
			rs = pstmt.executeQuery();
			arr = createArrayView(rs);
		} finally {
			close();
		}
		
		return arr;
	}
	
	public FreeBoardDTO[] readById(int writeId) throws SQLException {
		int cnt = 0;
		FreeBoardDTO[] arr = null;

		try {
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_INC_VIEWCNT);
		pstmt.setInt(1, writeId);
		cnt = pstmt.executeUpdate();

		pstmt.close();

		pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_SELECT_BY_ID);
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
	
	public int update(String subject, String content, int writeId) throws SQLException{
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_UPDATE);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, writeId);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	public int deleteById(int writeId) throws SQLException{
		int cnt = 0;
		
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARDID_DELETE_BY_ID);
			pstmt.setInt(1, writeId);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_FILE_DELETE);
			pstmt.setInt(1, writeId);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(FreeBoardDBQuery.SQL_FREEBOARD_DELETE_BY_ID);
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
