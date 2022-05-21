<%@page import="com.model.UserDao"%>
<%@page import="com.model.RecipesDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String nickName = request.getParameter("nickname");

	UserDao dao = new UserDao();
	
	if(type.equals("userCheck")){
		System.out.println("dao.userCHeck : "+dao.userCheck(id, pwd));
		out.print(dao.userCheck(id, pwd));
	}
%>