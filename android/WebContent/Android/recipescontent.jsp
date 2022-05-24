<%@page import="com.model.RecipecontentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String msg = request.getParameter("msg");
	String name = request.getParameter("name");

	RecipecontentDao dao = new RecipecontentDao();
	
	if(type.equals("insertRecipes")){
		out.print(dao.insert(msg));
	}
	else if(type.equals("selectRecipecontent")){
		out.print(dao.selectRecipecontent(name));
	}

%>