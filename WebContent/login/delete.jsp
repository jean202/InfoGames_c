<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("id") == null) {
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
<title><%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>)
	계정 삭제하기</title>
</head>

<style>
/* 이렇게 링크해서 사용하면 됩니다 */
/* font-family : 'Nanum Gothic'; 으로 지정해 줍니다. */
@import
	url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800')
	;

body {
	font-family: 'Nanum Gothic';
	font-size: 15px;
	background-color: #f2f2f2;
}

table, td {
	text-align: left;
}

th {
	width: 200px;
	text-align: left;
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
	border: 0px solid;
	font-size: 12px;
	width: 180px;
	height: 50px;
}

.btn3 {
	border-radius: 0.5em;
	color: #ffffff;
	background-color: #6aace6;
	border: 0px solid;
	font-size: 13px;
	font-style: boder;
	width: 180px;
	height: 50px;
}
</style>

<script>
	function chkSubmit() {
		var frm = document.forms['frm'];
		var pw = frm['pw'].value.trim();
		var chkPw = frm['chkPw'].value.trim();

		if (pw == "") {
			alert("비밀번호를 입력하세요.");
			frm['pw'].focus();
			return false;
		}
		if (chkPw == "") {
			alert("비밀번호 확인을 입력하세요.");
			frm['chkPw'].focus();
			return false;
		}
		if (pw != chkPw) {
			alert("비밀번호가 일치하지 않습니다.");
			frm['chkPw'].focus();
			return false;
		}
		if (pw != "<%=session.getAttribute("pw")%>") {
			alert("현재 계정의 비밀번호와 일치하지 않습니다.");
			frm['pw'].focus();
			return false;
		}
		if (pw == chkPw && pw == "<%=session.getAttribute("pw")%>
	") {

			if (confirm("정말 계정을 삭제하시겠습니까?") == true) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
</script>

<body>
	<p style="text-align: center;">
		<a style="font-size: xx-large; color: black; text-decoration: none;"
			href="/InfoGames/mainpage/GameRanking.do">InfoGames</a>
	</p>
	<center>

		<h1><%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>)
			계정 삭제하기
		</h1>
		<br> <br> <br> <br>
		<form name="frm" action="deleteOk.do" method="post"
			onsubmit="return chkSubmit()">
			<table>
				<tr>
					<th>비밀번호:</th>
					<td><input class="suip" type="password" name="pw" maxlength="25" /></td>
				</tr>
				<tr>
					<th>비밀번호확인:</th>
					<td><input class="suip" type="password" name="chkPw" maxlength="25" /></td>
					<input type="hidden" name="accNum"
						value="<%=session.getAttribute("accNum")%>">
				</tr>
			</table>
			<p>
				<input class="btn2" type="submit" value="삭제하기">
			</p>
			<button class="btn3"  onclick="location.href='welcome.do'">회원창으로 이동</button>
		</form>
	</center>
</body>
</html>