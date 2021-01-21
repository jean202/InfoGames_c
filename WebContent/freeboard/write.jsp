<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>글작성</title>
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
  border-top : 1px solid #444444;
  border-collapse: collapse;
  vertical-align: middle;
  text-align: center;
  border-color: #444444;
}
table,td  {
	text-align : left;
	margin:auto;
	vertical-align: middle;
	border-color: #444444;
}
tr {
	height: 40px;
}

th, td {
	padding: 20px;
	border-color: #444444;
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
    border-color: #444444;	
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

<% if(session.getAttribute("accNum") == null){ %>
	<script>
	alert("로그인 해주세요.");
	location.href="/InfoGames/login/login.do";
	</script>
<% } %>
<script src="ckeditor/ckeditor.js"></script>

<script>

function chkSubmit(){
	var frm = document.forms['frm'];
	var subject = frm['subject'].value.trim();
	var content = CKEDITOR.instances.content;
	
	if(subject == ""){
		alert("제목은 반드시 작성해야 합니다");
		frm['subject'].focus();
		return false;
	}
	
	if(content.getData() == ""){
		alert("내용은 반드시 작성해야 합니다");
		content.focus();
		return false;
	}
	
	return true;	
} // end chkSubmit()

function openFileUpload(){
	window.open("FileUploadForm.do", "fileUploadForm", "width=500, height=300, resizable=no, scrollbars=no");
}

</script>



<body>

<nav class="navbar navbar-expand-sm fixed-top navbar-light" style="display:inline-block; background-color: #6aace6;">
<a class="navbar-brand mr-auto mr-lg-0" style="font-size: xx-large; color: #ffffff; text-decoration: none;
 float:left" href="../mainpage/GameRanking.do">InfoGames</a>

<a style="font-size: xx-large;float:center; color: #ffffff; text-decoration: none; text-align:center" href="list.do">자유게시판</a>
<span style="float: right; color: #ffffff;">
<% if(session.getAttribute("id") == null){ %>	
	<a href='/InfoGames/login/login.do'>로그인 |  </a><a href="/InfoGames/login/signUp.do">회원가입</a>
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
<th>
<h2>글작성</h2>

</th>
</tr>
<tr>
<th>
<form name="frm" action="writeOk.do" method="post" onsubmit="return chkSubmit()">
<input type="hidden" name="accNum" value="<%=session.getAttribute("accNum") %>">
<%--작성자:
<%=session.getAttribute("id") %>(<%=session.getAttribute("nickName") %>)
||  --%>제목:	
<input type="text" name="subject"/>
 <button class="btn1" type="button" onclick="openFileUpload()">파일 업로드</button>
<%if(request.getParameter("originalFileName1") != null && request.getParameter("originalFileName2") != null){ %>
 파일1:<%=request.getParameter("originalFileName1") %> | 파일2:<%=request.getParameter("originalFileName2") %> 
<input type="hidden" name="originalFileName" value="<%=request.getParameter("originalFileName1")%>">
<input type="hidden" name="originalFileName" value="<%=request.getParameter("originalFileName2")%>">
<input type="hidden" name="systemFileName" value="<%=request.getParameter("systemFileName1")%>">
<input type="hidden" name="systemFileName" value="<%=request.getParameter("systemFileName2")%>">
<%}else if(request.getParameter("originalFileName1") != null){ %>
 파일1:<%=request.getParameter("originalFileName1") %>
<input type="hidden" name="originalFileName" value="<%=request.getParameter("originalFileName1")%>">
<input type="hidden" name="systemFileName" value="<%=request.getParameter("systemFileName1")%>">
<%} %>
</th>
</tr>

</table>
<textarea name="content" id="content"></textarea>
<table>
<tr>
<script>
	CKEDITOR.replace("content", {
		allowdContent: true,
		filebrowserUploadUrl : '<%=request.getContextPath()%>/tipboard/ImgFileUpload.do'
		});
</script>
</tr>

</table>

<input class="btn1" type="submit" value="등록"/>
</form>
</body>
<button class="btn1" type="button" onclick="location.href='list.do'">목록으로</button>



</html>