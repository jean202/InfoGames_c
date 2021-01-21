package com.infogames.reviewboard.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.ReviewBoardDAO;

public class ReviewBoardUpdateCommand implements ReviewBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReviewBoardDAO dao = new ReviewBoardDAO();
		int cnt = 0;

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int writeId = Integer.parseInt(request.getParameter("writeId"));

		try {
			cnt = dao.update(subject, content, writeId, rating);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
