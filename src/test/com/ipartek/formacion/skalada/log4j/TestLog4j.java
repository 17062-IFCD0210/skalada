package com.ipartek.formacion.skalada.log4j;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * 
 * @author Curso
 *
 */
public class TestLog4j {
	/**
	 * 
	 */
	static final String PATH = "/log4j.properties";
/**
 * 
 * @throws Exception
 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
/**
 * 
 * @throws Exception
 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
/**
 * 
 * @throws Exception
 */
	@Before
	public void setUp() throws Exception {
	}
/**
 * 
 * @throws Exception
 */
	@After
	public void tearDown() throws Exception {
	}
/**
 * 
 */
	@Test
	public final void testLib() {
		
		BasicConfigurator.configure();
		Logger log = Logger.getLogger("Logger de Test");
		log.warning("un warning");
		assertTrue("No funciona log4j BasicConfigurator", true);
	}
	/**
	 * 
	 */
	@Test
	public final void testProperties() {
		try {
			
			// Cargar properties
			Properties props = new Properties();		
			props.load(this.getClass().getResourceAsStream(PATH));
			PropertyConfigurator.configure(props);
			
			// Escribir linea
			Logger log = Logger.getLogger("Logger de Test");
			log.info("Test");
			
			assertTrue("No encontrado log4j.properties", true);
					
		} catch (Exception e) {
			fail("No encontrado log4j.properties " + PATH);
		}
	}
}
