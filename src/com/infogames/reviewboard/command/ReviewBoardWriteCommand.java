package com.infogames.reviewboard.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.ReviewBoardDAO;

public class ReviewBoardWriteCommand implements ReviewBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReviewBoardDAO dao = new ReviewBoardDAO();
		int cnt = 0;

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int accNum = Integer.parseInt(request.getParameter("accNum"));
		String[] originalFileName = request.getParameterValues("originalFileName");
		String[] systemFileName = request.getParameterValues("systemFileName");
		
		try {
			cnt = dao.insert(subject, content, accNum, originalFileName, systemFileName, rating);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
