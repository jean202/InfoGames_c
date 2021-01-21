<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>

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

.btn1 {
	width: 80px;
	border-radius: 0.5em;
	color: #ffffff;
	background-color: #6aace6;
	border : 0px solid ;
	height: 30px;
	font-size: 10px;
}

.idit {
	padding: 10px 10px 10px 10px;
	width: 228px;
	color: #b1aca8;
}

.pwit {
	padding: 10px 10px 10px 10px;
	width: 228px;
	color: #b1aca8;
}
.sbit {
	padding: 10px 10px 10px 10px;
	width: 250px;
	background-color: #337ab7;
	border-radius: 0.5em;
	color: #ffffff;
	border : 0px solid ;
}

</style>
	
</head>

<%
if(session.getAttribute("id") != null){
%>
<script>
location.href="welcome.do";
</script>
<%
	return;
}
%>

<script>

function chkSubmit(){
	var frm = document.forms['frm'];
	var id = frm['id'].value.trim();
	var pw = frm['pw'].value.trim();
	
	
	if(id == ""){
		alert("ID는 반드시 입력해야 합니다!");
		frm['id'].focus();
		return false;
	}
	
	if(pw == ""){
		alert("비밀번호는 반드시 입력해야 합니다!");
		frm['pw'].focus();
		return false;
	}
	
	return true;
}


</script>

<body>
<p style="text-align:center;">
<a style="font-size: xx-large; color: black; text-decoration: none; "href="/InfoGames/mainpage/GameRanking.do">InfoGames</a>
</p>
<br>
<br>
<br>
<form name="frm" action="loginOk.do" method="post" onsubmit="return chkSubmit()">
<input class="idit" type="text" name="id" placeholder="아이디를 입력해주세요."/><br>
<input class="pwit" type="password" name="pw" placeholder="비밀번호를 입력해주세요."/>
<p><input class="sbit" type="submit" value="로그인"/></p>
</form>

<button class="btn1" type="button" onclick="location.href='signUp.do'">회원가입</button>
<button class="btn1" type="button" onclick="location.href='forgotId.do'">ID찾기</button>
<button class="btn1" type="button" onclick="location.href='forgotPw.do'">비밀번호찾기</button>
</body>

</html>