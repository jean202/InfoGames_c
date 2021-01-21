package com.infogames.reviewboard.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.ReviewBoardDAO;

public class ReviewBoardDeleteCommand implements ReviewBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReviewBoardDAO dao = new ReviewBoardDAO();
		int cnt = 0;

		int writeId = Integer.parseInt(request.getParameter("writeId"));

		try {
			cnt = dao.deleteById(writeId);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
