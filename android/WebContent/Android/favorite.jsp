<%@page import="com.model.FavoriteDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String id = request.getParameter("userid");
	String name = request.getParameter("recipesname");

	FavoriteDao dao = new FavoriteDao();
	
	if(type.equals("selectFavorite")){
		out.print(dao.selecteFavorite(id));
	}
	else if(type.equals("favoriteCheck")){
		out.print(dao.favoriteCheck(id, name));
	}
	else if(type.equals("insertFavorite")){
		out.print(dao.insertFavorite(id, name));
	}
	else if(type.equals("deleteFavorite")){
		out.print(dao.deleteFavorite(id, name));
	}

%>