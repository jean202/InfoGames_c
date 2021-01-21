<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<String> originalList = (ArrayList<String>)request.getAttribute("originalList");
ArrayList<String> systemList = (ArrayList<String>)request.getAttribute("systemList");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>

<script>
function chk(){
	window.opener.name = "parentPage";
	document.frm.target = "parentPage";
	document.frm.action = "write.do";
	document.frm.submit();
	self.close();
}
</script>

<body>
<form name="frm" method="post">
<%for(int i = 0; i < originalList.size(); i++){%>
	file<%=i + 1%>: <%=originalList.get(i) %><br><hr>
	<input type="hidden" value="<%=originalList.get(i) %>" name="originalFileName<%=i+1%>">
	<input type="hidden" value="<%=systemList.get(i) %>" name="systemFileName<%=i+1%>">
<%}%>
</form>
<input type="button" onclick="chk()" value="확인">
</body>
</html>