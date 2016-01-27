package edu.upc.escert.curs.repository.nohack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.upc.escert.curs.repositori.*;

public class RepoUsuaris extends Repositori implements IRepositoriUsuaris {

	protected void crear() {
		executaSQL("CREATE TABLE IF NOT EXISTS USUARIS (USERNAME VARCHAR(100),PASSWORD VARCHAR(100),rol varchar2(10))");
		afegirUsuari("jaume","trustno1","admin");
		afegirUsuari("scott","tiger","usuari");
		afegirUsuari("ton","secret","usuari");
	}

	@Override
	public boolean autenticar (String username, String password) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			String sql="SELECT count(*) FROM USUARIS WHERE USERNAME=? AND PASSWORD=?";
			System.out.println("Executo consulta SQL:"+sql);
			rs=stmt.executeQuery(sql);
			rs.next();
			int n=rs.getInt(1);
			System.out.println("Resultat:"+n);
			return (n>0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}
	}

	@Override
	public void afegirUsuari (String username, String password, String rol) {
		executaSQL("INSERT INTO USUARIS VALUES ('" + username + "','" + password + "','" + rol+ "')");
	}


}
