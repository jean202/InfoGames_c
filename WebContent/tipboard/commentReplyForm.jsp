<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.infogames.beans.*"%>
<%
	
	CommentDTO[] comment = (CommentDTO[]) request.getAttribute("comment");
	
	int commentId = comment[0].getCommentId();
	int writeId = comment[0].getWriteId();
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>댓글 답변</title>

<style type="text/css">
#wrap {
	width: 550px;
	margin: 0 auto 0 auto;
	text-align: center;
}

#commentReplyForm {
	text-align: center;
}
</style>

<script type="text/javascript">
    
        var httpRequest = null;
        
        // httpRequest 객체 생성
        function getXMLHttpRequest(){
            var httpRequest = null;
        
            if(window.ActiveXObject){
                try{
                    httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
                } catch(e) {
                    try{
                        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e2) { httpRequest = null; }
                }
            }
            else if(window.XMLHttpRequest){
                httpRequest = new window.XMLHttpRequest();
            }
            return httpRequest;    
        }    	
    
        function checkValue()
        {	
            var form = document.forms[0];
            var commentId = '<%=commentId%>';
            console.log(commentId);         
            var writeId = '<%=writeId%>';
            console.log(writeId);
            var id = '<%=id%>';
           //현재 세션 주인의 id
			console.log(id);
			var commentText = form.comment_content.value;
		
		if (!commentText) {
			alert("내용을 입력하세요");
			return false;
		} else {
			//  var param="commentId="+commentId+"cwriteId="+cwriteId+
			// "&cId="+cId+"&commentText="+commentText;
			// commentId는 부모글의 id->comment_parent로 넣기 위해
			var param = "commentId=" + commentId + "&writeId=" + writeId
					+ "&id=" + id + "&commentText=" + commentText;

			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST",
					"http://localhost:8081/InfoGames/comment/CommentReply.do",
					true);
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded;charset=UTF-8');
			httpRequest.send(param);
		}
	}

	function checkFunc() {
		if (httpRequest.readyState == 4) {
			// 결과값을 가져온다.
			var resultText = httpRequest.responseText;
			if (resultText == 1) {
				if (opener != null) {
					// 부모창 새로고침
					window.opener.document.location.reload();
					opener.replyForm = null;
					self.close();
				}
			}
		}
	}
</script>

</head>
<body>
	<div id="wrap">
		<br> <b><font size="5" color="gray">댓글 답변</font></b>
		<hr size="1" width="550">
		<br>
		<!-- "freeboard/commentReply.jsp?commentId="+commentId,를 통해 여기로 넘어 옴 -->
		<div id="commentReplyForm">
			<form name="replyInfo" target="parentForm">
				<textarea rows="7" cols="70" name="comment_content"></textarea>
				<br>
				<br> <input type="button" value="등록" onclick="checkValue()">
				<input type="button" value="창닫기" onclick="window.close()">
			</form>
		</div>
	</div>
</body>
</html>



