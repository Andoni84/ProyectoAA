package servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import modelo.Usuario;

/**
 * Interfaz IServiciosUsuarios
 * 
 * Métodos para gestionar la información relativa a los usuarios.
 * 
 * 15/11/2018
 *
 * @author Miriam
 * @version v1.0
 */

public interface IServiciosUsuarios {

	public void addUser() throws  Exception; 
	public void addUser(Usuario user); 
	public void deleteUser() throws  Exception;
	public void deleteUser(Usuario user);
	public boolean CheckRepeat(ResultSet rs) throws SQLException;
	public void availableMovies() throws Exception;

	public void availableMovies(Usuario user) throws Exception;
	public void listUsers();
	public void listNotViewed(Usuario user);
	
	
	
	
	

	
	
}
