package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {

	private Connection getConnection()  throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds =(DataSource) envCtx.lookup("jdbc/androiddb");
		Connection con = ds.getConnection();
		return con;
	}
	
	public String select() {
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM board order by bdate desc;";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setId(rs.getInt("id"));
				dto.setTitle(rs.getString("title"));
				dto.setNickName(rs.getString("nickname"));
				String date = rs.getTimestamp("bdate").toString().replace(" ", "/");
				dto.setDate(date);
				dtos.add(dto);
			}
			if(dtos.size() >0) {
				returns = "";
				for(BoardDto dto :dtos) {
					returns += dto.getTitle() +" ";
				}
				returns += ",";
				for(BoardDto dto :dtos) {
					returns += dto.getNickName() +" ";
				}
				returns += ",";
				for(BoardDto dto :dtos) {
					returns += dto.getDate() +" ";
				}
				returns += ",";
				for(BoardDto dto :dtos) {
					returns += dto.getId() +" ";
				}
				
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String select(String id) {
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM board where id='"+id+"';";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			if(rs.next()) {
				String title = rs.getString("title");
				String nickName = rs.getString("nickname");
				String date = rs.getTimestamp("bdate").toString().replace(" ", "/");
				String content = rs.getString("content");
				returns = "";
				returns = title+","+nickName+","+date+","+content;
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String insertBoard(String nickName,String title,String content) {
		String sql = "INSERT INTO board(title, content, nickname) VALUES(?, ?, ?);";
		String returns="fail";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, nickName);
			pstmt.executeUpdate();
			returns="true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String updateBoard(String id,String title,String content) {
		String sql = "UPDATE board SET title=?, content=? WHERE id=?;";
		String returns="fail";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			returns="true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
}
