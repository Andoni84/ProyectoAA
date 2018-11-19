package servicios;

import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Pelicula;

public interface IServiciosPeliculas {

	/**
	 * Interface IServiciosPeliculas
	 * 
	 * Gestiona los servicios de peliculas, añade, elimina, lista.
	 * 
	 * 15/11/2018
	 *
	 * @author Andoni
	 * @version v1
	 * @throws Exception 
	 */

	// El ultimo tiene que generar Javadoc, formatear, calidad

	public void addMovie() throws Exception;

	public void addMovie(Pelicula pelicula) throws IllegalArgumentException, Exception;
	
	public void deleteMovie() throws Exception;

	public void deleteMovie(Pelicula pelicula) throws IllegalArgumentException, Exception;

	public void listMovies() throws Exception;

	public void insertListMovies() throws Exception;

	public void filterMovies() throws Exception;

	public void printList(ResultSet rs) throws Exception;
	
	public ArrayList movieValueGui();

}
