
<%@page import="com.model.RecipesDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String base = request.getParameter("base");
	String name = request.getParameter("name");
	String favorite = request.getParameter("favorite");

	RecipesDao dao = new RecipesDao();
	
	if(type.equals("selectBase")){
		out.print(dao.selectBase(base));
	}
	else if(type.equals("updateFavorite")){
		out.print(dao.updateFavorite(name, favorite));
	}

%>