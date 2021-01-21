<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.infogames.beans.*" %>

<% if(session.getAttribute("accNum") == null){ %>
	<script>
	alert("로그인 해주세요.");
	location.href="/InfoGames/login/login.do";
	</script>
<% } %>

<%
	FreeBoardDTO[] arr = (FreeBoardDTO[])request.getAttribute("list");
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
if(arr.length == 1){
	originalFileName1 = arr[0].getOriginalFileName();
}else if(arr.length == 2){
	originalFileName1 = arr[0].getOriginalFileName();
	originalFileName2 = arr[1].getOriginalFileName();
}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<title>수정<%= subject %></title>
<style>
/* 이렇게 링크해서 사용하면 됩니다 */
/* font-family : 'Nanum Gothic'; 으로 지정해 줍니다. */
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800');

body {
	font-family : 'Nanum Gothic';
	font-size: 18px;
	text-align : center;
	padding : 10px 0;
	background-color: #f2f2f2;
}

table {
  
  /*border-collapse: collapse;*/
  vertical-align: middle;
  text-align: center;
 
}

tr {
	border-top : 1px solid #444444;
	height: 40px;
}

th, td {
	border-top : 1px solid #444444;
	padding: 20px;
	
}

.btn1 {
	width: 90px;
	border-radius: 0.5em;
	color: #ffffff;
	background-color: #6aace6;
	border : 0px;
	height: 40px;
	font-size: 13px;
}

.tab1 {
    padding: 20px;
   
}
 a:link { color:#ffffff; text-decoration: none;}
 a:visited { color:#ffffff; text-decoration: none;}
 a:hover { color:#ffffff; text-decoration: underline;}
 a:active{text-decoration:none;}
 
 a#list { color: #555555;}

</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="CSS/paging.css"/>
<script src="https://kit.fontawesome.com/bb29575d31.js"></script>
</head>

<script src="ckeditor/ckeditor.js"></script>

<script>

function chkSubmit(){
	var frm = document.forms['frm'];
	var subject = frm['subject'].value.trim();
	var content = CKEDITOR.instances.content;
	
	if(subject == ""){
		alert("제목은 반드시 작성해야 합니다");
		frm["subject"].focus();
		return false;
	}
	
	if(content.getData() == ""){
		alert("내용은 반드시 작성해야 합니다");
		content.focus();
		return false;
	}
	
	return true;
}

</script>

<body>

<nav class="navbar navbar-expand-sm fixed-top navbar-light" style="display:inline-block; background-color: #6aace6;">
<a class="navbar-brand mr-auto mr-lg-0" style="font-size: xx-large; color: #ffffff; text-decoration: none;
 float:left" href="../mainpage/GameRanking.do">InfoGames</a>
 <a style="font-size: xx-large; color: #ffffff; text-decoration: none; text-align:center" href="list.do">자유게시판</a>
<span style="float: right; color: #ffffff;">
<% if(session.getAttribute("id") == null){ %>
	<a style="text-align:right;"  href='/InfoGames/login/login.do'>로그인 |  </a><a href="/InfoGames/login/signUp.do">회원가입</a>
	
<%} else{%>
	<span style="float:right;"><%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>)님</span><br>
	<a href="/InfoGames/login/logout.do">  로그아웃 |  </a>
	<a href="/InfoGames/login/welcome.do">계정정보</a>
</span>		
	
<%} %>
</nav>
<br>
<br>
<br>
<table>
<colgroup>
	<col width="20%" />
	<col width="20%" />
	<col width="20%" />
	<col width="*" />
	<col width="20%" />
</colgroup>
<tr>
<th><h2><%= subject %></h2></th>
</tr>
<tr>

<th style="border-top : 1px solid #444444;">
<%-- 글 내용이 많을수 있기 때문에 POST 방식 사용 --%>
<form name="frm" action="updateOk.do" method="post" onsubmit="return chkSubmit()">
<input type="hidden" name="writeId" value="<%=writeId %>"/>
작성자 <span style="font-weight: bold;"><%=id%>(<%= nickName %>)</span>
| 제목:
<input type="text" name="subject" value="<%= subject %>"/>
</th>
</tr>
</table>
<table>
<textarea name="content" id="content"><%= content %></textarea>
<script>
	CKEDITOR.replace("content", {
		allowdContent: true,
		filebrowserUploadUrl : '<%=request.getContextPath()%>/freeboard/ImgFileUpload.do'
	});
</script>
</table>

<p class="tab1">
<%if(originalFileName1 != null && originalFileName2 != null){ %>
업로드 파일1:<%=originalFileName1%> | 업로드 파일2:<%=originalFileName2%>
<%} else if(originalFileName1 != null){ %>
업로드 파일1:<%=originalFileName1%>
<%} %>
</p>


<input class="btn1" type="submit" value="수정"/>
</form>
</body>
<button class="btn1" onclick="history.back()">이전으로</button>
<button class="btn1" type="button" onclick="location.href='list.do'">목록으로</button>

</html>