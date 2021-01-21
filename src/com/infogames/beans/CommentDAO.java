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

import com.infogames.jsp.CommentDBQuery;

public class CommentDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public CommentDAO() {
		try {
			Class.forName(CommentDBQuery.DRIVER);
			conn = DriverManager.getConnection(CommentDBQuery.URL, CommentDBQuery.USERID, CommentDBQuery.USERPW);
			System.out.println("CommentDAO생성, 데이터베이스 연결!!");
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
	//CommentDTO(commentId, writeId, id, nickName, commentDate, commentText, comment_parent, comment_level)
	// 쿼리 결과로 생긴 rs를 ArrayList를 거쳐 dto에 담아주는 메소드
	/*     LEVEL COMMENT_ID   WRITE_ID COMMENTD COMMENT_PARENT COMMENTTEXT  ID    NICKNAME
   			1          2       	363   20/10/21             0 	안녕하세요     mysoul2   피자만먹음          
   			1          7        	363   20/10/28         0 	363의 두번째 mysoul2   피자만먹음          
   			1         21         363   20/10/30            0     안녕하세요        mysoul2   피자만먹음          
   			1         22        	363   20/10/30         0	363의 세번째   a   		아에      */
	/*public CommentDTO[] createArrayList(ResultSet rs) throws SQLException {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();

		while (rs.next()) {			
			int commentId = rs.getInt("COMMENT_ID");
			int writeId = rs.getInt("WRITE_ID");
			Date d = rs.getDate("commentDate");
			Time t = rs.getTime("commentDate");
			String commentDate = "";
			if (d != null) {
				commentDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
			String commentText = rs.getString("commentText");
			// int commentAvailable = rs.getInt("CommentAvailable"); // 얘로 뭘 해야되지..?
			int comment_parent = rs.getInt("COMMENT_PARENT");
			int comment_level = rs.getInt("LEVEL");
			String id = rs.getString("ID");
			String nickName = rs.getString("NICKNAME");
			CommentDTO dto = new CommentDTO(commentId, writeId, id, nickName, 
					commentDate, commentText, comment_parent, comment_level);
			dto.setCommentDate(commentDate);
			list.add(dto);
		}

		int size = list.size();

		CommentDTO[] arr = new CommentDTO[size];
		list.toArray(arr);

		return arr;
	}*/
	// 메소드가 ArrayList<CommentDTO>를 반환하도록 해보기

	public ArrayList<CommentDTO> createArrayList(ResultSet rs) throws SQLException {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();

		while (rs.next()) {			
			int commentId = rs.getInt("COMMENT_ID");
			int writeId = rs.getInt("WRITE_ID");
			Date d = rs.getDate("commentDate");
			Time t = rs.getTime("commentDate");
			String commentDate = "";
			if (d != null) {
				commentDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
			String commentText = rs.getString("commentText");
			// int commentAvailable = rs.getInt("CommentAvailable"); // 얘로 뭘 해야되지..?
			int comment_parent = rs.getInt("COMMENT_PARENT");
			int comment_level = rs.getInt("LEVEL");
			String id = rs.getString("ID");
			String nickName = rs.getString("NICKNAME");
			CommentDTO dto = new CommentDTO(commentId, writeId, id, nickName, 
					commentDate, commentText, comment_parent, comment_level);
			dto.setCommentDate(commentDate);
			list.add(dto);
		}
		return list;
		/*
		int size = list.size();
		CommentDTO[] arr = new CommentDTO[size];
		list.toArray(arr);
		return arr;
		*/
	}
	/*
	COMMENT_ID   WRITE_ID  ACCNUM COMMENTD COMMENTTEXT  COMMENTAVAILABLE COMMENT_PARENT   WRITE_ID SUBJECT  CONTENT  REGDATE VIEWCNT
    2       	 363          4 	20/10/21 	  안녕하세요              1             0       		363         요즘          후기좀     20/08/28       2	*/
	// readById(writeId)쿼리 결과로 생긴 rs를 가져와 dto에 담아주는 메소드, view에서 볼 DTO를 만들어줌
	public CommentDTO[] createArrayView(ResultSet rs) throws SQLException {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		while (rs.next()) {			
			int commentId = rs.getInt("COMMENT_ID");
			int writeId = rs.getInt("WRITE_ID");
			int accNum = rs.getInt("ACCNUM");
			Date d = rs.getDate("commentDate");
			Time t = rs.getTime("commentDate");
			String commentDate = "";
			if (d != null) {
				commentDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
			String commentText = rs.getString("commentText");
			//int commentAvailable = rs.getInt("CommentAvailable"); // 얘로 뭘 해야되지..?
			int comment_parent = rs.getInt("COMMENT_PARENT");
			CommentDTO dto = new CommentDTO(commentId, writeId, accNum, commentText, comment_parent);
			dto.setCommentDate(commentDate);
			list.add(dto);
		}

		int size = list.size();

		CommentDTO[] arr = new CommentDTO[size];
		list.toArray(arr);

		return arr;
	}

	// 새로운 코드
	// 댓글 등록
	/*
	 * "select accnum from account where id = ?";
	 * "INSERT INTO COMMENTID " +
	 * "(COMMENT_ID, WRITE_ID, ACCNUM, commentText) " + "VALUES "
	 * + "(Comment_ID_SEQ.nextval, ?, (select accnum from account where id = ?), ?)";
	 */
	// insert(writeId, cId, commentText);
	public int insert(int writeId, String cId, String commentText) throws SQLException {
		int cnt = 0;
		try {
			//conn.setAutoCommit(false);
			//pstmt = conn.prepareStatement(CommentDBQuery.SQL_SELECT_ACCNUM);
			//pstmt.setInt(1, cId);
			//rs = pstmt.executeQuery();
			//if(rs.next()) accNum = rs.getInt("ACCNUM");
			// 코멘트 입력하는 쿼리문
			pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_INSERT);
			pstmt.setInt(1, writeId);
			pstmt.setString(2, cId);
			pstmt.setString(3, commentText);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		return cnt;
	}

	// 댓글 등록 - 게시글 번호, 부모댓글의 글번호, 댓글 작성자, 댓글 내용
	public int insertReply(int writeId, int comment_parent, String cId, String commentText) throws SQLException {
		
		int cnt = 0;
		try {
			// 자동 커밋을 false로 한다.
			conn.setAutoCommit(false);
			/*
			 * "INSERT INTO COMMENTID" +
				" (COMMENT_ID, WRITE_ID, ACCNUM, "
				+ "COMMENTTEXT, COMMENT_PARENT)" +
				" VALUES(Comment_ID_SEQ.nextval,?,(select accnum from account where id = ? ),?,?)";
			 */
			pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_INSERTR);
			pstmt.setInt(1, writeId); // 게시글 글번호
			pstmt.setString(2, cId); // 댓글 작성자
			pstmt.setString(3, commentText); // 댓글 내용
			pstmt.setInt(4, comment_parent); // 부모글
			cnt = pstmt.executeUpdate();
			if (cnt > 0) {				
				conn.commit(); // 완료시 커밋
			}
		} catch (Exception e) {
			try {
				conn.rollback(); // 오류시 롤백
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally {
			close();
		}
		return cnt;
	};
	// 게시글 번호를 이용하여 해당 글에 있는 댓글 목록을 가져온다.
	// CommentDTO(commentId, writeId, id, nickName, commentDate, commentText, comment_parent, comment_level)
	/*댓글 목록
	 * 쿼리문
SELECT LEVEL, C.COMMENT_ID, C.WRITE_ID, C.COMMENTDATE, C.COMMENT_PARENT, C.COMMENTTEXT, A.ID, A.NICKNAME
FROM COMMENTID C 
    INNER JOIN ACCOUNT A
    ON C.ACCNUM = A.ACCNUM
WHERE C.WRITE_ID = ?
START WITH COMMENT_PARENT = 0 --최상위노드 설정
CONNECT BY PRIOR COMMENT_ID = COMMENT_PARENT; -- 부모노드와 자식노드 연결;
실행결과
  LEVEL COMMENT_ID   WRITE_ID COMMENTD COMMENT_PARENT COMMENTTEXT  ID    NICKNAME
   1          2       	363   20/10/21             0 	안녕하세요    mysoul2   피자만먹음          
   1          7        	363   20/10/28             0 	363의 두번째 mysoul2   피자만먹음          
   1         21         363   20/10/30             0     안녕하세요    mysoul2 피자만먹음          
   1         22        	363   20/10/30             0	363의 세번째   a   		아에        
	 */
	// 코멘트가 적혀진 글을 글번호로 찾아서 해당 글의 모든 댓글을 리스트에 담는 메소드->
		// dto에 담아와 dto를 반환하는 메소드
	/*
		public CommentDTO[] selectById(int writeId) throws SQLException {
			CommentDTO[] arr = null;
			try {
				PreparedStatement pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_LIST);
				pstmt.setInt(1, writeId);
				rs = pstmt.executeQuery();
				arr = createArrayList(rs); 
			} finally {
				close();
			}
			return arr; 
			//CommentDTO(commentId, writeId, id, nickName, commentText, comment_parent, comment_level)반환
		}
		*/
	
	public ArrayList<CommentDTO> selectById(int writeId) throws SQLException {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_LIST);
			pstmt.setInt(1, writeId);
			rs = pstmt.executeQuery();
			list = createArrayList(rs); 
		} finally {
			close();
		}
		return list; 
		//CommentDTO(commentId, writeId, id, nickName, commentText, comment_parent, comment_level)반환
	}
	// 게시글 번호를 이용하여 해당 글에 있는 댓글 목록을 가져온다.
	/*
	 * "SELECT LEVEL, COMMENT_ID, WRITE_ID, " +
	 * " ACCNUM, COMMENTDATE, COMMENT_PARENT, COMMENTTEXT " +
	 * "FROM COMMENTID WHERE WRITE_ID = ? " + "START WITH COMMENT_PARENT = 0 " +
	 * "CONNECT BY PRIOR COMMENT_ID = COMMENT_PARENT";
	 */
	// 댓글 목록 가져오는 코드
	public ArrayList<CommentDTO> getCommentList(int writeId) throws SQLException {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();

		try {
			pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_LIST);
			pstmt.setInt(1, writeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentDTO comment = new CommentDTO();
				comment.setComment_level(rs.getInt("LEVEL"));
				comment.setCommentId(rs.getInt("COMMENT_ID")); // 댓글 글번호
				comment.setWriteId(rs.getInt("WRITE_ID")); // 게시글 글번호
				comment.setAccNum(rs.getInt("ACCNUM")); // 댓글 작성자번호
				comment.setCommentDate(rs.getString("COMMENTDATE")); // 댓글 작성일
				comment.setComment_parent(rs.getInt("COMMENT_PARENT")); // 부모글
				comment.setCommentText(rs.getString("COMMENTTEXT")); // 댓글 내용
				list.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		close();
		return list;
	} // end getCommentList

	// 댓글 1개의 정보 가져오기
	/*		"SELECT * FROM COMMENTID C " +
				"INNER JOIN WRITEID W " +
				"ON C.WRITE_ID = W.WRITE_ID " +
				"WHERE W.WRITE_ID = ?";
COMMENT_ID   WRITE_ID  ACCNUM COMMENTD COMMENTTEXT  COMMENTAVAILABLE COMMENT_PARENT   WRITE_ID SUBJECT  CONTENT  REGDATE VIEWCNT
         2       	 363          4 	20/10/21 	  안녕하세요              1                    363         요즘          후기좀     20/08/28       2				
			*/	
	//readById(writeId)
	public CommentDTO[] readById(int writeId) throws SQLException {
		int cnt = 0;
		CommentDTO[] arr = null;
		try {			
			pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_VIEW_SELECT);
			pstmt.setInt(1, writeId);
			rs = pstmt.executeQuery();
			arr = createArrayView(rs); 
			//CommentDTO(commentId, writeId, accNum, commentText, comment_parent);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			close();
		}
		return arr;
	}
	// "SELECT * FROM COMMENTID WHERE COMMENT_ID = ?";
	public CommentDTO[] selectByCId(int commentId) throws SQLException {
		CommentDTO[] comment = null;
		try {
			pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_SELECT);
			pstmt.setInt(1, commentId);
			rs = pstmt.executeQuery();
			comment = createArrayView(rs);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		close();
		return comment;
	} 
	/*
	 * "DELETE FROM COMMENTID " + "WHERE COMMENT_ID IN " + "(SELECT COMMENT_ID " +
	 * " FROM COMMENTID " + "START WITH COMMENT_ID = ? " +
	 * "CONNECT BY PRIOR COMMENT_ID = COMMENT_PARENT) ";
	 */
	// 댓글 삭제
	public boolean deleteComment(int commentId) throws SQLException {
		int cnt = 0;
		boolean result = false;
		try {
			conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
			pstmt = conn.prepareStatement(CommentDBQuery.SQL_DELETE);
			pstmt.setInt(1, commentId);
			cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				result = true;
				conn.commit(); // 완료시 커밋
			}
		} catch (Exception e) {
			try {
				conn.rollback(); // 오류시 롤백
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}
		close();
		return result;
	} // end deleteComment
	
	// commentId로 코멘트를 지우는 메소드
		public int delete(int commentId) throws SQLException {
			int cnt = 0;
			try {
				pstmt = conn.prepareStatement(CommentDBQuery.SQL_DELETE);
				pstmt.setInt(1, commentId);
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			return cnt;
		}
	
		// "UPDATE COMMENTID SET COMMENTTEXT = ? WHERE COMMENT_ID = ?";
		// 댓글 수정
	public boolean updateComment(CommentDTO comment) throws SQLException {
		int cnt = 0;
		boolean result = false;
		try {
			conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
			pstmt = conn.prepareStatement(CommentDBQuery.SQL_UPDATE);
			pstmt.setString(1, comment.getCommentText());
			pstmt.setInt(2, comment.getCommentId());
			cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				result = true;
				conn.commit(); // 완료시 커밋
			}
		} catch (Exception e) {
			try {
				conn.rollback(); // 오류시 롤백
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		close();
		return result;
	} // end updateComment
	// "UPDATE COMMENTID SET commentText = ? WHERE COMMENT_ID = ?";
	// 해당 commentId 녀석이 commentText를 수정한 상황, DB에 수정된 commentText 넣어주기
		public int update(int commentId, String commentText) throws SQLException {
			int cnt = 0;
			try {
				pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_UPDATE);
				pstmt.setString(1, commentText);
				pstmt.setInt(2, commentId);
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			return cnt; // 오류나면 0, 제대로 되면 실행한 쿼리 개수가 반환
		}

	// commentId로 CommentText 찾아서 DTO에 담아주는 메소드
	public CommentDTO[] selectComment(int commentId) throws SQLException {
		CommentDTO[] arr = null;

		try {// "SELECT commentText FROM comment WHERE commentID = ?";
			pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_SELECT_BY_ID);
			pstmt.setInt(1, commentId);
			rs = pstmt.executeQuery();
			arr = createArrayView(rs);
		} finally {
			close();
		}
		return arr;
	}
	


	


	public int totalWrite() throws SQLException {
		int totalWrite = 0;

		try {
			pstmt = conn.prepareStatement(CommentDBQuery.SQL_COMMENT_TOTAL_CNT);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalWrite = rs.getInt(1);
			}
		} finally {

		}
		return totalWrite;
	}


}
