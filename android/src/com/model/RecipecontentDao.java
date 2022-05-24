package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RecipecontentDao {

	private Connection getConnection()  throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds =(DataSource) envCtx.lookup("jdbc/androiddb");
		Connection con = ds.getConnection();
		return con;
	}
	
	public String insert(String msg) {

		String sql = "INSERT INTO recipecontent(recipesname, content) VALUES(?, ?);";
		String returns="fail";

		String msgs[] = msg.split(",");
		String recipename =msgs[0];
		String recipeProof = msgs[1];
		String recipeContent = msgs[2];
		String base = msgs[6];
		String meterial = base+"-" + msgs[3];
		String[] meterialStr = meterial.split("-");
		String[] voluemStr = msgs[4].split("-");
		String[] formalitiesStr = msgs[5].split("-");
		int[] voluem = new int[meterialStr.length];

		MeterialDao mDao;
		FormalitiesDao fDao;
		
		
		for(int i=0;i<meterialStr.length;i++) {
			voluem[i] = Integer.parseInt(voluemStr[i]);
		}
		double proof = Double.parseDouble(recipeProof);
		RecipesDto dto = new RecipesDto(recipename,proof, base);
		RecipesDao dao = new RecipesDao();
		dao.insert(dto);
		mDao = new MeterialDao();
		for(int i=0;i<meterialStr.length;i++) {
			MeterialDto mDto = new MeterialDto(recipename, meterialStr[i], voluem[i]);
			mDao.insert(mDto);
		}
		fDao = new FormalitiesDao();
		for(int i=0;i<formalitiesStr.length;i++) {
			FormalitiesDto fdto = new FormalitiesDto(recipename, formalitiesStr[i], i+1);
			fDao.insert(fdto);
		}
			
		
		try (
			
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){
			pstmt.setString(1, recipename);
			pstmt.setString(2, recipeContent);
			pstmt.executeUpdate();
			returns = "true";
			
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		return returns;
	}

	public String selectRecipecontent(String name) {

		String sql = "SELECT * FROM recipecontent WHERE recipesname='"+name+"';";
		String returns="fail";
		
		String content="";
		String proof;
		String meterial;
		String voluem;
		String formalities;
		
		RecipesDao rDao = new RecipesDao();
		FormalitiesDao fDao = new FormalitiesDao();
		MeterialDao mDao = new MeterialDao();
		
		try (
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			if(rs.next()) {
				content = rs.getString("content");
			}

			proof = rDao.selectName(name);
			meterial = mDao.selectName(name);
			formalities = fDao.selectName(name);
			returns = name+","+content+","+proof+","+meterial+","+formalities;
		} catch (Exception e) {
			returns = "error";
			e.printStackTrace();
		}
		
		
		return returns;
	}
	
}
