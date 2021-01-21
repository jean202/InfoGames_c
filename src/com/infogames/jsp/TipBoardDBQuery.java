package com.infogames.jsp;

public class TipBoardDBQuery {
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USERID = "scott5";
	public static final String USERPW = "tiger5";

	public static final String SQL_TIPBOARD_INSERT = 
			"INSERT INTO WRITEID "
			+ "(WRITE_ID, SUBJECT, CONTENT) "
			+ "VALUES "
			+ "(WRITE_ID_SEQ.nextval, ?, ?)";
	
	public static final String SQL_TIPBOARD_ID_INSERT = 
			"INSERT INTO TIPBOARD VALUES "
			+ "(?, 2, WRITE_ID_SEQ.CURRVAL)";
	
	public static final String SQL_TIPBOARD_FILE_INSERT = 
			"INSERT INTO BOARDFILE VALUES "
			+ "(FILE_ID_SEQ.nextval, ?, ?, ?, 1, WRITE_ID_SEQ.CURRVAL)";
	
	public static final String SQL_TIPBOARD_FILE_SELECT =
			"SELECT * FROM BOARDFILE "
			+ "WHERE FILESEQ = ? AND WRITE_ID = ? AND BOARD_ID = 2";
	
	public static final String SQL_TIPBOARD_FILE_DELETE = 
			"DELETE FROM BOARDFILE WHERE WRITE_ID = ?";
	
	public static final String SQL_TIPBOARD_TOTAL_CNT = 
			"SELECT COUNT(*) FROM WRITEID W, TIPBOARD T WHERE W.WRITE_ID = T.WRITE_ID ";
	
	public static final String SQL_TIPBOARD_SELECT = 
			"SELECT * FROM " + 
			"(SELECT ROWNUM AS RNUM, T.* FROM " + 
			"(SELECT A.ID, A.NICKNAME, W.SUBJECT, W.CONTENT, W.REGDATE, W.VIEWCNT, W.WRITE_ID, F.ACCNUM, F.BOARD_ID " + 
			"FROM WRITEID W " + 
			"INNER JOIN TIPBOARD F " + 
			"ON W.WRITE_ID = F.WRITE_ID " + 
			"INNER JOIN ACCOUNT A " + 
			"ON A.ACCNUM = F.ACCNUM " + 
			"ORDER BY W.REGDATE DESC) T) " + 
			"WHERE RNUM >= ? AND RNUM < ?";
	
	public static final String SQL_TIPBOARD_SELECT_BY_ID = 
			"SELECT A.ID, A.NICKNAME, W.SUBJECT, W.CONTENT, W.REGDATE, W.VIEWCNT, W.WRITE_ID, F.ACCNUM, F.BOARD_ID, B.ORIGINALNAME, B.SYSTEMNAME, B.FILESEQ " + 
			"FROM WRITEID W " + 
			"INNER JOIN TIPBOARD F " + 
			"ON W.WRITE_ID = F.WRITE_ID " + 
			"INNER JOIN ACCOUNT A " + 
			"ON A.ACCNUM = F.ACCNUM " + 
			"LEFT OUTER JOIN BOARDFILE B " + 
			"ON W.WRITE_ID = B.WRITE_ID " + 
			"WHERE W.WRITE_ID = ?";
	
	public static final String SQL_TIPBOARD_INC_VIEWCNT = 
			"UPDATE WRITEID SET VIEWCNT = VIEWCNT + 1 WHERE WRITE_ID = ?";
	
	public static final String SQL_TIPBOARD_UPDATE =
			"UPDATE WRITEID SET SUBJECT = ?, CONTENT = ? WHERE WRITE_ID = ?";
	
	public static final String SQL_TIPBOARD_DELETE_BY_ID = 
			"DELETE FROM WRITEID WHERE WRITE_ID = ?";
	
	public static final String SQL_TIPBOARDID_DELETE_BY_ID = 
			"DELETE FROM TIPBOARD WHERE WRITE_ID = ?";

}
