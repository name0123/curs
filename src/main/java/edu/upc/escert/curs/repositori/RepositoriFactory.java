package edu.upc.escert.curs.repositori;

import edu.upc.escert.curs.repositori.parametric.*;

public class RepositoriFactory {

	static IRepositoriUsuaris repositoriUsuaris=new RepositoriUsuaris();
	static IRepositoriComentaris repositoriComentaris=new RepositoriComentaris();

	public static IRepositoriUsuaris getRepositoriUsuaris() {
		return repositoriUsuaris;
	}

	public static IRepositoriComentaris getRepositoriComentaris() {
		return repositoriComentaris;
	}

}