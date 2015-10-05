package com.ipartek.formacion.skalada.log4j;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLog4j {

	private static final String PATH = "/log4j.properties";

	@BeforeClass()
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass()
	public static void tearDownAfterClass() throws Exception {
	}

	@Before()
	public void setUp() throws Exception {
	}

	@After()
	public void tearDown() throws Exception {
	}

	@Test()
	public void testLib() {

		BasicConfigurator.configure();
		Logger log = Logger.getLogger("Logger de Test");
		log.warn("un warning");
		log.error("un error");
		assertTrue("No funciona log4j BasicConfigurator", true);
	}

	@Test()
	public void testProperties() {

		try {
			// cargar properties
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
