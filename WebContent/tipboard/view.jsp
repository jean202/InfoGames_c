<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.infogames.beans.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	TipBoardDTO[] arr = (TipBoardDTO[])request.getAttribute("list");
%>

<%
	if(arr == null || arr.length == 0){
%>
<script>
	alert("해당 글은 삭제되거나 없습니다");
	history.back();
</script>	
<%
	return;
	}
%>

<%
	String id = arr[0].getId();
	String nickName = arr[0].getNickName();
	String subject = arr[0].getSubject();
	String content = arr[0].getContent();
	String regdate = arr[0].getRegDate();
	int viewcnt = arr[0].getViewCnt();
	int writeId = arr[0].getWriteId();
	String originalFileName1 = null;
	String originalFileName2 = null;
	String systemFileName1 = null;
	String systemFileName2 = null;
	if(arr.length == 1){
		originalFileName1 = arr[0].getOriginalFileName();
		systemFileName1 = arr[0].getSystemFileName();
	}else if(arr.length == 2){
		originalFileName1 = arr[0].getOriginalFileName();
		originalFileName2 = arr[1].getOriginalFileName();
		systemFileName1 = arr[0].getSystemFileName();
		systemFileName2 = arr[1].getSystemFileName();
	}
%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous00">
<title>읽기 <%= subject %></title>
<style>
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800');
*{padding:0px; margin:0px;}
body {
	font-family : 'Nanum Gothic';
	font-size: 18px;
	text-align : center;
	padding : 10px 0;
	background-color: #ffffff;
	overflow:auto; /*자동 스크롤*/
	min-width: 552px;
}

#table, #table th, #table td  {
  border-collapse: collapse;
  vertical-align: middle;
  text-align: center;

}

table {
	text-align : left;
	vertical-align: middle;
	width:100%;
}

td{
	margin:0 auto 0 auto;
}

#table th, #table td {
	padding: 10px;
	border-top: 1px solid #DAEFF8;
}

.skyblue  {
	background-color: #DAEFF8;
	padding: 0 0 0 10px;
	text-align: center;
}
.skyborder{
	background-color:#EFF7FA;
	border: 10px solid #ffffff;
	text-align: center;	
}

#ctable{
	vertical-align:middle;
	background-color:#EFF7FA;
}
#ctable th, #ctable td {
	padding: 20px 0px 20px 20px;
}
 
#twrap {
            min-width: 552px;
            margin: 0 auto 0 auto;  
            
        }
#subject{
	min-height:300px;
}
#sbb{
	/*border-bottom:10px solid #DAEFF8;*/
	box-shadow:  inset 0 0 10px #DAEFF8, 2px 2px 2px #DAEFF8;
}
#coId{
	padding-left:20px;
}

textarea {
	width:650px;
	height:100px;
}

@media (max-width: 1023px) {
 textarea {
	width:500px;
	height:80px;
	}
}
@media ( max-width: 767px ) {
  textarea {
	width:300px;
	height:60px; 
	}
}

.c1 {
background-color:#DAEFF8;
	width: 400px;
}
.c2 {
background-color:#DAEFF8;
	width: 400px;
}
.c3 {
background-color:#DAEFF8;
	width: 150px;
	text-align: right;
	padding: 15px;
}

tr {
	height: 40px;
}

.btn1 {
	width: 90px;
	border-radius: 0.5em;
	color: #ffffff;
	background-color: #6aace6;
	border : 0px solid ;
	height: 40px;
	font-size: 13px;
}
.btn1C{
	text-align:center;
}
#d{
	font-size:24px;
}
#d1 {
	
	padding: 15px;
	text-align: left;
}

 a:link { color:#ffffff; text-decoration: none;}
 a:visited { color:#ffffff; text-decoration: none;}
 a:hover { color:#ffffff; text-decoration: underline;}
 a:active{text-decoration:none;}
 
 a#list { color: #555555;}


#btn a:link { color:#212529; text-decoration: none;}
#btn a:visited { color:#212529; text-decoration: none;}
#btn a:hover { color:#212529; text-decoration: underline;}
#btn a:active{text-decoration:none;}


span {
	display: inline-block;
}
        
#btn{		
            font-family :'돋움';
            font-size : 14;
            text-align :center;
            vertical-align:middle;
            width:116px;
        }

@media (max-width: 1023px) {
 #btn{		
           width:116px;
        }
}
@media ( max-width: 767px ) {
  #btn{		
           width:116px;
        }
}
</style>
<link rel="stylesheet" type="text/css" href="CSS/paging.css"/>
<script src="https://kit.fontawesome.com/bb29575d31.js"></script>
</head>

<script>
function chkDelete(writeId){
	var r = confirm("삭제하시겠습니까?");

	if(r){
	location.href = "deleteOk.do?writeId=" + writeId;	
	}
}
var httpRequest = null;

// 댓글 Ajax통신
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

// 댓글 등록
function writeCmt()
{
    var form = document.getElementById("writeCommentForm");
    
    var cwriteId = form.cwriteId.value
    var cId = form.cId.value
    var commentText = form.commentText.value;
    
    if(!commentText)
    {
        alert("내용을 입력하세요.");
        return false;
    }
    else
    {   // writeId와 id로 했어도 잘 동작했을 것임.
        var param="cwriteId="+cwriteId+"&cId="+cId+"&commentText="+commentText;
            
        httpRequest = getXMLHttpRequest();
        httpRequest.onreadystatechange = checkFunc;
        httpRequest.open("POST", "http://localhost:8081/InfoGames/comment/CommentWrite.do" , true);
        // httpRequest.open("POST", "CommentWrite.do" , true);
        // 여기서 view를 오픈해 줘야 하나??
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8'); 
        httpRequest.send(param);
        //alert("여기까지 왔다\n" + param + "cwriteId : " + cwriteId);
        // 이게 제대로 전달이 되어도,
    }
}

function checkFunc(){
    if(httpRequest.readyState == 4 && httpRequest.status == 200){
        // 결과값을 가져온다.
        var resultText = httpRequest.responseText;
        if(resultText == 1){ 
            document.location.reload(); // 상세보기 창 새로고침
            console.log(document.location);
        }
    }
}

// 댓글 답변
function cmReplyOpen(commentId)
{
	var userId = '<%=(String)session.getAttribute("id")%>';
    console.log(userId); 
    if(userId == "" || userId == "null"){
        alert("로그인후 사용가능합니다.");
        return false;
    }
    else{
        // 댓글 답변창 open
        window.name = "parentForm";
        // 열어줄 자식 창 주소를 명시, 자식창 이름(replyForm)과 설정사항을 정해준다.
        window.open("commentReplyForm.do?commentId="+commentId,
                    "commentReplyForm", "width=570, height=350, resizable = no, scrollbars = no");
    }
}

// 댓글 삭제창
function cmDeleteOpen(commentId){
    var msg = confirm("댓글을 삭제합니다.");    
    if(msg == true){ // 확인을 누를경우
        deleteCmt(commentId);
    }
    else{
        return false; // 삭제취소
    }
}

// 댓글 삭제
function deleteCmt(commentId)
{
    var param="commentId="+commentId;
    
    httpRequest = getXMLHttpRequest();
    httpRequest.onreadystatechange = checkFunc;
    httpRequest.open("POST", "http://localhost:8081/InfoGames/comment/CommentDelete.do", true);    
    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8'); 
    httpRequest.send(param);
}

//댓글 수정창
function cmUpdateOpen(commentId){
    window.name = "parentForm";
    window.open("commentUpdateForm.do?commentId="+commentId,
                "updateForm", "width=570, height=350, resizable = no, scrollbars = no");
}

</script>

<body>
<!-- <div style="text-align:left; word-spacing:510px; padding:15px;"> -->
<nav class="navbar navbar-expand-sm fixed-top navbar-light" style="display:inline-block;background-color: #6aace6;">
<a class="navbar-brand mr-auto mr-lg-0" style="font-size: xx-large; color: #ffffff; text-decoration: none;
 float:left" href="../mainpage/GameRanking.do">InfoGames</a>
<a style="font-size: xx-large; float:center;color: #ffffff; text-decoration: none; text-align:center;" href="list.do">팁게시판</a>
<span style="float: right; color: #ffffff;">
<% if(session.getAttribute("id") == null){ %>
	<a style="text-align:right;" href='/InfoGames/login/login.do'>로그인|  </a><a href="/InfoGames/login/signUp.do">회원가입</a>
	
<%} else{%>
	<span style="float:right;"><%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>)님</span><br>
	<a href="/InfoGames/login/logout.do">  로그아웃|  </a>
	<a href="/InfoGames/login/welcome.do">계정정보</a>
</span>	
	
<%} %>
</nav>
<!--</div>  -->
<br>
<br>
<br>
<div id="twrap">
<table id="table">
<tr>
<th class = "skyblue" colspan="3" id="d"><%= subject %></th>
</tr>
<tr>
<td class="c1" id="d1"><span style="font-weight: bold;"><%=id%>(<%= nickName %>)</span></td>
<td class="c2"><%= regdate %></td>
<td class="c3">조회수 <%= viewcnt %></td>
</tr>
<tr >
<td id="sbb" colspan="3">
<div id="subject">
<%= content %>
<!-- 댓글 부분 -->
<!-- request.setAttribute("commentList", arr); //commentList에 CommentDTO
(commentId, writeId, id, nickName, commentDate, commentText, comment_parent, comment_level)담겨있다-->


</div>
</td>
</tr>

<%if(session.getAttribute("accNum") != null){ %>
<%if((Integer)session.getAttribute("accNum") == arr[0].getAccNum()){ %>
<tr style="padding: 10px; margin:10px;">

<%if(originalFileName1 != null && originalFileName2 != null){ %>
<td class = "skyborder" style="width:50%">업로드 파일1:<a id="list" href="FileDownload.do?originalFileName=<%=originalFileName1%>&systemFileName=<%=systemFileName1%>"><%=originalFileName1%></a></td>

<td class = "skyborder" colspan="2" style="width:50%">업로드 파일2:<a id="list" href="FileDownload.do?originalFileName=<%=originalFileName2%>&systemFileName=<%=systemFileName2%>"><%=originalFileName2%></a>
<%} else if(originalFileName1 != null){ %>
<td class = "skyborder" >업로드 파일1:<a href="FileDownload.do?originalFileName=<%=originalFileName1%>&systemFileName=<%=systemFileName1%>"><%=originalFileName1%></a>
<%} %></td></tr>
</table>
</div>
<br>
<%String sessionId = String.valueOf(session.getAttribute("id"));%>
 <c:set var="sessionID" value="<%=sessionId%>" />
 <div id="twrap">
    <div id="comment">
        <table id="ctable">
    <!-- 댓글 목록 -->    
    <c:if test="${requestScope.commentList != null}">
        <c:forEach var="comment" items="${requestScope.commentList}">
            <tr>
                <!-- 작성자 아이디, 작성날짜 -->
                <td width="20%">
                    <div id="coId">
                     <c:if test="${comment.comment_level > 1}">
                            &nbsp;&nbsp;&nbsp;&nbsp; <!-- 답변글일경우 아이디 앞에 공백을 준다. -->
                            <img src="../img/reply_icon.gif">
                        </c:if>
                        ${comment.id}<br>
                        <font size="2" color="lightgray">${comment.commentDate}</font>
                    </div>
                </td>
                <!-- 본문내용 -->
                <td width="550">
                    <div class="text_wrapper">
                        &nbsp;&nbsp; ${comment.commentText}
                    </div>
                </td>
                <!-- 버튼 -->
                <td >
                    <div id="btn" >
                        <a href="#" onclick="cmReplyOpen(${comment.commentId})">[답변]</a><br>
                    <!-- 댓글 작성자만 수정, 삭제 가능하도록 -->    
                    <c:if test="${comment.id == sessionID}">
                        <a href="#" onclick="cmUpdateOpen(${comment.commentId})">[수정]</a><br>    
                        <a href="#" onclick="cmDeleteOpen(${comment.commentId})">[삭제]</a>
                    </c:if>        
                    </div>
                </td>
            </tr>
        </c:forEach>
    </c:if>
<!-- 로그인 했을 경우만 댓글 작성가능 -->
<c:if test="${sessionID != 'null'}"><!-- null이 문자열 null이 된 상황 -->
 <tr>
            <form id="writeCommentForm"> <!-- "${writeId}" -->
                <input type="hidden" name="cwriteId" value="<%=writeId %>">
                <input type="hidden" name="cId" value="${sessionID}">
                <!-- 아이디-->
                <td width="20%">
                    <div id="coId">
                        ${sessionID}
                    </div>
                </td>
                <!-- 본문 작성-->
                <td width="60%">
                    <div>
                        <textarea name="commentText"></textarea>
                    </div>
                </td>
                <!-- 댓글 등록 버튼 -->
                <td >
                    <div id="btn" style="text-align:center;">
                    <!--<a href="/comment/CommentWrite.do" onclick="writeCmt()">  -->
                        <a href="#" onclick="writeCmt()">[댓글등록]</a>   
                    </div>
                </td>
            </form>
            </tr>
            </c:if>    
        </table>
    </div>
    </div> 

<br>
<div class="btn1C">
<button class="btn1" onclick="location.href='update.do?writeId=<%= writeId %>'">수정하기</button>
<button class="btn1" onclick="chkDelete(<%= writeId %>)">삭제하기</button>
 <button class="btn1" onclick="location.href='list.do'">목록보기</button>
 </div>
<% } else {%>
</table>
</div>
<br>
<br>
<%String sessionId = String.valueOf(session.getAttribute("id"));%>
 <c:set var="sessionID" value="<%=sessionId%>" />
 <div id="twrap">
    <div id="comment">
        <table id="ctable">
    <!-- 댓글 목록 -->    
    <c:if test="${requestScope.commentList != null}">
        <c:forEach var="comment" items="${requestScope.commentList}">
            <tr>
                <!-- 작성자 아이디, 작성날짜 -->
                <td  width="20%">
                    <div id="coId">
                     <c:if test="${comment.comment_level > 1}">
                            &nbsp;&nbsp;&nbsp;&nbsp; <!-- 답변글일경우 아이디 앞에 공백을 준다. -->
                            <img src="../img/reply_icon.gif">
                        </c:if>
                        ${comment.id}<br>
                        <font size="2" color="lightgray">${comment.commentDate}</font>
                    </div>
                </td>
                <!-- 본문내용 -->
                <td width="550">
                    <div class="text_wrapper">
                        &nbsp;&nbsp; ${comment.commentText}
                    </div>
                </td>
                <!-- 버튼 -->
                <td >
                    <div id="btn" >
                        <a href="#" onclick="cmReplyOpen(${comment.commentId})">[답변]</a><br>
                    <!-- 댓글 작성자만 수정, 삭제 가능하도록 -->    
                    <c:if test="${comment.id == sessionID}">
                        <a href="#" onclick="cmUpdateOpen(${comment.commentId})">[수정]</a><br>    
                        <a href="#" onclick="cmDeleteOpen(${comment.commentId})">[삭제]</a>
                    </c:if>        
                    </div>
                </td>
            </tr>
        </c:forEach>
    </c:if>
<!-- 로그인 했을 경우만 댓글 작성가능 -->
<c:if test="${sessionID != 'null'}"><!-- null이 문자열 null이 된 상황 -->
 <tr>
            <form id="writeCommentForm"> <!-- "${writeId}" -->
                <input type="hidden" name="cwriteId" value="<%=writeId %>">
                <input type="hidden" name="cId" value="${sessionID}">
                <!-- 아이디-->
                <td width="20%">
                    <div id="coId">
                        ${sessionID}
                    </div>
                </td>
                <!-- 본문 작성-->
                <td width="60%">
                    <div>
                        <textarea name="commentText"></textarea>
                    </div>
                </td>
                <!-- 댓글 등록 버튼 -->
                <td>
                    <div id="btn" >
                    <!--<a href="/comment/CommentWrite.do" onclick="writeCmt()">  -->
                        <a href="#" onclick="writeCmt()">[댓글등록]</a>   
                    </div>
                </td>
            </form>
            </tr>
            </c:if>    
        </table>
    </div>
    </div> 
<br>
<div class="btn1C">
<button class="btn1" onclick="location.href='list.do'">목록보기</button>
</div>
<%} } else {%>
</table> 
</div>
<%String sessionId = String.valueOf(session.getAttribute("id"));%>
 <c:set var="sessionID" value="<%=sessionId%>" />
 <div id="twrap">
    <div id="comment">
        <table id="ctable">
    <!-- 댓글 목록 -->    
    <c:if test="${requestScope.commentList != null}">
<br>
<br>
        <c:forEach var="comment" items="${requestScope.commentList}">
            <tr>
                <!-- 작성자 아이디, 작성날짜 -->
                <td width="20%">
                    <div id="coId">
                     <c:if test="${comment.comment_level > 1}">
                            &nbsp;&nbsp;&nbsp;&nbsp; <!-- 답변글일경우 아이디 앞에 공백을 준다. -->
                            <img src="../img/reply_icon.gif">
                        </c:if>
                        ${comment.id}<br>
                        <font size="2" color="lightgray">${comment.commentDate}</font>
                    </div>
                </td>
                <!-- 본문내용 -->
                <td width="550">
                    <div class="text_wrapper">
                       &nbsp;&nbsp;  ${comment.commentText}
                    </div>
                </td>
                <!-- 버튼 -->
                <td>
                    <div id="btn" >
                        <a href="#" onclick="cmReplyOpen(${comment.commentId})">[답변]</a><br>
                    <!-- 댓글 작성자만 수정, 삭제 가능하도록 -->    
                    <c:if test="${comment.id == sessionID}">
                        <a href="#" onclick="cmUpdateOpen(${comment.commentId})">[수정]</a><br>    
                        <a href="#" onclick="cmDeleteOpen(${comment.commentId})">[삭제]</a>
                    </c:if>        
                    </div>
                </td>
            </tr>
        </c:forEach>
    </c:if>
<!-- 로그인 했을 경우만 댓글 작성가능 -->
<c:if test="${sessionID != 'null'}"><!-- null이 문자열 null이 된 상황 -->
 <tr>
            <form id="writeCommentForm"> <!-- "${writeId}" -->
                <input type="hidden" name="cwriteId" value="<%=writeId %>">
                <input type="hidden" name="cId" value="${sessionID}">
                <!-- 아이디-->
                <td width="20%">
                    <div id="coId">
                        ${sessionID}
                    </div>
                </td>
                <!-- 본문 작성-->
                <td width="60%">
                    <div>
                        <textarea name="commentText"></textarea>
                    </div>
                </td>
                <!-- 댓글 등록 버튼 -->
                <td>
                    <div id="btn" >
                    <!--<a href="/comment/CommentWrite.do" onclick="writeCmt()">  -->
                        <a href="#" onclick="writeCmt()">[댓글등록]</a>    
                    </div>
                </td>
            </form>
            </tr>
            </c:if>    
        </table>
    </div>
    </div>
<br>
<div class="btn1C">
<button class="btn1" onclick="location.href='list.do'">목록보기</button>
</div>
<%} %>

</body>
</html>