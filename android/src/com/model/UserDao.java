package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDao {
	private Connection getConnection()  throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds =(DataSource) envCtx.lookup("jdbc/androiddb");
		Connection con = ds.getConnection();
		return con;
	}
	
	public String userCheck(String id, String pwd) {
		String sql = "SELECT nickname from user where id=? and pwd=?;";
		String returns="!false!";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, id);
			pstmt.setString(2,pwd);
			System.out.println(id);
			try (ResultSet rs = pstmt.executeQuery();){
				System.out.println(pwd);
				if(rs.next()) {
					System.out.println(pwd);
					returns = rs.getString("nickname");
					System.out.println(returns);					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		System.out.println("returns:"+returns);
		return returns;
	}

}
