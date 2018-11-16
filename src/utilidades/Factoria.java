package utilidades;

import java.sql.Date;

import modelo.Usuario;

public class Factoria {
	
	/**
	 * Clase Factoria
	 * 
	 * M�todos para crear usuarios a partir de los atributos recibidos.
	 * 
	 * 15/11/2018
	 *
	 * @author Miriam Ord��ez
	 * @version v1
	 */
	
	//M�todo que devuelve un usuario. Valores de entrada: datos personales.
	public static Usuario factoriaUser(String name, Date birth, String city, int plan){
		Usuario user = new Usuario(name, birth, city, plan);
		return user;
	}
	
	//M�todo que devuelve un usuario. Valores de entrada: datos personales e identificador de usuario.
	public static Usuario factoriaUser(String name, Date birth, String city, int plan, int user_id){
		Usuario user = new Usuario(name, birth, city, plan, user_id); 
		return user;
	}
	
	
}
