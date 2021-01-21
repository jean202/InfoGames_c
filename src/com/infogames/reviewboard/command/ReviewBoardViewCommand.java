package com.infogames.reviewboard.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.ReviewBoardDAO;
import com.infogames.beans.ReviewBoardDTO;

public class ReviewBoardViewCommand implements ReviewBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReviewBoardDAO dao = new ReviewBoardDAO();
		ReviewBoardDTO[] arr = null;

		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		try {
			arr = dao.readById(writeId);
			request.setAttribute("list", arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
