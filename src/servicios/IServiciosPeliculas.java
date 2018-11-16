package servicios;

import java.sql.ResultSet;

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
	 */

	// El ultimo tiene que generar Javadoc, formatear, calidad

	public void addMovie();

	public void addMovie(Pelicula pelicula);

	public void deleteMovie(Pelicula pelicula);

	public void listMovies() throws Exception;

	public void insertListMovies();

	public void filterMovies() throws Exception;

	public void printList(ResultSet rs) throws Exception;

}
