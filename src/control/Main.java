package control;

import Datos.DAOMovies;
import Servicios.ServiciosPeliculas;
import modelo.Pelicula;

public class Main {

	public static void main(String[] args) {

		Pelicula p=new Pelicula("SAW",2011,4,1999,1);
		
		new ServiciosPeliculas().deleteMovie(p);

	}

}
