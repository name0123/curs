package edu.upc.escert.curs.repositori.parametric;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.upc.escert.curs.repositori.*;

public class RepositoriUsuaris extends Repositori implements IRepositoriUsuaris {

	protected void crear() {
		executaSQL("CREATE TABLE IF NOT EXISTS USUARIS (USERNAME VARCHAR(100),PASSWORD VARCHAR(100),rol varchar2(10))");
		afegirUsuari("jaume","trustno1","admin");
		afegirUsuari("scott","tiger","usuari");
		afegirUsuari("ton","secret","usuari");
	}

	@Override
	public boolean autenticar (String username, String password) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("SELECT count(*) FROM USUARIS WHERE USERNAME=? AND PASSWORD=?");
			pstmt.setString(1,username);
			pstmt.setString(2,generarHash(password));
			rs=pstmt.executeQuery();
			rs.next();
			int n=rs.getInt(1);
			System.out.println("Resultat:"+n);
			return (n>0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {pstmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}
	}

	@Override
	public void afegirUsuari (String username, String password, String rol) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO USUARIS VALUES (?,?,?)");
			pstmt.setString(1,username);
			pstmt.setString(2,generarHash(password));
			pstmt.setString(3,rol);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {pstmt.close();} catch (Exception e2) {;}
		}
	}

	private String generarHash(String password) {
		String hash=null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			hash=String.format("%064x", new java.math.BigInteger(1, digest));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return hash;
	}

}
