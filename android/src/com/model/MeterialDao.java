package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MeterialDao {

	private Connection getConnection()  throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds =(DataSource) envCtx.lookup("jdbc/androiddb");
		Connection con = ds.getConnection();
		return con;
	}
	
	public String insert(MeterialDto dto) {
		String sql = "INSERT INTO meterial(recipesname, meterialname, voluem) VALUES(?, ?, ?);";
		String returns="fail";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, dto.getRecipesname());
			pstmt.setString(2, dto.getMeterialname());
			pstmt.setInt(3, dto.getVoluem());
			pstmt.executeUpdate();
			returns = "true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String selectName(String name) {
		ArrayList<MeterialDto> dtos = new ArrayList<MeterialDto>();
		String sql = "SELECT * FROM meterial WHERE recipesname='"+name+"';";
		String returns="fail";
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			while(rs.next()) {
				String recipesname = rs.getString("recipesname");
				String meterialname = rs.getString("meterialname");
				int voluem = rs.getInt("voluem");
				MeterialDto dto = new MeterialDto(recipesname, meterialname, voluem);
				dtos.add(dto);
			}
			returns = "";
			for(MeterialDto dto : dtos) {
				returns += dto.getMeterialname()+" "; 
			}
			returns += ",";
			for(MeterialDto dto : dtos) {
				returns += dto.getVoluem()+"ml ";
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	
}
