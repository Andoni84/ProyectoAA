package utilidades;

import java.util.Scanner;

public class Lector {
	
	/**
	 * Clase Lector
	 * 
	 * Contiene metodos de lectura de diferentes tipos.
	 * 
	 * 15/11/2018
	 *
	 * @author Andoni
	 * @version v1
	 */
	
	
	
	
	
	
	//Utilidade de lectura, leen una entrada dependiendo del tipo de variables que se quiera obtener. 
	//Algunas muestran por pantalla un mensaje antes de la recogida de datos.
	//Propaga las excepciones.
	
	
	public static String leerString() throws Exception {
		String dato = new Scanner(System.in).nextLine();
		logger.debug("Valor de lectura: "+dato);
		return dato;
    }
	
	public static int leerInt() throws Exception {
		int dato = new Scanner(System.in).nextInt();
		logger.debug("Valor de lectura	: "+dato);
        return dato;
    }
	
	 public static int leerInt(String msg) throws Exception {
	        System.out.println(msg);
	        return leerInt();
	}      
	 
	 public static double leerDouble() throws Exception {
	        double dato = new Scanner(System.in).nextDouble();
	        return dato;
	    }
	 
	 public static double leerDouble(String msg) throws Exception {
	        System.out.println(msg);
	        return leerDouble();
	    }
	 
}
