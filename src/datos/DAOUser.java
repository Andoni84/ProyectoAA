package datos;

import modelo.Pelicula;

/**
 * Clase DAOUser
 *
 * Gestiona las operaciones para la clase Usuario con la Base de Datos.
 * 
 * 16/11/2018
 *
 * @author Grupo1
 * @version 1.0
 */

import modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilidades.Conexion;

public class DAOUser implements IDAOUser {

	Conexion con = new Conexion();
	private static Logger logger;

	static {
		try {
			logger = LogManager.getLogger(DAOMovies.class);
		} catch (Throwable e) {
			System.out.println("Logger Don't work");
		}
	}

	/*
	 * ------------------------------------ METODO PARA AÑADIR USUARIOS
	 * ------------------------------------
	 */
	/**
	 * Gestiona la base de datos para añadir un usuario
	 * 
	 * @param user
	 */
	public void addUser(Usuario user) {

		/*
		 * Datos de la conexion a la BBDD
		 */
		try {
			con.connect();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * Guardamos el statement, para usarlo luego
		 */
		String InsertTableSQL = "INSERT INTO user (Nombre,FechaNacimiento, CiudadResidencia, Abono_id, User_id) values (?,?,?,?,?);";

		/*
		 * Preparamos el statement con la string anterior
		 */
		con.createpreparedStatement(InsertTableSQL);
		try {
			con.pstmt.setString(1, user.getName());
			con.pstmt.setDate(2, user.getBirth());
			con.pstmt.setString(3, user.getCity());
			con.pstmt.setInt(4, user.getPlan());
			con.pstmt.setInt(5, user.getUser_id());

			/*
			 * Ejecutamos el statement
			 */
			con.pstmt.executeUpdate();

			/*
			 * Mensaje para saber que ha salido bien
			 */
			logger.info("El usuario se ha introducido con exito");
		} catch (SQLException e) {
			logger.error("Update no realizado");
			System.out.println(e.getMessage());
		}

	}

	/*
	 * ------------------------------------ METODO PARA BORRAR USUARIOS, es
	 * igual que el metodo addUser(), pero cambiando la sintaxis
	 * ------------------------------------
	 */
	/**
	 * Gestiona la base de datos para eliminar un usuario
	 * 
	 * @param user
	 */
	public void deleteUser(Usuario user) {
		try {
			con.connect();
		} catch (ClassNotFoundException e1) {
			logger.error("No se pudo establecer la conexion");
			e1.printStackTrace();
		}
		String InsertTableSQL = "DELETE FROM user WHERE User_id=?";
		con.createpreparedStatement(InsertTableSQL);
		try {
			con.pstmt.setInt(1, user.getUser_id());
			con.pstmt.executeUpdate();
			logger.info("El usuario se ha borrado con exito");
		} catch (SQLException e) {
			logger.error("No se pudo borrar usuario");
			System.out.println(e.getMessage());
		}
	}

	/*
	 * ------------------------------------ MÉTODO QUE DEVUELVE EL RESULTADO DE
	 * LA BÚSQUEDA DE UN USUARIO POR ID ------------------------------------
	 */
	/**
	 * Busca a un usuario en la base de datos, usando su id
	 * 
	 * @param user
	 * @return ResultSet
	 */
	public ResultSet CheckUser(Usuario user) {
		ResultSet rs = null;
		try {
			con.connect();
		} catch (ClassNotFoundException e1) {
			logger.error("No se pudo establecer la conexion");
			e1.printStackTrace();
		}
		String InsertTableSQL = "SELECT User_id FROM user WHERE User_id=?";
		con.createpreparedStatement(InsertTableSQL);
		try {
			con.pstmt.setInt(1, user.getUser_id());
			rs = con.pstmt.executeQuery();
		} catch (SQLException e) {
			logger.error("No se pudo ejecutar Query");
			System.out.println(e.getMessage());
		}
		return rs;
	}

	/*
	 * ------------------------------------ METODO PARA LISTAR TODOS LOS
	 * USUARIOS ------------------------------------
	 */
	/**
	 * Muestra todos los usuarios en la base de datos
	 * 
	 * @return String[]
	 */
	public String[] muestraUser() {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			con.connect();
		} catch (ClassNotFoundException e1) {
			logger.error("No se pudo establecer la conexion");
			e1.printStackTrace();
		}
		ResultSet rs = null;
		Statement st = null;
		try {
			st = (Statement) con.con.createStatement();
			rs = st.executeQuery("select nombre from movieflix.user;");
			while (rs.next()) {
				lista.add(rs.getString(1));

			}
			rs.first();
		} catch (SQLException e) {
			logger.error("No se pudo ejecutar Query");
			e.printStackTrace();
		}
		String[] lista_usuarios = new String[lista.size()];
		int i = 0;
		for (String s : lista) {
			lista_usuarios[i] = s;
			i++;
		}
		return lista_usuarios;
	}

	/*
	 * ------------------------------------ METODO PARA LISTAR LAS PELICULAS
	 * DISPONIBLES A UN USUARIO ------------------------------------
	 */
	/**
	 * Muestra las peliculas disponibles para un usuario
	 * 
	 * @param user
	 * @return ResultSet
	 */
	public ResultSet availableMovies(Usuario user) {


		try {
			con.connect();
		} catch (ClassNotFoundException e1) {
			logger.error("No se pudo establecer la conexion");
			e1.printStackTrace();
		}
		ResultSet rs = null;
		Statement st = null;
		try {
			st = (Statement) con.con.createStatement();
			rs = st.executeQuery("select distinct movies.Nombre" + " from movieflix.movies"
					+ " where movies.Categoria in (select distinct categoria.Nombre" + " from movieflix.categoria"
					+ " inner join movieflix.abono" + " on abono.Categoria_id=categoria.Categoria_id"
					+ " where categoria.Categoria_id in (select abono.Categoria_id" + " from movieflix.abono"
					+ " inner join movieflix.user" + " on user.Abono_id=abono.Abono_id" + " where user.User_id = '"
					+ user.getUser_id() + "' ));");
		} catch (SQLException e) {
			logger.error("No se pudo ejecutar Query");
			e.printStackTrace();
		}
		return rs;
	}
	
	/*
	 * METODO PARA MOSTRAR LAS PELICULAS NO VISTAS POR UN USUARIO
	 */
	/**
	 * devuelve un ResultSet con las peliculas no vistas por un usuario
	 * @param user
	 * @return ResultSet
	 */
	public ResultSet notviewedMovies(Usuario user){
		try {
			con.connect();
		} catch (ClassNotFoundException e1) {
			logger.error("No se pudo establecer la conexion");
			e1.printStackTrace();
		}

		ResultSet rs = null;
		Statement st = null;
		
		try{
			st = (Statement) con.con.createStatement();
			rs = st.executeQuery("select distinct movies.Nombre "
					+ "from movieflix.movies"
					+ "inner join movieflix.user_movie"
					+ "on movies.Isbn"
					+ "where movies.isbn not in ("
					+ "select user_movie.isbn"
					+ "from movieflix.user_movie"
					+ "where user_movie.User_id="+user.getUser_id()+");");
		}
		catch (SQLException e) {
			logger.error("No se pudo ejecutar Query");
			e.printStackTrace();
		}
		return rs;
		
		
	}
	
	public void addUserViewMovie(Usuario user, Pelicula pelicula){
		String query=  "INSERT INTO `user_movie`(Nombre_user,Isbn,User_id)" + " VALUE ('" + user.getName()
				+ "'," + pelicula.getIsbn() + "," + user.getUser_id()+")";
		con.updateQuery(query);
	}
	
	public void deleteUserViewMovie(Usuario user){
		String query=  "DELETE FROM user_movie WHERE User_id="+user.getUser_id();
		con.updateQuery(query);
	}
}
