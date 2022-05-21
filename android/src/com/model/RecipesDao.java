package com.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RecipesDao {
	
	private Connection getConnection()  throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds =(DataSource) envCtx.lookup("jdbc/androiddb");
		Connection con = ds.getConnection();
		return con;
	}
	
	public String selectBase(String base) {
		ArrayList<RecipesDto> dtos = new ArrayList<RecipesDto>();
		String sql = "SELECT name, proof, favorite FROM recipes where base='"+base+"';";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			while(rs.next()) {
				RecipesDto dto = new RecipesDto();
				dto.setName(rs.getString("name"));
				dto.setProof(rs.getDouble("proof"));
				dto.setFavorite(rs.getBoolean("favorite"));
				dtos.add(dto);
			}

			if(dtos.size() >0) {
				returns = "";
				for(RecipesDto dto :dtos) {
					returns += dto.getName() +",";
					returns += dto.getProof() +",";
					returns += dto.isFavorite()+",";
				}
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String updateFavorite(String name, String bool) {
		String sql = "update recipes set favorite=? where name=?;";
		String returns="false";
		System.out.println("update");
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			boolean favorite = bool.equals("true") ? true:false;
			System.out.println("favorite : "+favorite);
			System.out.println("name : "+name);
			pstmt.setBoolean(1, favorite);
			pstmt.setString(2, name);
			int result = pstmt.executeUpdate();
			System.out.println("result : "+result);
			if(result>0) {
				returns="true";
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}

	public String selectFavorite() {
		ArrayList<RecipesDto> dtos = new ArrayList<RecipesDto>();
		String sql = "SELECT name, proof, favorite FROM recipes where favorite=true;";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			while(rs.next()) {
				RecipesDto dto = new RecipesDto();
				dto.setName(rs.getString("name"));
				dto.setProof(rs.getDouble("proof"));
				dto.setFavorite(rs.getBoolean("favorite"));
				dtos.add(dto);
			}

			if(dtos.size() >0) {
				returns = "";
				for(RecipesDto dto :dtos) {
					returns += dto.getName() +",";
					returns += dto.getProof() +",";
					returns += dto.isFavorite()+",";
				}
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
}
