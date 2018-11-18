package datos;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	 * Gestiona la BBDD de peliculas, a�ade, elimina, lista.
	 * 
	 * 15/11/2018
	 *
	 * @author Andoni
	 * @version v1
	 */

	private static Logger logger;

	static {
		try {
			logger = LogManager.getLogger(DAOMovies.class);
		} catch (Throwable e) {
			System.out.println("Logger Don't work");
		}
	}

	public void addMovie(Pelicula peli) throws Exception {

		String query = "INSERT INTO `movies`(Nombre,Year,Categoria,Isbn,Vistas,Rating)" + " VALUE ('" + peli.getName()
				+ "'," + peli.getYear() + ",'" + peli.getGenre() + "'," + peli.getIsbn() + "," + peli.getViews() + ","
				+ peli.getRating() + ")";

		try {
			updateQuery(query);
			Escritor.write("Pelicula a�adida correctamente");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.addMovie(pelicula) Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.addMovie(pelicula) Problema con query");
			e.printStackTrace();
		}

		logger.debug("DAOmovies.addMovie(pelicula) Pelicula a�adida: ISBN " + peli.getIsbn());
	}

	public ResultSet isbnList(int isbn) {
		ResultSet rs = null;
		try {
			rs = Conexion.queryConsult("SELECT Isbn FROM movies WHERE Isbn=" + isbn);
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

	public ResultSet listMovies() {
		ResultSet rs = null;
		try {
			rs = Conexion.queryConsult("SELECT * FROM movies");
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

	@Override
	public void deleteMovie(Pelicula pelicula) throws Exception {
		// TODO Auto-generated method stub
		String query = "DELETE FROM movies WHERE Isbn=" + pelicula.getIsbn();

		try {
			updateQuery(query);
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

	public ResultSet filterMovies(String name) {
		ResultSet rs = null;
		String query = "SELECT * FROM `movies` WHERE LOCATE('" + name + "',`Nombre`) > 0";
		try {
			rs = Conexion.queryConsult(query);
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

	public ResultSet filterMovies(int year1, int year2) {
		ResultSet rs = null;
		String query = "SELECT * FROM `movies` WHERE Year>=" + year1 + " AND Year<=" + year2;
		try {
			rs = Conexion.queryConsult(query);
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

	public ResultSet filterMovies(String name, int top) {
		ResultSet rs = null;
		String query = "SELECT * FROM `movies`  WHERE " + name + ">0 ORDER BY " + name + " DESC LIMIT " + top;
		try {
			rs = Conexion.queryConsult(query);
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

	public void updateQuery(String query) {

		try {
			Connection con = Conexion.connect();

			Statement st = (Statement) con.createStatement();

			st.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.updateQuery(query) Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("DAOmovies.updateQuery(query) Problema con query");
			e.printStackTrace();
		}

	}

}
