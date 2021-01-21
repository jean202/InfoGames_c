package com.infogames.comment.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.CommentDAO;
import com.infogames.beans.FreeBoardDAO;

public class CommentWriteCommand implements CommentCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CommentDAO dao = new CommentDAO();
		int cnt = 0;
		/*
		 * "INSERT INTO COMMENTID " +
		 * "(COMMENT_ID, WRITE_ID, ACCNUM, commentText, commentAvailable) " + "VALUES "
		 * + "(Comment_ID_SEQ.nextval, ?, ?, ?, ?)";
		 * 
		 * public int insert(int writeId, int accNum, String commentText)
		 */
		// 어떤 글에, 누가, 어떤 내용의 코멘트를?
		int writeId = Integer.parseInt(request.getParameter("cwriteId"));
		// 이 글의 작성자가 아닌, 이 글을 보고 있는, 코멘트를 작성할 '나'의 정보가 필요함
		// 작성자 Id
		String cId = request.getParameter("cId");
		String commentText = request.getParameter("commentText");

		try {
			cnt = dao.insert(writeId, cId, commentText);
			request.setAttribute("cmtResult", cnt); // cnt에 성공, 실패 결과가 담겨서 돌아옴
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		// ajax 처리 해주는 부분
		if (cnt > 0) {
			response.setContentType("text/html;charset=UTF-8");
			try {
				PrintWriter out = response.getWriter();
				out.println("1");
				out.close();
				System.out.println("1도 내보냈다.");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
