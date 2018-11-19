package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import modelo.Pelicula;
import utilidades.Conexion;
import utilidades.Escritor;

public class DAOMovies implements IDAOMovies {

	/**
	 * ClaseDAOMovies
	 * 
	 * Gestiona la BBDD de peliculas, añade, elimina, lista.
	 * 
	 * 15/11/2018
	 *
	 * @author Andoni
	 * @version v1
	 */

	private static Logger logger;
    Conexion con = new Conexion();
	static {
		try {
			logger = LogManager.getLogger(DAOMovies.class);
		} catch (Throwable e) {
			System.out.println("Logger Don't work");
		}
	}
	
	/**
	 * Introduce una nueva peilcula en la base de  datos.
	 * 
	 * @param pelicula: objeto pelicula
	 * @see Conexion.updateQuery(query)
	 * @throws Exception
	 */

	public void addMovie(Pelicula peli) throws Exception {

		String query = "INSERT INTO `movies`(Nombre,Year,Categoria,Isbn,Vistas,Rating)" + " VALUE ('" + peli.getName()
				+ "'," + peli.getYear() + ",'" + peli.getGenre() + "'," + peli.getIsbn() + "," + peli.getViews() + ","
				+ peli.getRating() + ")";

		try {
			con.updateQuery(query);
			Escritor.write("Pelicula añadida correctamente");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.addMovie(pelicula) Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.addMovie(pelicula) Problema con query");
			e.printStackTrace();
		}

		logger.debug("DAOmovies.addMovie(pelicula) Pelicula añadida: ISBN " + peli.getIsbn());
	}

	
	/**
	 * Devuelve un Resulset con las peliculas que tienes el isbn imputado.
	 * 
	 * @param isbn: id unico de la pelicula
	 * @return ResultSet de isbn
	 * @see Conexion.queryConsult(query)
	 */
	
	public ResultSet isbnList(int isbn) {
		ResultSet rs = null;
	
			String query=("SELECT Isbn FROM movies WHERE Isbn=" + isbn);
			rs=isbnList(query);

		return rs;
	}
	
	public ResultSet isbnList() {
		ResultSet rs = null;
	
			String query=("SELECT Isbn FROM movies");
			rs=isbnList(query);

		return rs;
	}
	
	public ResultSet isbnList(String query) {
		ResultSet rs = null;
		try {
			rs = con.queryConsult(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.isbnList(isbn) Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.isbnList(isbn) Problema con query");
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * Devuelve un Resulset con todas las peliculas.
	 * 
	 * @return ResultSet de todas las peliculas.
	 * @see Conexion.queryConsult(query)
	 */

	public ResultSet listMovies() {
		ResultSet rs = null;
		try {
			rs = con.queryConsult("SELECT * FROM movies");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.listMovies() Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.listMovies() Problema con query");
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * Elimina la pelicula que tenga el mismo isbn que le objeto que se le imputa.
	 * 
	 * @param pelicula: objeto pelicula.
	 * @see Conexion.updateQuery(query)
	 */
	
	@Override
	public void deleteMovie(Pelicula pelicula) throws Exception {
		// TODO Auto-generated method stub
		String query = "DELETE FROM movies WHERE Isbn=" + pelicula.getIsbn();

		try {
			con.updateQuery(query);
			Escritor.write("Pelicula eliminada correctamente");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.deleteMovie(pelicula) Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.deleteMovie(pelicula) Problema con query");
			e.printStackTrace();
		}
		logger.debug("DAOmovies.deleteMovie(pelicula) Pelicula eliminada: ISBN " + pelicula.getIsbn());
	}

	/**
	 * Realiza un flitro de las peliculas que en el nombre contengan la palabra introducida y devuelve un ResultSet
	 * 
	 * @param name: palabra de flitrado
	 * @return ResultSet
	 * @see Conexion.queryConsult(query)
	 */
	
	public ResultSet filterMovies(String name) {
		ResultSet rs = null;
		String query = "SELECT * FROM `movies` WHERE LOCATE('" + name + "',`Nombre`) > 0";
		try {
			rs = con.queryConsult(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.filterMovie(string) Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.filterMovie(string) Problema con query");
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * Realiza un flitro de las peliculas que esten entre los años introducidos y devuelve un ResultSet
	 * 
	 * @param year1: año desde el que se realiza la consulta
	 * @param year2: año hasta el que se realiza la consulta
	 * @return ResultSet
	 * @see Conexion.queryConsult(query)
	 */
	
	public ResultSet filterMovies(int year1, int year2) {
		ResultSet rs = null;
		String query = "SELECT * FROM `movies` WHERE Year>=" + year1 + " AND Year<=" + year2;
		try {
			rs = con.queryConsult(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.filterMovie(year,year) Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.filterMovie(year,year) Problema con query");
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * Realiza un flitro de las primeras peliculas de la columna que se le imputa y devuelve un ResultSet
	 * 
	 * @param name: Columna de la que se quiere ontener resultados
	 * @param top: numero de resultados
	 * @return ResultSet
	 * @see Conexion.queryConsult(query)
	 */
	
	
	public ResultSet filterMovies(String name, int top) {
		ResultSet rs = null;
		String query = "SELECT * FROM `movies`  WHERE " + name + ">0 ORDER BY " + name + " DESC LIMIT " + top;
		try {
			rs = con.queryConsult(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.filterMovie(palabraclave,top) Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.filterMovie(palabraclave,top) Problema con query");
			e.printStackTrace();
		}
		return rs;
	}

	

}
