package com.infogames.tipboard.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.TipBoardDAO;

public class TipBoardWriteCommand implements TipBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		TipBoardDAO dao = new TipBoardDAO();
		int cnt = 0;

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int accNum = Integer.parseInt(request.getParameter("accNum"));
		String[] originalFileName = request.getParameterValues("originalFileName");
		String[] systemFileName = request.getParameterValues("systemFileName");
		
		try {
			cnt = dao.insert(subject, content, accNum, originalFileName, systemFileName);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
