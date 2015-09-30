package edu.upc.escert.curs.repositori.parametric;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import edu.upc.escert.curs.Comentari;
import edu.upc.escert.curs.repositori.*;

public class RepositoriComentaris extends Repositori implements IRepositoriComentaris {

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
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				Comentari c = crearComentari(rs);
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

	private Comentari crearComentari(ResultSet rs) throws SQLException {
		Comentari c=new Comentari();
		c.setId(rs.getInt("id"));
		c.setAutor(rs.getString("autor"));
		c.setTitol(rs.getString("titol"));
		c.setComentari(rs.getString("comentari"));
		c.setData(rs.getDate("data"));
		return c;
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
			pstmt = conn.prepareStatement("SELECT * FROM COMENTARIS WHERE AUTOR=?");
			pstmt.setString(1,autor);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				Comentari c = crearComentari(rs);
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
		Comentari c=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM COMENTARIS WHERE ID=?");
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				c = crearComentari(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {pstmt.close();} catch (Exception e2) {;}
			try {rs.close();} catch (Exception e3) {;}
		}
		return c;
	}

	@Override
	public void afegirComentari(Comentari c) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO COMENTARIS (autor,titol,comentari,data) VALUES (?,?,?,CURRENT_TIMESTAMP())");
			pstmt.setString(1,c.getAutor());
			pstmt.setString(2,c.getTitol());
			pstmt.setString(3,c.getComentari());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {pstmt.close();} catch (Exception e2) {;}
		}
	}

	@Override
	public void esborrarComentari(int id) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("DELETE COMENTARIS WHERE ID=?");
			pstmt.setInt(1,id);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {conn.close();} catch (Exception e1) {;}
			try {pstmt.close();} catch (Exception e2) {;}
		}
	}

}
