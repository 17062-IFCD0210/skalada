package com.ipartek.formacion.skalada.log4j;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.Test;

public class TestLog4j {

	private static final String PATH="/log4j.properties";
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testLib() {
		BasicConfigurator.configure();
		Logger log = Logger.getLogger("Logger de TestLib");
		log.warn("un warning");
		log.error("un error");
		assertTrue("No funciona log4j BasicConfigurator",true);
	}

	@Test
	public void testProperties() {
		try {
			//cargar properties
			Properties props = new Properties();		
			props.load( this.getClass().getResourceAsStream(PATH));
			PropertyConfigurator.configure(props);
			
			//escribir linea
			Logger log = Logger.getLogger("Logger de TestProperties");
			log.info("Test");

			assertTrue("No enontrado log4j.properties",true);			
		} catch (IOException e) {			
			fail("No enontrado log4j.properties" + PATH);
		}
	}

}
