package Datos;

import java.sql.ResultSet;

import modelo.Pelicula;

public interface IDAOMovies {
	
	/**
	 * Interface IDAOMovies
	 * 
	 * Gestiona la BBDD de peliculas, añade, elimina, lista.
	 * 
	 * 15/11/2018
	 *
	 * @author Andoni
	 * @version v1
	 */
	
	public void addMovie(Pelicula pelicula);
	public void deleteMovie(Pelicula pelicula);
	public ResultSet isbnList(int isbn);
	

}
