package edu.upc.escert.curs.repositori;

import edu.upc.escert.curs.repository.nohack.RepoComentaris;
import edu.upc.escert.curs.repository.nohack.RepoUsuaris;

public class RepositoriFactory {
	
	static IRepositoriUsuaris repositoriUsuaris=new RepoUsuaris();
	static IRepositoriComentaris repositoriComentaris=new RepoComentaris();	

	public static IRepositoriUsuaris getRepositoriUsuaris() {
		return repositoriUsuaris;
	}

	public static IRepositoriComentaris getRepositoriComentaris() {
		return repositoriComentaris;
	}
	
}