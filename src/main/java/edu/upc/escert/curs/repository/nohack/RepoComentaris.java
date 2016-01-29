package edu.upc.escert.curs.repository.nohack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.upc.escert.curs.Comentari;
import edu.upc.escert.curs.repositori.IRepositoriComentaris;
import edu.upc.escert.curs.repositori.Repositori;

public class RepoComentaris extends Repositori implements IRepositoriComentaris {

	protected void crear() {
		executaSQL("create table IF NOT EXISTS comentaris ("+
				"id int auto_increment, "+
				"autor varchar(100), "+
				"titol varchar(200), "+
				"comentari varchar2(2000), "+
				"data date default CURRENT_TIMESTAMP )");
		if (getComentaris().isEmpty()) {
			afegirComentari(new Comentari(
					"jaume","Benvinguts, comenteu!",
					"Aquesta aplicació està feta <b>expressament</b> amb vulnerabilitats."+
					"La idea es que al final del curs tinguem una versió <i>millorada</i>."
			));
		}
	}

	private List<Comentari> getComentarisFromSQL(String sql) {
		List<Comentari> comentaris=new ArrayList<Comentari>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				Comentari c=new Comentari();
				c.setId(rs.getInt("id"));
				c.setAutor(rs.getString("autor"));
				c.setTitol(rs.getString("titol"));
				c.setComentari(rs.getString("comentari"));
				c.setData(rs.getDate("data"));
				comentaris.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {stmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}
		return comentaris;
	}

	@Override
	public List<Comentari> getComentaris() {
		return getComentarisFromSQL("SELECT * FROM COMENTARIS");
	}

	@Override
	public List<Comentari> getComentarisPerAutor (String autor) {
		List<Comentari> comentaris=new ArrayList<Comentari>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from comentaris where autor=?");
			pstmt.setString(1,autor);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				Comentari c=new Comentari();
				c.setId(rs.getInt("id"));
				c.setAutor(rs.getString("autor"));
				c.setTitol(rs.getString("titol"));
				c.setComentari(rs.getString("comentari"));
				c.setData(rs.getDate("data"));
				comentaris.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {pstmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}

		return comentaris;
	}

	@Override
	public Comentari getComentariPerId (int id) {
		return getComentarisFromSQL("SELECT * FROM COMENTARIS WHERE ID="+id).get(0);
	}

	@Override
	public void afegirComentari(Comentari c) {
		executaSQL("INSERT INTO COMENTARIS (autor,titol,comentari,data) VALUES ('" +
				c.getAutor() + "','" + c.getTitol() + "','" + c.getComentari() + "',CURRENT_TIMESTAMP())");
	}

	@Override
	public void esborrarComentari(int id) {
		executaSQL("DELETE COMENTARIS WHERE ID="+id);
	}

}
