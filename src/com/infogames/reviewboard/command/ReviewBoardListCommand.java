package com.infogames.reviewboard.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.ReviewBoardDAO;
import com.infogames.beans.ReviewBoardDTO;



public class ReviewBoardListCommand implements ReviewBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReviewBoardDAO dao = new ReviewBoardDAO();
		ReviewBoardDTO[] arr = null;
		
		int curPage = 1;
		int totalWrite = 0; // 게시글 전체 개수
		int totalPage = 0; // 페이지 전체 개수
		int perWrite = 15; // 한 페이지 당 몇 개의 게시글?
		int perPage = 10; // 한 페이지 당 몇 개의 페이지?
		String pageParam = request.getParameter("page");

		try {
			totalWrite = dao.totalWrite();
			totalPage = (int)Math.ceil(totalWrite / (double)perWrite); // 총 몇페이지
			if(pageParam == null) {
				curPage = 1;
			} else if(Integer.parseInt(pageParam) <= 0 || Integer.parseInt(pageParam) > totalPage) {
				curPage = 1;
			} else if(pageParam != null && !pageParam.trim().equals("")){
				curPage = Integer.parseInt(pageParam);
			}
			int startRow = (curPage - 1) * perWrite + 1; // 몇 번째 row 부터
			arr = dao.select(startRow, startRow + perWrite);
			request.setAttribute("list", arr);
			request.setAttribute("curPage", curPage);
			request.setAttribute("totalWrite", totalWrite);
			request.setAttribute("perPage", perPage);
			request.setAttribute("totalPage", totalPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
