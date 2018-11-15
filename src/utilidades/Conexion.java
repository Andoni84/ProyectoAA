package utilidades;

/**
 * Clase Conexion
 *
 * Gestiona la conexion con la Base de Datos.
 * 
 * 15/11/2018
 *
 * @author Miguel
 * @version 1.0
 */

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class Conexion {
	static Connection con = null;
	static Statement st = null;
	static ResultSet rs = null;
	static PreparedStatement pstmt = null;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		connect();
		// queryConsult(
		// " select abono.nombre , categoria.nombre from abono left join
		// categoria ON abono.categoria_id = categoria.categoria_id WHERE
		// categoria.categoria_id = 1; ");
	}

	// --------------------
	// Metodos publicos
	// --------------------

	/**
	 * Establece conexion con la Base de Datos
	 */

	public static void connect() throws ClassNotFoundException {

		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://10.90.36.11:3306/movieflix?useSSL=false",
					"grupoa", "aaaa");

		} catch (SQLException e) {
			System.out.println("Excepcion SQL " + e.getMessage());
			System.out.println("Estado SQL " + e.getSQLState());
			System.out.println("Codigo del Error " + e.getErrorCode());
		}

	}

	/**
	 * Cierra la conexion con la Base de Datos
	 */

	public static void close() throws SQLException {
		try {
			con.close();

		} catch (SQLException e) {
			System.out.println("Excepcion SQL " + e.getMessage());
			System.out.println("Estado SQL " + e.getSQLState());
			System.out.println("Codigo del Error " + e.getErrorCode());
		}
	}

	/**
	 * Realiza consultas dado un String por parametro
	 */

	public static void queryConsult(String consulta) throws ClassNotFoundException, SQLException {

		connect();
		String query = consulta;
		try {
			st = (Statement) con.createStatement();

			rs = st.executeQuery(query);
			ResultSetMetaData rsm = (ResultSetMetaData) rs.getMetaData();
			int countCol = rsm.getColumnCount();

			System.out.println(" ");
			while (rs.next()) {

				for (int i = 1; i <= countCol; i++) {

					System.out.println(rsm.getColumnName(i) + ":");
					System.out.println(rs.getString(i));
					if (i < countCol) {
						System.out.println(" ");
					}
					if (i == countCol) {
						System.out.println("------------------------------------------------ ");
					}
				}
				System.out.println();

			}

		} catch (SQLException e) {
			System.out.println("Excepcion SQL " + e.getMessage());
			System.out.println("Estado SQL " + e.getSQLState());
			System.out.println("Codigo del Error " + e.getErrorCode());
		}

	}

	public static void createStatement(Connection con, String consulta) { 
	 String query = consulta; try { st =
	  (Statement) con.createStatement();
	  
	  } catch (SQLException e) { 
		  System.out.println("Excepcion SQL " +e.getMessage()); 
		  System.out.println("Estado SQL " + e.getSQLState());
	      System.out.println("Codigo del Error " + e.getErrorCode()); }
	  
	  }
	
	public static void createpreparedStatement(Connection con, String insert) { 
		try {
			pstmt = (PreparedStatement) con.prepareStatement(insert);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		  
	}

	public static void createResultSet(Statement state) {
		try {
			rs = state.executeQuery(st.toString());
			ResultSetMetaData rsm = (ResultSetMetaData) rs.getMetaData();
			int countCol = rsm.getColumnCount();

			System.out.println(" ");
			while (rs.next()) {

				for (int i = 1; i <= countCol; i++) {

					System.out.println(rsm.getColumnName(i) + ":");
					System.out.println(rs.getString(i));
					if (i < countCol) {
						System.out.println(" ");
					}
					if (i == countCol) {
						System.out.println("------------------------------------------------ ");
					}
				}
				System.out.println();

			}
		} catch (SQLException e) {
			System.out.println("Excepcion SQL " + e.getMessage());
			System.out.println("Estado SQL " + e.getSQLState());
			System.out.println("Codigo del Error " + e.getErrorCode());
		}
	}

}