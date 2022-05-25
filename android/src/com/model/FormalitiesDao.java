package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FormalitiesDao {
	
	private Connection getConnection()  throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds =(DataSource) envCtx.lookup("jdbc/androiddb");
		Connection con = ds.getConnection();
		return con;
	}
	
	public String insert(FormalitiesDto dto) {
		String sql = "INSERT INTO formalities(recipesname, num, content) VALUES(?, ?, ?);";
		String returns="fail";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, dto.getRecipesname());
			pstmt.setInt(2, dto.getNum());
			pstmt.setString(3, dto.getContent());
			pstmt.executeUpdate();
			returns = "true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}

	public String selectName(String name) {
		String sql = "SELECT * FROM formalities WHERE recipesname='"+name+"' ORDER BY num;";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
		){
			returns ="";
			int i =1;
			while(rs.next()) {
				returns += i+"."+rs.getString("content")+"-";
				i++;
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}

}
