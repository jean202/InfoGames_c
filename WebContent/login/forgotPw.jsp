<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>

<style>
/* 이렇게 링크해서 사용하면 됩니다 */
/* font-family : 'Nanum Gothic'; 으로 지정해 줍니다. */
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800');

body {
	font-family : 'Nanum Gothic';
	font-size: 15px;
	background-color: #f2f2f2;
}

table,td  {
	text-align : left;
}

th  {
	width: 200px;
	text-align : left;
	background-color: #e6e6e6;
	padding: 0 0 0 10px;
	font-size: 15px;
}

tr {
	height: 40px;
}

.suip {
	padding: 10px 10px 10px 10px;
	width: 228px;
	color: #b1aca8;
}

.btn2 {
	border-radius: 0.5em;
	color: #ffffff;
	background-color: #337ab7;
	border : 0px solid ;
	font-size: 12px;
	width: 180px;
	height: 50px;
}

.btn3 {
	border-radius: 0.5em;
	color: #ffffff;
	background-color: #6aace6;
	border : 0px solid ;
	font-size: 13px;
	font-style: boder;
	width: 180px;
	height: 50px;
}

</style>

</head>

<%
	if(session.getAttribute("id") != null){
%>
	<script>
		alert("로그아웃 후에 이용해주세요.");
		location.href = "welcome.do";
	</script>
<%
return;
	}
%>

<script>

function chkSubmit(){
	var frm = document.forms['frm'];
	var id = frm['id'].value.trim();
	var email = frm['email'].value.trim();
	
	if(id == ""){
		alert("아이디를 입력하세요.");
		frm['id'].focus();
		return false;
	}
	if(email == ""){
		alert("이메일을 입력하세요.");
		frm['email'].focus();
		return false;
	}
	return true;
}

</script>

<body>
<center>
		<p style="text-align: center;">
			<a style="font-size: xx-large; color: black; text-decoration: none;"
				href="/InfoGames/mainpage/GameRanking.do">InfoGames</a>
		</p>
		<br>
<br>
<br>
<br>
		<h1>비밀번호 찾기</h1>

		<form name="frm" action="forgotPwOk.do" method="post"
			onsubmit="return chkSubmit()">
			<table>
				<tr>
					<th>이름:</th>
					<td><input class="suip" type="text" name="id">
					</td>
				</tr>
				<tr>
					<th>Email:</th>
					<td><input class="suip" type="text" name="email"
						></td>
				</tr>
			</table>
			<p>
			<input class="btn2" type="submit" value="비밀번호찾기">
			</p>
			<button class="btn3" type="button" onclick="location.href='login.do'">로그인
				화면으로 이동</button>
		</form>
	</center>
</body>
</html>