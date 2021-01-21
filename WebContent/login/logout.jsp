<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%if(session.getAttribute("id") == null){%>
	<script>
	alert("다시 로그인 해주세요.");
	location.href="/InfoGames/mainpage/GameRanking.do";
	</script>
<%return;}%>
<%
session.invalidate();
%>
<script>
location.href="/InfoGames/mainpage/GameRanking.do";
</script>
