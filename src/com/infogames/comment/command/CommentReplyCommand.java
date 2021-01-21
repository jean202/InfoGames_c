package com.infogames.comment.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.CommentDAO;

import java.io.IOException;
import java.io.PrintWriter;

public class CommentReplyCommand implements CommentCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		// 대댓글 입력에 필요한 정보
		// 게시글 번호, 부모댓글의 글번호, 댓글 작성자, 댓글 내용 -> 새로운 insert메소드 필요
		//   var param="commentId="+commentId+"&writeId="+writeId
		//	+"&id="+id+"&commentText="+commentText;
		// 파라미터를 가져온다.
		// 댓글 글번호 -> 부모글의 글번호
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int writeId = Integer.parseInt(request.getParameter("writeId"));// 게시글 번호
		String id = request.getParameter("id");// 댓글 작성자
		String commentText = request.getParameter("commentText");

		CommentDAO dao = new CommentDAO();

		// insertReply(int writeId, int comment_parent, String cId, String commentText)
		/*
		 * "INSERT INTO COMMENTID" + " (COMMENT_ID, WRITE_ID, ACCNUM, COMMENTDATE, " +
		 * "COMMENTTEXT, COMMENT_PARENT)" +
		 * " VALUES(Comment_ID_SEQ.nextval,?,?,sysdate,?,?)";
		 */
		try {
			cnt = dao.insertReply(writeId, commentId, id, commentText);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			// 정상적으로 댓글을 저장했을경우 1을 전달한다.
			if (cnt > 0) out.println("1");
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
