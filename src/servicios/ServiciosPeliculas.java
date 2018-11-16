package Servicios;

import java.sql.ResultSet;
import java.sql.SQLException;

import Datos.DAOMovies;
import modelo.Pelicula;
import utilidades.Escritor;
import utilidades.Factoria;
import utilidades.Lector;

public class ServiciosPeliculas implements IServiciosPeliculas {

	/**
	 * ClaseServicioPeliculas
	 * 
	 * Contiene metodos de añadir, eliminar, listar, filtrar peliculas Y añadir
	 * y eliminar categorias
	 * 
	 * 15/11/2018
	 *
	 * @author Andoni
	 * @version v1
	 */

	DAOMovies daoMovies = new DAOMovies();

	@Override
	public void addMovie() throws IllegalArgumentException {

		boolean seguir = false;
		do {
			try {
				Escritor.write("-----NUEVA PELICULA-----");
				String name = Lector.readString("Nombre: ");
				int year = Lector.readInt("Año: ");
				Escritor.write("Elige Categoria");
				Escritor.write("\t1-policiaca");
				Escritor.write("\t2-romantica");
				Escritor.write("\t3-aventuras");
				Escritor.write("\t4-comedia");
				Escritor.write("\t5-animacion");
				Escritor.write("\t6-thriller");
				int indCat = Lector.readInt();
				String idGenre = "";
				
				switch (indCat) {
				case 1:
					idGenre = "policiaca";
					break;
				case 2:
					idGenre = "romantica";
					break;
				case 3:
					idGenre = "aventuras";
				case 4:
					idGenre = "comedia";
				case 5:
					idGenre = "animacion";
				case 6:
					idGenre = "thriller";
				}

				Pelicula pelicula = Factoria.factoriaPelicula(name, year, idGenre, 0, 0);

				addMovie(pelicula);

				seguir = true;

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (seguir == false);

	}

	public void addMovie(Pelicula pelicula) throws IllegalArgumentException {
		// TODO Auto-generated method stub

		try {

			ResultSet isbnRs = daoMovies.isbnList(pelicula.getIsbn());

			if (isbnRs.next()) {
				throw new IllegalArgumentException("Objeto repetido");
			}
		} catch (IllegalArgumentException e) {
			try {
				Escritor.write(e.getMessage());

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		daoMovies.addMovie(pelicula);

	}

	@Override
	public void deleteMovie(Pelicula pelicula) {
		// TODO Auto-generated method stub
		try {

			ResultSet isbnRs = daoMovies.isbnList(pelicula.getIsbn());

			if (!isbnRs.next()) {
				throw new IllegalArgumentException("No existe objeto");
			}
		} catch (IllegalArgumentException e) {
			try {
				Escritor.write(e.getMessage());

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		daoMovies.deleteMovie(pelicula);
	}

	public void listMovies() throws Exception {
		ResultSet rs = daoMovies.listMovies();
		if (!rs.next())
			throw new IllegalArgumentException("No existen objetos a mostar");

		do {
			Pelicula pelicula = Factoria.factoriaPelicula(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
					rs.getInt(5));
			Escritor.write(pelicula.toString());

		} while (rs.next());

	}

}
