package utilidades;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	// Utilidade de lectura, leen una entrada dependiendo del tipo de variables
	// que se quiera obtener.
	// Algunas muestran por pantalla un mensaje antes de la recogida de datos.
	// Propaga las excepciones.

	private static Logger logger;

	static {
		try {
			logger = LogManager.getLogger(Lector.class);
		} catch (Throwable e) {
			System.out.println("Logger Don't work");
		}
	}

	public static String readString() throws Exception {
		String dato = new Scanner(System.in).nextLine();
		logger.debug("Valor de lectura: " + dato);
		return dato;
	}

	public static int readInt() throws Exception {
		int dato = new Scanner(System.in).nextInt();
		logger.debug("Valor de lectura	: " + dato);
		return dato;
	}

	public static int readInt(String msg) throws Exception {
		Escritor.write(msg);
		return readInt();
	}

	public static double readDouble() throws Exception {
		double dato = new Scanner(System.in).nextDouble();
		return dato;
	}

	public static double readDouble(String msg) throws Exception {
		Escritor.write(msg);
		return readDouble();
	}

	public static String readString(String msg) throws Exception {
        Escritor.write(msg);
        return readString();
} 	
}
