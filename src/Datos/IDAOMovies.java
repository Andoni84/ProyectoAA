package Datos;

import java.sql.ResultSet;

import modelo.Pelicula;

public interface IDAOMovies {
	
	public void addMovie(Pelicula pelicula);
	public void deleteMovie(Pelicula pelicula);
	public ResultSet isbnList(int isbn);
	

}
