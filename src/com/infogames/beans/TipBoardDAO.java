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

import com.infogames.jsp.TipBoardDBQuery;

public class TipBoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public TipBoardDAO() {
		try {
			Class.forName(TipBoardDBQuery.DRIVER);
			conn = DriverManager.getConnection(TipBoardDBQuery.URL, TipBoardDBQuery.USERID, TipBoardDBQuery.USERPW);
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
	
	public TipBoardDTO[] createArrayList(ResultSet rs) throws SQLException {
		ArrayList<TipBoardDTO> list = new ArrayList<TipBoardDTO>();

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

			TipBoardDTO dto = new TipBoardDTO(accNum, boardId, writeId, subject, content, viewCnt, id, nickName, rnum);
			dto.setRegDate(regdate);
			list.add(dto);
		}

		int size = list.size();

		TipBoardDTO[] arr = new TipBoardDTO[size];
		list.toArray(arr);

		return arr;
	}
	
	public TipBoardDTO[] createArrayView(ResultSet rs) throws SQLException {
		ArrayList<TipBoardDTO> list = new ArrayList<TipBoardDTO>();

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

			TipBoardDTO dto = new TipBoardDTO(accNum, boardId, writeId, subject, content, viewCnt, id, nickName, originalFileName, systemFileName, fileSeq);
			dto.setRegDate(regdate);
			list.add(dto);
		}

		int size = list.size();

		TipBoardDTO[] arr = new TipBoardDTO[size];
		list.toArray(arr);

		return arr;
	}
	
	public int insert(String subject, String content, int accNum, String[] originalFileName, String[] systemFileName) throws SQLException {
		int cnt = 0;
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_INSERT);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();

			pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_ID_INSERT);
			pstmt.setInt(1, accNum);
			cnt = pstmt.executeUpdate();
			
			if(originalFileName != null) {
				pstmt.close();
			
				for(int i = 0; i < originalFileName.length; i++) {
					pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_FILE_INSERT);
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
	
	public TipBoardDTO[] select(int startNum, int endNum) throws SQLException {
		TipBoardDTO[] arr = null;

		try {
			pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_SELECT);
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
			pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_TOTAL_CNT);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalWrite = rs.getInt(1);
			}
		} finally {
			
		}
		return totalWrite;
	}
	
	public TipBoardDTO[] selectById(int writeId) throws SQLException{
		TipBoardDTO[] arr = null;
		
		try {
			pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_SELECT_BY_ID);
			pstmt.setInt(1, writeId);
			rs = pstmt.executeQuery();
			arr = createArrayView(rs);
		} finally {
			close();
		}
		
		return arr;
	}
	
	public TipBoardDTO[] readById(int writeId) throws SQLException {
		int cnt = 0;
		TipBoardDTO[] arr = null;

		try {
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_INC_VIEWCNT);
		pstmt.setInt(1, writeId);
		cnt = pstmt.executeUpdate();

		pstmt.close();

		pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_SELECT_BY_ID);
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
			pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_UPDATE);
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
			
			pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARDID_DELETE_BY_ID);
			pstmt.setInt(1, writeId);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_FILE_DELETE);
			pstmt.setInt(1, writeId);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(TipBoardDBQuery.SQL_TIPBOARD_DELETE_BY_ID);
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
