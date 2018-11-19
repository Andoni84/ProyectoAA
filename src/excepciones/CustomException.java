package excepciones;

public class CustomException extends Exception {

    public CustomException(String mensaje) {
        super(mensaje);
    }

    public CustomException(String mensaje, Throwable error) {
        super(mensaje, error);
    }

}
		
	
