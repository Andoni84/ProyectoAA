package Datos;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import modelo.Pelicula;
import utilidades.Conexion;

public class DAOMovies implements IDAOMovies {
	
	/**
	 * ClaseDAOMovies
	 * 
	 * Gestiona la BBDD de peliculas, añade, elimina, lista.
	 * 
	 * 15/11/2018
	 *
	 * @author Andoni
	 * @version v1
	 */

	
	public void addMovie(Pelicula peli){
	
		String query= "INSERT INTO `movies`(Nombre,Year,Categoria_id,Isbn,Vistas,Rating)"
+ " VALUE ('"+peli.getName()+"',"+peli.getYear()+","+peli.getGenre()+","+peli.getIsbn()+","+peli.getViews()+","+peli.getRating()+")";
		
		
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
	
	public ResultSet isbnList(int isbn){
		ResultSet rs=null;
		try {
			rs= Conexion.queryConsult("SELECT Isbn FROM movies WHERE Isbn="+isbn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet listMovies(){
		ResultSet rs=null;
		try {
			rs= Conexion.queryConsult("SELECT * FROM movies");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	

	@Override
	public void deleteMovie(Pelicula pelicula) {
		// TODO Auto-generated method stub
		String query="DELETE FROM movies WHERE Isbn="+pelicula.getIsbn() ;
						
						
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
	public void updateQuery(String query) throws SQLException, ClassNotFoundException{
			Connection con=Conexion.connect();

				Statement st = (Statement) con.createStatement();

				st.executeUpdate(query);
		
		
	}
	
	
}
