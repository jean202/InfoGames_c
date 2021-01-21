package com.infogames.tipboard.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.TipBoardDAO;

public class TipBoardUpdateCommand implements TipBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		TipBoardDAO dao = new TipBoardDAO();
		int cnt = 0;

		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int writeId = Integer.parseInt(request.getParameter("writeId"));

		try {
			cnt = dao.update(subject, content, writeId);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
