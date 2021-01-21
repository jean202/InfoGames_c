package com.infogames.comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommentCommand {
	void execute(HttpServletRequest request,HttpServletResponse response);
}
