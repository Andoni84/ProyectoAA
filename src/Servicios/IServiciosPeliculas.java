package Servicios;

import modelo.Pelicula;

public interface IServiciosPeliculas {

	// El ultimo tiene que generar Javadoc, formatear, calidad

	public void addMovie();
	
	public void addMovie(Pelicula pelicula);
	
	public void deleteMovie(Pelicula pelicula);
	
}
