package com.ipartek.formacion.skalada.modelo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.skalada.bean.Grado;



public class TestModeloGrado {
	
	String nombreGrado1 = "Mock1";
	Grado grado1 = new Grado(nombreGrado1);
	ModeloGrado modeloGrado = new ModeloGrado();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		assertNotEquals("No insertado " + grado1.toString(), -1, modeloGrado.save(grado1));
	}

	@After
	public void tearDown() throws Exception {
		assertTrue("No insertado " + grado1.toString(), modeloGrado.delete(grado1.getId()));
	}

	@Test
	public void testCRUD() {
		int total = modeloGrado.getAll().size();
		
	}

}
