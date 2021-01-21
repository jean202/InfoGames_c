package com.infogames.freeboard.command;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.CommentDAO;
import com.infogames.beans.CommentDTO;
import com.infogames.beans.FreeBoardDAO;
import com.infogames.beans.FreeBoardDTO;

public class FreeBoardViewCommand implements FreeBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		FreeBoardDAO dao = new FreeBoardDAO();
		FreeBoardDTO[] arr = null; 

		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		try {
			arr = dao.readById(writeId);
			request.setAttribute("list", arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 // 게시글 번호를 이용하여 해당 글에 있는 댓글 목록을 가져온다.
		/*CommentListCommand에서 하기로 했음
		CommentDAO cdao = new CommentDAO();
		//CommentDTO[] carr = null;
		ArrayList<CommentDTO> clist = new ArrayList<CommentDTO>();
		try {
			clist = cdao.selectById(writeId);
			// 댓글이 1개라도 있다면 request에 commentList를 세팅한다.
	        if(clist.size() > 0) request.setAttribute("commentList",clist);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		*/
		/*
        CommentDAO commentDAO = new CommentDAO();
        ArrayList<CommentDTO> commentList;
		try {
			commentList = commentDAO.getCommentList(writeId);
			// 댓글이 1개라도 있다면 request에 commentList를 세팅한다.
	        if(commentList.size() > 0)request.setAttribute("commentList", commentList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}

}
