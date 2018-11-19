package funcion;

/**
 * Clase Test DAOUserTest
 *
 *Comprueba que el metodo deleteUser de la clase datos.DAOUser funciona correctamente.
 * 
 * 16/11/2018
 *
 * @author Grupo1
 * @version 1.0
 */

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.Statement;

import datos.DAOUser;
import modelo.Usuario;
import utilidades.Conexion;

public class DAOUserTest {

	private static Usuario dummy;
	private int num_filas_ini;
	private int num_filas_fin;

	private static Logger logger;

	@Test
	public void CheckDeletetest() {
		logger.info("@Test: Esmpieza el test");
		ResultSet rs = null;
		Statement st = null;
		/*
		 * num filas inicial
		 */
		try {
			st = (Statement) Conexion.con.createStatement();
			rs = st.executeQuery("select * from movieflix.user;");
			num_filas_ini = 0;
			while (rs.next()) {
				num_filas_ini++;
			}
			rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * borramos user
		 */
		DAOUser.deleteUser(dummy);
		/*
		 * numero filas fin
		 */
		try {
			num_filas_fin = 0;
			while (rs.next()) {
				num_filas_fin++;
			}
			rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * aseguramos que tiene tienen que ser dif
		 */
		assertFalse(num_filas_ini == num_filas_fin);
	}

	@BeforeClass
	public static void onceExecutedBeforeAll() {
		logger.info("@Before: Al inicio de las pruebas");
		dummy = new Usuario();
		dummy.setBirth(java.sql.Date.valueOf("2019-11-15"));
		dummy.setCity("tontolandia");
		dummy.setName("tontonman");
		dummy.setPlan(1);
		dummy.setUser_id(1);
		DAOUser.addUser(dummy);
	}
}
