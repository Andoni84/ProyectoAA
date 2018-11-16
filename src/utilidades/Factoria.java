package utilidades;

import java.sql.Date;

import modelo.Pelicula;
import modelo.Usuario;

public class Factoria {
	
	/**
	 * Clase Factoria
	 * 
	 * Métodos para crear usuarios y películas a partir de los datos recibidos.
	 * 
	 * 15/11/2018
	 *
	 * @author Miriam Ordóñez
	 * @version v1
	 */
	
	//Método que devuelve un usuario. Valores de entrada: datos personales.
	public static Usuario factoriaUser(String name, Date birth, String city, int plan){
		Usuario user = new Usuario(name, birth, city, plan);
		return user;
	}
	
	//Método que devuelve un usuario. Valores de entrada: datos personales e identificador de usuario.
	public static Usuario factoriaUser(String name, Date birth, String city, int plan, int user_id){
		Usuario user = new Usuario(name, birth, city, plan, user_id); 
		return user;
	}
	
	//Método que devulve una película. Valores de entrada: datos de la película.
	public static Pelicula factoriaPelicula(String name, int year, String genre, int views, double rating){
		Pelicula movie = new Pelicula(name, year, genre, views,  rating);		
		return movie;
	}
	
	//Método que devulve una película. Valores de entrada: datos de la película y el identificador.
	public static Pelicula factoriaPelicula(String name, int year, String genre, int views, double rating, int isbn){
		Pelicula movie = new Pelicula(name, year, genre, views,  rating, isbn);		
		return movie;
	}
}
