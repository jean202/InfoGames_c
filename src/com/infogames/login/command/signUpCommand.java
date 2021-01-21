package com.infogames.login.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.LoginDAO;

public class signUpCommand implements LoginCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LoginDAO dao = new LoginDAO();
		int cnt = 0;

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String birth = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
		String gender = request.getParameter("gender");
		String nickName = request.getParameter("nickName");

		try {
			cnt = dao.signUpAcc(id, pw, name, email, birth, gender, nickName);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
