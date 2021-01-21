package com.infogames.comment.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.CommentDAO;

public class CommentUpdateCommand implements CommentCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CommentDAO dao = new CommentDAO();
		int cnt = 0;

		// 파라미터를 가져온다.
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String commentText = request.getParameter("commentText");

		try {
			cnt = dao.update(commentId, commentText);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			// 정상적으로 댓글을 수정했을경우 1을 전달한다.
			if (cnt > 0) out.println("1");
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
