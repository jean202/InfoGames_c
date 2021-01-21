package com.infogames.tipboard.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.TipBoardDAO;
import com.infogames.beans.TipBoardDTO;

public class TipBoardViewCommand implements TipBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		TipBoardDAO dao = new TipBoardDAO();
		TipBoardDTO[] arr = null;

		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		try {
			arr = dao.readById(writeId);
			request.setAttribute("list", arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
