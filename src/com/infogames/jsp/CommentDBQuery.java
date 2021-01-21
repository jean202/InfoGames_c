package com.infogames.jsp;

public class CommentDBQuery {
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USERID = "scott5";
	public static final String USERPW = "tiger5";
	
	// select sequence
	public static final String SQL_COMMENT_SEQ =
			"SELECT COMMENT_SEQ.NEXTVAL FROM DUAL";
	// select accnum
	public static final String SQL_SELECT_ACCNUM =
			"SELECT ACCNUM FROM ACCOUNT WHERE ID = ?";
	// select id, nickname
	public static final String SQL_SELECT_IDN =
			"SELECT A.ID, A.NICKNAME " +
					"FROM ACCOUNT A INNER JOIN COMMENTID C " +
			"ON C.ACCNUM = A.ACCNUM WHERE C.WRITE_ID = ?";
	
	// selectById(int writeId)
	public static final String SQL_COMMENT_LIST =
			"SELECT LEVEL, C.COMMENT_ID, C.WRITE_ID, C.COMMENTDATE, C.COMMENT_PARENT, C.COMMENTTEXT, A.ID, A.NICKNAME " + 
			"FROM COMMENTID C " + 
			"INNER JOIN ACCOUNT A " + 
			"ON C.ACCNUM = A.ACCNUM " + 
			"WHERE C.WRITE_ID = ? " + 
			"START WITH COMMENT_PARENT = 0 " + 
			"CONNECT BY PRIOR COMMENT_ID = COMMENT_PARENT";
//삭제하려는 댓글 글번호(comment_num)부터 계층을 이루는 모든 댓글을 검색하여 같이 삭제하도록 한다.
	// deleteComment
	public static final String SQL_DELETE =
			"DELETE FROM COMMENTID " +
			"WHERE COMMENT_ID IN " +
    			"(SELECT COMMENT_ID " +
    				" FROM COMMENTID " +
    					"START WITH COMMENT_ID = ? " +
    					"CONNECT BY PRIOR COMMENT_ID = COMMENT_PARENT) ";

	// delete(int commentId) : commentId에 해당하는 코멘트 코멘트 테이블에서 해당 코멘트만 지우기
	public static final String SQL_COMMENT_DELETE =
			"DELETE FROM COMMENTID WHERE COMMENT_ID = ?";
	
	// updateComment
	public static final String SQL_UPDATE =
		"UPDATE COMMENTID SET COMMENTTEXT = ? WHERE COMMENT_ID = ?";
	// update(int commentId, String commentText) 코멘트아이디에 해당하는 코멘트내용을 수정
	public static final String SQL_COMMENT_UPDATE =
			"UPDATE COMMENTID SET commentText = ? WHERE COMMENT_ID = ?";
	
	// insert(int writeId, int accNum, String commentText)
	public static final String SQL_COMMENT_INSERT = 
			"INSERT INTO COMMENTID "
			+ "(COMMENT_ID, WRITE_ID, ACCNUM, commentText) "
			+ "VALUES "						// 이부분의 ?가 작동이 안됨
			+ "(Comment_ID_SEQ.nextval, ?, (select accnum from account where id = ? ), ?)";
	// insertReply
	public static final String SQL_COMMENT_INSERTR =
				"INSERT INTO COMMENTID" +
				" (COMMENT_ID, WRITE_ID, ACCNUM, "
				+ "COMMENTTEXT, COMMENT_PARENT)" +
				" VALUES(Comment_ID_SEQ.nextval,?,(select accnum from account where id = ? ),?,?)";
	
	// selectComment(int commentId) : 코멘트id로 commentText찾기
	public static final String SQL_COMMENT_SELECT_BY_ID = 
		"SELECT commentText FROM COMMENTID WHERE COMMENT_ID = ?";
		

	
	// selectByCId(int commentId) : 해당 commentid의 코멘트 테이블에 있는 모든 정보 보여주기
	public static final String SQL_COMMENT_SELECT = 
			"SELECT * FROM COMMENTID WHERE COMMENT_ID = ?";

	
	
	// 원래 쓰던 코드
	public static final String SQL_COMMENT_VIEW_SELECT = 
		"SELECT * FROM COMMENTID C " +
				"INNER JOIN WRITEID W " +
				"ON C.WRITE_ID = W.WRITE_ID " +
				"WHERE C.COMMENT_ID = 2";
	
	public static final String SQL_COMMENT_ID_INSERT = 
			"INSERT INTO FREEBOARD VALUES "
			+ "(?, 1, WRITE_ID_SEQ.CURRVAL)";
	
	public static final String SQL_COMMENT_FILE_INSERT = 
			"INSERT INTO BOARDFILE VALUES "
			+ "(FILE_ID_SEQ.nextval, ?, ?, ?, 1, WRITE_ID_SEQ.CURRVAL)";
	
	public static final String SQL_COMMENT_FILE_SELECT =
			"SELECT * FROM BOARDFILE "
			+ "WHERE FILESEQ = ? AND WRITE_ID = ? AND BOARD_ID = 1";
	
	public static final String SQL_COMMENT_FILE_DELETE = 
			"DELETE FROM BOARDFILE WHERE WRITE_ID = ?";
	
	public static final String SQL_COMMENT_TOTAL_CNT = 
			"SELECT COUNT(*) FROM WRITEID W, FREEBOARD F WHERE W.WRITE_ID = F.WRITE_ID";
	
	public static final String SQL_COMMENT_INC_VIEWCNT = 
			"UPDATE WRITEID SET VIEWCNT = VIEWCNT + 1 WHERE WRITE_ID = ?";
	
	public static final String SQL_COMMENT_DELETE_BY_ID = 
			"DELETE FROM WRITEID WHERE WRITE_ID = ?";
	
	public static final String SQL_COMMENTID_DELETE_BY_ID = 
			"DELETE FROM FREEBOARD WHERE WRITE_ID = ?";

}
