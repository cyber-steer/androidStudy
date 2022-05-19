
<%@page import="com.model.RecipesDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String table = request.getParameter("table");
	String base = request.getParameter("base");
	String name = request.getParameter("name");
	String favorite = request.getParameter("favorite");

	System.out.println("type : "+type);
	System.out.println("table : "+table);
	System.out.println("name : "+name);
	System.out.println("favorite : "+favorite);
	RecipesDao dao = new RecipesDao();
	if(type.equals("insert")) {
	}
	else if(type.equals("selectBase")){
		if(table.equals("recipes")){
			out.print(dao.selectBase(base));
		}
	}
	else if(type.equals("updateFavorite")){
		if(table.equals("recipes")){
			out.print(dao.updateFavorite(name, favorite));
		}
	}
	else if(type.equals("selectFavorite")){
		if(table.equals("recipes")){
			out.print(dao.selectFavorite());
		}
	}

%>