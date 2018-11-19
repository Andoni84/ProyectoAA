package control;

import servicios.ServiciosPeliculas;
import servicios.ServiciosUsuarios;
import utilidades.Lector;

public class GUI {

	public void iniciar() throws Exception {

		System.out.println(

				"USUARIOS\n\n" +

						"1- Añadir Usuario \n" +

						"2- Borrar Usuario \n" +

						"3- Listar Usuarios \n" +

						"4- Peliculas disponibles de usuario \n\n" +

						"PELICULAS\n" +

						"5- Añadir pelicula  \n" +

						"6- Borrar pelicula  \n" +

						"7- Listado todas peliculas \n" +

						"8- Listado filtrado \n\n" +

						"OTROS\n\n" +

						"9- Cargar Peliculas desde Fichero a la BBDD\n");

		ServiciosUsuarios su = new ServiciosUsuarios();
		ServiciosPeliculas sp = new ServiciosPeliculas();

		int n = Lector.readInt("Selecciona una opcion.");

		switch (n) {
		case 1:
			su.addUser();
			break;
		case 2:
			su.deleteUser();
			break;
		case 3:
			su.listUsers();
			break;
		case 4:
			su.availableMovies();
			break;
		case 5:
			sp.addMovie();
			break;
		case 6:
			sp.deleteMovie();
			break;
		case 7:
			sp.listMovies();
			break;
		case 8:
			sp.filterMovies();
			break;
		case 9:
			sp.insertListMovies();
			break;
		default:
			System.out.println("Opcion no valida.");
			iniciar();
			break;
		}
	}
}
