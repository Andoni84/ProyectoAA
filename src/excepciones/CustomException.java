package excepciones;

/**
 * Clase CustomException
 *
 * Excepci�n personalizada para ser lanzada en el GUI en caso de ingresar un n�mero no v�lido.
 * 
 * 19/11/2018
 *
 * @author Grupo1
 * @version 1.0
 */

public class CustomException extends Exception {

	private int valor;

	public CustomException() {

	}

	/**
	 * Recibe el valor del n�mero introducido por teclado y lo almacena.
	 */
	public CustomException(int valor) {
		super();
		this.valor = valor;
	}

	/**
	 * Devuelve el mensaje de error
	 */
	public String toString() {

		return "Numero no valido : " + valor;

	}
}
