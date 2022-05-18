
<%@page import="com.model.RecipesDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String table = request.getParameter("table");
	String base = request.getParameter("base");

	System.out.println("type"+type);
	System.out.println("table"+table);
	System.out.println("base"+base);
	RecipesDao dao = new RecipesDao();
	if(type.equals("insert")) {
	}
	else if(type.equals("selectBase")){
		if(table.equals("recipes")){
			out.print(dao.selectBase(base));
		}
	}
	else if(type.equals("select")){
	}

%>