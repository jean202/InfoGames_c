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
	int writeId = Integer.parseInt(request.getParameter("writeId"));
%>
<% if(cnt == 0){ %>
	<script>
		alert("수정 실패!");
		history.back();    // 브라우저가 직전에 기억하는 페이지로
	</script>
<% } else { %>
	<script>
		location.href = "view.do?writeId=<%=writeId%>";
	</script>
<% } %>
