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
		String sql = "SELECT recipesname, proof FROM recipes where base='"+base+"';";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			while(rs.next()) {
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
	public String selectRecipe(String keyWord) {
		ArrayList<RecipesDto> dtos = new ArrayList<RecipesDto>();
		String sql = "SELECT * FROM recipes WHERE recipesname LIKE '%"+keyWord+"%';;";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			while(rs.next()) {
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
	public String selectName(String name) {
		String sql = "SELECT * FROM recipes WHERE recipesname='"+name+"';";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			if(rs.next()) {
				returns = rs.getString("proof");
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
		
	}
	public String insert(RecipesDto dto) {
		String sql = "INSERT INTO recipes (recipesName, proof, base) VALUES(?,?,?);";
		String returns="fail";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getProof()+"");
			pstmt.setString(3, dto.getBase());
			pstmt.executeUpdate();
			returns = "true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String deleteRecipe(String name) {
		String sql = "DELETE FROM formalities WHERE recipesName='"+name+"';";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
		){
			stmt.executeUpdate(sql);
			sql = "DELETE FROM meterial WHERE recipesName='"+name+"';";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM recipecontent WHERE recipesName='"+name+"';";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM favorite WHERE recipesName='"+name+"';";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM recipes WHERE recipesName='"+name+"';";
			stmt.executeUpdate(sql);
			
			returns = "true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}

}
