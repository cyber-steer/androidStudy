<%@page import="com.model.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String nickName = request.getParameter("nickName");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String id = request.getParameter("id");

	BoardDao dao = new BoardDao();
	
	if(type.equals("selectBoard")){
		out.print(dao.select());
	}
	else if(type.equals("insertBoard")){
		out.print(dao.insertBoard(nickName, title, content));
	}
	else if(type.equals("selectId")){
		out.print(dao.select(id));
	}
	else if(type.equals("updateBoard")){
		out.print(dao.updateBoard(id, title, content));
	}

%>