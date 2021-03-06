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
		String sql = "SELECT nickname from user where userid=? and pwd=?;";
		String returns="!false!";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, id);
			pstmt.setString(2,pwd);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					returns = rs.getString("nickname");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String userCheckId(String id) {
		String sql = "SELECT pwd, nickname from user where userid=?;";
		String returns="!false!";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					returns = "";
					returns += rs.getString("pwd")+",";
					returns += rs.getString("nickname");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}
	public String userCheckNickName(String nickName) {
		String sql = "SELECT userid, pwd from user where nickname=?;";
		String returns="!false!";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, nickName);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					returns = "";
					returns += rs.getString("id")+",";
					returns += rs.getString("pwd");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}

	public String insertUser(String id, String nickName, String pwd) {
		if(!userCheckId(id).equals("!false!")){
			return "????????? ??????????????????";
		}
		if(!userCheckNickName(nickName).equals("!false!")) {
			return "????????? ??????????????????";
		}
		String sql = "insert into user(userid, nickname, pwd) values(?, ?, ?);";
		String returns="fail";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, id);
			pstmt.setString(2,nickName);
			pstmt.setString(3, pwd);
			pstmt.executeUpdate();
			returns = "true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}

}
