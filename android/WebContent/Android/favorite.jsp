<%@page import="com.model.FavoriteDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String id = request.getParameter("userid");

	FavoriteDao dao = new FavoriteDao();
	
	if(type.equals("selectFavorite")){
		out.print(dao.selecteFavorite(id));
	}

%>