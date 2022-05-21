package com.model;

import java.sql.Connection;
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
}
