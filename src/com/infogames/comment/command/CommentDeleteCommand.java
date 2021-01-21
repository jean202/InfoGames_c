package com.infogames.comment.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.CommentDAO;

public class CommentDeleteCommand implements CommentCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CommentDAO dao = new CommentDAO();
		int cnt = 0;

		int commentId = Integer.parseInt(request.getParameter("commentId"));

		try {
			cnt = dao.delete(commentId);
			request.setAttribute("result", cnt);
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        // 정상적으로 댓글을 삭제했을경우 1을 전달한다.
	        if(cnt > 0) out.println("1");
	        out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}
