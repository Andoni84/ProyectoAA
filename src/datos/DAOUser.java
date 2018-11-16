package datos;

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
import java.util.List;

import org.apache.logging.log4j.Logger;

import utilidades.Conexion;

public class DAOUser implements IDAOUser {
	
	private static Logger logger;
	
	
	/*
	 * METODO PARA AÑADIR USUARIOS
	 */
	public static void addUser(Usuario user){
		
		/*
		 * Datos de la conexion a la BBDD
		 */
		try {
			Conexion.connect();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		/*
		 * Guardamos el statement, para usarlo luego
		 */
		String InsertTableSQL = "INSERT INTO user (Nombre,FechaNacimiento, CiudadResidencia, Abono_id, User_id) values (?,?,?,?,?);" ;
		
		/*
		 * Preparamos el statement con la string anterior
		 */
		Conexion.createpreparedStatement(InsertTableSQL);
		try{
			 Conexion.pstmt.setString(1, user.getName());
			 Conexion.pstmt.setDate(2,user.getBirth());
			 Conexion.pstmt.setString(3, user.getCity());
			 Conexion.pstmt.setInt(4, user.getPlan());
			 Conexion.pstmt.setInt(5, user.getUser_id());
			 
			/*
			 * Ejecutamos el statement 
			 */
			 Conexion.pstmt.executeUpdate();
			 
			 /*
			  * Mensaje para saber que ha salido bien
			  */
			 logger.info("El usuario se ha introducido con exito");
		}catch (SQLException e) {
			logger.error("ERROR");
            System.out.println(e.getMessage());
        } 
	
	}
	
	
	/*
	 * METODO PARA BORRAR USUARIOS, es igual que el metodo addUser(), pero cambiando la sintaxis
	 */
	public static void deleteUser(Usuario user){
		try {
			Conexion.connect();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String InsertTableSQL = "DELETE FROM user WHERE User_id=?" ;
		Conexion.createpreparedStatement(InsertTableSQL);
		try{
			 Conexion.pstmt.setInt(1, user.getUser_id()); 
			 Conexion.pstmt.executeUpdate();
			 logger.info("El usuario se ha borrado con exito");
		}catch (SQLException e) {
			logger.error("ERROR");
            System.out.println(e.getMessage());
        } 
	}
	
	/*
	 *MÉTODO QUE DEVUELVE EL RESULTADO DE LA BÚSQUEDA DE UN USUARIO POR ID	
	 */
	public ResultSet CheckUser(Usuario user){			
		ResultSet rs = null;
		try {
				Conexion.connect();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}			
			String InsertTableSQL = "SELECT FROM user WHERE User_id=?" ;
			Conexion.createpreparedStatement(InsertTableSQL);
			try{
				 Conexion.pstmt.setInt(1, user.getUser_id()); 
				 rs = Conexion.pstmt.executeQuery();
			}catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } 
			return rs;
		}
	
	
	
	
	/*
	 * METODO PARA LISTAR TODOS LOS USUARIOS
	 */
	
	public static String[] muestraUser(){
		ArrayList<String> lista = new ArrayList<String>();
		
		try {
			Conexion.connect();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		 ResultSet rs = null;
		 Statement st = null;
		 try{
				st = (Statement) Conexion.con.createStatement();
				rs = st.executeQuery("select nombre from movieflix.user;");
				while (rs.next()) {
				    lista.add(rs.getString(1));
				    
				}
				rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 String[] lista_usuarios = new String[lista.size()];
		 int i=0;
		for(String s:lista){
			lista_usuarios[i]=s;
			i++;
		}
		return lista_usuarios;
	}
		
	
	
	
	
	
	
}
