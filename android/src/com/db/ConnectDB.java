package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectDB {
	private static ConnectDB instans = new ConnectDB();
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql ="";
	private String returns = "";
	private String url = "jdbc:mariadb://localhost:3333/androiddb";
	private String user = "android";
	private String pwd= "1111";
	
	public static ConnectDB getInstans() {
		return instans;
	}
	private ConnectDB() {}
	public String insert(String name, String tleno, String email) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pwd);
			sql = "select * from customer where name=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				returns = "false";
			}
			else {
				sql = "INSERT INTO customer VALUES(1,?, ?, ?);";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,tleno);
				pstmt.setString(3,email);
				pstmt.executeUpdate();
				returns = "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return returns;
	}
	public String select() {
		return "";
	}
	public String select(String name) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pwd);
			sql = "select * from customer where name=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String tel = rs.getString("telno");
				String email = rs.getString("email");
				returns = tel +" "+email;
			}
			else {
				returns = "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return returns;
	}
}
