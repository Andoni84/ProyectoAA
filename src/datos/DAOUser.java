package datos;
import modelo.Usuario;
import java.sql.SQLException;
import utilidades.Conexion;

public class DAOUser implements IDAOUser {
	public void addUser(){
	}
	public void deleteUser(){
		
	}
		
	
	public static void addUser(Usuario user){
		
		// Datos de la conexion a la BBDD
		try {
			Conexion.connect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Guardamos el statement, para usarlo luego
		String InsertTableSQL = "INSERT INTO user (Nombre,FechaNacimiento, CiudadResidencia, Abono_id, User_id) values (?,?,?,?,?);" ;
		// Preparamos el statement con la string anterior
		Conexion.createpreparedStatement(InsertTableSQL);
		try{
			
			 
			 Conexion.pstmt.setString(1, user.getName());
			 Conexion.pstmt.setDate(2,user.getBirth());
			 Conexion.pstmt.setString(3, user.getCity());
			 Conexion.pstmt.setInt(4, user.getPlan());
			 Conexion.pstmt.setInt(5, user.getUser_id());
			 
			 Conexion.pstmt.executeUpdate();
			 System.out.println("El usuario se ha introducido con exito");
		}catch (SQLException e) {

            System.out.println(e.getMessage());

        } 
	
		
	}
	
	public static void deleteUser(Usuario user){
		try {
			Conexion.connect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String InsertTableSQL = "DELETE FROM user WHERE User_id=?" ;
		Conexion.createpreparedStatement(InsertTableSQL);
		try{
			
			 Conexion.pstmt.setInt(1, user.getUser_id());
			 
			 Conexion.pstmt.executeUpdate();
			 System.out.println("El usuario se ha borrado con exito");
		}catch (SQLException e) {

            System.out.println(e.getMessage());

        } 
		
		
		
		
	}
}
