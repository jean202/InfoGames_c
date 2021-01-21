<%@ page language="java" contentType="application/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- TODO --%>
<%@ page import="java.io.*, java.net.*"%>
<%
	try{
		// URL로부터 데이터 받기 위한 InputStream준비, request 발생
		URL url = new URL("http://api.gevolution.co.kr/rank/xml/?aCode=BCDE480921&market=g&appType=game&rankType=1&rank=20");
		InputStream is = url.openStream();
		InputStreamReader isReader = new InputStreamReader(is, "utf-8");
		
		// URL로부터 respose 데이터 읽어 들어와 StringBuffer 에 저장
		StringBuffer sb = new StringBuffer();
		int readByte;
		while((readByte = isReader.read()) != -1){
			sb.append((char)readByte);
		}
		isReader.close();
		is.close();
		
		// response
		out.clearBuffer();
		out.print(sb.toString());
		out.flush();
		
	} catch (Exception e){
		e.printStackTrace();
	} 
%>