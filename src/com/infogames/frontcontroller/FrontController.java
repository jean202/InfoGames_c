package com.infogames.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infogames.comment.command.CommentCommand;
import com.infogames.comment.command.CommentDeleteCommand;
import com.infogames.comment.command.CommentListCommand;
import com.infogames.comment.command.CommentReplyCommand;
import com.infogames.comment.command.CommentReplyFormCommand;
import com.infogames.comment.command.CommentUpdateCommand;
import com.infogames.comment.command.CommentUpdateFormCommand;
import com.infogames.comment.command.CommentWriteCommand;
import com.infogames.freeboard.command.FreeBoardCommandIF;
import com.infogames.freeboard.command.FreeBoardDeleteCommand;
import com.infogames.freeboard.command.FreeBoardFileDownloadCommand;
import com.infogames.freeboard.command.FreeBoardFileUploadCommand;
import com.infogames.freeboard.command.FreeBoardImgFileUploadCommand;
import com.infogames.freeboard.command.FreeBoardListCommand;
import com.infogames.freeboard.command.FreeBoardSelectCommand;
import com.infogames.freeboard.command.FreeBoardUpdateCommand;
import com.infogames.freeboard.command.FreeBoardViewCommand;
import com.infogames.freeboard.command.FreeBoardWriteCommand;
import com.infogames.login.command.LoginCommandIF;
import com.infogames.login.command.checkIdCommand;
import com.infogames.login.command.checkNickNameCommand;
import com.infogames.login.command.deleteAccCommand;
import com.infogames.login.command.findIdCommand;
import com.infogames.login.command.findPwCommand;
import com.infogames.login.command.loginCommand;
import com.infogames.login.command.signUpCommand;
import com.infogames.login.command.updateAccCommand;
import com.infogames.reviewboard.command.ReviewBoardCommandIF;
import com.infogames.reviewboard.command.ReviewBoardDeleteCommand;
import com.infogames.reviewboard.command.ReviewBoardFileDownloadCommand;
import com.infogames.reviewboard.command.ReviewBoardFileUploadCommand;
import com.infogames.reviewboard.command.ReviewBoardImgFileUploadCommand;
import com.infogames.reviewboard.command.ReviewBoardListCommand;
import com.infogames.reviewboard.command.ReviewBoardSelectCommand;
import com.infogames.reviewboard.command.ReviewBoardUpdateCommand;
import com.infogames.reviewboard.command.ReviewBoardViewCommand;
import com.infogames.reviewboard.command.ReviewBoardWriteCommand;
import com.infogames.tipboard.command.TipBoardCommandIF;
import com.infogames.tipboard.command.TipBoardDeleteCommand;
import com.infogames.tipboard.command.TipBoardFileDownloadCommand;
import com.infogames.tipboard.command.TipBoardFileUploadCommand;
import com.infogames.tipboard.command.TipBoardImgFileUploadCommand;
import com.infogames.tipboard.command.TipBoardListCommand;
import com.infogames.tipboard.command.TipBoardSelectCommand;
import com.infogames.tipboard.command.TipBoardUpdateCommand;
import com.infogames.tipboard.command.TipBoardViewCommand;
import com.infogames.tipboard.command.TipBoardWriteCommand;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		//컨트롤러는 아래 두 가지 정보를 갖고 움직여야 한다.
		String viewPage = null; // 어떠한 페이지에 보여줄지 (View)
		LoginCommandIF loginCommand = null; // 어떠한 로직을 수행할지
		FreeBoardCommandIF freeBoardCommand = null; // 어떠한 로직을 수행할지
		TipBoardCommandIF tipBoardCommand = null; // 어떠한 로직을 수행할지
		ReviewBoardCommandIF reviewBoardCommand = null; // 어떠한 로직을 수행할지
		CommentCommand commentCommand = null;
		
		//URL로부터 URI, ContextPath, Command 분리
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length()); // URL에서 커맨드 분리
		
		//테스트 출력
		System.out.println("uri: " + uri);
		System.out.println("conPath: " + conPath);
		System.out.println("com: " + com);
		
		// 컨트롤러는 커맨드에 따라, 로직을 수행하고
		// 결과를 내보낼 View를 결정한다.
		switch(com) {
		case "/login/login.do":
			viewPage = "/login/login.jsp";
			break;
		case "/login/loginOk.do":
			loginCommand = new loginCommand();
			loginCommand.execute(request, response);
			viewPage = "/login/loginOk.jsp";
			break;
		case "/login/welcome.do":
			viewPage = "/login/welcome.jsp";
			break;
		case "/login/logout.do":
			viewPage = "/login/logout.jsp";
			break;
		case "/login/signUp.do":
			viewPage = "/login/signUp.jsp";
			break;
		case "/login/signUpOk.do":
			loginCommand = new signUpCommand();
			loginCommand.execute(request, response);
			viewPage = "/login/signUpOk.jsp";
			break;
		case "/login/forgotId.do":
			viewPage = "/login/forgotId.jsp";
			break;
		case "/login/forgotIdOk.do":
			loginCommand = new findIdCommand();
			loginCommand.execute(request, response);
			viewPage = "/login/forgotIdOk.jsp";
			break;
		case "/login/forgotPw.do":
			viewPage = "/login/forgotPw.jsp";
			break;
		case "/login/forgotPwOk.do":
			loginCommand = new findPwCommand();
			loginCommand.execute(request, response);
			viewPage = "/login/forgotPwOk.jsp";
			break;
		case "/login/delete.do":
			viewPage = "/login/delete.jsp";
			break;
		case "/login/deleteOk.do":
			loginCommand = new deleteAccCommand();
			loginCommand.execute(request, response);
			viewPage = "/login/deleteOk.jsp";
			break;
		case "/login/updateAccChk.do":
			viewPage = "/login/updateAccChk.jsp";
			break;
		case "/login/updateAcc.do":
			viewPage = "/login/updateAcc.jsp";
			break;
		case "/login/updateAccOk.do":
			loginCommand = new updateAccCommand();
			loginCommand.execute(request, response);
			viewPage = "/login/updateAccOk.jsp";
			break;
		case "/login/idCheckOk.do":
			loginCommand = new checkIdCommand();
			loginCommand.execute(request, response);
			viewPage = "/login/idCheckOk.jsp";
			break;
		case "/login/nickNameCheckOk.do":
			loginCommand = new checkNickNameCommand();
			loginCommand.execute(request, response);
			viewPage = "/login/nickNameCheckOk.jsp";
			break;
		case "/freeboard/list.do":
			freeBoardCommand = new FreeBoardListCommand();
			freeBoardCommand.execute(request, response);
			viewPage = "/freeboard/list.jsp";
			break;
		case "/freeboard/write.do":
			viewPage = "/freeboard/write.jsp";
			break;
		case "/freeboard/writeOk.do":
			freeBoardCommand = new FreeBoardWriteCommand();
			freeBoardCommand.execute(request, response);
			viewPage = "/freeboard/writeOk.jsp";
			break;
		case "/freeboard/view.do":
			freeBoardCommand = new FreeBoardViewCommand();
			freeBoardCommand.execute(request, response);
			commentCommand = new CommentListCommand();
			commentCommand.execute(request, response);
			viewPage = "/freeboard/view.jsp";
			break;
		case "/freeboard/commentReplyForm.do":
			commentCommand = new CommentReplyFormCommand();
			commentCommand.execute(request, response);
			viewPage = "/freeboard/commentReplyForm.jsp";
			break;
		case "/freeboard/commentUpdateForm.do":
			commentCommand = new CommentUpdateFormCommand();
			commentCommand.execute(request, response);
			viewPage = "/freeboard/commentUpdateForm.jsp";
			break;
		case "/freeboard/update.do":
			freeBoardCommand = new FreeBoardSelectCommand();
			freeBoardCommand.execute(request, response);
			viewPage = "/freeboard/update.jsp";
			break;
		case "/freeboard/updateOk.do":
			freeBoardCommand = new FreeBoardUpdateCommand();
			freeBoardCommand.execute(request, response);
			viewPage = "/freeboard/updateOk.jsp";
			break;
		case "/freeboard/deleteOk.do":
			freeBoardCommand = new FreeBoardDeleteCommand();
			freeBoardCommand.execute(request, response);
			viewPage = "/freeboard/deleteOk.jsp";
			break;
		case "/freeboard/FileUploadForm.do":
			viewPage = "/freeboard/FileUploadForm.jsp";
			break;
		case "/freeboard/FileUpload.do":
			freeBoardCommand = new FreeBoardFileUploadCommand();
			freeBoardCommand.execute(request, response);
			viewPage = "/freeboard/FileUpload.jsp";
			break;
		case "/freeboard/FileDownload.do":
			freeBoardCommand = new FreeBoardFileDownloadCommand();
			freeBoardCommand.execute(request, response);
			viewPage = null;
			break;
		case "/freeboard/ImgFileUpload.do":
			freeBoardCommand = new FreeBoardImgFileUploadCommand();
			freeBoardCommand.execute(request, response);
			viewPage = null;
			break;
		case "/reviewboard/list.do":
			reviewBoardCommand = new ReviewBoardListCommand();
			reviewBoardCommand.execute(request, response);
			viewPage = "/reviewboard/list.jsp";
			break;
		case "/reviewboard/write.do":
			viewPage = "/reviewboard/write.jsp";
			break;
		case "/reviewboard/writeOk.do":
			reviewBoardCommand = new ReviewBoardWriteCommand();
			reviewBoardCommand.execute(request, response);
			viewPage = "/reviewboard/writeOk.jsp";
			break;
		case "/reviewboard/view.do":
			reviewBoardCommand = new ReviewBoardViewCommand();
			reviewBoardCommand.execute(request, response);
			commentCommand = new CommentListCommand();
			commentCommand.execute(request, response);
			viewPage = "/reviewboard/view.jsp";
			break;
		case "/reviewboard/commentReplyForm.do":
			commentCommand = new CommentReplyFormCommand();
			commentCommand.execute(request, response);
			viewPage = "/freeboard/commentReplyForm.jsp";
			break;
		case "/reviewboard/commentUpdateForm.do":
			commentCommand = new CommentUpdateFormCommand();
			commentCommand.execute(request, response);
			viewPage = "/freeboard/commentUpdateForm.jsp";
			break;	
		case "/reviewboard/update.do":
			reviewBoardCommand = new ReviewBoardSelectCommand();
			reviewBoardCommand.execute(request, response);
			viewPage = "/reviewboard/update.jsp";
			break;
		case "/reviewboard/updateOk.do":
			reviewBoardCommand = new ReviewBoardUpdateCommand();
			reviewBoardCommand.execute(request, response);
			viewPage = "/reviewboard/updateOk.jsp";
			break;
		case "/reviewboard/deleteOk.do":
			reviewBoardCommand = new ReviewBoardDeleteCommand();
			reviewBoardCommand.execute(request, response);
			viewPage = "/reviewboard/deleteOk.jsp";
			break;
		case "/reviewboard/FileUploadForm.do":
			viewPage = "/reviewboard/FileUploadForm.jsp";
			break;
		case "/reviewboard/FileUpload.do":
			reviewBoardCommand = new ReviewBoardFileUploadCommand();
			reviewBoardCommand.execute(request, response);
			viewPage = "/reviewboard/FileUpload.jsp";
			break;
		case "/reviewboard/FileDownload.do":
			reviewBoardCommand = new ReviewBoardFileDownloadCommand();
			reviewBoardCommand.execute(request, response);
			viewPage = null;
			break;
		case "/reviewboard/ImgFileUpload.do":
			reviewBoardCommand = new ReviewBoardImgFileUploadCommand();
			reviewBoardCommand.execute(request, response);
			viewPage = null;
			break;
		case "/tipboard/list.do":
			tipBoardCommand = new TipBoardListCommand();
			tipBoardCommand.execute(request, response);
			viewPage = "/tipboard/list.jsp";
			break;
		case "/tipboard/write.do":
			viewPage = "/tipboard/write.jsp";
			break;
		case "/tipboard/writeOk.do":
			tipBoardCommand = new TipBoardWriteCommand();
			tipBoardCommand.execute(request, response);
			viewPage = "/tipboard/writeOk.jsp";
			break;
		case "/tipboard/view.do":
			tipBoardCommand = new TipBoardViewCommand();
			tipBoardCommand.execute(request, response);
			commentCommand = new CommentListCommand();
			commentCommand.execute(request, response);
			viewPage = "/tipboard/view.jsp";
			break;
		case "/tipboard/commentReplyForm.do":
			commentCommand = new CommentReplyFormCommand();
			commentCommand.execute(request, response);
			viewPage = "/freeboard/commentReplyForm.jsp";
			break;
		case "/tipboard/commentUpdateForm.do":
			commentCommand = new CommentUpdateFormCommand();
			commentCommand.execute(request, response);
			viewPage = "/freeboard/commentUpdateForm.jsp";
			break;	
		case "/tipboard/update.do":
			tipBoardCommand = new TipBoardSelectCommand();
			tipBoardCommand.execute(request, response);
			viewPage = "/tipboard/update.jsp";
			break;
		case "/tipboard/updateOk.do":
			tipBoardCommand = new TipBoardUpdateCommand();
			tipBoardCommand.execute(request, response);
			viewPage = "/tipboard/updateOk.jsp";
			break;
		case "/tipboard/deleteOk.do":
			tipBoardCommand = new TipBoardDeleteCommand();
			tipBoardCommand.execute(request, response);
			viewPage = "/tipboard/deleteOk.jsp";
			break;
		case "/tipboard/FileUploadForm.do":
			viewPage = "/tipboard/FileUploadForm.jsp";
			break;
		case "/tipboard/FileUpload.do":
			tipBoardCommand = new TipBoardFileUploadCommand();
			tipBoardCommand.execute(request, response);
			viewPage = "/tipboard/FileUpload.jsp";
			break;
		case "/tipboard/FileDownload.do":
			tipBoardCommand = new TipBoardFileDownloadCommand();
			tipBoardCommand.execute(request, response);
			viewPage = null;
			break;
		case "/tipboard/ImgFileUpload.do":
			tipBoardCommand = new TipBoardImgFileUploadCommand();
			tipBoardCommand.execute(request, response);
			viewPage = null;
			break;
		case "/mainpage/GameRanking.do":
			viewPage = "/mainpage/GameRanking.jsp";
			break;
		case "/mainpage/ajax.do":
			viewPage = "/mainpage/ajax.jsp";
			break;
		case "/comment/CommentWrite.do":
			commentCommand = new CommentWriteCommand();
			commentCommand.execute(request, response);
			viewPage = null; // 이거 안해주면 페이지를 찾을수 없음 에러가 나온다
			break;
		case "/comment/CommentReply.do":
			commentCommand = new CommentReplyCommand();
			commentCommand.execute(request, response);
			viewPage = null;
			break;
		case "/comment/CommentDelete.do":
			commentCommand = new CommentDeleteCommand();
			commentCommand.execute(request, response);
			viewPage = null;
			break;
		case "/comment/CommentUpdate.do":
			commentCommand = new CommentUpdateCommand();
			commentCommand.execute(request, response);
			viewPage = null;
			break;
		}// end switch
		
		//response를 위해서 
		// 위에서 결정된 viewPage 에 forward 해줌
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
