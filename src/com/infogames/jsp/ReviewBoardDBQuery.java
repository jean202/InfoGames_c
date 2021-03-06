package com.infogames.jsp;

public class ReviewBoardDBQuery {
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USERID = "scott5";
	public static final String USERPW = "tiger5";

	public static final String SQL_REVIEWBOARD_INSERT = 
			"INSERT INTO WRITEID "
			+ "(WRITE_ID, SUBJECT, CONTENT) "
			+ "VALUES "
			+ "(WRITE_ID_SEQ.nextval, ?, ?)";
	
	public static final String SQL_REVIEWBOARD_ID_INSERT = 
			"INSERT INTO REVIEWBOARD VALUES "
			+ "(?, 3, WRITE_ID_SEQ.CURRVAL, ?)";
	
	public static final String SQL_REVIEWBOARD_FILE_INSERT = 
			"INSERT INTO BOARDFILE VALUES "
			+ "(FILE_ID_SEQ.nextval, ?, ?, ?, 1, WRITE_ID_SEQ.CURRVAL)";
	
	public static final String SQL_REVIEWBOARD_FILE_SELECT =
			"SELECT * FROM BOARDFILE "
			+ "WHERE FILESEQ = ? AND WRITE_ID = ? AND BOARD_ID = 3";
	
	public static final String SQL_REVIEWBOARD_FILE_DELETE = 
			"DELETE FROM BOARDFILE WHERE WRITE_ID = ?";
	
	public static final String SQL_REVIEWBOARD_TOTAL_CNT = 
			"SELECT COUNT(*) FROM WRITEID W, REVIEWBOARD R WHERE W.WRITE_ID = R.WRITE_ID";
	
	public static final String SQL_REVIEWBOARD_SELECT = 
			"SELECT * FROM " + 
			"(SELECT ROWNUM AS RNUM, T.* FROM " + 
			"(SELECT A.ID, A.NICKNAME, W.SUBJECT, W.CONTENT, W.REGDATE, W.VIEWCNT, W.WRITE_ID, R.ACCNUM, R.BOARD_ID, R.RATING " + 
			"FROM WRITEID W " + 
			"INNER JOIN REVIEWBOARD R " + 
			"ON W.WRITE_ID = R.WRITE_ID " + 
			"INNER JOIN ACCOUNT A " + 
			"ON A.ACCNUM = R.ACCNUM " + 
			"ORDER BY W.REGDATE DESC) T) " + 
			"WHERE RNUM >= ? AND RNUM < ?";
	
	public static final String SQL_REVIEWBOARD_SELECT_BY_ID = 
			"SELECT A.ID, A.NICKNAME, W.SUBJECT, W.CONTENT, W.REGDATE, W.VIEWCNT, W.WRITE_ID, R.ACCNUM, R.BOARD_ID, R.RATING, B.ORIGINALNAME, B.SYSTEMNAME, B.FILESEQ " + 
			"FROM WRITEID W " + 
			"INNER JOIN REVIEWBOARD R " + 
			"ON W.WRITE_ID = R.WRITE_ID " + 
			"INNER JOIN ACCOUNT A " + 
			"ON A.ACCNUM = R.ACCNUM " + 
			"LEFT OUTER JOIN BOARDFILE B " + 
			"ON W.WRITE_ID = B.WRITE_ID " + 
			"WHERE W.WRITE_ID = ?";
	
	public static final String SQL_REVIEWBOARD_INC_VIEWCNT = 
			"UPDATE WRITEID SET VIEWCNT = VIEWCNT + 1 WHERE WRITE_ID = ?";
	
	public static final String SQL_REVIEWBOARD_UPDATE =
			"UPDATE WRITEID SET SUBJECT = ?, CONTENT = ? WHERE WRITE_ID = ?";
	
	public static final String SQL_REVIEWBOARD_RATING_UPDATE = 
			"UPDATE REVIEWBOARD SET RATING = ? WHERE WRITE_ID = ?";
	
	public static final String SQL_REVIEWBOARD_DELETE_BY_ID = 
			"DELETE FROM WRITEID WHERE WRITE_ID = ?";
	
	public static final String SQL_REVIEWBOARDID_DELETE_BY_ID = 
			"DELETE FROM REVIEWBOARD WHERE WRITE_ID = ?";

}
