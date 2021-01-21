package com.infogames.comment.command;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.beans.CommentDAO;
import com.infogames.beans.CommentDTO;


public class CommentListCommand implements CommentCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		 CommentDAO dao = new CommentDAO();
		// CommentDTO[] arr = null;
			ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		// 게시글 번호를 이용하여 해당 글에 있는 댓글 목록을 가져온다.
        // 현재 게시글의 번호가 필요하다."writeId", "cwriteId" 둘다 될듯 한데
		// [코멘트가 있는 게시글]은 "cwriteId"가 보증한다. --> 근데 이건 view에서 걸러줌
		// [게시글]이 보여질때 댓글도 같이 보여지는거니까 "writeId"여야
			int writeId = Integer.parseInt(request.getParameter("writeId"));
			System.out.println("commentListCommand의 writeId = " + writeId);
			
			try {
				list = dao.selectById(writeId);
				// 댓글이 1개라도 있다면 request에 commentList를 세팅한다.
				if(list.size() > 0) request.setAttribute("commentList", list); //list에 select결과물 담겨온다
				// CommentDTO(commentId, writeId, id, nickName, commentDate, commentText, comment_parent, comment_level)반환
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		
	}

}
