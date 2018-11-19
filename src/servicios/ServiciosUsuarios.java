package servicios;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import datos.DAOUser;
import modelo.Usuario;
import modelo.Pelicula; //Es necesario para crear un objeto pelicula en el metodo addUser mediante la factoria.
import utilidades.Escritor;
import utilidades.Factoria;
import utilidades.GenViewedMov;
import utilidades.Lector;

/**
 * Clase ServiciosUsuarios
 * 
 * Métodos para gestionar la información relativa a los usuarios.
 * 
 * 15/11/2018
 *
 * @author Miriam
 * @version v1.0
 */

public class ServiciosUsuarios implements IServiciosUsuarios {

	private static Logger logger;

	static {
		try {
			logger = LogManager.getLogger(ServiciosUsuarios.class);
		} catch (Throwable e) {
			System.out.println("Logger Don't work");
		}
	}

	DAOUser daoUser = new DAOUser();
	// --------------------
	// MÉTODOS PARA AÑADIR UN NUEVO USUARIO
	// --------------------

	/**
	 * Solicita por pantalla los datos necesarios para crear un nuevo usuario.
	 * @throws Exception 
	 */
	public void addUser() throws Exception {
		boolean error = true;
		do {
			try {
				Usuario user;
				Escritor.write("------NUEVO USUARIO------");
				String name = Lector.readString("Nombre: ");
				Escritor.write("Fecha de nacimiento");
				int year = Lector.readInt("\tAño: ");
				if(1870>year || 2018<year ){
					throw new InputMismatchException();
				}
				int month = Lector.readInt("\tMes: ");
				if(1>month || 12<month){
					throw new InputMismatchException();
				}
				int day = Lector.readInt("\tDía: ");
				if(1>day || 31<day){
					throw new InputMismatchException();
				}
				Date birth = Date.valueOf(year + "-" + month + "-" + day);
				String city = Lector.readString("Localidad: ");
				int plan = Lector.readInt("Seleccionar Abono: \n\t1-BASICO \n\t2-EXTRA \n\t3-PREMIUM ");
				logger.info("Proceso de solicitud de datos de usuario completado.");
				if(plan>3  || plan<1){
					throw new IllegalArgumentException("El numero introducido no corresponde a ningun plan");
				}
				user = Factoria.factoriaUser(name, birth, city, plan);
				addUser(user);
				error = false;

			} catch (IllegalArgumentException e) {
				Escritor.write(e.getMessage());
				
			} catch (InputMismatchException e1) {
				Escritor.write("Introduzca el tipo de datos correcto");
			}
		} while (error == true);
	}

	/**
	 * Comprueba si existe un usuario con los mismos datos en la base de datos
	 * y, en caso negativo, añade el nuevo usuario.
	 * 
	 * @param user
	 */
	@Override
	public void addUser(Usuario user) {
		ArrayList<Integer> miArray=null;
		ResultSet rset = daoUser.CheckUser(user);
		try {
			if (CheckRepeat(rset) == true) {
				logger.info("Ya existe un usuario con los mismos datos.");
				addUser(); // Volver a entrar en el método principal y pedir
							// datos.
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		miArray=GenViewedMov.genViewMov();
		for(int val:miArray){
			daoUser.addUserViewMovie(user,Factoria.factoriaPelicula("", 0, "", 0,0, val));
		}
		daoUser.addUser(user);
	}

	// --------------------
	// MÉTODOS PARA ELIMINAR UN USUARIO
	// --------------------

	/**
	 * Solicita por pantalla los datos del usuario que se desea eliminar.
	 * @throws Exception 
	 */

	public void deleteUser() throws Exception {
		boolean error = true;
		do {
			try {
				Usuario user;
				Escritor.write("------ELIMINAR USUARIO------");
				String name = Lector.readString("Nombre: ");
				Escritor.write("Fecha de nacimiento");
				int year = Lector.readInt("\tAño: ");
				if(1870>year || 2018<year ){
					throw new InputMismatchException();
				}
				int month = Lector.readInt("\tMes: ");
				if(1>month || 12<month){
					throw new InputMismatchException();
				}
				int day = Lector.readInt("\tDía: ");
				if(1>day || 31<day){
					throw new InputMismatchException();
				}
				Date birth = Date.valueOf(year + "-" + month + "-" + day);
				logger.info("Proceso de solicitud de datos de usuario completado.");

				user = Factoria.factoriaUser(name, birth);
				deleteUser(user); // Comprueba si se encuentra en la base de
									// datos. En caso positivo lo borra.
				error = false;

			} catch (IllegalArgumentException e) {
				e.getMessage();
			} catch (InputMismatchException e1) {
				Escritor.write("Introduxca el tipo de datos correcto");
			}
		} while (error == true);

	}

	/**
	 * Comprueba si el usuario se encuentra en la base de datos y lo elimina.
	 * 
	 * @param user
	 */
	@Override
	public void deleteUser(Usuario user) {
		ResultSet rset = daoUser.CheckUser(user);
		try {
			if (CheckRepeat(rset) == false) {
				logger.info("El usuario no está registrado en la base de datos.");
				deleteUser();// Vuelve a entrar en el método principal para
								// introducir los datos
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		daoUser.deleteUserViewMovie(user);
		daoUser.deleteUser(user);
	}

	// --------------------
	// MÉTODO DE COMPROBACIÓN
	// --------------------

	/**
	 * Comprueba si un usuario aparece en la base de datos.
	 * 
	 * @param rs
	 * @return repetido
	 *         <ul>
	 *         <li>true: el usuario ya existe en la base de datos.</li>
	 *         <li>false: el usuario no se encuentra registrado en la base de
	 *         datos.</li>
	 *         </ul>
	 * @throws SQLException
	 */
	/*
	 * public boolean CheckRepeat(ResultSet rs) throws SQLException{ boolean
	 * repetido=false; if (rs != null){ repetido = true;
	 * logger.info("Los datos introducidos ya existen en la base de datos. "); }
	 * return repetido; }
	 */
	@Override
	public boolean CheckRepeat(ResultSet rs) throws SQLException {
		boolean repetido = false;
		try {
			if (rs.next()) {
				repetido = true;
				logger.info("Los datos introducidos ya existen en la base de datos. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repetido;
	}

	// --------------------
	// MÉTODOS PARA VISUALIZAR LA OFERTA DE PELÍCULAS DE UN USUARIO
	// --------------------

	/**
	 * Solicita los datos del usuario del que se quiere consultar la
	 * información.
	 * @throws Exception 
	 */
	@Override
	public void availableMovies() throws Exception {

		boolean error = true;
		do {
			try {
				Usuario user;
				Escritor.write("------CONSULTA DEL CATÁLOGO DE USUARIO------");
				String name = Lector.readString("Nombre: ");
				Escritor.write("Fecha de nacimiento");
				int year = Lector.readInt("\tAño: ");
				if(1870>year || 2018<year ){
					throw new InputMismatchException();
				}
				int month = Lector.readInt("\tMes: ");
				if(1>month || 12<month){
					throw new InputMismatchException();
				}
				int day = Lector.readInt("\tDía: ");
				if(1>day || 31<day){
					throw new InputMismatchException();
				}
				Date birth = Date.valueOf(year + "-" + month + "-" + day);
				logger.info("Proceso de solicitud de datos de usuario completado.");

				user = Factoria.factoriaUser(name, birth);
				availableMovies(user);
				error = false;

			} catch (IllegalArgumentException e) {
				e.getMessage();
			}catch (InputMismatchException e1) {
				Escritor.write("Introduxca el tipo de datos correcto");
			}
		} while (error == true);
	}

	/**
	 * Devuelve la lista de películas a las que puede acceder el usuario.
	 * 
	 * @param user
	 * @throws Exception 
	 */
	@Override
	public void availableMovies(Usuario user) throws Exception {
		ResultSet lista = daoUser.availableMovies(user);
		while (lista.next()){
			Escritor.write(lista.getString(1));
		}
		
	}

	/**
	 * Devuelve la lista de películas que el usuario no ha visto de las
	 * disponibles en su/s catálogos/s.
	 */
	
	public void listNotViewed(Usuario user) {
		ResultSet rs = daoUser.notviewedMovies(user);
		try{
			while (rs.next()) {
				logger.info(rs.getString(1));
			}
			rs.first();
		}catch (SQLException e) {
			logger.error("No se pudo ejecutar Query");
			e.printStackTrace();
		}
		
	}

	// --------------------
	// MÉTODO PARA VISUALIZAR LA LISTA DE USUARIOS
	// --------------------

	/**
	 * Devuelve la lista completa de usuarios registrados en la base de datos.
	 */
	@Override
	public void listUsers() {
		String[] listaUsers = daoUser.muestraUser();
		for (String usuario : listaUsers) {
			try {
				Escritor.write(usuario);
				logger.info("Nombre de usuario:" + usuario);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
