package utilidades;

import java.sql.Date;

import modelo.Pelicula;
import modelo.Usuario;


/**
 * Clase Factoria
 * 
 * M�todos para crear usuarios y pel�culas a partir de los datos recibidos.
 * 
 * 15/11/2018
 *
 * @author Miriam Ord��ez
 * @version v1
 */

public class Factoria {
		
	// --------------------
	// M�TODOS PARA GENERAR OBJETOS USUARIO
	// --------------------
	
	/**
	 * Genera usuarios a partir de los siguientes par�metros:
	 * @param name
	 * @param birth
	 * @param city
	 * @param plan
	 * @return
	 */
	public static Usuario factoriaUser(String name, Date birth, String city, int plan){
		Usuario user = new Usuario(name, birth, city, plan);
		return user;
	}
	
	/**
	 * Genera usuarios a partir de los siguientes par�metros:
	 * @param name
	 * @param birth
	 * @param city
	 * @param plan
	 * @param user_id
	 * @return
	 */
	public static Usuario factoriaUser(String name, Date birth, String city, int plan, int user_id){
		Usuario user = new Usuario(name, birth, city, plan, user_id); 
		return user;
	}	
	
	/**
	 * Genera usuarios a partir de los siguientes par�metros:
	 * @param name
	 * @param birth
	 * @return
	 */
	public static Usuario factoriaUser(String name, Date birth){
		Usuario user = new Usuario(name, birth); 
		return user;
	}
	
	// --------------------
	// M�TODOS PARA GENERAR OBJETOS PEL�CULA
	// --------------------	
	
	/**
	 * Genera pel�culas a partir de los siguientes par�metros:
	 * @param name
	 * @param year
	 * @param genre
	 * @param views
	 * @param rating
	 * @return
	 */
	public static Pelicula factoriaPelicula(String name, int year, String genre, int views, double rating){
		Pelicula movie = new Pelicula(name, year, genre, views,  rating);		
		return movie;
	}
	
	/**
	 * Genera pel�culas a partir de los siguientes par�metros:
	 * @param name
	 * @param year
	 * @param genre
	 * @param views
	 * @param rating
	 * @param isbn
	 * @return
	 */
	public static Pelicula factoriaPelicula(String name, int year, String genre, int views, double rating, int isbn){
		Pelicula movie = new Pelicula(name, year, genre, views,  rating, isbn);		
		return movie;
	}
}
