package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FavoriteDao {

	private Connection getConnection()  throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds =(DataSource) envCtx.lookup("jdbc/androiddb");
		Connection con = ds.getConnection();
		return con;
	}
	
	public String selecteFavorite(String id) {
		ArrayList<RecipesDto> dtos = new ArrayList<RecipesDto>();
		String sql = "SELECT recipesname, proof "
				+ "FROM recipes "
				+ "WHERE recipesname IN ("
				+ "SELECT recipesname FROM favorite WHERE userid='"+id+"');";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			while(rs.next()) {
				System.out.println("true");
				RecipesDto dto = new RecipesDto();
				dto.setName(rs.getString("recipesname"));
				dto.setProof(rs.getDouble("proof"));
				dtos.add(dto);
			}

			if(dtos.size() >0) {
				returns = "";
				for(RecipesDto dto :dtos) {
					returns += dto.getName() +",";
					returns += dto.getProof() +",";
				}
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}

	public String favoriteCheck(String id, String name) {
		String sql = "SELECT recipesname FROM favorite WHERE userid=? AND recipesname=?;";
		String returns="false";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					returns = "true";
				}
				
			}catch (Exception e) {
				returns = "error";
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String insertFavorite(String id, String name) {
		String sql = "INSERT INTO favorite(userid, recipesname) VALUES(?, ?);";
		String returns="false";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			returns="true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String deleteFavorite(String id, String name) {
		String sql ="DELETE FROM favorite WHERE userid=? AND recipesname=?;";
		String returns="false";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			returns="true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
}
