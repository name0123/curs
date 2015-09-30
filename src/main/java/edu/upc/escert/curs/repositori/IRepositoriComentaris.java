package edu.upc.escert.curs.repositori;

import java.util.List;

import edu.upc.escert.curs.Comentari;

public interface IRepositoriComentaris {
	public abstract List<Comentari> getComentaris();
	public abstract Comentari getComentariPerId(int id);
	public abstract List<Comentari> getComentarisPerAutor(String autor);
	public abstract void afegirComentari(Comentari c);
	public abstract void esborrarComentari(int id);
}