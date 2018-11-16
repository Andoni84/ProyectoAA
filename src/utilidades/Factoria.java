package utilidades;

import java.sql.Date;

import modelo.Usuario;

public class Factoria {
	
	/**
	 * Clase Factoria
	 * 
	 * Métodos para crear usuarios a partir de los atributos recibidos.
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
	
	
}
