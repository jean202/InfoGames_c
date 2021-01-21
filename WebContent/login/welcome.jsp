<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	if(session.getAttribute("id") == null){
%>
	<script>
		alert("다시 로그인 해주세요.");
		location.href = "login.do";
	</script>
<%
return;
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><%=session.getAttribute("nickName") %>님 환영합니다.</title>
</head>
<style>
/* 이렇게 링크해서 사용하면 됩니다 */
/* font-family : 'Nanum Gothic'; 으로 지정해 줍니다. */
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800');

body {
	font-family : 'Nanum Gothic';
	font-size: 15px;
	background-color: #f2f2f2;
}

.btn1 {
	width: 120px;
	border-radius: 0.5em;
	color: #ffffff;
	background-color: #6aace6;
	border : 0px solid ;
	height: 45px;
	font-size: 12px;
}

</style>
<body>
<p style="text-align:center;">
<a  style="font-size: xx-large; color: black; text-decoration: none;"href="/InfoGames/mainpage/GameRanking.do">InfoGames</a>
</p>
<br>
<br>
<br>
<br>
<center>
<table>
<tr>
<tr><h1><%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>)님 </h1></tr>
<td><button class="btn1" type="button" onclick="location.href='logout.do'">로그아웃</button>
<button class="btn1" type="button" onclick="location.href='updateAccChk.do'">계정정보 수정하기</button>
<button class="btn1" type="button" onclick="location.href='delete.do?id=<%=session.getAttribute("id")%>'">계정삭제하기</button></td>
</tr>
</table>
</center>
</body>
</html>