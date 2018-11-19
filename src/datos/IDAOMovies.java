package datos;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	 * @throws Exception 
	 */

	public void addMovie(Pelicula pelicula) throws Exception;

	public void deleteMovie(Pelicula pelicula) throws Exception;

	public ResultSet isbnList(int isbn);
	
	public ResultSet isbnList();
	
	public ResultSet listMovies();

	public ResultSet filterMovies(String name) throws ClassNotFoundException, SQLException;

	public ResultSet filterMovies(int year1, int year2) throws ClassNotFoundException, SQLException;

	public ResultSet filterMovies(String name, int top) throws ClassNotFoundException, SQLException;
}
