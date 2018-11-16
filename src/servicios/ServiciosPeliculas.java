package servicios;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	DAOMovies daoMovies = new DAOMovies();

	@Override
	public void addMovie() throws IllegalArgumentException {

		boolean seguir = false;
		do {
			try {
				Escritor.write("-----NUEVA PELICULA-----");
				String name = Lector.readString("Nombre: ").toUpperCase();
				int year = Lector.readInt("Año: ");
				Escritor.write("Elige Categoria");
				Escritor.write("\t1-policiaca");
				Escritor.write("\t2-romantica");
				Escritor.write("\t3-aventuras");
				Escritor.write("\t4-comedia");
				Escritor.write("\t5-animacion");
				Escritor.write("\t6-thriller");
				int indCat = Lector.readInt();
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
				case 4:
					Genre = "comedia".toUpperCase();
				case 5:
					Genre = "animacion".toUpperCase();
				case 6:
					Genre = "thriller".toUpperCase();
				}

				Pelicula pelicula = Factoria.factoriaPelicula(name, year, Genre,GenRatingViews.generadorViews(),GenRatingViews.generadorViews());

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
		printList(rs);

	}
	
	public void insertListMovies(){
	try( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Peliculas.txt")))) {
	    String line = null;
	    Pelicula pelicula;
	    while ((line = br.readLine()) != null) {
	        String[] values = line.split(","); // Split 
	        pelicula=Factoria.factoriaPelicula(values[0].toUpperCase(), Integer.parseInt(values[1]),values[2].toUpperCase(), Integer.parseInt(values[3]), Double.parseDouble(values[4]));
	        addMovie(pelicula);
	    }
	   
	} catch (IOException ioe) {
	    // Excepción
	}
	}
	
	public void filterMovies() throws Exception{
		ResultSet rs;
		int year1=0,year2=0;
		
		Escritor.write("-----FILTRADO DE PELICULAs-----");
		Escritor.write("Elige opcion:");
		Escritor.write("\t1-Filtar por año");
		Escritor.write("\t2-Filtrar por nombre");
		Escritor.write("\t3-TOP 5 peliculas mas vistas");
		Escritor.write("\t4-TOP 5 peliculas mas valoradas");
		
		switch(Lector.readInt()){
		case 1:
			boolean rango;
			do{
			rango=true;
			Escritor.write("\tIntroduce rango de años");
			year1=Lector.readInt("\tDesde:");
			year2=Lector.readInt("\tHasta:");
			if(year1<0 || year2<year1){
				rango=false;
				Escritor.write("Rango no valido");
			}
			}while(rango==false);
			rs=daoMovies.filterMovies(year1,year2);
			printList(rs);	
			break;
		case 2:
			String name1=Lector.readString("\tIntroduce el nombre o una parte de el:");
			rs=daoMovies.filterMovies(name1);
			printList(rs);
			break;	
		case 3:
			rs=daoMovies.filterMovies("Vistas",5);
			printList(rs);
			break;	
		case 4:
			rs=daoMovies.filterMovies("Rating",5);
			printList(rs);
			break;	
			
		}
	
		
	}
	
	public void printList(ResultSet rs) throws Exception{
			if (!rs.next())
				throw new IllegalArgumentException("No existen objetos a mostar");

			do {
				Pelicula pelicula = Factoria.factoriaPelicula(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(5),
						rs.getDouble(6),rs.getInt(4));
				Escritor.write(pelicula.toString());

			} while (rs.next());
		
	}
}
