package utilidades;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import modelo.Pelicula;


public class LecturaFicheroPeliculas {

	//Asigno (V1) e imprimo el toString de cada peli 
	List<Pelicula> pelis = new ArrayList<Pelicula>();
	try {
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Peliculas.txt"))); 
	    String line = null;
	    while ((line = br.readLine()) != null) {
	        String[] values = line.split(","); // Split 
	        pelis.add(new Pelicula(values[0], Integer.parseInt(values[1]),values[2], Integer.parseInt(values[3]), Double.parseDouble(values[4])));
	    }
	} catch (IOException ioe) {
	    // Excepción
	}
		System.out.println(pelis);
		
		
	//Asigno (V2) e imprimo sólo película y año

		BufferedReader br =null;
		try {
			br = new BufferedReader(new FileReader("Peliculas.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   List<Pelicula> list=new ArrayList<>();
	   String line;
		   try {
			while ((line = br.readLine()) != null){
				   //String[] splitedString = line.split(",");
			       Pelicula peli=new Pelicula();
			       peli.setName(line.split(",")[0]);
			       peli.setYear(Integer.parseInt(line.split(",")[1]));
			       peli.setGenre(line.split(",")[2]);
			       peli.setViews(Integer.parseInt(line.split(",")[3]));
			       peli.setRating(Double.parseDouble(line.split(",")[4]));
			       list.add(peli);
			   }
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		   for (Pelicula peli : list)
	        {
	            System.out.println(peli.getName()+" "+peli.getYear());
	        }
		
		
		
		
		
}	
}		
	
	
	
	
	
	

