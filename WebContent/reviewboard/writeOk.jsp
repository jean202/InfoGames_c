<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% if(session.getAttribute("accNum") == null){ %>
	<script>
	alert("로그인 해주세요.");
	location.href="/InfoGames/login/login.do";
	</script>
<% } %>

<%
	int cnt = (Integer)request.getAttribute("result");
%>

<% if(cnt == 0){ %>
	<script>
		alert("등록실패 !!!!");
		history.back();
	</script>
<% } else { %>
	<script>
		location.href = "list.do";
	</script>
<% } %>
