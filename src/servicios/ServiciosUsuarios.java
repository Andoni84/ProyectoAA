package servicios;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import org.apache.logging.log4j.Logger;

import datos.DAOUser;
import modelo.Usuario;
import utilidades.Escritor;
import utilidades.Factoria;
import utilidades.Lector;

/**
 * Clase ServiciosUsuarios
 * 
 * M�todos para gestionar la informaci�n relativa a los usuarios.
 * 
 * 15/11/2018
 *
 * @author Miriam
 * @version v1.0
 */

public class ServiciosUsuarios implements IServiciosUsuarios {
	
	private static Logger logger;
	
	// --------------------
	// M�TODOS PARA A�ADIR UN NUEVO USUARIO
	// --------------------
	
	/**
	 * Solicita por pantalla los datos necesarios para crear un nuevo usuario.
	 */
	public void addUser() throws InputMismatchException {
		boolean error = true;
		do{
			try {
				 Usuario user;
				 Escritor.write("------NUEVO USUARIO------");
				 String name = Lector.readString("Nombre: ");
				 Escritor.write("Fecha de nacimiento");
				 String year = Lector.readString("\tA�o: ");
				 String month = Lector.readString("\tMes: ");
				 String day = Lector.readString("\tD�a: ");
				 Date birth = Date.valueOf(year+"-"+month+"-"+day);
				 String city = Lector.readString("Localidad: ");
				 int plan = Lector.readInt("Seleccionar Abono: \n\t1-BASICO \n\t2-EXTRA \n\t3-PREMIUM ");
				 
				 user = Factoria.factoriaUser(name, birth, city, plan);
				 addUser(user);
				 error = false;
				 
			} catch (IllegalArgumentException e) {
				e.getMessage();
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}while (error == true);	
	}
	
	
	/**
	 * Comprueba si existe un usuario con los mismos datos en la base de datos y, en
	 * caso negativo, a�ade el nuevo usuario.
	 * 
	 * @param user
	 */
	@Override
	public void addUser(Usuario user) {
		ResultSet rset = DAOUser.CheckUser(user);
		try {
			if (CheckRepeat(rset)==true){
				logger.info("Ya existe un usuario con los mismos datos.");
				addUser(); //Volver a entrar en el m�todo principal y pedir datos.
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DAOUser.addUser(user);
	}
		
	
	// --------------------
	// M�TODOS PARA ELIMINAR UN USUARIO
	// --------------------
	
	/**
	 * Solicita por pantalla los datos del usuario que se desea eliminar.
	 * 
	 *  @throws InputMismatchException
	 */

	public void deleteUser() throws InputMismatchException {
		boolean error = true;
		do{
			try {
				 Usuario user;
				 Escritor.write("------ELIMINAR USUARIO------");
				 String name = Lector.readString("Nombre: ");
				 Escritor.write("Fecha de nacimiento");
				 String year = Lector.readString("\tA�o: ");
				 String month = Lector.readString("\tMes: ");
				 String day = Lector.readString("\tD�a: ");
				 Date birth = Date.valueOf(year+"-"+month+"-"+day);
				 
				 user = Factoria.factoriaUser(name, birth);
				 deleteUser(user); //Entra en el m�todo que comprueba si se encuentra en la base de datos. En caso positivo lo borra.
				 error = false;
				 
			} catch (IllegalArgumentException e) {
				e.getMessage();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}while (error == true);	
		
	}
	
	/**
	 * Comprueba si el usuario se encuentra en la base de datos y lo elimina.
	 * 
	 * @param user
	 */
	@Override
	public void deleteUser(Usuario user) {
		ResultSet rset = DAOUser.CheckUser(user);
		try {
			if (CheckRepeat(rset)==false){
				logger.info("El usuario no est� registrado en la base de datos.");
				deleteUser();//Vuelve a entrar en el m�todo principal para introducir los datos
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DAOUser.deleteUser(user);
	}


	// --------------------
	// M�TODO DE COMPROBACI�N 
	// --------------------

	/**
	 * Comprueba si un usuario aparece en la base de datos.
	 * @param rs
	 * @return repetido
	 *         <ul>
	 *         <li>true: el usuario ya existe en la base de datos.</li>
	 *         <li>false: el usuario no se encuentra registrado en la base de datos.</li>
	 *         </ul>
	 * @throws SQLException
	 */
	/*public boolean CheckRepeat(ResultSet rs) throws SQLException{
		boolean repetido=false;
		if (rs != null){
			 repetido = true;}
		return repetido;
	}*/

	public boolean CheckRepeat(ResultSet rs) throws SQLException{
		boolean repetido=false;
		if (rs.next()){
			 repetido = true;}
		return repetido;
	}
	
	
	// --------------------
	// M�TODOS PARA VISUALIZAR LA OFERTA DE PEL�CULAS DE UN USUARIO
	// --------------------	
	
	/**
	 * Solicita los datos del usuario del que se quiere consultar la informaci�n.
	 */	
	@Override
	public void availableMovies() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Devuelve la lista de pel�culas a las que puede acceder el usuario.
	 * 
	 * @param user
	 */	
	@Override
	public void availableMovies(Usuario user) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Devuelve la lista de pel�culas que el usuario no ha visto de las disponibles en su/s cat�logos/s.
	 */	
	@Override
	public void listNotViewed() {
		// TODO Auto-generated method stub
		
	}
	
	
	// --------------------
	// M�TODO PARA VISUALIZAR LA LISTA DE USUARIOS
	// --------------------	

	/**
	 * Devuelve la lista completa de usuarios registrados en la base de datos.
	 */		
	@Override
	public void listUsers() {
		String[] listaUsers = DAOUser.muestraUser();
		for (String usuario: listaUsers){
			try {
				Escritor.write(usuario);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
	
	
	
		
	