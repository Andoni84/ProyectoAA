package utilidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import modelo.Usuario;

public class FactoriaTest {
	
	private int user_id1;
	private int user_id2;
	private String name1;
	private String name2;
	private Date birth1;
	private Date birth2;


	private static Logger logger;

	// Inicializo
	static {
		try {
			logger = LogManager.getLogger(FactoriaTest.class);
		} catch (Throwable e) {
			System.out.println("Don't work");
		}
	}

	@BeforeClass
	public static void onceExecutedBeforeAll() {
		logger.info("@BeforeClass: Al inicio de las pruebas");
	}

	@Before
	public void executedBeforeEach() {
		// Sustituye al setUp
		logger.info("@Before: Antes de cada prueba");
		user_id1 = 3;
		user_id2 = 5;
		name1 = "Juan";
		name2 = "Pedro";
		birth1 = Date.valueOf("2000-02-25");
		birth2 = Date.valueOf("2001-05-29");

	}

	@AfterClass
	public static void onceExecutedAfterAll() {
		logger.info("@AfterClass: Al final de las pruebas");
	}

	@After
	public void executedAfterEach() {
		// Sustituye al tearDown
		logger.info("@After: Despues de cada prueba");
		user_id1 = 0;
		user_id2 = 0;
		name1 = "";
		name2 = "";
		birth1 = null;
		birth2 = null;
	}

	@Ignore
	//Puedes usarlo en vez de comentar el método para que no se ejecute
	//http://junit.sourceforge.net/javadoc/org/junit/Ignore.html
	public void executionIgnored() {
		logger.info("@Ignore: This execution is ignored");
	}

	
	

	@Test
	public void testNotEqual() {
		String city = "Madrid";
		int plan = 1;
		logger.info("Test::testNotEqual(): comprobar que no se generan dos user_id iguales");
		Usuario user1 = Factoria.factoriaUser(name1, birth1, city, plan, user_id1);
		Usuario user2 = Factoria.factoriaUser(name2, birth2, city, plan, user_id2);
		assertNotSame(user1.getUser_id(), user2.getUser_id());
	}
	

	@Test
	public void testNotEqual1() {
		String city = "Madrid";
		int plan = 1;
		logger.info("Test::testNotEqual(): comprobar que no se generan dos user_id iguales generando el id_user automáticamente");
		Usuario user1 = Factoria.factoriaUser(name1, birth1, city, plan);
		Usuario user2 = Factoria.factoriaUser(name2, birth2, city, plan);
		assertNotSame(user1.getUser_id(), user2.getUser_id());
	}
	

	@Test
	public void testNotEqual2() {
		String city = "Madrid";
		int plan = 1;
		logger.info("Test::testNotEqual(): comprobar que el user_id introducido en el método es el mismo que se genera automáticamente.");
		Usuario user1 = Factoria.factoriaUser(name1, birth1, city, plan, user_id1);
		assertEquals(user1.getUser_id(), user_id1);
	}
	

}
