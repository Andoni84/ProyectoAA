package servicios;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import modelo.Usuario;
import utilidades.Conexion;
import utilidades.Escritor;
import utilidades.Factoria;
import utilidades.Lector;

public class ServiciosUsuarios implements IServiciosUsuarios {
	
	/**
	 * Clase ServiciosUsuarios
	 * 
	 * Métodos para solicitar datos y generar nuevos usuarios.
	 * 
	 * 15/11/2018
	 *
	 * @author Miriam
	 * @version v1.2
	 */

	
	private static Logger logger;


	@Override
	public void addUser() {
		try {
			
			do{
				Usuario user;
				Escritor.write("------NUEVO USUARIO------");
				String name = Lector.readString("Nombre: ");
				Escritor.write("Fecha de nacimiento");
				String year = Lector.readString("\tAño: ");
				String month = Lector.readString("\tMes: ");
				String day = Lector.readString("\tDía: ");
				Date birth = Date.valueOf(year+"-"+month+"-"+day);
				String city = Lector.readString("Localidad: ");
				int plan = Lector.readInt("Seleccionar Abono: \n\t1-BASICO \n\t2-EXTRA \n\t3-PREMIUM ");
				user = Factoria.factoriaUser(name, birth, city, plan);
				rset = DAOUser.CheckUser1(Usuario user);
				if (CheckRepeat(rset)==true){
					Escritor.write("Ya existe un usuario con los mismos datos la base de datos.\nPor favor, vuelva a introducir el usuario.");
				}
				}while (CheckRepeat(rset)==true);
			
		} catch (IllegalArgumentException e) {
			try {
				Escritor.write("ERROR: Vuelva a introducir los datos.");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logger.trace("Tipo introducido");			
		} catch (Exception e) {
			e.printStackTrace();
			
	}
	}



	////// DAOUser: MÉTODO QUE DEVUELVE EL RESULTADO DE LA BÚSQUEDA DE UN USUARIO POR ID	
		public ResultSet CheckUser(Usuario user)
		{
			ResultSet rs = null;
			try {
				Conexion.connect();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
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
		
	
	////// MÉTODO QUE DICE SI 		
		public boolean CheckRepeat(ResultSet rs) throws SQLException{
			boolean repetido=false;
			if (rs != null){
				 repetido = true;}
			return repetido;
		}
		
		
	
		
		

		
		
		
		
		

	@Override
	public void addUser(Usuario user) {
		
	}
	
	

}
