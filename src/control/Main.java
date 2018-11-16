package control;

import Datos.DAOMovies;
import Servicios.ServiciosPeliculas;
import modelo.Pelicula;

public class Main {

	public static void main(String[] args) {

		Pelicula p=new Pelicula("La_purga",2010,1,1000,4.5);
		
		new ServiciosPeliculas().addMovie(p);

	}

}
