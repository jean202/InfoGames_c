<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>) 계정 정보 수정하기</title>
<style>


/* 이렇게 링크해서 사용하면 됩니다 */
/* font-family : 'Nanum Gothic'; 으로 지정해 줍니다. */
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800');

body {
	font-family : 'Nanum Gothic';
	font-size: 18px;
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


.btn2 {
	border-radius: 0.5em;
	color: #ffffff;
	background-color: #6aace6;
	border : 0px solid ;
	font-size: 12px;
	width: 100px;
	height: 40px;
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
.suip {
	padding: 10px 10px 10px 10px;
	width: 228px;
	color: #000000;
}
</style>
</head>

<%
	if(session.getAttribute("id") == null){
%>
	<script>
		alert("로그인 후에 이용해주세요.");
		location.href = "login.do";
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
	var chkPw = frm['chkPw'].value.trim();
	var email = frm['email'].value.trim();
	var nickName = frm['nickName'].value.trim();
	var idDuplication = frm['idDuplication'].value.trim();
	var nickNameDuplication = frm['nickNameDuplication'].value.trim();
	
	var idReg = /^[A-za-z0-9]/g;
	var emailReg = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

	if(id == ""){
		alert("아이디를 입력하세요.");
		frm['id'].focus();
		return false;
	}
	if(idReg.test(id)==false){
		alert("아이디를 제대로 입력하세요.");
		frm['id'].focus();
		return false;
	}
	if(pw == ""){
		alert("비밀번호를 입력하세요.");
		frm['pw'].focus();
		return false;
	}
	if(chkPw == ""){
		alert("비밀번호 확인을 입력하세요.");
		frm['chkPw'].focus();
		return false;
	}
	if(pw != chkPw){
		alert("비밀번호가 일치하지 않습니다.");
		frm['chkPw'].focus();
		return false;
	}
	if(email == ""){
		alert("이메일을 입력하세요.");
		frm['email'].focus();
		return false;
	}
	if (emailReg.test(email)==false) {
		   alert("이메일을 제대로 입력하세요.");
		   frm['email'].focus();
		   return false;
		}
	if(nickName == ""){
		alert("닉네임을 입력하세요.");
		frm['nickName'].focus();
		return false;
	}
	if(idDuplication != "idCheck"){
		alert("아이디 중복체크를 하세요.")
		return false;
	}
	if(nickNameDuplication != "nickNameCheck"){
		alert("닉네임 중복체크를 하세요.")
		return false;
	}
	
	return true;
}

function openChkId(){
	var frm = document.forms['frm'];
	var id = frm['id'].value.trim();
	var idReg = /^[A-za-z0-9]/g;
	
	if(id == ""){
		alert("아이디를 입력하세요.");
		return;
	}
	
	if(id == "<%=session.getAttribute("id")%>"){
		alert("사용 가능한 아이디입니다.");
		document.forms['frm'].idDuplication.value="idCheck";
		return;
	}
	
	if(idReg.test(id)==false){
		alert("아이디를 제대로 입력하세요.");
		frm['id'].focus();
		return;
	}
	
	
	window.open("idCheckOk.do?id=" + frm['id'].value.trim(), "chkId", "width=500, height=300, resizable=no, scrollbars=no");
}

function inputIdChk(){
	document.forms['frm'].idDuplication.value="idUncheck";
}

function openChkNickName(){

	if(document.forms['frm']['nickName'].value.trim() == ""){
		alert("닉네임을 입력하세요.");
		return;
	}
	
	if(document.forms['frm']['nickName'].value.trim() == "<%=session.getAttribute("nickName")%>"){
		alert("사용 가능한 닉네임입니다.");
		document.forms['frm'].nickNameDuplication.value="nickNameCheck";
		return;
	}
	
	window.open("nickNameCheckOk.do?nickName=" + frm['nickName'].value.trim(), "chkNickName", "width=500, height=300, resizable=no, scrollbars=no");
}

function inputNickNameChk(){
	document.forms['frm'].nickNameDuplication.value="nickNameUncheck";
}

</script>

<body>
<p style="text-align:center;">
<a  style="font-size: xx-large; color: black; text-decoration: none;"href="/InfoGames/mainpage/GameRanking.do">InfoGames</a>
</p>
<br>

<center>
<h3><%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>) 계정 정보 수정하기</h3>

<form name="frm" action="updateAccOk.do" method="post" onsubmit="return chkSubmit()">
<table>
<tr>
<th>ID(최대 15자):</th>
<td>
<input class="suip" type="text" name="id" maxlength="15" onkeydown="inputIdChk()" value="<%=session.getAttribute("id") %>" placeholder="영문, 숫자만 가능"/>
<input class="btn2" type="button" onclick="openChkId()" value="중복확인">
<input class="suip" type="hidden" name="idDuplication" value="idUncheck">
</td>
</tr>

<tr>
<th>비밀번호:</th>
<td><input class="suip" type="password" name="pw" value="<%=session.getAttribute("pw")%>" maxlength="25"/></td>
</tr>

<tr>
<th>
비밀번호확인:
</th>
<td>
<input class="suip" type="password" name="chkPw" value="<%=session.getAttribute("pw")%>" maxlength="25"/></td>
</tr>

<tr>
<th>
Email:
</th>
<td>
<input class="suip" type="text" name="email" value="<%=session.getAttribute("email") %>" maxlength="40" placeholder="ex)asd123@naver.com"/>
</td>
</tr>

<tr>
<th>
닉네임(최대 10자):
</th>
<td>
<input class="suip" type="text" name="nickName" maxlength="10" value="<%=session.getAttribute("nickName")%>" onkeydown="inputNickNameChk()"/>
<input class="btn2" type="button" onclick="openChkNickName()" value="중복확인">
<input class="suip" type="hidden" name="nickNameDuplication" value="nickNameUncheck">
<input class="suip" type="hidden" name="accNum" value="<%=session.getAttribute("accNum")%>">
<input class="btn2" type="submit" value="수정하기"/>
</td>
</tr>
</table>
</form>
<br>
<button class="btn3" onclick="location.href='welcome.do'">회원창으로 이동</button>
</center>
</body>
</html>