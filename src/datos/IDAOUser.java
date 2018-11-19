package datos;

import java.sql.ResultSet;

/**
 * Interfaz IDAOUser
 *
 * Interfaz para la clase DAOUser
 * 
 * 15/11/2018
 *
 * @author Grupo1
 * @version 1.0
 */

import modelo.Usuario;

public interface IDAOUser {

	public void addUser(Usuario user);

	public void deleteUser(Usuario user);

	public ResultSet CheckUser(Usuario user);

	public String[] muestraUser();

	public String[] availableMovies(Usuario user);

}
