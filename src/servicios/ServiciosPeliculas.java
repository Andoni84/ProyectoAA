package servicios;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import datos.DAOMovies;
import modelo.Pelicula;
import utilidades.Escritor;
import utilidades.Factoria;
import utilidades.GenRatingViews;
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

	private static Logger logger;

	static {
		try {
			logger = LogManager.getLogger(ServiciosPeliculas.class);
		} catch (Throwable e) {
			System.out.println("Logger Don't work");
		}
	}

	DAOMovies daoMovies = new DAOMovies();

	@Override
	public void addMovie() throws Exception {
		@SuppressWarnings("rawtypes")
		ArrayList miArray = new ArrayList();
		boolean seguir = false;
		do {
			try {

				Escritor.write("-----NUEVA PELICULA-----");
				miArray = movieValueGui();
				logger.trace("Datos pelucila recogidos");
				logger.debug("ServiciosPeliculas.addMovie() miARRay:" + miArray.toString());

				Pelicula pelicula = Factoria.factoriaPelicula(miArray.get(0).toString(), (int) miArray.get(1),
						miArray.get(2).toString(),0,0);
				logger.debug("ServiciosPeliculas.addMovie() pelicula:" + pelicula.toString());

				addMovie(pelicula);

				seguir = true;


			} catch (IllegalArgumentException e) {
				Escritor.write(e.getMessage());
				logger.debug("ServiciosPeliculas.addMovie(): " + e.getMessage());
				Escritor.write("Pulse 1 para volver a introducir los datos");
				if (Lector.readInt("Pulse 0 para salir ") == 0)
					seguir = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (seguir == false);

	}

	public void addMovie(Pelicula pelicula) throws IllegalArgumentException, Exception {
		// TODO Auto-generated method stub

			ResultSet isbnRs = daoMovies.isbnList(pelicula.getIsbn());

			if (isbnRs.next()) {
				logger.debug("ServiciosPeliculas.addMovie(pelicula): Objeto repetido");
				throw new IllegalArgumentException("Pelicula repetida");
			}
			if(pelicula.getViews()==0)pelicula.setViews(GenRatingViews.generadorViews());
			if(pelicula.getRating()==0)pelicula.setRating(GenRatingViews.generadorRating());
			daoMovies.addMovie(pelicula);
		
	}

	public void deleteMovie() throws Exception {
		@SuppressWarnings("rawtypes")
		ArrayList miArray = new ArrayList();
		boolean seguir = false;
		do {
			try {

				Escritor.write("-----ELIMINAR PELICULA-----");
				Escritor.write("Elige opcion:");
				Escritor.write("\t1-Por ISBN");
				Escritor.write("\t2-Por nombre, año y categoria");
				Escritor.write("\t3-Salir");
				int opcion = Lector.readInt();
				logger.debug("ServiciosPeliculas.deleteMovie() Opcion: " + opcion);
				switch (opcion) {
				case 1:
					int isbn = Lector.readInt("Introduce ISBN:");
					logger.debug("ServiciosPeliculas.deleteMovie() ISBN: " + isbn);
					deleteMovie(Factoria.factoriaPelicula("", 0, "", 0, 0, isbn));
					break;
				case 2:
					miArray = movieValueGui();
					String name = miArray.get(0).toString();
					String Genre = miArray.get(2).toString();
					int year = (int) miArray.get(1);
					deleteMovie(Factoria.factoriaPelicula(name, year, Genre, 0, 0));
					break;
				case 3:
				default:
					seguir = true;
					break;
				}

				seguir = true;
				
			} catch (IllegalArgumentException e) {
				Escritor.write(e.getMessage());
				logger.debug("ServiciosPeliculas.deleteMovie(): No existe la pelicula: ISBN inexistente");
			}
		} while (seguir == false);

	}

	@Override
	public void deleteMovie(Pelicula pelicula) throws IllegalArgumentException, Exception {
		// TODO Auto-generated method stub
		
			ResultSet isbnRs = daoMovies.isbnList(pelicula.getIsbn());

			if (!isbnRs.next()) {
				logger.debug("ServiciosPeliculas.deleteMovie(Pelicula): Existe pelicula con ese ISBN");
				throw new IllegalArgumentException("ServiciosPeliculas.deleteMovie(pelicula): ISBN inexistente");
			}
			daoMovies.deleteMovie(pelicula);

	}

	public void listMovies() throws Exception {
		ResultSet rs = daoMovies.listMovies();
		printList(rs);

	}

	public void insertListMovies() throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Peliculas.txt")))) {
			String line = null;
			Pelicula pelicula;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(","); // Split
				pelicula = Factoria.factoriaPelicula(values[0].toUpperCase(), Integer.parseInt(values[1]),
						values[2].toUpperCase(), Integer.parseInt(values[3]), Double.parseDouble(values[4]));
				try{
				addMovie(pelicula);
				}catch (IllegalArgumentException e) {
					Escritor.write(e.getMessage()+" : "+pelicula.toString());		
					logger.debug("ServiciosPeliculas.insertlistMovies(): Pelicula repetida : "+pelicula.toString());}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
			logger.error("ServiciosPeliculas.insertlistMovies(): Problema lectura archivo");
		}
	}

	public void filterMovies() throws Exception {
		ResultSet rs;
		int year1 = 0, year2 = 0;

		Escritor.write("-----FILTRADO DE PELICULAs-----");
		Escritor.write("Elige opcion:");
		Escritor.write("\t1-Filtar por año");
		Escritor.write("\t2-Filtrar por nombre");
		Escritor.write("\t3-TOP 5 peliculas mas vistas");
		Escritor.write("\t4-TOP 5 peliculas mas valoradas");
		int opcion = Lector.readInt();
		logger.debug("ServiciosPeliculas.listMovies() Opcion: " + opcion);
		switch (opcion) {
		case 1:
			boolean rango;
			do {
				rango = true;
				Escritor.write("\tIntroduce rango de años");
				year1 = Lector.readInt("\tDesde:");
				year2 = Lector.readInt("\tHasta:");
				logger.debug("ServiciosPeliculas.listMovies() Filtrado por año: " + year1 + "-" + year2);
				if (year1 < 0 || year2 < year1) {
					rango = false;
					Escritor.write("Rango no valido");
					logger.debug("ServiciosPeliculas.listMovies() Filtrado por año: Rango no valido");
				}
			} while (rango == false);
			rs = daoMovies.filterMovies(year1, year2);
			printList(rs);
			break;
		case 2:
			String name1 = Lector.readString("\tIntroduce el nombre o una parte de el:");
			logger.debug("ServiciosPeliculas.listMovies() Filtrado por nombre: " + name1);
			rs = daoMovies.filterMovies(name1);
			printList(rs);
			break;
		case 3:
			rs = daoMovies.filterMovies("Vistas", 5);
			printList(rs);
			break;
		case 4:
			rs = daoMovies.filterMovies("Rating", 5);
			printList(rs);
			break;

		}

	}

	public void printList(ResultSet rs) throws Exception {
		if (!rs.next()) {
			logger.debug("ServiciosPeliculas.printList(ResultSet): No existen obetos a imprimir");
			throw new IllegalArgumentException("No existen objetos a mostar");
		}

		do {
			Pelicula pelicula = Factoria.factoriaPelicula(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(5),
					rs.getDouble(6), rs.getInt(4));
			Escritor.write(pelicula.toString());

		} while (rs.next());

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList movieValueGui() {
		boolean seguir=false;
		int year=0;
		int indCat=0;
		ArrayList miArray = new ArrayList();
		try {

			String name = Lector.readString("Nombre: ").toUpperCase();
			do{
			try{	
			year = Lector.readInt("Año: ");
			if (year<1895 && year>2018) throw new IllegalArgumentException("Año introducido incorrecto");
			seguir=true;
			}catch(InputMismatchException e){
				Escritor.write("Dato introducido incorrecto, introducca un año");
				logger.debug(e.getMessage());
			}catch(IllegalArgumentException a) {
				Escritor.write(a.getMessage());
				logger.debug(a.getMessage());
			}
			}while(seguir==false);
			
			seguir=false;
			
			do{
				try{
			Escritor.write("Elige Categoria");
			Escritor.write("\t1-policiaca");
			Escritor.write("\t2-romantica");
			Escritor.write("\t3-aventuras");
			Escritor.write("\t4-comedia");
			Escritor.write("\t5-animacion");
			Escritor.write("\t6-thriller");
			indCat = Lector.readInt();
			if (indCat<1 && indCat>6) throw new IllegalArgumentException("Selecccion fuera de rango");
			seguir=true;
			}catch(InputMismatchException e){
				Escritor.write("Dato introducido incorrecto, introducca un opcion");
				logger.debug(e.getMessage());
			}catch(IllegalArgumentException a) {
				Escritor.write(a.getMessage());
				logger.debug(a.getMessage());
			}
			}while(seguir==false);
			
			String Genre = "";

			switch (indCat) {
			case 1:
				Genre = "policiaca".toUpperCase();
				break;
			case 2:
				Genre = "romantica".toUpperCase();
				break;
			case 3:
				Genre = "aventuras".toUpperCase();
				break;
			case 4:
				Genre = "comedia".toUpperCase();
				break;
			case 5:
				Genre = "animacion".toUpperCase();
				break;
			case 6:
				Genre = "thriller".toUpperCase();
				break;
			}
			
			
			miArray.add(name);
			miArray.add(year);
			miArray.add(Genre);

			logger.debug("ServiciosPeliculas.movieValueGui() miArray: " + miArray.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return miArray;

	}

}