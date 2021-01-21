package com.infogames.beans;

public class FreeBoardDTO {
	private int accNum;
	private int boardId;
	private int writeId;
	private String id;
	private String nickName;
	private String subject;
	private String content;
	private String regDate;
	private int viewCnt;
	private String originalFileName;
	private String systemFileName;
	private int fileSeq;
	private int rnum;
	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getSystemFileName() {
		return systemFileName;
	}

	public void setSystemFileName(String systemFileName) {
		this.systemFileName = systemFileName;
	}

	public int getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public FreeBoardDTO() {
		super();
	}
	
	public FreeBoardDTO(int accNum, int boardId, int writeId, String subject, String content, int viewCnt, String id, String nickName, String originalFileName, String systemFileName, int fileSeq) {
		super();
		this.accNum = accNum;
		this.boardId = boardId;
		this.writeId = writeId;
		this.subject = subject;
		this.content = content;
		this.viewCnt = viewCnt;
		this.id = id;
		this.nickName = nickName;
		this.originalFileName = originalFileName;
		this.systemFileName = systemFileName;
		this.fileSeq = fileSeq;
	}
	
	public FreeBoardDTO(int accNum, int boardId, int writeId, String subject, String content, int viewCnt, String id, String nickName, int rnum) {
		super();
		this.accNum = accNum;
		this.boardId = boardId;
		this.writeId = writeId;
		this.subject = subject;
		this.content = content;
		this.viewCnt = viewCnt;
		this.id = id;
		this.nickName = nickName;
		this.rnum = rnum;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAccNum() {
		return accNum;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getWriteId() {
		return writeId;
	}

	public void setWriteId(int writeId) {
		this.writeId = writeId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
}
