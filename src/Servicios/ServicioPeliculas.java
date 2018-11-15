package Servicios;

import modelo.Pelicula;
import utilidades.Escritor;
import utilidades.Lector;

public class ServicioPeliculas implements IServiciosPeliculas {

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

	@Override
	public void addMovie() {

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

				Pelicula pelicula = Factoria.factoriaMovie(name, year, idGenre);

				int[] isbnPeliculas = DaoMovies.listIsbn();

				for (int val : isbnPeliculas) {
					if (pelicula.getIsbn() == val)
						throw new IllegalArgumentException("Objeto repetido");
				}

				DaoMovies.addMovie(pelicula);
			
				seguir = true;
				
			}catch(IllegalArgumentException e){
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (seguir == false);

	}

	@Override
	public void addMovie(Pelicula pelicula) {
		// TODO Auto-generated method stub

	}

}
