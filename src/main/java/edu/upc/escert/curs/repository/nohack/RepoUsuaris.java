package edu.upc.escert.curs.repository.nohack;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.upc.escert.curs.repositori.IRepositoriUsuaris;
import edu.upc.escert.curs.repositori.Repositori;

public class RepoUsuaris extends Repositori implements IRepositoriUsuaris {

	protected void crear() {
		executaSQL("CREATE TABLE IF NOT EXISTS USUARIS (USERNAME VARCHAR(100),PASSWORD VARCHAR(100),rol varchar2(10))");
		afegirUsuari("jaume","trustno1","admin");
		afegirUsuari("scott","tiger","usuari");
		afegirUsuari("ton","secret","usuari");
	}

	@Override
	public boolean autenticar(String username, String pas) {
		String password = encripta(pas);
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
				
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("SELECT count(*) FROM USUARIS WHERE USERNAME=? AND PASSWORD=?");
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			System.out.println("Executo consulta SQL:"+ pstmt.toString());
			rs=pstmt.executeQuery();
			rs.next();
			int n=rs.getInt(1);
			System.out.println("Resultat:"+n);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {pstmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}
		return true;
	}

	@Override
	public void afegirUsuari (String username, String password, String rol) {
		String enc = encripta(password);
		System.out.println(enc);
		executaSQL("INSERT INTO USUARIS VALUES ('" + username + "','" + enc + "','" + rol+ "')");
	}

	private String encripta(String password) {
		String hash=null;
	    try {
	      MessageDigest md = MessageDigest.getInstance("SHA-256");
	      md.update(password.getBytes("UTF-8"));
	      byte[] digest = md.digest();
	      hash=String.format("%064x", 
	        new java.math.BigInteger(1, digest));
	    } catch (Exception e) {
	      throw new RuntimeException(e.getMessage());
	    }
	    
	    return hash;
	  }

}
