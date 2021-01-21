package com.infogames.comment.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.CommentDAO;
import com.infogames.beans.CommentDTO;

public class CommentReplyFormCommand implements CommentCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CommentDAO dao = new CommentDAO();
		// 부모글의 글번호를 가져온다.
		int commentId = Integer.parseInt(request.getParameter("commentId"));

		// "SELECT * FROM COMMENTID WHERE COMMENT_ID = ?";
		// public CommentDTO selectByCId(int commentId) 
		CommentDTO[] comment;
		try {
			comment = dao.selectByCId(commentId);
			// 댓글 정보를 request에 세팅한다.
			request.setAttribute("comment", comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
