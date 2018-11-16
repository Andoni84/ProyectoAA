package servicios;

import java.sql.Date;

import org.apache.logging.log4j.Logger;

import modelo.Usuario;
import utilidades.Escritor;
import utilidades.Factoria;
import utilidades.Lector;

public class ServiciosUsuarios implements IServiciosUsuarios {
	
	/**
	 * Clase ServiciosUsuarios
	 * 
	 * Métodos para solicitar datos y generar nuevos usuarios.
	 * 
	 * 15/11/2018
	 *
	 * @author Sergio
	 * @version v1.2
	 */

	
	private static Logger logger;


	@Override
	public void addUser() {
		try {
			Escritor.write("------NUEVO USUARIO------");
			String name = Lector.readString("Nombre: ");
			Escritor.write("Fecha de nacimiento");
			String year = Lector.readString("\tAño: ");
			String month = Lector.readString("\tMes: ");
			String day = Lector.readString("\tDía: ");
			Date birth = Date.valueOf(year+"-"+month+"-"+day);
			String city = Lector.readString("Localidad: ");
			int plan = Lector.readInt("Seleccionar Abono: \n\t1-BASICO \n\t2-EXTRA \n\t3-PREMIUM ");
			Factoria.factoriaUser(name, birth, city, plan);
			
			
			//Introducir código para identificar repetidos 
			
			
		} catch (IllegalArgumentException e) {
			Escritor.write("ERROR: Vuelva a introducir los datos.");
			logger.trace("Tipo introducido");			
		} catch (Exception e) {
			e.printStackTrace();
			
	}



	@Override
	public void addUser(Usuario user) {
		
	}
	
	

}
