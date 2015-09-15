package edu.upc.escert.curs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RepositoriComentaris extends Repositori {

	static RepositoriComentaris instance;

	public static RepositoriComentaris getInstance() {
		if (instance == null) {
			instance = new RepositoriComentaris();
		}
		return instance;
	}

	protected void crear() {
		executaSQL("create table IF NOT EXISTS comentaris ("+
				"id int auto_increment, "+
				"autor varchar(100), "+
				"comentari varchar2(2000), "+
				"data date default CURRENT_TIMESTAMP )");
		afegirComentari(new Comentari("jaume","Benvinguts, comenteu!"));
	}

	public List<Comentari> getComentarisFromSQL(String sql) {
		List<Comentari> comentaris=new ArrayList<Comentari>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				Comentari c=new Comentari();
				c.setId(rs.getInt("id"));
				c.setAutor(rs.getString("autor"));
				c.setComentari(rs.getString("comentari"));
				c.setData(rs.getDate("data"));
				comentaris.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}
		return comentaris;
	}

	public List<Comentari> getComentaris() {
		return getComentarisFromSQL("SELECT * FROM COMENTARIS");
	}

	public List<Comentari> getComentarisPerAutor (String autor) {
		return getComentarisFromSQL("SELECT * FROM COMENTARIS WHERE AUTOR='"+autor+"'");
	}

	public void afegirComentari(Comentari c) {
		executaSQL("INSERT INTO COMENTARIS (autor,comentari,data) VALUES ('" + c.getAutor() + "','" + c.getComentari() + "',CURRENT_TIMESTAMP())");
	}
	
	public void esborrarComentari(int id) {
		executaSQL("DELETE COMENTARIS WHERE ID="+id);
	}	

}
