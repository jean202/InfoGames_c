package com.infogames.tipboard.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.TipBoardDAO;

public class TipBoardDeleteCommand implements TipBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		TipBoardDAO dao = new TipBoardDAO();
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
