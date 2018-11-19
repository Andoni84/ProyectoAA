package utilidades;

/**
 * Clase Conexion
 *
 * Gestiona la conexion con la Base de Datos.
 * 
 * 15/11/2018
 *
 * @author Grupo1
 * @version 1.0
 */

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import datos.DAOMovies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {

	private static Logger logger;

	static {
		try {
			logger = LogManager.getLogger(Conexion.class);
		} catch (Throwable e) {
			System.out.println("Logger Don't work");
		}
	}

	public Connection con = null;
	public Statement st = null;
	public ResultSet rs = null;
	public PreparedStatement pstmt = null;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

	}

	// --------------------
	// Metodos publicos
	// --------------------

	/**
	 * Establece conexion con la Base de Datos
	 */

	public void connect() throws ClassNotFoundException {

		try {
			logger.info("Conectando...");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://10.90.36.11:3306/movieflix?useSSL=false",
					"grupoa", "aaaa");

		} catch (SQLException e) {
			logger.error("ERROR. No se pudo conectar");
			System.out.println("Excepcion SQL " + e.getMessage());
			System.out.println("Estado SQL " + e.getSQLState());
			System.out.println("Codigo del Error " + e.getErrorCode());
		}

	}

	/**
	 * Cierra la conexion con la Base de Datos
	 */

	public void close() throws SQLException {
		try {
			logger.error("Cerrando conexion...");
			con.close();

		} catch (SQLException e) {
			logger.error("No se pudo cerrar la conexion");
			System.out.println("Excepcion SQL " + e.getMessage());
			System.out.println("Estado SQL " + e.getSQLState());
			System.out.println("Codigo del Error " + e.getErrorCode());
		}
	}

	/**
	 * Recibe una consulta por parametro y devuelve su ResultSet.
	 */

	public ResultSet queryConsult(String consulta) throws ClassNotFoundException, SQLException {

		connect();
		String query = consulta;
		try {
			st = (Statement) con.createStatement();

			rs = st.executeQuery(query);

		} catch (SQLException e) {
			logger.error("No se pudo ejecutar Query");
			System.out.println("Excepcion SQL " + e.getMessage());
			System.out.println("Estado SQL " + e.getSQLState());
			System.out.println("Codigo del Error " + e.getErrorCode());
		}
		return rs;
	}

	/**
	 * Ejecuta Updates en la BBDD pasados por parametro.
	 */

	public void updateQuery(String query) {

		try {
			connect();

			Statement st = (Statement) con.createStatement();

			st.executeUpdate(query);
		} catch (ClassNotFoundException e) {

			logger.error(" Problema driver conexion");
			e.printStackTrace();
		} catch (SQLException e) {

			logger.error(" Problema con query");
			e.printStackTrace();
		}

	}

	/**
	 * Crea PreparedStatement dado un String por parametro.
	 */
	
	public void createpreparedStatement(String insert) {
		try {
			pstmt = (PreparedStatement) con.prepareStatement(insert);
		} catch (SQLException e) {
			logger.error(" No se pudo crear PreparedStatement");
			e.printStackTrace();
		}

	}

}