<%@page import="com.model.RecipesDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	RecipesDao dao = new RecipesDao();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%=dao.selectBase("보드카") %>
</body>
</html>