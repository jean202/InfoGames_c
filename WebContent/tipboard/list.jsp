<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.infogames.beans.*" %>
<%
	TipBoardDTO[] arr = (TipBoardDTO[])request.getAttribute("list");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<title>팁게시판</title>
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

table,th,td  {
  border-top : 1px solid black;
  border-collapse: collapse;
  vertical-align: middle;
  text-align: center;
  border-color: #000000;
}

table{
	width:100%;
	height:100px;
	margin:auto;
	vertical-align: middle;
	text-align: center;
	border-color: #000000;
}

th, td {
	border-bottom: 1px solid #444444;
	padding: 20px;
	text-align: center;
	border-color: #000000;
}

th {
	text-align: center;
	background-color: #e6e6e6;
}

.btn1 {
	width: 90px;
	border-radius: 0.5em;
	color: #ffffff;
	background-color: #6aace6;
	border : 0px solid ;
	height: 40px;
	font-size: 15px;
}

 a:link { color:#ffffff; text-decoration: none;}
 a:visited { color:#ffffff; text-decoration: none;}
 a:hover { color:#ffffff; text-decoration: underline;}
 a:active{text-decoration:none;}
 
 a#list { color: #555555;}
 
span {
	display: inline-block;
}

</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="CSS/paging.css"/>
<script src="https://kit.fontawesome.com/bb29575d31.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm fixed-top navbar-light" style="display:inline-block; background-color: #6aace6;">
<a class="navbar-brand mr-auto mr-lg-0" style="font-size: xx-large; float: left; color: #ffffff; text-decoration: none; text-align: left;" href="../mainpage/GameRanking.do">InfoGames</a>

<a style="font-size: xx-large; color: #ffffff; float: center; text-decoration: none; text-align:center" href="list.do">팁게시판</a>
<span style="float: right; color: #ffffff;">
<% if(session.getAttribute("id") == null){ %>	
	<a href='/InfoGames/login/login.do'>로그인 | </a><a href="/InfoGames/login/signUp.do">회원가입</a>
<%} else{%>	
<span style="float:right;"><%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>)님</span><br>
	<a href="/InfoGames/login/logout.do">로그아웃 | </a>
	<a href="/InfoGames/login/welcome.do">계정정보</a>	
</span>	
<%} %>
</nav>

<br>
<br>
<br>
<br>
<hr>
<h4>총 <%= request.getAttribute("totalWrite") %>개의 게시글이 존재합니다</h4>
<hr>
<%
	if(arr == null || arr.length == 0){
		out.println("<h2>게시글이 하나도 없어요 ㅠㅠ...</h2><br>");	
	} else{
%>
<table style="width:100%">
<tr>
<th>번호</th>
<th>제목</th>
<th>작성자</th>
<th>조회수</th>
<th>등록일</th>
</tr>
<%
	for(int i = 0; i < arr.length; i++){
	out.println("<tr>");
	out.println("<td>" + arr[i].getRnum() + "</td>");
	out.println("<td><a id='list' href='view.do?writeId=" + arr[i].getWriteId() + "&page=" + request.getAttribute("curPage") + "'>" + arr[i].getSubject() + "</a></td>");
	out.println("<td>" + arr[i].getId() + "(" + arr[i].getNickName() + ")</td>");
	out.println("<td>" + arr[i].getViewCnt() + "</td>");
	out.println("<td>" + arr[i].getRegDate() + "</td>");
	out.println("</tr>");
	} 
%>

</table>

<%
	}
%>
<h4>현재 <%=request.getAttribute("curPage") %>페이지</h4>
<%if(session.getAttribute("accNum") != null){ %>
<button class="btn1" onClick="location.href='write.do'">새글등록</button>
<% }%>

<jsp:include page="pagination.jsp">
<jsp:param value="<%= request.getAttribute(\"perPage\") %>" name="perPage"/>
<jsp:param value="<%= request.getAttribute(\"totalPage\") %>" name="totalPage"/>
<jsp:param value="<%= request.getAttribute(\"curPage\") %>" name="curPage"/>
</jsp:include>
</body>
</html>