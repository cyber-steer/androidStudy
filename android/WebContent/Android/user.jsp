<%@page import="com.model.UserDao"%>
<%@page import="com.model.RecipesDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String nickName = request.getParameter("nickName");
	UserDao dao = new UserDao();
	
	if(type.equals("userCheck")){
		out.print(dao.userCheck(id, pwd));
	}
	else if(type.equals("insertUser")){
		out.print(dao.insertUser(id, nickName, pwd));
		
	}
%>