package com.infogames.beans;

public class CommentDTO {
	private int commentId; // 댓글 글번호
	private int writeId; // 게시글 글번호
	private int accNum; // 댓글 작성자
	// 작성자 아이디, 작성자 닉네임 멤버 추가
	private String id;
	private String nickName;
	private String commentDate; // 댓글 작성일
	private int comment_parent; // 부모글
	private String commentText; // 댓글 내용
	private int commentAvailable; // 코멘트가 적힌 글에 코멘트 여부 표시
	private int comment_level; // 댓글- 답변글 깊이

	public CommentDTO() {
		super();
	}
	// CommentDTO(commentId, writeId, id, nickName, commentDate, commentText, comment_parent, comment_level)
	public CommentDTO(int commentId, int writeId, String id, String nickName, 
			String commentDate, String commentText, int comment_parent, int comment_level) {
		super();
		this.commentId = commentId;
		this.writeId = writeId;
		this.id = id;
		this.nickName = nickName;
		this.commentDate = commentDate;
		this.comment_parent = comment_parent;
		this.commentText = commentText;
		this.comment_level = comment_level;
	}
	
	// CommentDTO(commentId, writeId, accNum, commentText,comment_parent, comment_level)
	public CommentDTO(int commentId, int writeId, int accNum, String commentText, int comment_parent, int comment_level) {
		super();
		this.commentId = commentId;
		this.writeId = writeId;
		this.accNum = accNum;
		this.commentText = commentText;
		this.comment_parent = comment_parent;
		this.comment_level = comment_level;
	}

	// CommentDTO(commentId, writeId, accNum, commentText, comment_parent)
	public CommentDTO(int commentId, int writeId, int accNum, String commentText, int comment_parent) {
		super();	
		this.commentId = commentId;
		this.writeId = writeId;
		this.accNum = accNum;
		this.comment_parent = comment_parent; 
		this.commentText = commentText;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public int getWriteId() {
		return writeId;
	}

	public void setWriteId(int writeId) {
		this.writeId = writeId;
	}
	
	public int getAccNum() {
		return accNum;
	}
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
	
	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	
	public int getComment_parent() {
		return comment_parent;
	}
	public void setComment_parent(int comment_parent) {
		this.comment_parent = comment_parent;
	}
	
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	public int getComment_level() {
		return comment_level;
	}
	public void setComment_level(int comment_level) {
		this.comment_level = comment_level;
	}
	
	public int getCommentAvailable() {
		return commentAvailable;
	}

	public void setCommentAvilable(int commentAvailable) {
		this.commentAvailable = commentAvailable;
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
}
