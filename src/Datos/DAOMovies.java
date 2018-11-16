package Datos;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import modelo.Pelicula;
import utilidades.Conexion;

public class DAOMovies {
	
	public static void addMovie(Pelicula peli){
	
		String query= "INSERT INTO `movies`(Nombre,Year,Categoria_id,Isbn,Vistas,Rating)"
+ " VALUE ('"+peli.getName()+"',"+peli.getYear()+","+peli.getidGenre()+","+peli.getIsbn()+","+peli.getViews()+","+peli.getRating()+")";
		
		System.out.println(query);
		
		try {
			updateQuery(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ResultSet isbnList(){
		ResultSet rs=null;
		try {
			rs= Conexion.queryConsult("SELECT Isbn FROM movies");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	public static void updateQuery(String query) throws SQLException, ClassNotFoundException{
		Connection con=Conexion.connect();

			Statement st = (Statement) con.createStatement();

			st.executeUpdate(query);
	}

}
