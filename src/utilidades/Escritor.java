package utilidades;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Escritor {

	/**
	 * Clase Escritor
	 * 
	 * Contiene un metodo escritura.
	 * 
	 * 15/11/2018
	 *
	 * @author Andoni
	 * @version v1
	 */

	// Utilidad escritura.Imprime en consola.

	private static Logger logger;

	static {
		try {
			logger = LogManager.getLogger(Lector.class);
		} catch (Throwable e) {
			System.out.println("Logger Don't work");
		}
	}

	public static void write(String msg) throws Exception {
		System.out.println(msg);
		logger.debug("Escritura correcta");

	}

}
