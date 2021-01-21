<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous00">

<title>GameRanking</title>

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

img {
    width: 50px;
    height: 50px;
    border: 0px solid #dbdbdb;
    float: center;
    border-radius: 5px;
    image-rendering: -moz-auto;
    image-rendering: -o-auto;
    image-rendering: -webkit-optimize-contrast;
    image-rendering: auto;
    -ms-interpolation-mode: nearest-neighbor;
    image-rendering: auto;
	image-rendering: crisp-edges;
	image-rendering: pixelated;

	/* 전역 값 */
	image-rendering: inherit;
	image-rendering: initial;
	image-rendering: unset;
}

 a:link { color:#ffffff; text-decoration: none;}
 a:visited { color:#ffffff; text-decoration: none;}
 a:hover { color:#ffffff; text-decoration: underline;}
 a:active{text-decoration:none;}
 
span {
	display: inline-block;
}
.navbar navbar-expand-sm fixed-top navbar-light{
	display:table;
}
.navbar navbar-expand-sm fixed-top navbar-light > #title {
	display:table-cell;
	vertical-align:middle;
}
</style>


</head>
<body onload="loadData()">
<nav class="navbar navbar-expand-sm fixed-top navbar-light" style="display:inline-block; background-color: #6aace6;">
<div>

<span id="title"><a style="font-size: 35px; color: #ffffff; text-decoration: none; " href="GameRanking.do">InfoGames&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span>

<span style="float: right; color: #ffffff;">
<% if(session.getAttribute("id") == null){ %>
<a href='/InfoGames/login/login.do'>로그인 |  </a><a href="/InfoGames/login/signUp.do">회원가입</a>
<%} else{%>
<span style="float:right;">	<%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>)님 </span><br>
	<a href="/InfoGames/login/logout.do">로그아웃 |  </a>
	<a href="/InfoGames/login/welcome.do">계정정보</a>
<%} %>
</span>
<span style="float:left; font-size: 22px; ">
<a href="/InfoGames/freeboard/list.do" >자유게시판 | </a>
<a href="/InfoGames/tipboard/list.do"> 팁게시판 | </a>
<a href="/InfoGames/reviewboard/list.do" > 리뷰게시판</a><br>
</span>

</div>
<%-- 
<p style="text-align: right;">
<% if(session.getAttribute("id") == null){ %>
<a href='/InfoGames/login/login.do'>로그인|  </a><a href="/InfoGames/login/signUp.do">회원가입</a>
<%} else{%>
	<%=session.getAttribute("id")%>(<%=session.getAttribute("nickName")%>)님 
	<a href="/InfoGames/login/logout.do">로그아웃|  </a>
	<a href="/InfoGames/login/welcome.do">계정정보</a>
<%} %>
</p>
<h3>
<div style="text-align:center;">
<a href="/InfoGames/freeboard/list.do" style=" color:#ffffff; font-size: 15px;">자유게시판| </a>
<a href="/InfoGames/tipboard/list.do" style=" color:#ffffff; font-size: 15px;"> 팁게시판| </a>
<a href="/InfoGames/reviewboard/list.do" style=" color:#ffffff; font-size: 15px;"> 리뷰게시판</a><br>
</div>
</h3>
 --%>
</nav>
<br>
<br>
<hr>
<h2 style="text-align:center">
게임 순위 TOP 20
</h2>
<hr>
<table id="demoXML" class="table"></table>
<script>

function loadData(){
	
	//var url = "http://api.gevolution.co.kr/rank/xml/?aCode=HIJK372129&market=g&appType=game&rankType=1&rank=20";
	var url = "ajax.do";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			parseXML(this.responseXML);
		}
	};
	xhttp.open("GET", url, true);
	xhttp.send();
}

function parseXML(xmlDOM){
	var table = "<tr><th>이번주 순위</th><th>저번주 순위</th><th>게임아이콘</th><th>게임 명</th><th>회사이름</th><th>앱 평가 점수 (0~100)</th><th>구글 플레이</th><th>앱스토어</th><th>게임 영상</th></tr>";
	
	var item = xmlDOM.getElementsByTagName("item");
	for(i = 0; i < item.length; i++){
		table += "<tr>";
		table += "<td>" + item[i].getElementsByTagName("ranking")[0].childNodes[0].nodeValue + "</td>";
		table += "<td>" + item[i].getElementsByTagName("lastWeek")[0].childNodes[0].nodeValue + "</td>";
		table += "<td><img src=" + item[i].getElementsByTagName("iconUrl")[0].childNodes[0].nodeValue + "></td>";
		table += "<td>" + item[i].getElementsByTagName("gameName")[0].childNodes[0].nodeValue + "</td>";
		table += "<td>" + item[i].getElementsByTagName("publisher")[0].childNodes[0].nodeValue + "</td>";		
		table += "<td>" + item[i].getElementsByTagName("rating")[0].childNodes[0].nodeValue + "</td>";
		table += "<td><a href=" + item[i].getElementsByTagName("googleUrl")[0].childNodes[0].nodeValue + 
				"><img src='https://img.icons8.com/color/480/000000/google-play.png'/></a></td>";
		table += "<td><a href=" + item[i].getElementsByTagName("appleUrl")[0].childNodes[0].nodeValue + 
				"><img src='https://img.icons8.com/color/480/000000/apple-app-store--v1.png'/></a></td>";
		table += "<td><a href=" + item[i].getElementsByTagName("movieUrl")[0].childNodes[0].nodeValue + 
				"><img src='https://img.icons8.com/color/480/000000/youtube-play.png'/></a></td>";
		table += "</tr>";
	}	
	document.getElementById("demoXML").innerHTML = table;
}
</script>
</body>
</html>