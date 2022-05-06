<%@page import="com.db.ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String telno = request.getParameter("telno");
	String email = request.getParameter("email");
	String type = request.getParameter("type");
	
	ConnectDB connectDB = ConnectDB.getInstans();
	if(type.equals("insert")) {
		out.print(connectDB.insert(name,telno,email));	
	}
	else if(type.equals("select")){
		out.print(connectDB.select(name));
	}

%>