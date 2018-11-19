package utilidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.DAOMovies;

public class GenViewedMov {
	
	public static ArrayList<Integer> genViewMov(){
		ResultSet rs=new DAOMovies().isbnList();
		ArrayList<Integer> miArray=new ArrayList<Integer>();
		int aleatorio=0;
		try {
			while(rs.next()){
			aleatorio= (int) (Math.random()*10000);
			if(aleatorio%3==0)  miArray.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return miArray;
	}

}
